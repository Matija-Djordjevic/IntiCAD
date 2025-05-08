package raf.draft.dsw.core;

public class Tuple<TFirst, TSecond> {
    public final TFirst first;
    public final TSecond second;


    public Tuple(TFirst first, TSecond second) {
        this.first = first;
        this.second = second;
    }
}