package raf.draft.dsw.models.structure.spacialmodelcollections;

import lombok.NonNull;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Deprecated
public class SpacialModelCollectionDepreciated {
    //TODO child should become model in every method
    ArrayList<SpacialModel> collection = new ArrayList<>();

    private final String nullChildrenArgsMsg = "Use of null value for child";

    private void handleIllegalChildArgs() { throw new IllegalArgumentException(nullChildrenArgsMsg); }

    private void handleAlreadyContained(SpacialModel node) {
        final String msg = String.format("Child with the name '%s' already exists!", node.getName());
        throw new IllegalArgumentException(msg);
    }

    public void forEach(Consumer<SpacialModel> action){ collection.forEach(action); }

    public ArrayList<SpacialModel> getAll() { return new ArrayList<>(collection); }

    public ArrayList<SpacialModel> getAllReversed() { return new ArrayList<>(collection.reversed()); }

    public void clear() { collection.clear(); }

    public boolean remove(SpacialModel child) { return collection.remove(child); }

    public SpacialModel getAt(final int index) { return collection.get(index); }

    public int size() { return collection.size(); }

    public int indexOf(@NonNull SpacialModel child) { return collection.indexOf(child); }

    private boolean doAdd(ArrayList<SpacialModel> children) {
        // TODO This should be attomic? If one child fails to add,
        // TODO remove all the kids that were suc added, on failure, fatal error?
        var count = new AtomicInteger();
        children.forEach(child -> count.addAndGet(add(child) ? 1 : 0) );

        return count.get() == children.size();
    }

    public boolean add(@NonNull SpacialModel child) {
        if (child == null) handleIllegalChildArgs();
        if (nameTaken(child.getName())) handleAlreadyContained(child);

        return collection.add(child);
    }

    public boolean nameTaken(@NonNull String name) {
        StringUtils.requireNonNullAndNotEmpty(name, "name can't be null or empty string!");
        return collection.stream().anyMatch(child -> child.getName().equals(name));
    }


    public boolean add(@NonNull SpacialModel... children){
        Arrays.stream(children).anyMatch(Objects::isNull);
        return doAdd((ArrayList<SpacialModel>) Arrays.stream(children).collect(Collectors.toList()));
    }

    public boolean add(@NonNull ArrayList<SpacialModel> children){
        if(children == null || children.stream().anyMatch(Objects::isNull)) handleIllegalChildArgs();
        return doAdd(children);
    }

    public boolean contains(SpacialModel node) { return collection.contains(node); }

    public boolean contains(@NonNull final String name) { return collection.stream().anyMatch(n -> n.getName().equals(name)); }


    public SpacialModel find(SpacialModel targetChild) { return find(targetChild.getName()); }

    public SpacialModel find(@NonNull String targetName) {
        Objects.requireNonNull(targetName, "Argument targetName can't be null");

        return collection
                .stream()
                .filter(c -> targetName.equals(c.getName()))
                .findFirst()
                .orElse(null);
    }
}
