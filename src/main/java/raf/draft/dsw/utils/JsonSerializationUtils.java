package raf.draft.dsw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;

import java.io.File;

public final class JsonSerializationUtils {
    private static final String SUCCESS_MSG = "JSON loaded/saved successfully";
    private static final String ERROR_MSG   = "Failed to load/save JSON";

    private JsonSerializationUtils() {}

    public static <T> T loadFromFile(@NonNull Class<T> targetClass, @NonNull File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            T result = objectMapper.readValue(file, targetClass);

            LogInfo info = new LogInfo(LogLevel.INFO, SUCCESS_MSG);
            ApplicationFramework.getInstance().getMessageGenerator().notify(info);

            return result;
        } catch (Exception e) {
            LogInfo info = new LogInfo(LogLevel.ERROR, ERROR_MSG + " (load). " + e.getMessage());
            ApplicationFramework.getInstance().getMessageGenerator().notify(info);
            return null;
        }
    }

    public static void saveToFile(@NonNull Object data, @NonNull File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);

            ApplicationFramework.getInstance().getMessageGenerator().notify(
                    new LogInfo(LogLevel.INFO, SUCCESS_MSG));

        } catch (Exception e) {
            ApplicationFramework.getInstance().getMessageGenerator().notify(
                    new LogInfo(LogLevel.ERROR, ERROR_MSG + " (save). " + e.getMessage()));
        }
    }
}
