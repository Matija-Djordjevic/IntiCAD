package raf.draft.dsw.gui.swing.tree.models;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import javax.swing.tree.DefaultMutableTreeNode;

public class DraftTreeNode extends DefaultMutableTreeNode {

    public DraftTreeNode(ISpacialModel spacialModel) {
        super(spacialModel);
    }

    @Override
    public boolean isLeaf() {
        var model = this.getUserObject();
        return !(model instanceof CompositeSpacialModel);
    }

    @Override
    public String toString() {
        return this.getUserObject().toString();
    }

    public void setName(String newName){
        if(this.getUserObject() instanceof ISpacialModel sm) sm.setName(newName);
    }
}
