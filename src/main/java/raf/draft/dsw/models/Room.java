package raf.draft.dsw.models;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.spacialmodelcollections.IGenericRepo;
import raf.draft.dsw.models.structure.spacialmodelcollections.PositionalSpacialModelCollection;

@Getter @Setter
public class Room extends CompositeSpacialModel {
    int widthCm = 400, heightCm = 400;

    public Room(String name, CompositeSpacialModel parent) { super(name, parent); }

    @Override
    public IGenericRepo<ISpacialModel> generateChildrenCollection() {
        return new PositionalSpacialModelCollection();
    }
}
