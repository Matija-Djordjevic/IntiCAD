package raf.draft.dsw.models.structure.iterators;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class TreeIter implements Iterator<ISpacialModel> {
    protected Deque<ISpacialModel> deque = new ArrayDeque<>();
    protected Function<CompositeSpacialModel, ArrayList<ISpacialModel>> orderStrategy = null;

    public TreeIter(CompositeSpacialModel root, Function<CompositeSpacialModel, ArrayList<ISpacialModel>> orderStrategy) {
        deque.addLast(root);
        this.orderStrategy = orderStrategy;
    }

    public TreeIter(CompositeSpacialModel root) {
        deque.addLast(root);
    }

    // TODO ili eventualno da dobijemo drvo
    public TreeIter() {}

    @Override
    public boolean hasNext() { return !deque.isEmpty(); }

    protected ArrayList<ISpacialModel> getChildren(ISpacialModel model) {
        return switch (model) {
            case CompositeSpacialModel cmp when orderStrategy == null ->
                    cmp.getChildrenCollection()
                            .getAllAsStream()
                            .collect(Collectors.toCollection(ArrayList::new));
            case CompositeSpacialModel cmp -> orderStrategy.apply(cmp);
            default -> new ArrayList<>();
        };
    }
}
