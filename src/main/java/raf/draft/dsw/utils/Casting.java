package raf.draft.dsw.utils;

import java.util.List;
import java.util.stream.Stream;

public class Casting {
    private Casting() { }

    @SuppressWarnings("unchecked")
    public static <T> Stream<T> recast(List<?> l){
        return l.stream().map(el -> (T)el);
    }
}
