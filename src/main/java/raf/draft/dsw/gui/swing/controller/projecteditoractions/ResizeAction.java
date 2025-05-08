package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ResizeAction extends AbstractDraftAction {

    public ResizeAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/resize.png"));
        putValue(NAME, "Resize");
        putValue(SHORT_DESCRIPTION, "Resize room elements");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        pv.startResizeState();
    }
}
