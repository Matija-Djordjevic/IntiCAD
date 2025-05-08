package raf.draft.dsw.models.roomelements;

import lombok.NonNull;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.geom.Point2D;

public class Shower extends RoomElement<Shower> {
    public Shower(Point2D.@NonNull Double topLeftCorner, double width, double height, double rotation, String name, @NonNull CompositeSpacialModel parent) {
        super(topLeftCorner, width, height, rotation, name, parent);
    }

    public Shower(PositionalElement positionalElement, SpacialModel spacialModel) {
        super(positionalElement, spacialModel);
    }

    public Shower(RoomElement<Shower> other) {
        super(other);
    }

    @Override
    public Shower copy() {
        return new Shower(this);
    }
}
