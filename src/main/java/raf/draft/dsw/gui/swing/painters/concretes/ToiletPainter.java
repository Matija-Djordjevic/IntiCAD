package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.gui.swing.utils.RoundColoredRactangle;
import raf.draft.dsw.models.roomelements.Toilet;

import java.awt.*;
import java.awt.geom.Point2D;

public class ToiletPainter implements MyPainter<Toilet> {
    @Override
    public void paint(Graphics2D g2d, Toilet element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        var vodokotlic = getVodokotlic(g2d, element);
        vodokotlic.draw();

        var newPos = new Point((int)element.getTopLeftCorner().getX(), (int)element.getTopLeftCorner().getY());
        newPos.y += element.getHeight() / 100 * 30;
        var keramikaHeight = element.getHeight() / 100 * 70;
        g2d.drawOval(newPos.x, newPos.y, (int) element.getWidth(), (int) keramikaHeight);
        int offset = (int) (element.getHeight() / 100 * 7);
        g2d.drawOval(newPos.x + offset, newPos.y + offset,  (int) (element.getWidth() - 2 * offset), (int) (element.getHeight() / 100 * 70 - 2 * offset));
    }

    private static RoundColoredRactangle getVodokotlic(Graphics2D g2d, Toilet toilet) {
        var kotlicWidth = (int) toilet.getWidth();
        var kotlicHeight = (int) (toilet.getHeight() / 100 * 30);

        var intPoint = new Point((int)toilet.getTopLeftCorner().getX(), (int)toilet.getTopLeftCorner().getY());

        return new RoundColoredRactangle(g2d)
                .setPoint(intPoint)
                .setWidth(kotlicWidth)
                .setHeight(kotlicHeight)
                .setBorderColor(Color.BLACK)
                .setBackgroundColor(Color.WHITE)
                .setStrokeWidth(3)
                .setArcWidth((int)((double)kotlicWidth / 100 * 15))
                .setArcHeight((int)((double)kotlicWidth / 100 * 30));
    }
}
