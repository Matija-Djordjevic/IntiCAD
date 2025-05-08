package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.awt.event.ActionEvent;
import java.util.UUID;

public class CopyAction extends AbstractDraftAction {
    public CopyAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/copy.png"));
        putValue(NAME, "Copy");
        putValue(SHORT_DESCRIPTION, "Copy selected elements");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var roomView = pv.getProjectViewModel().getLastSelectedRoomView();
        var selected = roomView.getSelected();
        var copied = roomView.getCopied();

        copied.clear();
        selected.forEach(el -> {
            if(!(el instanceof RoomElement<?> roomEl)) return;
            var copy = roomEl.copy();
            copy.setName(el.getName() + UUID.randomUUID().toString().substring(0, 3));
            copied.add(copy);
        });
        copied.forEach(el -> {
            System.out.println(el);
        });
    }
}
