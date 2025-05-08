package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomAction extends AbstractDraftAction {

    public ZoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/zoom-in.png"));
        putValue(NAME, "ZoomIn");
        putValue(SHORT_DESCRIPTION, "Zoom in room canvas");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        pv.startZoomState();
    }
}
