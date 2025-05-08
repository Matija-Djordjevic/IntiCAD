package raf.draft.dsw.models.structure.spacialmodelcollections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

// SpacialModel

public interface IGenericRepo<T> {
    @JsonIgnore
    T getById(@NonNull UUID id);

    @JsonIgnore
    Stream<T> getAllAsStream(); // returns every containing entity
    Stream<T> find(@NonNull Predicate<T> predicate); //returns every model that passes the predicate

    int count();

    boolean remove(T element);
    // removes every model that passes the predicate
    // true if any are removed!
    boolean removeByCondition(@NonNull Predicate<T> predicate);
    void removeAll(); // removes every model

    boolean add(@NonNull T entity);
    default boolean addAll(@NonNull ArrayList<T> entities) {
        // returns true if all models are added
        // nek je default, skuplja dara nego mera
        return entities.stream().filter(this::add).count() == entities.size();
    }
}