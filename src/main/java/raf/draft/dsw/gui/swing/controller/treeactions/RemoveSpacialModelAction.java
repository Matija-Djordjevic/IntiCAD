package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.event.ActionEvent;

public class RemoveSpacialModelAction extends AbstractDraftAction {
    public RemoveSpacialModelAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/remove-cascade.png"));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove a model and it's children");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var treeView = MainFrame.getInstance().getTreeView();
        var projectManager = ApplicationFramework.getInstance().getProjectManager();

        var childNode = (DraftTreeNode)treeView.getLastSelectedPathComponent();

        // BRISANJE MODEL
        var childModel = (SpacialModel)childNode.getUserObject();
        projectManager.remove(childModel);
    }
}


