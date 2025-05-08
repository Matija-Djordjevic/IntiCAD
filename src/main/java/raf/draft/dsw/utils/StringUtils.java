package raf.draft.dsw.utils;

import lombok.NonNull;

public final class StringUtils {
    private StringUtils() {}

    public static boolean isNullOrEmpty(String str) { return str == null || str.isEmpty(); }
    public static void requireNonNullAndNotEmpty(String str, String message) { if(isNullOrEmpty(str)) throw new IllegalArgumentException(message); }
}
