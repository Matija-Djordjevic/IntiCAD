package raf.draft.dsw.utils;

import lombok.NonNull;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.iterators.Aterosklerozator;
import raf.draft.dsw.models.structure.iterators.DepthFirstTreeIter;
import raf.draft.dsw.models.structure.iterators.ParentsIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class HierarchyFetchingUtils {
    private HierarchyFetchingUtils() {}
    
    public static CompositeSpacialModel getFirstAncestor(@NonNull Class<? extends CompositeSpacialModel> ancestorClazz, @NonNull SpacialModel descendent) {
        var iter = new ParentsIterator(descendent);

        while(iter.hasNext()) {
            var ancestor = iter.next();
            if(ancestor.getClass() == ancestorClazz) return ancestor;
        }

        return null;
    }

    public static CompositeSpacialModel getLastAncestor(@NonNull Class<? extends CompositeSpacialModel> ancestorClazz, @NonNull SpacialModel descendent) {
        var iter = new ParentsIterator(descendent);
        CompositeSpacialModel lastAncestor = null;

        while(iter.hasNext()) {
            var ancestor = iter.next();
            if(ancestor.getClass() == ancestorClazz) lastAncestor = ancestor;
        }

        return lastAncestor;
    }

    @Deprecated
    /*
     * We now use getDescendents()
     * */
    public static  ArrayList<ISpacialModel> getDescendentsDeprecated(@NonNull Class<? extends ISpacialModel> descendentClazz, @NonNull CompositeSpacialModel ancestor) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DepthFirstTreeIter(ancestor), Spliterator.ORDERED), false)
                        .filter(desc -> desc.getClass() == descendentClazz)
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    public static  ArrayList<ISpacialModel> getDescendents(@NonNull Class<? extends ISpacialModel> descendentClazz, @NonNull CompositeSpacialModel ancestor) {
        var list = new ArrayList<ISpacialModel>();

        var descendents = new DepthFirstTreeIter(ancestor);
        descendents.forEachRemaining(desc -> {
            if (desc.getClass() == descendentClazz) list.add(desc);
        });

        return list;
    }

    /**
     * Retrieves all descendants (of target type) of the ancestor (of target type) that are under one or more sub ancestors (of target type). They are order by the sub ancestors that hold them
     * note: any descendants that don't have sub ancestor as their ancestor will be ignored
     * note: if sub ancestors has one or more ancestors that are the same type, the highest sub ancestor will be assigned to all the descendents
     * ps: amin
     * */
    // TODO treba da ide u svoju klasu... ali NEĆE
    public static  HashMap<ISpacialModel, ArrayList<ISpacialModel>> getDescendentsByAncestor(@NonNull CompositeSpacialModel ancestor,
                                                                                           @NonNull Class<? extends CompositeSpacialModel> subAncestorClazz,
                                                                                           @NonNull Class<? extends ISpacialModel> descendentClazz) {
        var map = new HashMap<ISpacialModel, ArrayList<ISpacialModel>>();

        var subAncestors = new ArrayList<ISpacialModel>();

        var iter = new Aterosklerozator(ancestor, subAncestorClazz);
        iter.forEachRemaining(subAncestors::add);

        for(ISpacialModel subAncestor: subAncestors) {
            if (subAncestor.getClass()== subAncestorClazz){
                //TO DO PREKLAPANJE TIPOVA
                map.put(subAncestor, getDescendents(descendentClazz, (CompositeSpacialModel) subAncestor));
            }
        }
        return map;
    }
}
