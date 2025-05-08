package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.gui.swing.utils.RoundColoredRactangle;
import raf.draft.dsw.iterators.ColorGenerator;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.roomelements.Shower;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;

public class ShowerPainter implements MyPainter<Shower> {
    @Override
    public void paint(Graphics2D g2d, Shower element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        var pos = GraphicsUtils.toPoint(element.getTopLeftCorner());

        g2d.drawRect(pos.x, pos.y, (int) element.getWidth(), (int) element.getHeight());

        var offset = Math.min(
                element.getWidth() / 100 * 10,
                element.getHeight() / 100 * 10);

        var unutrasnjiDeoKateIdk = getUnutrasnjiDeoKadeIdk(g2d, (Shower) element, pos, (int) offset);
        unutrasnjiDeoKateIdk.draw();
    }

    private static RoundColoredRactangle getUnutrasnjiDeoKadeIdk(Graphics2D g2d, Shower element, Point pos, int offset) {
        return new RoundColoredRactangle(g2d)
                .setPoint(new Point(pos.x + offset, pos.y + offset))
                .setWidth((int) element.getWidth() - 2 * offset)
                .setHeight((int) element.getHeight() - 2 * offset)
                .setArcHeight((int) element.getWidth() / 100 * 10)
                .setArcWidth((int) element.getWidth() / 100 * 10)
                .setBackgroundColor(ColorGenerator.ZimaBlue());
    }
}
