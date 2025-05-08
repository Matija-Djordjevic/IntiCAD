package raf.draft.dsw.compatibility;

import lombok.NonNull;
import raf.draft.dsw.core.Tuple;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.CompositeSpacialModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class SpacialModelsCompatibilityChecker {
    protected final String nullChildOrParentMsg = "Parent and child must note be null!";
    protected void handleNullParentOrChild() { throw new IllegalArgumentException(nullChildOrParentMsg); }

    public abstract ArrayList<Tuple<Class<? extends CompositeSpacialModel>, Class<? extends ISpacialModel>>> getPossibleParentChildPairsTypes();
    public abstract ArrayList<Class<? extends ISpacialModel>> getNonDeletableConcreteTypes();
    public abstract ArrayList<Class<? extends ISpacialModel>> getAllConcreteTypes();

    // TODO hvataj nulls itd
    public boolean typeCanBeDeleted(ISpacialModel node) { return typeCanBeDeleted(node.getClass()); }

    public boolean typeCanBeDeleted(Class<? extends ISpacialModel> clazz) { return !getNonDeletableConcreteTypes().contains(clazz); }

    public boolean canContainTheChild(@NonNull Class<? extends CompositeSpacialModel> parentClazz, @NonNull  Class<? extends ISpacialModel> childClazz) {
        return getPossibleParentChildPairsTypes().stream()
                .filter(tuple -> tuple.first.equals(parentClazz))
                .anyMatch(tuple -> tuple.second.equals(childClazz));
    }

    private static boolean canHaveChildren(Class<? extends ISpacialModel> child) {
        return CompositeSpacialModel.class.isAssignableFrom(child);
    }

    public ArrayList<Class<? extends ISpacialModel>> getAllPossibleChildrenTypes(@NonNull CompositeSpacialModel parentNode) { return getAllPossibleChildrenTypes(parentNode.getClass()); }

    public ArrayList<Class<? extends ISpacialModel>> getAllPossibleChildrenTypes(@NonNull Class<? extends CompositeSpacialModel> ofParentClazz) {
        return getPossibleParentChildPairsTypes()
                .stream()
                .filter(parentChild -> parentChild.first.equals(ofParentClazz))
                .map(parentChild -> parentChild.second)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
