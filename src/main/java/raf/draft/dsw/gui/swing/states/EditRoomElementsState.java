package raf.draft.dsw.gui.swing.states;


import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.spacialmodelcollections.PositionalSpacialModelCollection;

import javax.swing.*;
import java.awt.*;

public class EditRoomElementsState implements IState {
    @Override
    public void MouseClickedSameSameButDifferentButStillSame(RoomView roomView, Point point) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var room = pv.getProjectViewModel().getLastSelectedRoomView().getRoom();
        var collection = (PositionalSpacialModelCollection) room.getChildrenCollection();

        var selected = collection.findOnPoint(point).stream().findFirst().orElse(null);
        if(selected == null) return;

        JDialog dialog = new JDialog(MainFrame.getInstance(), "Set Element Properties", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(MainFrame.getInstance());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel widthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        widthPanel.add(new JLabel("Set Width:"));
        JTextField widthField = new JTextField(10);
        widthPanel.add(widthField);

        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        heightPanel.add(new JLabel("Set Height:"));
        JTextField heightField = new JTextField(10);
        heightPanel.add(heightField);

        JPanel rotationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rotationPanel.add(new JLabel("Set Rotation:"));
        JTextField rotationField = new JTextField(10);
        rotationPanel.add(rotationField);

        mainPanel.add(widthPanel);
        mainPanel.add(heightPanel);
        mainPanel.add(rotationPanel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            var selectedEl = (RoomElement<?>) selected;
            try{
                var rotation = Integer.parseInt(rotationField.getText());
                if(!(rotation == 90 || rotation == 180 || rotation == 270 || rotation == 0)) return;
                selectedEl.setRotation(rotation);
                selectedEl.setWidth(Integer.parseInt(widthField.getText()));
                selectedEl.setHeight(Integer.parseInt(heightField.getText()));
                ApplicationFramework.getInstance().getProjectManager().notifyUpdateCrudSubscribers(selectedEl);
                dialog.dispose();
            }catch (Exception _) {
                return;
            }
        });

        cancelButton.addActionListener(e -> {
            dialog.dispose();
        });

        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        // Add panels to dialog
        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.add(buttonsPanel, BorderLayout.SOUTH);

        // Show the dialog (this call blocks until the dialog is closed because it's modal)
        dialog.setVisible(true);
    }
}
