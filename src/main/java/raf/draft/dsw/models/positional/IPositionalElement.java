package raf.draft.dsw.models.positional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public interface IPositionalElement {
    Point2D.Double getTopLeftCorner();
    void setTopLeftCorner(Point2D.Double topLeftCorner);

    double getWidth();
    void setWidth(double width);
    double getHeight();
    void setHeight(double height);

    double getRotation();
    void setRotation(double rotation);

    @JsonIgnore
    Path2D getTransformedBounds();
}

