package raf.draft.dsw.models.structure;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.models.structure.spacialmodelcollections.IGenericRepo;

import java.util.ArrayList;

@Getter
@Setter
public abstract class CompositeSpacialModel extends SpacialModel implements ISpacialModel{
    @JsonManagedReference
    IGenericRepo<ISpacialModel> childrenCollection;

    public CompositeSpacialModel(String name,
                                 CompositeSpacialModel parent) {
        super(name, parent);
        childrenCollection = generateChildrenCollection();
    }

    public abstract IGenericRepo<ISpacialModel> generateChildrenCollection();

    public CompositeSpacialModel(String name,
                                 CompositeSpacialModel parent,
                                 ArrayList<ISpacialModel> children) {
        super(name, parent);
        this.childrenCollection.addAll(children);
    }

    // TODO ???????
    public ISpacialModel getChild(String name) { return childrenCollection.find(child -> child.getName().equals(name)).findFirst().orElse(null); }
}
