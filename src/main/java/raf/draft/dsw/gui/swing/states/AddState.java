package raf.draft.dsw.gui.swing.states;

import org.jetbrains.annotations.NotNull;
import raf.draft.dsw.commands.LoggingCommand;
import raf.draft.dsw.commands.roomelementcommands.AddRoomElementCommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.*;
import raf.draft.dsw.utils.GraphicsUtils;
import raf.draft.dsw.utils.InstanceCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;


public class AddState implements IState {
    Point clickPosition;

    @Override
    public void MouseClickedSameSameButDifferentButStillSame(RoomView roomView, Point e) {
        clickPosition = e;
        performMouseRelease(e);
    }

    JDialog dialog;
    JLabel warningLbl;
    JTextField elWidth, elHeight;
    private void performMouseRelease(Point lastRelease) {
        JDialog dialog = new JDialog(MainFrame.getInstance(), "Select room element to add", true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(MainFrame.getInstance());
        this.dialog = dialog;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.X_AXIS));

        JPanel widthPanel = new JPanel();
        widthPanel.setLayout(new BoxLayout(widthPanel, BoxLayout.Y_AXIS));
        JLabel widthLabel = new JLabel("Width:");
        var elWidth = new JTextField(10);
        this.elWidth = elWidth;
        widthPanel.add(widthLabel);
        widthPanel.add(elWidth);
        fieldsPanel.add(widthPanel);

        fieldsPanel.add(Box.createHorizontalStrut(20));

        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new BoxLayout(heightPanel, BoxLayout.Y_AXIS));
        JLabel heightLabel = new JLabel("Height:");
        var elHeight = new JTextField(10);
        this.elHeight = elHeight;
        heightPanel.add(heightLabel);
        heightPanel.add(elHeight);

        fieldsPanel.add(heightPanel);

        mainPanel.add(fieldsPanel);

        mainPanel.add(Box.createVerticalStrut(10));

        String warning = "Invalid dimensions or non-number symbols used";
        var warningLbl = new JLabel(warning);
        warningLbl.setVisible(false);
        warningLbl.setForeground(Color.RED);
        this.warningLbl = warningLbl;
        mainPanel.add(warningLbl);

        mainPanel.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        addButtons(buttonsPanel);

        dialog.setLayout(new BorderLayout());
        dialog.add(mainPanel, BorderLayout.NORTH);
        dialog.add(buttonsPanel, BorderLayout.CENTER);

        dialog.setSize(400, 230);
        dialog.setVisible(true);

    }

    private void addButtons(JPanel buttonsPanel) {
        var checker = ApplicationFramework.getInstance().getChecker();
        var possibleElements = checker
                .getAllPossibleChildrenTypes(Room.class)
                .stream()
                .map(el -> (Class<? extends RoomElement<?>>) el);

        possibleElements.forEach(type -> {
            var buttonName = type.getSimpleName();
            buttonsPanel.add(new JButton(buttonName) {{ addActionListener(_ -> onClick(type));}});
        });
    }

    private void onClick(Class<? extends RoomElement<?>> type) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        var room = rv.getRoom();


        int width, height;
        try{
            width = Integer.parseInt(elWidth.getText());
            height = Integer.parseInt(elHeight.getText());
        }catch (Exception _){
            warningLbl.setVisible(true);
            return;
        }

        if(wouldExceedRoomDimensions(new Point(clickPosition), width, room, height)) {
            warningLbl.setVisible(true);
            return;
        }

        var topLeftCorner = GraphicsUtils.toPoint2DDouble(clickPosition);
        var roomElement = InstanceCreator.getRoomElement(
                type,
                getName(type),
                room,
                topLeftCorner,
                width,
                height,
                0);

        var factory = MainFrame.getInstance().getRoomElementCommandsFactory();
        var addCommand = factory.buildCommand(
                "add",
                options ->
                        options.setRoomElement(roomElement)
        );

        try{
            rv.getCommandsManager().addCommand(addCommand);
        }catch (Exception _){
            warningLbl.setVisible(true);
            System.out.println("Can't add because collision");
            return;
        }

        var selected = pv.getProjectViewModel().getLastSelectedRoomView().getSelected();
        selected.clear();
        selected.add(roomElement);
        pv.startMoveState();
        dialog.dispose();
    }

    private static @NotNull String getName(Class<? extends RoomElement<?>> type) {
        var pathAndName = type.getSimpleName().split(",");
        var name = pathAndName[pathAndName.length - 1];
        var id = UUID.randomUUID().toString().substring(0, 5);
        return  name + " (" + id + ")";
    }

    private static boolean wouldExceedRoomDimensions(Point pos, int width, Room room, int height) {
        var outOfRoomWidth = pos.x + width > room.getWidthCm() - 10;
        var outOfRoomHeight = pos.y + height > room.getHeightCm() - 10;
        return outOfRoomWidth || outOfRoomHeight;
    }
}
