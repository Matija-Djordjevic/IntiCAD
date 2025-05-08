package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.painters.PaintersFactory;
import raf.draft.dsw.gui.swing.utils.RoundColoredRactangle;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.utils.PathUtils;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RoomPainter implements MyPainter<Room> {
    @Override
    public void paint(Graphics2D g2d, Room element) {
        if (RoomHasDimensions(element))
            renderRoom(g2d, element);
        else
            renderSetDimensionsPrompt(g2d, element);
    }

    private boolean RoomHasDimensions(Room room) {
        return room.getWidthCm() != 0 && room.getHeightCm() != 0;
    }

    private void renderRoom(Graphics2D g2d, Room element){
        getCanvas(g2d, element.getWidthCm(), element.getHeightCm()).draw();
        var painterFactory = new PaintersFactory();
        element.getChildrenCollection().getAllAsStream().forEach(c -> {
            if (c instanceof RoomElement<?> roomElement) {
                Graphics2D g2dCopy = (Graphics2D) g2d.create();

                g2dCopy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                var painter = painterFactory.getPainter(roomElement);
                painter.paint(g2dCopy, roomElement);

                g2dCopy.dispose();
            }
        });
    }

    private void renderSetDimensionsPrompt(Graphics2D g2d, Room room) {
        var fontSize = 20;

        var font = new Font("Arial", Font.PLAIN, fontSize); // Corrected "Ariel" to "Arial"
        g2d.setFont(font);

        // Calculate the center based on the current component size
        int componentWidth = g2d.getClipBounds().width;
        int componentHeight = g2d.getClipBounds().height;
        var point = new Point(componentWidth / 2, componentHeight / 2);

        var enterDimensionsMsg = "Please set room dimensions to begin to draw";

        var info = new ArrayList<>(Arrays.asList(
                PathUtils.getPathAsStr(room),
                enterDimensionsMsg));

        DrawingUtils.drawStringsAroundPoint(point, info, g2d);
    }

    private static RoundColoredRactangle getCanvas(Graphics2D g2d, int newWidth, int newHeight) {
        return new RoundColoredRactangle(g2d)
                .setPoint(new Point(0, 0))
                .setWidth(newWidth)
                .setHeight(newHeight)
                .setBorderColor(Color.BLACK)
                .setBackgroundColor(Color.WHITE)
                .setStrokeWidth(1)
                .setArcWidth(0)
                .setArcHeight(0);
    }
}
