package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.factories.commands.roomelements.IRoomElementCommandsFactory;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class RotateRightAction extends AbstractDraftAction {
    IRoomElementCommandsFactory commandsFactory;

    public RotateRightAction(IRoomElementCommandsFactory commandsFactory) {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/rotate-right.png"));
        putValue(NAME, "Rotate");
        putValue(SHORT_DESCRIPTION, "Rotate room elements");

        this.commandsFactory = commandsFactory;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        RotateHelper.doRotate(90, commandsFactory);
        pv.getActionManager().refresh();
    }
}
