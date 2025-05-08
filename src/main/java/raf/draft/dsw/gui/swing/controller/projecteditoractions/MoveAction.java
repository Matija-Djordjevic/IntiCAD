package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractDraftAction {
    public MoveAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/move.png"));
        putValue(NAME, "Move");
        putValue(SHORT_DESCRIPTION, "Move elements across the canvas");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        pv.startMoveState();
    }
}
