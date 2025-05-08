package raf.draft.dsw.gui.swing.tree.controllers.mouselisteners;

import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.event.MouseEvent;

public class SingleClickMouseListener extends AbstractTreeMouseAdapter{
    @Override
    void performAction(MouseEvent e) {
        var node = (DraftTreeNode) MainFrame.getInstance().getTreeView().getLastSelectedPathComponent();
        MainFrame.getInstance().getActionManager().update((SpacialModel) node.getUserObject());
    }

    @Override
    int clicksRequired() { return 1; }
}
