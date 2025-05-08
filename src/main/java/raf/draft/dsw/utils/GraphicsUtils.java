package raf.draft.dsw.utils;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphicsUtils {
    public static Point2D.Double toPoint2DDouble(Point p){
        return new Point2D.Double(p.x, p.y);
    }

    public static Point toPoint(Point2D.Double p){
        return new Point((int) p.x, (int) p.y);
    }

    public static Point2D.Double makePoint2DCopy (Point2D.Double other) { return new Point2D.Double(other.x, other.y); }
}
