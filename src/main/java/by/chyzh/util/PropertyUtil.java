package by.chyzh.util;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static by.chyzh.util.PathUtil.*;

@Log4j
@UtilityClass
public class PropertyUtil {

    public String url;
    public int maxDepth;
    public int maxQuantityPage;
    public int limitSort;
    public String[] words;

    public void getConnectToProperty(String path){

        Properties property = new Properties();
        FileInputStream file = null;

        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            log.error("File config.properties not found");
        }

        try {
            property.load(file);
        } catch (IOException e) {
            log.error("File config.properties was not loaded");
        }

        url = property.getProperty(URL);
        maxDepth = Integer.parseInt(property.getProperty(MAX_DEPTH));
        maxQuantityPage = Integer.parseInt(property.getProperty(MAX_QUANTITY_PAGE));
        limitSort = Integer.parseInt(property.getProperty(LIMIT_SORT));
        words = property.getProperty(WORDS).split(",");
    }
}
