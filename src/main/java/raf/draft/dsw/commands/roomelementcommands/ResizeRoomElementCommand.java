package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.models.roomelements.RoomElement;

public class ResizeRoomElementCommand extends RoomElementCommand {
    final double newWidth;
    final double newHeight;

    final double oldWidth;
    final double oldHeight;

    public ResizeRoomElementCommand(RoomElement<?> roomElement, double newWidth, double newHeight) {
        super(roomElement);
        oldWidth = roomElement.getWidth();
        oldHeight = roomElement.getHeight();

        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    @Override
    public void redo() throws CommandExecuteException {
        roomElement.setWidth(newWidth);
        roomElement.setHeight(newHeight);
    }

    @Override
    public void undo() throws CommandExecuteException {
        roomElement.setWidth(oldWidth);
        roomElement.setHeight(oldHeight);
    }

    @Override
    public String getUndoDescription() {
        return String.format("Setting back the size of %s to w:%f h:%f",
                roomElement.getName(),
                oldWidth,
                oldHeight);
    }

    @Override
    public String getRedoDescription() {
        return String.format("Setting size of %s to w:%f h:%f",
                roomElement.getName(),
                newWidth,
                newHeight);
    }

    @Override
    public String getName() { return "Resize Room Element Command"; }
}
