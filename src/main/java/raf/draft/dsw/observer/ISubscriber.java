package raf.draft.dsw.observer;

import javax.management.Notification;

public interface ISubscriber {
    void update(Object notification);
}
