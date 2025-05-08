package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.SpacialModel;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RenameAction extends AbstractDraftAction {
    public RenameAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename selected model in the tree");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var frame = new JFrame("Rename selected model");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,150);
        frame.setLocationRelativeTo(MainFrame.getInstance());

        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        var inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        var label = new JLabel("Name:");
        var txt = new JTextField(15);

        inputPanel.add(label);
        inputPanel.add(txt);

        JButton submitButton = new JButton("Submit") {{ addActionListener(_ -> onClick(txt, frame)); }};

        panel.add(inputPanel);
        panel.add(submitButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void onClick(JTextField txt, JFrame frame) {
        var treeView = MainFrame.getInstance().getTreeView();
        var treeModel = (DefaultTreeModel)treeView.getModel();
        var projectManager = ApplicationFramework.getInstance().getProjectManager();

        var targetNode = (DraftTreeNode) treeView.getLastSelectedPathComponent();
        var targetModel = (SpacialModel)targetNode.getUserObject();

        // MODEL CHANGED (WILL BE REFLECTED BOTH IN TREE MODEL AND TREE VIEW)
        var newName = txt.getText();
        projectManager.rename(targetModel, newName);

        frame.dispose();
    }
}
