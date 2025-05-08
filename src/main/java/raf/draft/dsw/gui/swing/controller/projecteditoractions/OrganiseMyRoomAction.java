package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.roomorganiser.view.RoomOrganiserView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class OrganiseMyRoomAction extends AbstractDraftAction {
    public OrganiseMyRoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/blueprint.png"));
        putValue(NAME, "Add Room Element");
        putValue(SHORT_DESCRIPTION, "Automatically arrange room elements in a spiral layout");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var room = pv.getProjectViewModel().getLastSelectedRoomView().getRoom();
        RoomOrganiserView organiserView = new RoomOrganiserView(room);
        organiserView.setVisible(true);
    }
}
