package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.projecteditor.views.StrokeManager;
import raf.draft.dsw.gui.swing.view.MainFrame;

public class ZoomState implements IState {
    @Override
    public void MouseWheelUpSameSameButDifferentButStillSame(RoomView roomView, int e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        var zoom = rv.getStrokeManager().getZoom();
        rv.getStrokeManager().setZoom(zoom / 100 * 110);
    }

    @Override
    public void MouseWheelDownSameSameButDifferentButStillSame(RoomView roomView, int e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        var zoom = rv.getStrokeManager().getZoom();
        rv.getStrokeManager().setZoom(zoom / 100 * 90);
    }
}
