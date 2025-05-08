package raf.draft.dsw.models.structure.spacialmodelcollections;


import java.awt.*;
import java.util.ArrayList;

public interface IPositionalRepo<T> {
    ArrayList<T> findInArea(Point startPoint, Point endPoint);
    ArrayList<T> findOnPoint(Point point);
    public boolean overlapWithAnyButSelf(T ignore);
}
