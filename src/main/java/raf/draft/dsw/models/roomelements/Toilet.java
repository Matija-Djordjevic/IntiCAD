package raf.draft.dsw.models.roomelements;

import lombok.NonNull;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.geom.Point2D;
import java.util.UUID;

public class Toilet extends RoomElement<Toilet> {

    public Toilet(Point2D.@NonNull Double topLeftCorner, double width, double height, double rotation, String name, @NonNull CompositeSpacialModel parent) {
        super(topLeftCorner, width, height, rotation, name, parent);
    }

    public Toilet(PositionalElement positionalElement, SpacialModel spacialModel) {
        super(positionalElement, spacialModel);
    }

    public Toilet(RoomElement<Toilet> other) {
        super(other);
    }

    @Override
    public Toilet copy() {
        return new Toilet(this);
    }
}
