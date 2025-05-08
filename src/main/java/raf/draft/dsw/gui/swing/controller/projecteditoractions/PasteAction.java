package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;
import java.awt.event.ActionEvent;

public class PasteAction extends AbstractDraftAction {
    public PasteAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/paste.png"));
        putValue(NAME, "Paste");
        putValue(SHORT_DESCRIPTION, "Paste selected elements");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var roomView = pv.getProjectViewModel().getLastSelectedRoomView();
        var copied = roomView.getCopied();

        var cantMove = copied.stream().anyMatch(el -> {
            if(!(el instanceof RoomElement<?> roomElement)) return true;

            var position = GraphicsUtils.toPoint(roomElement.getTopLeftCorner());

            var posX = position.x;
            var room = roomView.getRoom();
            return posX + roomElement.getWidth() > room.getWidthCm() - 20;
        });

        copied.forEach(el -> {
            if(!(el instanceof RoomElement<?> roomEl)) return;
            var oldPos = GraphicsUtils.toPoint(roomEl.getTopLeftCorner());
            roomEl.setTopLeftCorner(GraphicsUtils.toPoint2DDouble(new Point(oldPos.x + (cantMove ? 0 : 10), oldPos.y)));
            ApplicationFramework.getInstance().getProjectManager().add(roomView.getRoom(), roomEl);
        });

        for (int i = 0; i < copied.size(); i++) {
            if(!(copied.get(i) instanceof RoomElement<?> roomEl)) return;
            copied.set(i, roomEl.copy());
        }

        pv.getActionManager().update(null);
    }
}
