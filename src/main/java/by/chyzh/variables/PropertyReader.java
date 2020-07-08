package by.chyzh.variables;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j
public class PropertyReader {

    public static final String WORDS = "words";
    public static final String LIMIT_SORT = "limitSort";
    public static final String MAX_QUANTITY_PAGE = "maxQuantityPage";
    public static final String MAX_DEPTH = "maxDepth";
    public static final String URL = "URL";
    public static final String ALL_STATISTIC_FILE_NAME = "allStatisticFileName";
    public static final String SORT_STATISTIC_FILE_NAME = "sortStatisticFileName";

    @Getter
    private String rootUrl;
    @Getter
    private String allStatisticFileName;
    @Getter
    private String sortStatisticFileName;
    @Getter
    private int maxDepth;
    @Getter
    private int maxQuantityPage;
    @Getter
    private int limitSort;
    @Getter
    private String[] words;
    private Properties property;

    public void getConnectToProperty(String path) {

        InputStream resourceAsStream = this.getClass().getResourceAsStream(path);

        property = new Properties();

        try {
            property.load(resourceAsStream);
            maxDepth = Integer.parseInt(getProperty(MAX_DEPTH));
            maxQuantityPage = Integer.parseInt(getProperty(MAX_QUANTITY_PAGE));
            limitSort = Integer.parseInt(getProperty(LIMIT_SORT));
        } catch (NumberFormatException e) {
            log.error("Properties maxDepth and maxQuantityPage must be number");
            System.exit(0);
        } catch (FileNotFoundException e) {
            log.error("File config.properties not found");
        } catch (IOException e) {
            log.error("File config.properties was not loaded");
        }

        rootUrl = getProperty(URL);
        words = getProperty(WORDS).split(",");
        allStatisticFileName = getProperty(ALL_STATISTIC_FILE_NAME);
        sortStatisticFileName = getProperty(SORT_STATISTIC_FILE_NAME);
    }

    private String getProperty(String key) {
        if (property.containsKey(key) && !property.getProperty(key).equals("")) {
            return property.getProperty(key);
        } else {
            log.error("Property not found or null");
            System.exit(0);
        }
        return "";
    }
}
