package raf.draft.dsw.gui.swing.tree.view;

import raf.draft.dsw.gui.swing.tree.controllers.CrudDraftTreeController;
import raf.draft.dsw.gui.swing.tree.controllers.DraftTreeSelectionListener;
import raf.draft.dsw.gui.swing.tree.models.DraftTree;

import javax.swing.*;
import javax.swing.tree.TreeModel;

public class DraftJTree extends JTree {
    CrudDraftTreeController crudDraftTreeController;

    public DraftJTree(TreeModel treeModel) {
        super(treeModel);
        addTreeSelectionListener(new DraftTreeSelectionListener());
        crudDraftTreeController = new CrudDraftTreeController((DraftTree) treeModel);
    }
}
