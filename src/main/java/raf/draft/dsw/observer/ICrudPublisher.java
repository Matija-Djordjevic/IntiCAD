package raf.draft.dsw.observer;

import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

public interface ICrudPublisher {
    void addSubscriber(ICrudSubscriber subscriber);
    void removeSubscriber(ICrudSubscriber subscriber);
    default void notifyAddCrudSubscriber(ISpacialModel parent, ISpacialModel child) {}
    default void notifyDeleteCrudSubscriber(ISpacialModel child) {}
    default void notifyUpdateCrudSubscribers(ISpacialModel model) {}
}
