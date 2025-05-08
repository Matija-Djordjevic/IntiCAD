package raf.draft.dsw.gui.swing.projecteditor.controllers.handlers;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

public class RemoveHandler {
    ProjectView projectView;
    public RemoveHandler(ProjectView projectView) {
        this.projectView = projectView;
    }

    public void handleRemove(ISpacialModel child) {
        switch (child){
            case Project proj -> handleRemovedProj(proj);
            case Building building -> handleRemoveBuilding(building);
            case Room room -> handleRemoveRoom(room);
            default -> {}
        }
    }

    private void handleRemoveRoom(Room room) {
        var tabs = projectView.getTabs();
        for(int i = tabs.getTabCount() - 1; i >= 0; i--){
            var roomView = (RoomView)tabs.getComponentAt(i);
            if(roomView.getRoom() == room)
                tabs.removeTabAt(i);
        }
    }

    private void handleRemoveBuilding(Building building) {
        var tabs = projectView.getTabs();
        for(int i = tabs.getTabCount() - 1; i >= 0; i--){
            var roomView = (RoomView)tabs.getComponentAt(i);
            if(roomView.getRoom().getParent() == building) tabs.removeTabAt(i);
        }
    }

    private void handleRemovedProj(Project removedProj) {
        if(projectView.getProject() == removedProj) projectView.redraw(null);
    }
}
