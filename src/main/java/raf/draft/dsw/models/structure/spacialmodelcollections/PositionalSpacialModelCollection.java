package raf.draft.dsw.models.structure.spacialmodelcollections;

import lombok.experimental.Delegate;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.utils.GraphicsUtils;

import java.awt.*;
import java.util.ArrayList;

public class PositionalSpacialModelCollection
        implements IGenericRepo<ISpacialModel>, IPositionalRepo<RoomElement<?>> {
    @Delegate
    SpacialModelCollection spacialModelCollection;

    public PositionalSpacialModelCollection() {
        spacialModelCollection = new SpacialModelCollection();
    }

    private boolean collidesWithArea(RoomElement<?> el, Point topLeft, Point bottomRight) {
        var position = GraphicsUtils.toPoint(el.getTopLeftCorner());


        var rectLeft = position.x;
        var rectRight = position.x + el.getWidth();
        var rectTop = position.y;
        var rectBottom = position.y + el.getHeight();

        var areaLeft = topLeft.x;
        var areaRight = bottomRight.x;
        var areaTop = topLeft.y;
        var areaBottom = bottomRight.y;

        return !(rectRight <= areaLeft
                || areaRight <= rectLeft
                || rectBottom <= areaTop
                || areaBottom <= rectTop);
    }

    private boolean isPointInsideBounds(Point p, Point startPoint, Point endPoint) {
        int minX = Math.min(startPoint.x, endPoint.x);
        int maxX = Math.max(startPoint.x, endPoint.x);
        int minY = Math.min(startPoint.y, endPoint.y);
        int maxY = Math.max(startPoint.y, endPoint.y);

        return (p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY);
    }

    @Override
    public ArrayList<RoomElement<?>> findInArea(Point topLeft, Point bottomRight) {
        var res = new ArrayList<RoomElement<?>>();
        spacialModelCollection.getAllAsStream().forEach(el -> {
            if(collidesWithArea((RoomElement<?>) el, topLeft, bottomRight)) {
                res.add((RoomElement<?>) el);
            }
        });

        return res;
    }

    @Override
    public ArrayList<RoomElement<?>> findOnPoint(Point point) {
        var res = new ArrayList<RoomElement<?>>();
        spacialModelCollection.getAllAsStream().forEach(el -> {
            var position = GraphicsUtils.toPoint(((RoomElement<?>) el).getTopLeftCorner());

            if(isPointInsideBounds(point, position,
                    new Point(
                            (int)(position.x + ((RoomElement<?>) el).getWidth()),
                            (int)(position.y + ((RoomElement<?>) el).getHeight())))) {
                res.add((RoomElement<?>) el);
            }
        });

        return res;
    }

    @Override
    public boolean overlapWithAnyButSelf(RoomElement<?> ignore) {
        var position = GraphicsUtils.toPoint(ignore.getTopLeftCorner());

        var end = new Point((int)(position.x + ignore.getWidth()), (int)(position.y + ignore.getHeight()));
        return findInArea(position, end)
                .stream()
                .anyMatch(neighbour -> !neighbour.getGuid().equals(ignore.getGuid()));
    }
}