package raf.draft.dsw.gui.swing.utils;

import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;
import java.util.ArrayList;

public final class DrawingUtils {
    private DrawingUtils() {
    }
    public static void setRotationAroundCenter(Graphics2D g2d, RoomElement<?> element){
        var pos = GraphicsUtils.toPoint(element.getTopLeftCorner());
        g2d.rotate(Math.toRadians(element.getRotation()),
                pos.getX() + element.getWidth() / 2,
                pos.getY() + element.getHeight() / 2);
    }

    public static void drawStringRoundPoint(Point point,
                                            String txt,
                                            Graphics2D g2d){
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        int txtWidth = metrics.stringWidth(txt);
        int txtHeight = metrics.getHeight();

        int newX = point.x - txtWidth / 2;
        int newY = point.y + txtHeight / 2 - metrics.getDescent();

        g2d.drawString(txt, newX, newY);
    }

    public static void drawStringsAroundPoint(Point point,
                                             ArrayList<String> strings,
                                             Graphics2D g2d){
        var strCount = strings.size();
        var font = g2d.getFont();
        var fontSize = font.getSize();

        var offset = strCount % 2 == 0
                ? ((strCount / 2) - 1) * fontSize + (fontSize / 2)
                : strCount / 2 * fontSize;

        point = new Point(point.x, point.y - offset);

        for(int i = 0; i < strCount; i++){
            drawStringRoundPoint(point, strings.get(i), g2d);
            point.y += fontSize;
        }
    }

}