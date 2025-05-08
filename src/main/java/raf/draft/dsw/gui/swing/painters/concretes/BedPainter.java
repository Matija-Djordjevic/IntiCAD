package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.projecteditor.views.StrokeManager;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.iterators.ColorGenerator;
import raf.draft.dsw.models.roomelements.Bed;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;

public class BedPainter implements MyPainter<Bed> {
    @Override
    public void paint(Graphics2D g2d, Bed element) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        DrawingUtils.setRotationAroundCenter(g2d, element);

        int strokeWidth = 3;
        g2d.setStroke(new BasicStroke(strokeWidth));

        g2d.setColor(Color.BLACK);
        var topLeftCornerPoint = GraphicsUtils.toPoint(element.getTopLeftCorner());
        g2d.drawRect(topLeftCornerPoint.x, topLeftCornerPoint.y, (int)(element.getWidth()), (int)(element.getHeight()));

        g2d.drawRect(topLeftCornerPoint.x, topLeftCornerPoint.y, (int)(element.getWidth() / 100 * 30), (int)(element.getHeight()));

        g2d.setColor(ColorGenerator.PerfectBlue());
        g2d.fillRect(topLeftCornerPoint.x + strokeWidth, (int)(topLeftCornerPoint.y + element.getHeight() / 100 * 5), (int)(element.getWidth() / 100 * 13), (int)(element.getHeight() / 100 * 40));
        g2d.fillRect(topLeftCornerPoint.x + strokeWidth, (int)(topLeftCornerPoint.y + element.getHeight() / 100 * 55) , (int)(element.getWidth() / 100 * 13), (int)(element.getHeight() / 100 * 40));


        g2d.setColor(ColorGenerator.CujuNas());
        g2d.fillRect((int)(topLeftCornerPoint.x + element.getWidth() / 100 * 30), topLeftCornerPoint.y, (int)(element.getWidth() / 100 * 70), (int)(element.getHeight()));

        g2d.setColor(Color.black);
        g2d.drawRect((int)(topLeftCornerPoint.x + element.getWidth() / 100 * 30), topLeftCornerPoint.y, (int)(element.getWidth() / 100 * 70), (int)(element.getHeight()));
    }

}
