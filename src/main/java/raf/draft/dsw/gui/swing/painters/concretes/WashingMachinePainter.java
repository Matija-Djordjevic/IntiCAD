package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.roomelements.WashingMachine;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;

public class WashingMachinePainter implements MyPainter<WashingMachine> {
    @Override
    public void paint(Graphics2D g2d, WashingMachine element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        var pos = GraphicsUtils.toPoint(element.getTopLeftCorner());

        g2d.drawRect(pos.x, pos.y, (int) element.getWidth(), (int) element.getHeight());

        var minSide = Math.min(element.getWidth(), element.getHeight());

        var padding = minSide / 100 * 10;
        var bureDiameter = minSide - 2 * padding;

        var bureX = pos.x + element.getWidth() / 2 - bureDiameter / 2;
        var bureY = pos.y + element.getHeight() / 2 - bureDiameter / 2;

        g2d.drawOval((int) bureX, (int) bureY, (int) bureDiameter, (int) bureDiameter);
    }
}
