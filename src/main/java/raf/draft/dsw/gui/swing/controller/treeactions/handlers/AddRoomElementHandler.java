package raf.draft.dsw.gui.swing.controller.treeactions.handlers;

import lombok.NoArgsConstructor;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.utils.InstanceCreator;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
public class AddRoomElementHandler implements IAddRoomElementHandler {

    @Override
    public void doAddElement(Class<? extends ISpacialModel> type,
                             JTextField txtBox,
                             JLabel warningLbl,
                             JFrame frame) {
        var treeView = MainFrame.getInstance().getTreeView();
        var projManager = ApplicationFramework.getInstance().getProjectManager();

        var parentNode  = (DraftTreeNode) treeView.getLastSelectedPathComponent();
        var parentModel = (CompositeSpacialModel) parentNode.getUserObject();
        var childModel  = InstanceCreator.getSpacialModel(type, txtBox.getText(), parentModel);

        // DODAVANJE U PROJECT MANAGER MODEL
        if (!projManager.add(parentModel, childModel)) {
            guiAddFailureAdjust(txtBox, warningLbl);
            return;
        };
        guiAddSuccessAdjust(txtBox, warningLbl);

        frame.dispose();
    }

    private void guiAddSuccessAdjust(JTextField txtBox, JLabel warningLbl) {
        warningLbl.setVisible(false);
        txtBox.setText("");
        txtBox.requestFocus();
    }

    private void guiAddFailureAdjust(JTextField txtBox, JLabel warningLbl) {
        warningLbl.setVisible(true);
        txtBox.requestFocus();
    }
}
