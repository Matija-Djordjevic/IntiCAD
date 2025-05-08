package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.Closet;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;

public class ClosetPainter implements MyPainter<Closet> {
    @Override
    public void paint(Graphics2D g2d, Closet element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        var pos = GraphicsUtils.toPoint(element.getTopLeftCorner());

        int lineWidth = 3;
        g2d.setStroke(new BasicStroke(lineWidth));

        g2d.drawRect(pos.x, pos.y, (int) element.getWidth(), (int) element.getHeight());

        int lineX = pos.x + (int) (element.getWidth() / 2);

        int handleDiameter = (int) Math.min(
                element.getWidth() / 10,
                element.getHeight() / 10
        );
        int handleOffset = handleDiameter * 2;

        int handle1X = lineX - handleOffset - handleDiameter / 2;
        int handle2X = lineX + handleOffset - handleDiameter / 2;
        int handleY = pos.y + (int) (element.getHeight() / 2) - handleDiameter / 2;

        g2d.drawLine(lineX, pos.y, lineX, pos.y + (int) element.getHeight());

        g2d.drawOval(handle1X, handleY, handleDiameter, handleDiameter);
        g2d.drawOval(handle2X, handleY, handleDiameter, handleDiameter);
    }
}
