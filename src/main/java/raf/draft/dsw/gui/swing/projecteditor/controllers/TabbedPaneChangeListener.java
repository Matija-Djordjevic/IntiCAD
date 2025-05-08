package raf.draft.dsw.gui.swing.projecteditor.controllers;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPaneChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var selectedRoomView = (RoomView) pv.getTabs().getSelectedComponent();
        var projViewModel = pv.getProjectViewModel();
        projViewModel.setLastSelectedRoomView(selectedRoomView);

        if(projViewModel.getLastSelectedRoomView() == null) return;

        var room = projViewModel.getLastSelectedRoomView().getRoom();
        if(room.getWidthCm() == 0 || room.getHeightCm() == 0) pv.startEditRoomState();
    }
}
