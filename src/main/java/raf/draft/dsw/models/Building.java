package raf.draft.dsw.models;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.spacialmodelcollections.IGenericRepo;
import raf.draft.dsw.models.structure.spacialmodelcollections.SpacialModelCollection;

import java.util.ArrayList;

public class Building extends CompositeSpacialModel {
    public Building(String name, CompositeSpacialModel parent) { super(name, parent); }

    @Override
    public IGenericRepo<ISpacialModel> generateChildrenCollection() {
        return new SpacialModelCollection();
    }

    public Building(String name, CompositeSpacialModel parent, ArrayList<ISpacialModel> children) { super(name, parent, children); }
}
