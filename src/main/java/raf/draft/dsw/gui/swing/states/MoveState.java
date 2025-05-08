package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.Main;
import raf.draft.dsw.commands.ICommand;
import raf.draft.dsw.commands.LoggingCommand;
import raf.draft.dsw.commands.roomelementcommands.MoveRoomElementCommand;
import raf.draft.dsw.commands.roomelementcommands.MoveRoomElementsCompositeCommand;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.*;
import java.util.ArrayList;

public class MoveState  extends DraggingState {
    @Override
    void handleReleaseAfterDrag(Point dragStartPoint, Point dragEndPoint) {
        // Retrieve the current project and room views
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        var roomView = projectView.getProjectViewModel().getLastSelectedRoomView();
        var currentRoomView = projectView.getProjectViewModel().getLastSelectedRoomView();
        var selectedElements = currentRoomView.getSelected();
        if(selectedElements.isEmpty()) return;
        var commandsManager = roomView.getCommandsManager();

        int deltaX = dragEndPoint.x - dragStartPoint.x;
        int deltaY = dragEndPoint.y - dragStartPoint.y;

        var factory = MainFrame.getInstance().getRoomElementCommandsFactory();

        commandsManager.addCommand(
                factory.buildCommand(
                        "move_many",
                        options ->
                                options
                                        .setRoomElementsList(selectedElements)
                                        .setXAndYDrag(deltaX, deltaY)
                )
        );
    }

    @Override
    void handleClick(Point clickPoint) { }

    @Override
    void handleDrag(Point startPoint, Point endPoint) { }
}
