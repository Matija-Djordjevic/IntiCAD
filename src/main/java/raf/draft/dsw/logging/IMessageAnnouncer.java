package raf.draft.dsw.logging;

public interface IMessageAnnouncer<T> {
    IMessageAnnouncer<T> add(IMessageListener<T> listener);
    IMessageAnnouncer<T> remove(IMessageListener<T> listener);
    void notify(T info);
}
