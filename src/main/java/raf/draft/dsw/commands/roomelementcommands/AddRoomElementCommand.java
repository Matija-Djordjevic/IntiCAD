package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.util.UUID;

public class AddRoomElementCommand extends RoomElementCommand{
    public AddRoomElementCommand(RoomElement<?> roomElement) { super(roomElement); }

    @Override
    public void redo() throws CommandExecuteException {
        var pm = ApplicationFramework.getInstance().getProjectManager();
        var success = pm.add(roomElement.getParent(), roomElement);
        if(!success) throw new CommandExecuteException("Couldn't remove room element");
    }

    @Override
    public void undo() throws CommandExecuteException {
        var pm = ApplicationFramework.getInstance().getProjectManager();
        var success = pm.remove(roomElement);
        if(!success) throw new CommandExecuteException("Couldn't remove room element");
    }

    @Override
    public String getUndoDescription() {
        return String.format("Removing room element '%s' from room '%s'",
                roomElement.getName(),
                roomElement.getParent().getName());
    }

    @Override
    public String getRedoDescription() {
        return String.format("Adding room element '%s' to room '%s'",
                roomElement.getName(),
                roomElement.getParent().getName());
    }

    @Override
    public UUID getId() { return super.getId(); }

    @Override
    public String getName() { return this.getClass().getSimpleName(); }
}
