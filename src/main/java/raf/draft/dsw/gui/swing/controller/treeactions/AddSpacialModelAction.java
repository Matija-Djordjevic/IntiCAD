package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.controller.treeactions.handlers.AddRoomElementHandler;
import raf.draft.dsw.gui.swing.controller.treeactions.handlers.IAddRoomElementHandler;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.CompositeSpacialModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddSpacialModelAction extends AbstractDraftAction {
    public AddSpacialModelAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/treeactions/plus.png"));
        putValue(NAME, "Add new tree item");
        putValue(SHORT_DESCRIPTION, "Add new tree item");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var frame = new JFrame("Add tree node");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(MainFrame.getInstance());

        JPanel panel = new JPanel(new FlowLayout());

        var txtBox = new JTextField(15);
        panel.add(txtBox);

        var warningLbl = new JLabel("Invalid name!");
        warningLbl.setVisible(false);
        warningLbl.setForeground(Color.RED);
        panel.add(warningLbl);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        addButtons(buttonsPanel, txtBox, warningLbl, frame);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        frame.setSize(400, 200);

        frame.setVisible(true);
    }

    private void addButtons(JPanel buttonsPanel, JTextField txtBox, JLabel warningLbl, JFrame frame) {
        var treeView = MainFrame.getInstance().getTreeView();
        var parentNode = (DraftTreeNode)treeView.getLastSelectedPathComponent();
        var parentModel = parentNode.getUserObject();

        if (parentModel instanceof CompositeSpacialModel cmpSpacialParent) {
            var possibleChildren = ApplicationFramework
                    .getInstance()
                    .getChecker()
                    .getAllPossibleChildrenTypes(cmpSpacialParent);

            IAddRoomElementHandler handler = new AddRoomElementHandler();
            possibleChildren.forEach(type -> {
                var buttonName = type.getSimpleName();
                buttonsPanel.add(new JButton(buttonName) {{ addActionListener(_ ->
                        handler.doAddElement(type, txtBox, warningLbl, frame));}});
            });
        }
    }
}
