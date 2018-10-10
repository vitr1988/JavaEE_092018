package ru.otus.gwt.client.util;

public class Formatter {
    /**
     * Substitutes the indexed parameters.
     *
     * @param text the text
     * @param params the parameters
     * @return the new text
     */
    public static String substitute(String text, Object... params) {
        for (int i = 0; i < params.length; i++) {
            Object p = params[i];
            if (p == null) {
                p = "";
            }
            text = text.replaceAll("\\{" + i + "}", safeRegexReplacement(p.toString()));
        }
        return text;
    }

    /**
     * Replace any \ or $ characters in the replacement string with an escaped \\
     * or \$.
     *
     * @param replacement the regular expression replacement text
     * @return null if replacement is null or the result of escaping all \ and $
     * characters
     */
    private static String safeRegexReplacement(String replacement) {
        if (replacement == null) {
            return replacement;
        }

        return replacement.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
    }
}
