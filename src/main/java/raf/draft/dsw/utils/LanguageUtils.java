package raf.draft.dsw.utils;

import java.util.HashMap;

public final class LanguageUtils {
    private LanguageUtils() {};

    public static final HashMap<String, HashMap<String, String>> LanguagesByLanguage = new HashMap<>() {{
            // TODO zameni vrednosti sa ispravnim!
            put("en", new HashMap<>() {{
                put("en", "english");
                put("sr", "serbian");
                put("de", "deutsch");
                put("fr", "français");
            }});

            put("sr", new HashMap<>() {{
                put("en", "engleski");
                put("sr", "srpski");
                put("de", "nemački");
                put("fr", "francuski");
            }});

            put("de", new HashMap<>() {{
                put("en", "englisch");
                put("sr", "srpski");
                put("de", "deutsch");
                put("fr", "französisch");
            }});

            put("fr", new HashMap<>() {{
                put("en", "english");
                put("sr", "srpski");
                put("de", "allemand");
                put("fr", "français");
            }});
        }};
}
