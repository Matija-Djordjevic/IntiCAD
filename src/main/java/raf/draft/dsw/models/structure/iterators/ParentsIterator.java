package raf.draft.dsw.models.structure.iterators;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.util.Iterator;

public class ParentsIterator implements Iterator<CompositeSpacialModel> {
    CompositeSpacialModel parent;

    public ParentsIterator(SpacialModel child) {
        if(child.getParent() instanceof CompositeSpacialModel cmp) parent = cmp;
    }

    @Override
    public boolean hasNext() { return parent.getParent() == null; }

    @Override
    public CompositeSpacialModel next() {
        var curParent = parent;
        parent = parent.getParent();
        return curParent;
    }
}
