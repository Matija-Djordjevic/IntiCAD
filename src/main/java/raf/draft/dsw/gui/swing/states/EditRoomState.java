package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.*;

public class EditRoomState implements IState {
    @Override
    public void MouseClickedSameSameButDifferentButStillSame(RoomView roomView, Point e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        pv.getActionManager().getEditRoomAction().actionPerformed(null);
    }
}
