package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditRoomAction extends AbstractDraftAction {
    JTextField roomWidthField;
    JTextField roomHeightField;
    JFrame resizeFrame;

    public EditRoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/new-document.png"));
        putValue(NAME, "New Documment");
        putValue(SHORT_DESCRIPTION, "Set room dimensions");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        resizeFrame = new JFrame("WHAT");
        resizeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resizeFrame.setSize(300,150);
        resizeFrame.setLocationRelativeTo(MainFrame.getInstance());

        JPanel panel = new JPanel();

        JLabel roomWidthLabel = new JLabel("Width:");
        roomWidthField = new JTextField(20);


        JLabel roomHeightLabel = new JLabel("Height:");
        roomHeightField = new JTextField(20);

        JButton submitButton = new JButton("Submit") {{ addActionListener(_ ->  onClick()); }};

        resizeFrame.add(panel);
        panel.add(roomWidthLabel);
        panel.add(roomWidthField);
        panel.add(roomHeightLabel);
        panel.add(roomHeightField);
        panel.add(submitButton);

        resizeFrame.setVisible(true);
    }

    void onClick(){
        var widthStr = roomWidthField.getText();
        var heightStr = roomHeightField.getText();

        if(widthStr.isEmpty() || heightStr.isEmpty()) return;

        int width, height;
        try {
            width = Integer.parseInt(roomWidthField.getText());
            height = Integer.parseInt(roomHeightField.getText());
        }catch (NumberFormatException e) {
            return;
        }

        var projView = (ProjectView)MainFrame.getInstance().getProjectView();
        var roomView = projView.getProjectViewModel().getLastSelectedRoomView();
        var room = roomView.getRoom();

        ApplicationFramework.getInstance().getProjectManager().updateModel(room, r -> {
            ((Room) r).setWidthCm(width);
            ((Room) r).setHeightCm(height);
        });

        resizeFrame.dispose();
        projView.getActionManager().refresh();
        projView.startAddState();
    }

}
