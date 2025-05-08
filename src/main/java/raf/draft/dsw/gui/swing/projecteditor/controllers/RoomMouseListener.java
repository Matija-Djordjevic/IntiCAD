package raf.draft.dsw.gui.swing.projecteditor.controllers;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.projecteditor.views.StrokeManager;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Room;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class RoomMouseListener extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        pv.PVMouseReleased(rv, rv.getStrokeManager().requestNewResizedPoint(e.getPoint()));
        pv.getActionManager().update(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        pv.PVMouseClicked(rv, rv.getStrokeManager().requestNewResizedPoint(e.getPoint()));
        pv.getActionManager().update(null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        pv.PVMousePressed(rv, rv.getStrokeManager().requestNewResizedPoint(e.getPoint()));
        pv.getActionManager().update(null);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rotation = e.getWheelRotation();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        if(rotation < 0) pv.PVMouseWheelUp(rv, rotation);
        else pv.PVMouseWheelDown(rv, rotation);
        pv.getActionManager().update(null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        pv.PVMouseDragged(rv, rv.getStrokeManager().requestNewResizedPoint(e.getPoint()));
        pv.getActionManager().update(null);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        pv.PVMouseMoved(rv, rv.getStrokeManager().requestNewResizedPoint(e.getPoint()));
        pv.getActionManager().update(null);
    }
}
