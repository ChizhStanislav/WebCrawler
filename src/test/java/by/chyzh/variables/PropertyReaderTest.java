package by.chyzh.variables;

import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyReaderTest {

    @Test
    public void getConnectToProperty(){

        PropertyReader.getConnectToProperty("src\\test\\resources\\config-test.properties");

        String url = PropertyReader.getRootUrl();
        int limitSort = PropertyReader.getLimitSort();
        int maxDepth = PropertyReader.getMaxDepth();
        int maxQuantityPage = PropertyReader.getMaxQuantityPage();
        String[] words = PropertyReader.getWords();

        assertEquals(10, limitSort);
        assertEquals(8, maxDepth);
        assertEquals(10000, maxQuantityPage);
        assertEquals("https://www.tut.by/", url);
        assertEquals(1, words.length);
    }

}