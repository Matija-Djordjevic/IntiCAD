package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.ProjectInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeProjectInfoAction extends AbstractDraftAction {
    JTextField authorTextField;
    JTextField projectTextField;
    JTextField pathTextField;
    JFrame setInfoFrame;

    public ChangeProjectInfoAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/change-info.png"));
        putValue(NAME, "Change project info");
        putValue(SHORT_DESCRIPTION, "Set new author and project name and path");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setInfoFrame = new JFrame();

        setInfoFrame.setTitle("Set project info");
        setInfoFrame.setSize(300, 200);
        setInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setInfoFrame.setLocationRelativeTo(MainFrame.getInstance());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel authorLabel = new JLabel("Author:");
        authorTextField = new JTextField(20);

        JLabel projectLabel = new JLabel("Project:");
        projectTextField = new JTextField(20);

        JLabel pathLabel = new JLabel("Path:");
        pathTextField = new JTextField(20);

        var proj = (DraftTreeNode) MainFrame.getInstance().getTreeView().getLastSelectedPathComponent();

        JButton submitButton = new JButton("Submit") {{ addActionListener(_ ->  onClick(proj)); }};

        panel.add(authorLabel);
        panel.add(authorTextField);
        panel.add(projectLabel);
        panel.add(projectTextField);
        panel.add(pathLabel);
        panel.add(pathTextField);
        panel.add(submitButton);

        setInfoFrame.add(panel);
        setInfoFrame.setVisible(true);
    }

    private void onClick(DraftTreeNode proj) {
        ((Project)proj.getUserObject()).setProjectInfo(
                new ProjectInfo(
                    authorTextField.getText(),
                    projectTextField.getText(),
                    pathTextField.getText()));

        setInfoFrame.dispose();
    }
}