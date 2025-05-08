package raf.draft.dsw.models.roomelements;


import lombok.NonNull;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.awt.geom.Point2D;

public class Table extends RoomElement<Table>{

    public Table(Point2D.@NonNull Double topLeftCorner, double width, double height, double rotation, String name, @NonNull CompositeSpacialModel parent) {
        super(topLeftCorner, width, height, rotation, name, parent);
    }

    public Table(PositionalElement positionalElement, SpacialModel spacialModel) {
        super(positionalElement, spacialModel);
    }

    public Table(RoomElement<Table> other) {
        super(other);
    }

    @Override
    public Table copy() {
        return new Table(this);
    }
}
