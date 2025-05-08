package raf.draft.dsw.gui.swing.tree.controllers.mouselisteners;

import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractTreeMouseAdapter extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == clicksRequired()){
            if(!nodeWasClicked(e)) return;
            performAction(e);
            e.consume();
        }
    }

    abstract void performAction(MouseEvent e);
    abstract int clicksRequired();


    private boolean nodeWasClicked(MouseEvent e) {
        var treeView = MainFrame.getInstance().getTreeView();
        return treeView.getPathForLocation(e.getX(), e.getY()) !=null;
}
}