package raf.draft.dsw.models;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.spacialmodelcollections.IGenericRepo;
import raf.draft.dsw.models.structure.spacialmodelcollections.SpacialModelCollection;

import java.util.ArrayList;

@Getter @Setter
public class Project extends CompositeSpacialModel {
    ProjectInfo projectInfo;

    public Project(String name, CompositeSpacialModel parent) { super(name, parent); }

    @Override
    public IGenericRepo<ISpacialModel> generateChildrenCollection() {
        return new SpacialModelCollection();
    }

    public Project(String name, CompositeSpacialModel parent, ArrayList<ISpacialModel> children) { super(name, parent, children); }
}
