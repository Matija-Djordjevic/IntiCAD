package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.roomelements.Table;

import java.awt.*;

public class TablePainter implements MyPainter<Table> {
    @Override
    public void paint(Graphics2D g2d, Table element) {
        DrawingUtils.setRotationAroundCenter(g2d, element);

        // Retrieve the top-left corner, width, and height
        var topLeftX = (int) Math.round(element.getTopLeftCorner().getX());
        var topLeftY = (int) Math.round(element.getTopLeftCorner().getY());
        var width = (int) Math.round(element.getWidth());
        var height = (int) Math.round(element.getHeight());

        // Draw the rectangle representing the element
        g2d.drawRect(topLeftX, topLeftY, width, height);

        // Calculate dot dimensions and padding
        var dotDiameter = (int) Math.round(Math.min(height / 100.0 * 10, width / 100.0 * 10));
        var padding = (int) Math.round(dotDiameter * 2);

        // Calculate positions for the four dots
        var d1x = (int) Math.round(topLeftX + padding - dotDiameter / 2.0);
        var d1y = (int) Math.round(topLeftY + padding - dotDiameter / 2.0);

        var d2x = d1x;
        var d2y = (int) Math.round(topLeftY + height - padding - dotDiameter / 2.0);

        var d3x = (int) Math.round(d1x + (d2y - d1y));
        var d3y = d1y;

        var d4x = d3x;
        var d4y = d2y;

        // Draw the four dots
        g2d.drawOval(d1x, d1y, dotDiameter, dotDiameter);
        g2d.drawOval(d2x, d2y, dotDiameter, dotDiameter);
        g2d.drawOval(d3x, d3y, dotDiameter, dotDiameter);
        g2d.drawOval(d4x, d4y, dotDiameter, dotDiameter);
    }
}
