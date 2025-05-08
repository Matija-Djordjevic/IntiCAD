package raf.draft.dsw.gui.swing.painters.concretes;

import lombok.NoArgsConstructor;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.roomelements.Door;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.awt.*;

@NoArgsConstructor
public class DoorPainter implements MyPainter<Door>{
    @Override
    public void paint(Graphics2D g2d, Door element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        var doorWidth = (int) element.getWidth();
        var doorHeight = (int) element.getWidth() / 100 * 5;
        var doorX = (int) element.getTopLeftCorner().x;
        var doorY = (int) element.getTopLeftCorner().y;

        float[] outlineDashPattern = {4, 3};
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, outlineDashPattern, 0));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(doorX, doorY, (int) element.getWidth(), (int) element.getWidth());

        g2d.setColor(Color.BLACK);
        g2d.fillRect(doorX, doorY, doorWidth, doorHeight);

        float[] arcDashPattern = {7, 3};
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, arcDashPattern, 0));

        int arcDiameter = doorWidth * 2;
        int arcX = doorX - doorWidth;
        int arcY = doorY - doorWidth;

        g2d.setColor(Color.BLACK);
        g2d.drawArc(arcX, arcY, arcDiameter, arcDiameter, 270, 90);
    }
}
