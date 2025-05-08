package raf.draft.dsw.gui.swing.painters;

import java.awt.*;

public interface MyPainter<T> {
    void paint(Graphics2D g2d, T element);
}