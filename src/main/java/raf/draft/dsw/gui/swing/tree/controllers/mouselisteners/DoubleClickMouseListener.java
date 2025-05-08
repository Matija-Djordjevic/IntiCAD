package raf.draft.dsw.gui.swing.tree.controllers.mouselisteners;

import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.event.MouseEvent;

public class DoubleClickMouseListener extends AbstractTreeMouseAdapter {
    @Override
    void performAction(MouseEvent e) {
        var node = (DraftTreeNode)MainFrame.getInstance().getTreeView().getLastSelectedPathComponent();
        var model = (SpacialModel)node.getUserObject();
        var projectView = (ProjectView)MainFrame.getInstance().getProjectView();

        if(model instanceof Project proj) projectView.redraw(proj);
    }

    @Override
    int clicksRequired() { return 2;}

}