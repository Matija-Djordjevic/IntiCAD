package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddAction extends AbstractDraftAction {
    public AddAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/double-bed.png"));
        putValue(NAME, "Add Room Element");
        putValue(SHORT_DESCRIPTION, "Add new room elements");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        pv.startAddState();
    }
}
