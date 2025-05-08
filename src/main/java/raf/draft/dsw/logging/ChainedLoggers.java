package raf.draft.dsw.logging;

import java.util.ArrayList;
import java.util.Arrays;

public class ChainedLoggers implements IMessageListener<LogInfo> {
    public ArrayList<IMessageListener<LogInfo>> loggers =
            new ArrayList<>();

    private ChainedLoggers(){}
    public ChainedLoggers(ArrayList<IMessageListener<LogInfo>> loggers) {
        if (loggers == null) throwNullArgError();

        this.loggers = loggers;
    }
    private final String nullArgMsg = "Can't pass null to chained loggers";
    private void throwNullArgError() { throw new NullPointerException(nullArgMsg); }

    public ChainedLoggers(IMessageListener<LogInfo> logger) {
        if(logger == null) throwNullArgError();

        this.loggers.add(logger);
    }
    @SafeVarargs
    public ChainedLoggers(IMessageListener<LogInfo>... loggers) {
        if(loggers == null) throwNullArgError();

        this.loggers.addAll(Arrays.asList(loggers));
    }

    @Override
    public void onMessageReceived(LogInfo message) { loggers.forEach(l -> l.onMessageReceived(message)); }
}
