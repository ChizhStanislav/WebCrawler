package by.chyzh.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void checkCountEntry() {

        String text = "black   red_Black&&&redRed738748--black";
        String word = "black";

        Assert.assertEquals(3, StringUtil.countEntryWordToText(text, word));

    }

}