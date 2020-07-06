package by.chyzh.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public static int countEntry(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }
}
