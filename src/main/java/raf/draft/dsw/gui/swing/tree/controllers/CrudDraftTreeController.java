package raf.draft.dsw.gui.swing.tree.controllers;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tree.models.DraftTree;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.observer.ICrudSubscriber;


public class CrudDraftTreeController implements ICrudSubscriber {
    DraftTree draftTree;

    public CrudDraftTreeController(DraftTree draftTree) {
        this.draftTree = draftTree;

        ApplicationFramework.getInstance().getProjectManager().addSubscriber(this);
    }

    @Override
    public void updateAddCrud(ISpacialModel parent, ISpacialModel child) {
        var mapping = draftTree.getMapping();

        var parentNode = mapping.get(parent);
        var childNode = new DraftTreeNode(child);
        parentNode.add(childNode);

        mapping.put(child, childNode);

        // TREE GUI UPDATE
        var array = new int[]{parentNode.getIndex(childNode)}; // [0 1 2 ....]
        draftTree.nodesWereInserted(parentNode, array);
    }

    @Override
    public void updateDeleteCrud(ISpacialModel child) {
        var mapping = draftTree.getMapping();

        var parent = child.getParent();
        var parentNode = mapping.get(parent);
        var childNode = mapping.get(child);
        parentNode.remove(childNode);

        mapping.remove(child);

        draftTree.nodeStructureChanged(parentNode);
    }

    @Override
    public void updateUpdateCrud(ISpacialModel model) {
        var mapping = draftTree.getMapping();
        var modelNode = mapping.get(model);

        // GUI UPDATE
        draftTree.nodeChanged(modelNode);
    }
}
