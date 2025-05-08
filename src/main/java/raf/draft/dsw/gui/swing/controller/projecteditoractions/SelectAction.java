package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractDraftAction {
    public SelectAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/selection.png"));
        putValue(NAME, "Select");
        putValue(SHORT_DESCRIPTION, "Select room elements");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        pv.startSelectState();
    }
}
