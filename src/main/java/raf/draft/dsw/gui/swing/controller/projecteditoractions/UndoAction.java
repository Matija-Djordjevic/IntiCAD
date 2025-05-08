package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction  extends AbstractDraftAction {
    public UndoAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/undo.png"));
        putValue(NAME, "Resize");
        putValue(SHORT_DESCRIPTION, "Undo");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        rv.getCommandsManager().undo();
        pv.getActionManager().refresh();
    }
}