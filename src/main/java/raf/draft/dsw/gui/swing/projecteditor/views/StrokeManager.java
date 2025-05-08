package raf.draft.dsw.gui.swing.projecteditor.views;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class StrokeManager {
    @Getter @Setter
    double zoom = 0.0;
    double minZoom = 0.3;
    double maxZoom = 4;

    public Point requestNewResizedPoint(final Point point){
        return new Point((int)(point.x / zoom), (int)(point.y / zoom));
    }
}
