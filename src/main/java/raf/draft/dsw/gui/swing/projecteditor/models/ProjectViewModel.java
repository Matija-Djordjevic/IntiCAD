package raf.draft.dsw.gui.swing.projecteditor.models;

import lombok.Getter;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.observer.IPublisher;
import raf.draft.dsw.observer.ISubscriber;

import java.util.ArrayList;

public class ProjectViewModel implements IPublisher {
    final ArrayList<ISubscriber> subscribers = new ArrayList<>();

    @Getter
    RoomView lastSelectedRoomView;

    public void setLastSelectedRoomView(RoomView view){
        lastSelectedRoomView = view;
        notifySubscribers();
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        subscribers.forEach(sub -> sub.update(this));
    }
}
