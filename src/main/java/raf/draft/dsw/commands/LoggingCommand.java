package raf.draft.dsw.commands;

import raf.draft.dsw.logging.IMessageAnnouncer;
import raf.draft.dsw.logging.IMessageListener;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;

import java.util.UUID;

public class LoggingCommand implements ICommand {
    final ICommand command;
    final IMessageAnnouncer<LogInfo> logger;

    public LoggingCommand(ICommand command,
                          IMessageAnnouncer<LogInfo> logger) {
        if(command == null) throw new NullPointerException("command is null");
        if(command instanceof LoggingCommand) throw new IllegalArgumentException("Creating circular decorator");

        this.command = command;
        this.logger = logger;

        log(GetCommandDesc() + " was created");
    }

    private void log(String msg) { logger.notify(new LogInfo(LogLevel.INFO, msg)); }

    public ICommand unwrapCommand() { return command; };

    String GetCommandDesc() { return String.format("Command %s (id:%s) was created", command.getName(), command.getId().toString().substring(0, 8)); }

    @Override
    public void redo() throws CommandExecuteException {
       log("Doing: " + command.getRedoDescription());

        try {
            command.redo();
        } catch (CommandExecuteException e) {
            log("Failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void undo() throws CommandExecuteException {
        log("Undoing: " + command.getUndoDescription());
        command.undo();
    }

    @Override
    public String getUndoDescription() { return command.getUndoDescription(); }

    @Override
    public String getRedoDescription() { return command.getRedoDescription(); }

    @Override
    public UUID getId() { return command.getId(); }

    @Override
    public String getName() { return command.getName(); }
}
