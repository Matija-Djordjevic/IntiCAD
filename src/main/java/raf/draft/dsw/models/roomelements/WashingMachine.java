package raf.draft.dsw.models.roomelements;

import lombok.NonNull;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.geom.Point2D;

public class WashingMachine extends RoomElement<WashingMachine>{

    public WashingMachine(Point2D.@NonNull Double topLeftCorner, double width, double height, double rotation, String name, @NonNull CompositeSpacialModel parent) {
        super(topLeftCorner, width, height, rotation, name, parent);
    }

    public WashingMachine(PositionalElement positionalElement, SpacialModel spacialModel) {
        super(positionalElement, spacialModel);
    }

    public WashingMachine(RoomElement<WashingMachine> other) {
        super(other);
    }

    @Override
    public WashingMachine copy() {
        return new WashingMachine(this);
    }
}
