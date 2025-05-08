package raf.draft.dsw.logging;

public interface IMessageListener<T> {
    void onMessageReceived(T message);
}
