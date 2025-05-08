package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.spacialmodelcollections.PositionalSpacialModelCollection;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.geom.Point2D;

public class MoveRoomElementCommand extends RoomElementCommand {
    final double xDrag;
    final double yDrag;
    final Point2D.Double oldPosition;

    public MoveRoomElementCommand(RoomElement<?> roomElement, double xDrag, double yDrag) {
        super(roomElement);
        this.xDrag = xDrag;
        this.yDrag = yDrag;

        oldPosition = GraphicsUtils.makePoint2DCopy(
                roomElement.getTopLeftCorner());
    }

    @Override
    public void redo() throws CommandExecuteException {
        doMove(roomElement, (Room) roomElement.getParent());
    }

    private void doMove(RoomElement<?> element, Room containingRoom) {
        int roomWidth = containingRoom.getWidthCm();
        int roomHeight = containingRoom.getHeightCm();

        var adjustedPosition = GraphicsUtils.makePoint2DCopy(element.getTopLeftCorner());

        adjustedPosition.x += xDrag;
        adjustedPosition.y += yDrag;

        // Enforce boundary
        adjustedPosition.x = Math.max(10, Math.min(adjustedPosition.x, roomWidth - 10 - (int) element.getWidth()));
        adjustedPosition.y = Math.max(10, Math.min(adjustedPosition.y, roomHeight - 10 - (int) element.getHeight()));

        // Adjust back the position based on room element's rotation
        int rotationDegrees = (int) element.getRotation();
        if (rotationDegrees == 90 || rotationDegrees == 270) {
            adjustedPosition.x -= (int) (element.getWidth() / 2 - element.getHeight() / 2);
            adjustedPosition.y += (int) (element.getWidth() / 2 - element.getHeight() / 2);
        }

        // Update the room element's position
        element.setTopLeftCorner(GraphicsUtils.makePoint2DCopy(adjustedPosition));

        // Notify subscribers about the position update
        ApplicationFramework.getInstance()
                .getProjectManager()
                .notifyUpdateCrudSubscribers(element);
    }

    @Override
    public void undo() throws CommandExecuteException {
        roomElement.setTopLeftCorner(GraphicsUtils.makePoint2DCopy(oldPosition));
    }

    @Override
    public String getUndoDescription() {
        return String.format("Moving room element %s to it's original position (x: %f, y: %f)",
                roomElement.getName(),
                roomElement.getTopLeftCorner().x,
                roomElement.getTopLeftCorner().y);
    }

    @Override
    public String getRedoDescription() {
        return String.format("Moving room element %s by (x drag: %f, y drag: %f)",
                roomElement.getName(),
                xDrag,
                yDrag);
    }

    @Override
    public String getName() {
        return "";
    }
}
