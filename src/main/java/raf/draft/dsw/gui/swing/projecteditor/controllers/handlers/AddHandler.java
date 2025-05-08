package raf.draft.dsw.gui.swing.projecteditor.controllers.handlers;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.structure.ISpacialModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AddHandler {
    ProjectView projectView;
    public AddHandler(ProjectView projectView) {
        this.projectView = projectView;
    }

    public void handleAdd(ISpacialModel parent, ISpacialModel child) {
        if(!(child instanceof Room room)) return;
        if(!(parent instanceof Building building)) return;

        var roomId = room.getGuid();
        var buildingId = building.getGuid();

        var tabs = projectView.getTabs();
        for(int i = tabs.getTabCount() - 1; i >= 0; i--){
            var roomView = (RoomView)tabs.getComponentAt(i);
            if(roomView.getRoom().getParent() == room.getParent()) {
                projectView.addRoomTab(room, Color.RED, i + 1);
                var color = tabs.getBackgroundAt(i);
                tabs.setBackgroundAt(i + 1, color);
                return;
            }
        }
        // in case it's a building
        projectView.addRoomTabs(room.getParent().getChildrenCollection().getAllAsStream().collect(Collectors.toCollection(ArrayList::new)));
    }
}
