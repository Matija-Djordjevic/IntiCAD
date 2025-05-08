package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.models.roomelements.RoomElement;

public class RotateRoomElementCommand extends RoomElementCommand {
    final double rotateFor;

    public RotateRoomElementCommand(RoomElement<?> roomElement, double rotateFor) {
        super(roomElement);
        this.rotateFor = rotateFor;
    }

    @Override
    public void redo() throws CommandExecuteException { roomElement.setRotation(roomElement.getRotation() + rotateFor); }

    @Override
    public void undo() throws CommandExecuteException { roomElement.setRotation(roomElement.getRotation() - rotateFor); }

    @Override
    public String getUndoDescription() {
        return String.format("Rotate %s back to %.2f degrees",
                rotateFor,
                roomElement.getRotation() - rotateFor);
    }

    @Override
    public String getRedoDescription() { return String.format("Rotating %s for %.2f degrees", roomElement.getName(), rotateFor); }

    @Override
    public String getName() { return "Rotate Room Element Command"; }
}
