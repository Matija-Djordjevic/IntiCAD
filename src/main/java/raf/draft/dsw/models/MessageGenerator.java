package raf.draft.dsw.models;


import raf.draft.dsw.logging.IMessageAnnouncer;
import raf.draft.dsw.logging.IMessageListener;
import raf.draft.dsw.logging.LogInfo;

import java.util.ArrayList;

public class MessageGenerator implements IMessageAnnouncer<LogInfo> {
    private final ArrayList<IMessageListener<LogInfo>> subscribers =
            new ArrayList<>();

    public MessageGenerator() {}

    @Override
    public MessageGenerator add(IMessageListener<LogInfo> listener) {
        if(listener == null || subscribers.contains(listener)) return this;

        subscribers.add(listener);
        return this;
    }

    @Override
    public MessageGenerator remove(IMessageListener<LogInfo> listener) {
        if(listener == null) return this;

        subscribers.remove(listener);
        return this;
    }

    @Override
    public void notify(LogInfo info) { subscribers.forEach(s -> s.onMessageReceived(info)); }
}
