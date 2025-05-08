package raf.draft.dsw.models.structure.iterators;

import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class LevelsIterator implements Iterator<ArrayList<ISpacialModel>> {
    Stack<ISpacialModel> stack = new Stack<>();

    public LevelsIterator(ISpacialModel root) { stack.push(root); }

    @Override
    public boolean hasNext() { return !stack.isEmpty(); }

    @Override
    public ArrayList<ISpacialModel> next() {
        var initLength = stack.size();

        var models = new ArrayList<ISpacialModel>();
        while(initLength-- != 0){
            var model = stack.pop();
            models.add(model);

            if(model instanceof CompositeSpacialModel cmp)
                cmp.getChildrenCollection()
                    .getAllAsStream()
                    .forEach(stack::push);
        }

        return models;
    }
}
