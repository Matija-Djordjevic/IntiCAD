package raf.draft.dsw.models.structure.iterators;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.util.ArrayList;
import java.util.function.Function;

public class DepthFirstTreeIter extends TreeIter {

    public DepthFirstTreeIter(CompositeSpacialModel root, Function<CompositeSpacialModel, ArrayList<ISpacialModel>> orderStrategy) { super(root, orderStrategy); }
    public DepthFirstTreeIter(CompositeSpacialModel root) { super(root); }

    public ISpacialModel next() {
        var current = deque.removeLast();

        getChildren(current).forEach(child -> deque.addLast(child));

        return current;
    }
}
