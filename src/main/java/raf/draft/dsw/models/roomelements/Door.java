package raf.draft.dsw.models.roomelements;

import lombok.NonNull;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.geom.Point2D;

public class Door extends RoomElement<Door>{
    public Door(Point2D.@NonNull Double topLeftCorner, double width, double height, double rotation, String name, @NonNull CompositeSpacialModel parent) {
        super(topLeftCorner, width, height, rotation, name, parent);
    }

    public Door(PositionalElement positionalElement, SpacialModel spacialModel) {
        super(positionalElement, spacialModel);
    }

    public Door(RoomElement<Door> other) {
        super(other);
    }

    @Override
    public Door copy() {
        return new Door(this);
    }
}
