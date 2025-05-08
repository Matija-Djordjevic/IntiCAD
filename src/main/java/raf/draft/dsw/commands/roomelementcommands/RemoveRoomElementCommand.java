package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.models.roomelements.RoomElement;

public class RemoveRoomElementCommand extends RoomElementCommand {
    AddRoomElementCommand addCommand;

    public RemoveRoomElementCommand(RoomElement<?> roomElement) {
        super(roomElement);
        addCommand = new AddRoomElementCommand(roomElement);
    }

    @Override
    public void redo() throws CommandExecuteException{ addCommand.undo(); }

    @Override
    public void undo() throws CommandExecuteException{ addCommand.redo(); }

    @Override
    public String getUndoDescription() { return addCommand.getRedoDescription(); }

    @Override
    public String getRedoDescription() { return addCommand.getUndoDescription(); }

    @Override
    public String getName() { return "Remove Room Element Command"; }
}
