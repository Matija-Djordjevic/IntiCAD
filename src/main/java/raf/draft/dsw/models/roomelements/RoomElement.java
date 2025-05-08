// RoomElement.java
package raf.draft.dsw.models.roomelements;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Delegate;
import raf.draft.dsw.models.positional.IPositionalElement;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.prototype.IPrototype;

import java.awt.geom.Point2D;

@Getter
@Setter
public abstract class RoomElement<T extends RoomElement<T>>
        implements ISpacialModel, IPrototype<T>, IPositionalElement {

    @Delegate
    private final IPositionalElement positionalElement;

    @Delegate
    private final ISpacialModel spacialModel;

    public RoomElement(@NonNull Point2D.Double topLeftCorner,
                       double width,
                       double height,
                       double rotation,
                       String name,
                       @NonNull CompositeSpacialModel parent) {
        this.spacialModel = new SpacialModel(name, parent);
        this.positionalElement = new PositionalElement(topLeftCorner, width, height, rotation);
    }

    public RoomElement(PositionalElement positionalElement, SpacialModel spacialModel) {
        this.positionalElement = positionalElement;
        this.spacialModel = spacialModel;
    }

    public RoomElement(RoomElement<T> other) {
        this.positionalElement = new PositionalElement(other.getPositionalElement());
        this.spacialModel = new SpacialModel(other.getSpacialModel());
    }

    @Override
    public abstract T copy();
}
