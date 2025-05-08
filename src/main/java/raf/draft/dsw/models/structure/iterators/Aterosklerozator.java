package raf.draft.dsw.models.structure.iterators;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

import java.util.ArrayList;
import java.util.function.Function;

public class Aterosklerozator extends TreeIter {
    Class<? extends ISpacialModel> plakClazz;

    public Aterosklerozator(CompositeSpacialModel root, Class<? extends ISpacialModel> plakClazz, Function<CompositeSpacialModel, ArrayList<ISpacialModel>> orderStrategy) { super(root, orderStrategy);  this.plakClazz = plakClazz; }
    public Aterosklerozator(CompositeSpacialModel root, Class<? extends ISpacialModel> plakClazz) { super(root); this.plakClazz = plakClazz; }

    public ISpacialModel next() {
        var current = deque.removeFirst();

        if(current.getClass() != plakClazz) getChildren(current).forEach(child -> deque.addLast(child));

        return current;
    }
}
