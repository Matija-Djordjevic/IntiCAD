package raf.draft.dsw.gui.swing.tree.controllers;

import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.SpacialModel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class DraftTreeSelectionListener implements TreeSelectionListener {

    public DraftTreeSelectionListener() {  }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        var path  = e.getNewLeadSelectionPath();

        var node = path != null ? (DraftTreeNode) path.getLastPathComponent() : null;
        var model = node != null ? (SpacialModel) node.getUserObject() : null;

        MainFrame.getInstance().getActionManager().update(model);
    }
}
