package by.chyzh.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyUtilTest {

    @Test
    public void getConnectToProperty(){

        PropertyUtil.getConnectToProperty("src/test/resources/config.properties");

        String url = PropertyUtil.getUrl();
        int limitSort = PropertyUtil.getLimitSort();
        int maxDepth = PropertyUtil.getMaxDepth();
        int maxQuantityPage = PropertyUtil.getMaxQuantityPage();
        String[] words = PropertyUtil.getWords();

        assertEquals(10, limitSort);
        assertEquals(8, maxDepth);
        assertEquals(10000, maxQuantityPage);
        assertEquals("https://www.tut.by/", url);
        assertEquals(1, words.length);
    }

}