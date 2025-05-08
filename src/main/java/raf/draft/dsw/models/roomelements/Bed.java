package raf.draft.dsw.models.roomelements;

import lombok.NonNull;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.geom.Point2D;

public class Bed extends RoomElement<Bed>{
    public Bed(Point2D.@NonNull Double topLeftCorner,
               double width,
               double height,
               double rotation,
               String name,
               @NonNull CompositeSpacialModel parent) {
        super(topLeftCorner, width, height, rotation, name, parent);
    }

    public Bed(PositionalElement positionalElement, SpacialModel spacialModel) {
        super(positionalElement, spacialModel);
    }

    public Bed(RoomElement<Bed> other) {
        super(other);
    }

    @Override
    public Bed copy() {
        return new Bed(this);
    }
}
