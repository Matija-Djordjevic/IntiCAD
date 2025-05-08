package raf.draft.dsw.models.structure.iterators;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.util.ArrayList;
import java.util.function.Function;

public class BreathFirstTreeIter extends TreeIter {
    public BreathFirstTreeIter(CompositeSpacialModel root, Function<CompositeSpacialModel, ArrayList<ISpacialModel>> orderStrategy) { super(root, orderStrategy); }
    public BreathFirstTreeIter(CompositeSpacialModel root) { super(root); }

    public ISpacialModel next() {
        var current = deque.removeFirst();

        getChildren(current).forEach(child -> deque.addLast(child));

        return current;
    }
}
