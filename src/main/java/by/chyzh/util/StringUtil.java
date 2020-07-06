package by.chyzh.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public int countEntry(String str, String target) {
        return (str.length() - str.toUpperCase().replace(target.toUpperCase(), "").length()) / target.length();
    }
}
