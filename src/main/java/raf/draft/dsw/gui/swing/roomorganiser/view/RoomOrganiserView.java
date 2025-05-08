package raf.draft.dsw.gui.swing.roomorganiser.view;

import raf.draft.dsw.Main;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.iterators.SpiralCoordinatesIterator;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.models.MessageGenerator;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.utils.InstanceCreator;

import javax.management.Notification;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomOrganiserView extends JFrame {

    Room room;
    // XD !
    JComboBox<ComboBoxItem> comboBox;
    JTextField widthField;
    JTextField heightField;

    // This panel will hold each added element + an "X" for removal
    JPanel addedElementsPanel;

    // Temporarily store the element descriptors
    List<ElementDescriptor> addedElementsList;

    JButton btnSubmit;
    JButton btnRemoveAll;

    JLabel warningLabel;

    public RoomOrganiserView(Room room) {
        this.room = room;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(MainFrame.getInstance());

        getContentPane().setLayout(new BorderLayout());

        // =============== Top Panel ===============
        JPanel topPanel = new JPanel(new FlowLayout());

        topPanel.add(new JLabel("Width:"));
        widthField = new JTextField(5);
        topPanel.add(widthField);

        topPanel.add(new JLabel("Height:"));
        heightField = new JTextField(5);
        topPanel.add(heightField);

        // The combo box with known element types
        comboBox = new JComboBox<>();

        var checker = ApplicationFramework.getInstance().getChecker();
        var possibleElements = checker
                .getAllPossibleChildrenTypes(Room.class)
                .stream()
                .map(el -> (Class<? extends RoomElement<?>>) el);

        possibleElements.forEach(el -> comboBox.addItem(new ComboBoxItem(el.getSimpleName(), el)));

        topPanel.add(comboBox);

        JButton addElementButton = new JButton("Add Element");
        addElementButton.addActionListener(e -> addElement());
        topPanel.add(addElementButton);

        warningLabel = new JLabel();
        warningLabel.setForeground(Color.RED);
        warningLabel.setVisible(false);

        getContentPane().add(topPanel, BorderLayout.NORTH);

        // =============== Center Panel ===============
        addedElementsPanel = new JPanel();
        addedElementsPanel.setLayout(new BoxLayout(addedElementsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(addedElementsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // =============== Bottom Panel ===============
        JPanel bottomPanel = new JPanel(new FlowLayout());

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> onSubmit());
        bottomPanel.add(btnSubmit);

        btnRemoveAll = new JButton("Remove All");
        btnRemoveAll.addActionListener(e -> removeAllElements());
        bottomPanel.add(btnRemoveAll);
        bottomPanel.add(warningLabel);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // =============== Internal List ===============
        addedElementsList = new ArrayList<>();
    }

    record ComboBoxItem(String name, Class<? extends RoomElement<?>> type) {
        @Override
        public String toString() { return name; }
    }

    private void addElement() {
        var selectedItem = (ComboBoxItem) comboBox.getSelectedItem();

        String widthText    = widthField.getText();
        String heightText   = heightField.getText();

        if (selectedItem == null || widthText.isBlank() || heightText.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an element type and enter valid width/height.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double w, h;
        try {
            w = Double.parseDouble(widthText);
            h = Double.parseDouble(heightText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Width and Height must be numeric!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add a new "ElementDescriptor" to our list
        var descriptor = new ElementDescriptor(selectedItem.type, w, h);
        addedElementsList.add(descriptor);

        // Show it in the panel
        JPanel elementPanel = createElementRow(descriptor);
        addedElementsPanel.add(elementPanel);
        addedElementsPanel.revalidate();
        addedElementsPanel.repaint();
    }



    private JPanel createElementRow(ElementDescriptor descriptor) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lbl = new JLabel(descriptor.toString());
        // Make the label clickable
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open an edit dialog when label is clicked
                openEditDialog(descriptor, lbl);
            }
        });
        row.add(lbl);

        JButton removeButton = new JButton("X");
        removeButton.addActionListener(e -> {
            addedElementsList.remove(descriptor);
            addedElementsPanel.remove(row);
            addedElementsPanel.revalidate();
            addedElementsPanel.repaint();
        });
        row.add(removeButton);

        return row;
    }

    /**
     * Small dialog to edit the selected element's width and height.
     */
    private void openEditDialog(ElementDescriptor descriptor, JLabel label) {
        // A modal dialog for editing
        JDialog editDialog = new JDialog(this, "Edit Element", true);
        editDialog.setLayout(new FlowLayout());
        editDialog.setLocationRelativeTo(this);

        // Pre-fill fields with current values
        JTextField widthField = new JTextField(String.valueOf(descriptor.width), 5);
        JTextField heightField = new JTextField(String.valueOf(descriptor.height), 5);

        editDialog.add(new JLabel("Width:"));
        editDialog.add(widthField);

        editDialog.add(new JLabel("Height:"));
        editDialog.add(heightField);

        // OK button
        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(evt -> {
            try {
                double w = Double.parseDouble(widthField.getText());
                double h = Double.parseDouble(heightField.getText());
                descriptor.width  = w;
                descriptor.height = h;
                label.setText(descriptor.toString());
                editDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        editDialog,
                        "Width and Height must be numeric!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        editDialog.add(okBtn);

        // Cancel button
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(evt -> editDialog.dispose());
        editDialog.add(cancelBtn);

        editDialog.pack();
        editDialog.setVisible(true);
    }

    /**
     * Called when "Submit" is clicked.
     */

    private void onSubmit() {
        if (addedElementsList.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No elements to submit!",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int roomWidth = room.getWidthCm();
        int roomHeight = room.getHeightCm();

        int maxElWidth = addedElementsList
                .stream()
                .map(info -> info.width)
                .max(Double::compareTo)
                .get()
                .intValue();
        int maxElHeight = addedElementsList
                .stream()
                .map(info -> info.height)
                .max(Double::compareTo)
                .get()
                .intValue();

        int rows = roomHeight / maxElHeight;
        int cols = roomWidth / maxElWidth;



        if (addedElementsList.size() > rows * cols) {

            warningLabel.setText("Invalid configuration");
            warningLabel.setVisible(true);

            ApplicationFramework.getInstance().messageGenerator.notify(
                    new LogInfo(LogLevel.INFO, "Ne moze baki")
            );
            return;
        }

        SpiralCoordinatesIterator spiralIterator = new SpiralCoordinatesIterator(cols, rows);

        int elementIndex = 0;


        var submitEls = new ArrayList<RoomElement<?>>();
        while (spiralIterator.hasNext() && elementIndex < addedElementsList.size()) {
            List<Point> ringCoordinates = spiralIterator.next();

            for (Point cell : ringCoordinates) {
                if (elementIndex >= addedElementsList.size()) {
                    break;
                }

                // cell.x = column index, cell.y = row index
                int rowIndex = cell.x;
                int colIndex = cell.y;

                int cellLeft   = colIndex * maxElWidth;
                int cellRight  = (colIndex == (cols - 1)) ? roomWidth : (colIndex + 1) * maxElWidth;
                int cellTop    = rowIndex * maxElHeight;
                int cellBottom = (rowIndex == (rows - 1)) ? roomHeight : (rowIndex + 1) * maxElHeight;

                var descriptor = addedElementsList.get(elementIndex++);
                int elWidth  = (int) descriptor.width;
                int elHeight = (int) descriptor.height;

                // Default to top-left corner of that cell
                int finalX = cellLeft;
                int finalY = cellTop;

                // If we’re on the last column, right-align the element
                if (colIndex == cols - 1) {
                    finalX = cellRight - elWidth;
                }
                // If we’re on the last row, bottom-align the element
                if (rowIndex == rows - 1) {
                    finalY = cellBottom - elHeight;
                }

                var roomElement = InstanceCreator.getRoomElement(
                        descriptor.type,
                        descriptor.type.getSimpleName() + UUID.randomUUID(),
                        room,
                        new Point2D.Double(finalX, finalY),
                        descriptor.width,
                        descriptor.height,
                        0
                );

                submitEls.add(roomElement);

                log("Placing " + descriptor.type +
                        " at [" + finalX + "," + finalY + "]" +
                        " with w=" + elWidth + ", h=" + elHeight);
            }
        }

        var addElementsCommand = MainFrame.getInstance().getRoomElementCommandsFactory().buildCommand("add_many", options ->
                options
                        .setRoomElementsList(submitEls));
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        rv.getCommandsManager().addCommand(addElementsCommand);


        JOptionPane.showMessageDialog(this,
                "All elements were successfully placed in the room!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }

    /**
     * Removes all elements from the list and clears the panel.
     */
    private void removeAllElements() {
        addedElementsList.clear();
        addedElementsPanel.removeAll();
        addedElementsPanel.revalidate();
        addedElementsPanel.repaint();
    }

    private void log(String msg){
        var mg = ApplicationFramework.getInstance().getMessageGenerator();
        mg.notify(new LogInfo(LogLevel.INFO, msg));
    }

    /**
     * Simple descriptor storing an element's type, width, and height.
     */
    private static class ElementDescriptor {
        Class<? extends RoomElement<?>> type;
        double width;
        double height;

        public ElementDescriptor(Class<? extends RoomElement<?>> type, double width, double height) {
            this.type = type;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return String.format("%s (w=%.2f, h=%.2f)", type.getSimpleName(), width, height);
        }
    }
}
