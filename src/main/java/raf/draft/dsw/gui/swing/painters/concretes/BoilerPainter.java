package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.roomelements.Boiler;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.awt.*;

public class BoilerPainter implements MyPainter<Boiler> {
    @Override
    public void paint(Graphics2D g2d, Boiler element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        g2d.drawOval((int) element.getTopLeftCorner().x,
                (int) element.getTopLeftCorner().y,
                (int) element.getWidth(),
                (int) element.getHeight());

        int x = (int) (element.getTopLeftCorner().x + element.getWidth() / 2);
        int y = (int) (element.getTopLeftCorner().y + element.getHeight() / 2);
        int halfSize = (int) Math.min(element.getHeight() / 10, element.getWidth() / 10);

        g2d.drawLine(x - halfSize, y - halfSize, x + halfSize, y + halfSize); // Top-left to bottom-right
        g2d.drawLine(x - halfSize, y + halfSize, x + halfSize, y - halfSize); // Bottom-left to top-right

    }
}
