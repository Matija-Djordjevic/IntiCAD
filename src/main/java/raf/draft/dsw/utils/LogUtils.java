package raf.draft.dsw.utils;

import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;

public final class LogUtils {
    private LogUtils() {}
    /**
     * We now use method .toString()
     */
    @Deprecated
    private static String logLevelToStr(LogLevel l){
        return switch(l) {
            case LogLevel.ERROR -> "GRESKA";
            case LogLevel.WARNING -> "UPOZORENJE";
            case LogLevel.INFO -> "OBAVESTENJE";
            default -> throw new IllegalArgumentException("Unsupported log level: " + l);
        };
    }

    public static String getLogMsg(LogInfo info) {
        return String.format("[%s] [%s] %s",
                info.level().toString(),
                DateUtils.getDateForLogging(),
                info.message());
    }
}
