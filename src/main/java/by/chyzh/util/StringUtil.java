package by.chyzh.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public int countEntryWordToText(String text, String word) {
        return (text.length() - text.toUpperCase().replace(word.toUpperCase(), "").length()) / word.length();
    }
}
