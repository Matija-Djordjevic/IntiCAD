package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.commands.ICommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.awt.*;

public class ResizeState extends DraggingState {
    private RoomElement<?> selectedElement; // The element currently being resized
    private int initialWidth;               // Element's width before resizing
    private int initialHeight;              // Element's height before resizing

    @Override
    void handleClick(Point clickPoint) { }

    @Override
    void handleDrag(Point startPoint, Point currentPoint) {
        if (selectedElement == null) return;

        // Calculate the shift in mouse position
        int shiftX = currentPoint.x - startPoint.x;
        int shiftY = currentPoint.y - startPoint.y;

        // Determine the new dimensions, ensuring they don't fall below 1
        int newWidth = Math.max(1, initialWidth + shiftX);
        int newHeight = Math.max(1, initialHeight + shiftY);

        // Apply the new dimensions to the selected element
        selectedElement.setWidth(newWidth);
        selectedElement.setHeight(newHeight);

        // Refresh the room view to reflect changes
        // Assuming roomView is accessible; otherwise, pass it appropriately
        // roomView.getRoomPanel().repaint();
    }

    @Override
    void handleReleaseAfterDrag(Point startPoint, Point endPoint) {
        if (selectedElement == null) return;
        selectedElement.setWidth(initialWidth);
        selectedElement.setHeight(initialHeight);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();

        int shiftX = endPoint.x - startPoint.x;
        int shiftY = endPoint.y - startPoint.y;

        // Determine the new dimensions, ensuring they don't fall below 1

        int newWidth = Math.max(1, initialWidth + shiftX);
        int newHeight = Math.max(1, initialHeight + shiftY);

        //new ResizeElementCommand(list, newWidth, newHeight)

        var mg = ApplicationFramework.getInstance().messageGenerator;
        var resizeCommand = createCommand(newWidth, newHeight);
        try{
            rv.getCommandsManager().addCommand(resizeCommand);
        }catch(CommandExecuteException e){
            mg.notify(new LogInfo(LogLevel.INFO, "Can't resize element"));
            return;
        }

        // Notify subscribers about the update
        ApplicationFramework.getInstance()
                .getProjectManager()
                .notifyUpdateCrudSubscribers(selectedElement);
    }

    private ICommand createCommand(int newWidth, int newHeight) {
        var factory = MainFrame.getInstance().getRoomElementCommandsFactory();
        return factory.buildCommand(
                "resize",
                options ->
                        options
                                .setRoomElement(selectedElement)
                                .setNewWidthAndHeight(newWidth, newHeight));
    }

    @Override
    public void MousePressedSameSameButDifferentButSame(RoomView roomView, Point mousePressedPoint) {
        super.MousePressedSameSameButDifferentButSame(roomView, mousePressedPoint);

        // Retrieve the current project and room views
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        var currentRoomView = projectView.getProjectViewModel().getLastSelectedRoomView();

        RoomElement<?> element = (RoomElement<?>) currentRoomView.getSelected()
                .stream()
                .findFirst()
                .orElse(null);

        if (element == null) return;

        // Determine if the mouse press is within the resize handle area
        if (isWithinResizeHandle(element, mousePressedPoint)) {
            selectedElement = element;
            initialWidth = (int) selectedElement.getWidth();
            initialHeight = (int) selectedElement.getHeight();
            isDragging = true; // Set dragging flag from DraggingState
        } else {
            selectedElement = null; // Clicked outside the resize handle
        }
    }

    private boolean isWithinResizeHandle(RoomElement<?> element, Point mousePoint) {
        int handleSize = (int) (Math.min(element.getWidth(), element.getHeight()) * 0.2);

        Point pos = new Point((int) element.getTopLeftCorner().x, (int) element.getTopLeftCorner().y);

        // Calculate the center of the element
        double centerX = pos.x + element.getWidth() / 2.0;
        double centerY = pos.y + element.getHeight() / 2.0;

        // Convert mouse coordinates to the element's local (unrotated) space
        double angleRadians = Math.toRadians(element.getRotation());
        double dx = mousePoint.x - centerX;
        double dy = mousePoint.y - centerY;
        double localX = dx * Math.cos(-angleRadians) - dy * Math.sin(-angleRadians) + centerX;
        double localY = dx * Math.sin(-angleRadians) + dy * Math.cos(-angleRadians) + centerY;

        // Define the resize handle area in the unrotated space
        int handleXStart = pos.x + (int) element.getWidth() - handleSize;
        int handleYStart = pos.y + (int) element.getHeight() - handleSize;

        boolean withinHorizontal = localX >= handleXStart && localX <= pos.x + element.getWidth();
        boolean withinVertical = localY >= handleYStart && localY <= pos.y + element.getHeight();

        return withinHorizontal && withinVertical;
    }
}
