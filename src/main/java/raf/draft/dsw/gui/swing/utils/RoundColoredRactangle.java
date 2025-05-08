package raf.draft.dsw.gui.swing.utils;

import raf.draft.dsw.gui.swing.projecteditor.views.StrokeManager;

import java.awt.*;

public class RoundColoredRactangle {
    private Point point;
    private Graphics2D g2d;
    private int width;
    private int height;
    private Color borderColor;
    private Color backgroundColor;
    private int strokeWidth;
    private int arcWidth;
    private int arcHeight;

    public RoundColoredRactangle(Graphics2D g2d) {
        this.point = new Point(50, 50);
        this.g2d = g2d;
        this.width = 200;
        this.height = 100;
        this.borderColor = Color.BLACK;
        this.backgroundColor = Color.WHITE;
        this.strokeWidth = 2;
        this.arcWidth = 30;
        this.arcHeight = 30;
    }

    public void draw() {
        if (g2d == null) {
            throw new IllegalStateException("Graphics2D object is not set!");
        }

        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(point.x, point.y, width, height, arcWidth, arcHeight);

        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(borderColor);
        g2d.drawRoundRect(point.x, point.y, width, height, arcWidth, arcHeight);
    }

    public RoundColoredRactangle setPoint(Point point) {
        this.point = point;
        return this;
    }

    public RoundColoredRactangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public RoundColoredRactangle setHeight(int height) {
        this.height = height;
        return this;
    }

    public RoundColoredRactangle setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public RoundColoredRactangle setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public RoundColoredRactangle setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public RoundColoredRactangle setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
        return this;
    }

    public RoundColoredRactangle setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
        return this;
    }
}
