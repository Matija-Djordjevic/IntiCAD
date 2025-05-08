package raf.draft.dsw.gui.swing.tree.models;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.HashMap;

@Getter
@Setter
public class DraftTree extends DefaultTreeModel implements IDraftTree {
    TreeSelectionListener treeSelectionListener;
    final HashMap<ISpacialModel, DraftTreeNode> mapping = new HashMap<>();

    public DraftTree(TreeNode root) {
        super(root);
    }

    @Override
    public void generateTree() {}
}
