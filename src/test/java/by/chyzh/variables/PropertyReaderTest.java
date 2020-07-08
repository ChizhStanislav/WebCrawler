package by.chyzh.variables;

import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyReaderTest {

    @Test
    public void getConnectToProperty(){

        PropertyReader propertyReader = new PropertyReader();

        propertyReader.getConnectToProperty("/config-test.properties");

        String url = propertyReader.getRootUrl();
        int limitSort = propertyReader.getLimitSort();
        int maxDepth = propertyReader.getMaxDepth();
        int maxQuantityPage = propertyReader.getMaxQuantityPage();
        String[] words = propertyReader.getWords();

        assertEquals(10, limitSort);
        assertEquals(8, maxDepth);
        assertEquals(10000, maxQuantityPage);
        assertEquals("https://www.tut.by/", url);
        assertEquals(1, words.length);
    }

}