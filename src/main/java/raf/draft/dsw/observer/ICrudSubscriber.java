package raf.draft.dsw.observer;

import raf.draft.dsw.core.Tuple;
import raf.draft.dsw.models.structure.ISpacialModel;

import java.util.List;

public interface ICrudSubscriber {
    default void updateAddCrud(ISpacialModel parent, ISpacialModel child) {}
    default void updateDeleteCrud(ISpacialModel child) {}
    default void updateUpdateCrud(ISpacialModel model) {}
}

