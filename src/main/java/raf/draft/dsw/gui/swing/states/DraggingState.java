package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;

import java.awt.*;

public abstract class DraggingState implements IState{
    boolean isDragging = false; // Flag to track dragging
    Point startPoint;

    @Override
    public void MouseReleasedSameSameButDifferentButStillSame(RoomView roomView, Point e) {
        if (isDragging) handleReleaseAfterDrag(new Point(startPoint), new Point(e));
        else handleClick(new Point(e));

        isDragging = false;
        startPoint = null;
    }

    abstract void handleClick(Point clickPoint);

    abstract void handleReleaseAfterDrag(Point startPoint, Point endPoint);

    @Override
    public void MousePressedSameSameButDifferentButSame(RoomView roomView, Point e) {
        isDragging = false; // Reset dragging flag
        startPoint = e;
    }

    @Override
    public void MouseDraggedSameSameButDifferentButStillSame(RoomView roomView, Point e) {
        isDragging = true;
        handleDrag(
                new Point(startPoint),
                new Point(e)
        );
    }

    abstract void handleDrag(Point startPoint, Point endPoint);
}
