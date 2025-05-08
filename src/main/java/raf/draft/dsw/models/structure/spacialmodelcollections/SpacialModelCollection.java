package raf.draft.dsw.models.structure.spacialmodelcollections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;
import raf.draft.dsw.models.structure.ISpacialModel;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SpacialModelCollection implements IGenericRepo<ISpacialModel>{
    final HashSet<ISpacialModel> collection = new HashSet<>();

    @Override
    public ISpacialModel getById(@NonNull UUID id) {
        return collection
                .stream()
                .filter(model -> model.getGuid().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Stream<ISpacialModel> getAllAsStream() { return collection.stream(); }

    public List<ISpacialModel> getCollection() {
        return new ArrayList<>(collection);
    }

    @Override
    public Stream<ISpacialModel> find(@NonNull Predicate<ISpacialModel> predicate) {
        return collection
                .stream()
                .filter(predicate);
    }

    @Override
    public int count() { return collection.size(); }

    @Override
    public boolean remove(ISpacialModel element) {
        return collection.remove(element);
    }

    @Override
    public boolean removeByCondition(@NonNull Predicate<ISpacialModel> predicate) { return collection.removeIf(predicate); }

    @Override
    public void removeAll() { collection.clear(); }

    @Override
    public boolean add(@NonNull ISpacialModel entity) {
        // TODO change to hash map
        return collection.stream().noneMatch(el -> el.getName().equals(entity.getName())) && collection.add(entity);
    }
}
