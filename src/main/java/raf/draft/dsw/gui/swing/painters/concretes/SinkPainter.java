package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.roomelements.Sink;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;

public class SinkPainter implements MyPainter<Sink> {
    @Override
    public void paint(Graphics2D g2d, Sink element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        var position = GraphicsUtils.toPoint(element.getTopLeftCorner());
        var sinkWidth = element.getWidth();
        var sinkHeight = element.getHeight();

        double x1 = position.x, x2 = position.x + sinkWidth, x3 = position.x + (double)sinkWidth / 2;
        double y1 = position.y, y2 = position.y, y3 = position.y + sinkHeight;

        int centerX = (int)((x1 + x2 + x3) / 3);
        int centerY = (int)((y1 + y2 + y3) / 3);

        int pointSize = 6;
        g2d.fillOval(centerX - pointSize / 2, centerY - pointSize / 2, pointSize, pointSize);

        int[] xPoints = {(int) x1, (int) x2, (int) x3};
        int[] yPoints = {(int) y1, (int) y2, (int) y3};
        g2d.drawPolygon(xPoints, yPoints, 3);
    }
}
