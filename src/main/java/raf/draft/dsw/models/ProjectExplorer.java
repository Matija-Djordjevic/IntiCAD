package raf.draft.dsw.models;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.spacialmodelcollections.IGenericRepo;
import raf.draft.dsw.models.structure.spacialmodelcollections.SpacialModelCollection;

public class ProjectExplorer extends CompositeSpacialModel {
    public ProjectExplorer(String name) { super(name, null); }

    @Override
    public IGenericRepo<ISpacialModel> generateChildrenCollection() {
        return new SpacialModelCollection();
    }
}
