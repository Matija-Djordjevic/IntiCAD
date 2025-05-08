package raf.draft.dsw.models.positional;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

@Getter
@Setter
public class PositionalElement implements IPositionalElement {
    private Point2D.Double topLeftCorner;
    private double width;
    private double height;
    private double rotation; // in degrees

    private Path2D transformedBounds;
    private boolean isCacheValid = false;

    public PositionalElement(@NonNull Point2D.Double topLeftCorner,
                             double width,
                             double height,
                             double rotation) {
        if (width <= 0 || height <= 0)  throw new IllegalArgumentException("Width and height must be positive.");

        this.topLeftCorner = topLeftCorner;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    @Override
    public Path2D getTransformedBounds() {
        if (!isCacheValid) {
            transformedBounds = computeTransformedBoundsPath();
            isCacheValid = true;
        }
        return transformedBounds;
    }

    private Path2D computeTransformedBoundsPath() {
        var transform = new AffineTransform();
        var centerX = topLeftCorner.x + width / 2.0;
        var centerY = topLeftCorner.y + height / 2.0;
        transform.rotate(Math.toRadians(rotation), centerX, centerY);

        var path = new Path2D.Double();
        path.moveTo(topLeftCorner.x, topLeftCorner.y);
        path.lineTo(topLeftCorner.x + width, topLeftCorner.y);
        path.lineTo(topLeftCorner.x + width, topLeftCorner.y + height);
        path.lineTo(topLeftCorner.x, topLeftCorner.y + height);
        path.closePath();

        return (Path2D) transform.createTransformedShape(path);
    }

    public void invalidateCache() {
        isCacheValid = false;
    }

    public PositionalElement(IPositionalElement other) {
        this.topLeftCorner = new Point2D.Double(other.getTopLeftCorner().x, other.getTopLeftCorner().y);
        this.width = other.getWidth();
        this.height = other.getHeight();
        this.rotation = other.getRotation();
    }
}

