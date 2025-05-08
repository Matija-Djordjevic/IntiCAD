package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.spacialmodelcollections.PositionalSpacialModelCollection;

import java.awt.*;
import java.awt.desktop.AppForegroundListener;
import java.util.List;
import java.util.Locale;

public class SelectState extends DraggingState {

    @Override
    void handleClick(Point clickPoint) {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        var currentRoomView = projectView.getProjectViewModel().getLastSelectedRoomView();
        var collection = (PositionalSpacialModelCollection) currentRoomView.getRoom().getChildrenCollection();

        // Find the first element under the click point
        var selectedElement = collection.findOnPoint(clickPoint)
                .stream()
                .findFirst()
                .orElse(null);

        var selection = currentRoomView.getSelected();
        selection.clear();

        var mg = ApplicationFramework.getInstance().messageGenerator;
        if (selectedElement != null) {
            selection.add(selectedElement);
            mg.notify(new LogInfo(LogLevel.INFO, "Selected Element: " + selectedElement));
        } else {
            mg.notify(new LogInfo(LogLevel.INFO, "No element found at the clicked point."));
        }
    }

    @Override
    void handleDrag(Point startPoint, Point currentPoint) {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        var currentRoomView = projectView.getProjectViewModel().getLastSelectedRoomView();
        var collection = (PositionalSpacialModelCollection) currentRoomView.getRoom().getChildrenCollection();

        // Find all elements within the selection area
        var topLeft = new Point(Math.min(startPoint.x, currentPoint.x), Math.min(startPoint.y, currentPoint.y));
        var bottomRight = new Point(Math.max(startPoint.x, currentPoint.x), Math.max(startPoint.y, currentPoint.y));
        var selectedElements = collection.findInArea(topLeft, bottomRight);

        var selection = currentRoomView.getSelected();
        selection.clear();
        selection.addAll(selectedElements);

        System.out.println("Selected Elements within area: " + selectedElements);
    }

    @Override
    void handleReleaseAfterDrag(Point startPoint, Point endPoint) {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        var currentRoomView = projectView.getProjectViewModel().getLastSelectedRoomView();
        var selection = currentRoomView.getSelected();

        System.out.println("Selection finalized with " + selection.size() + " elements.");
    }
}