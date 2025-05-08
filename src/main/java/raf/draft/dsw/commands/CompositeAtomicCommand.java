package raf.draft.dsw.commands;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CompositeAtomicCommand implements ICommand {
    @Getter
    protected final UUID Id = UUID.randomUUID();
    protected final List<ICommand> commands;

    public CompositeAtomicCommand(ArrayList<ICommand> commands) { this.commands = commands; }

    @Override
    public void redo() throws CommandExecuteException {
        for (int i = 0; i < commands.size(); i++) {
            var command = commands.get(i);
            try {
                command.redo();
            } catch (CommandExecuteException e){
                doRollback(i);
                throw e;
            }

            var success = doFinalCheck();
            if(!success) {
                doRollback(commands.size() - 1);
                throw new CommandExecuteException("Final check failed");
            }
        }
    }

    protected abstract boolean doFinalCheck();

    private void doRollback(int failedAt) { commands.subList(0, failedAt + 1).forEach(ICommand::undo); }

    @Override
    public void undo() throws CommandExecuteException { commands.forEach(ICommand::undo); }

    @Override
    public String getUndoDescription() { return "Running undo commands:\n" + getAllCommandsDescriptions(); }

    @Override
    public String getRedoDescription() { return "Running redo commands:\n" + getAllCommandsDescriptions(); }

    private StringBuilder getAllCommandsDescriptions() {
        var sb = new StringBuilder();
        commands.forEach(cmd -> sb.append(cmd.getRedoDescription()).append("\n"));
        return sb;
    }
}
