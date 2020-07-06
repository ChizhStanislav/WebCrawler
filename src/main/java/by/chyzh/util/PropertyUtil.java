package by.chyzh.util;

import lombok.Getter;
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

    @Getter
    private String url;
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

        property = new Properties();
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

        if (property.containsKey(URL) && !property.getProperty(URL).equals("")) {
            url = property.getProperty(URL);
        } else {
            log.error(String.format("Property %s not found or null", URL));
            System.exit(0);
        }

        if (property.containsKey(MAX_DEPTH) && !property.getProperty(MAX_DEPTH).equals("")) {
            try {
                maxDepth = Integer.parseInt(property.getProperty(MAX_DEPTH));
            } catch (NumberFormatException e) {
                log.error(String.format("Property %s must be number", MAX_DEPTH));
                System.exit(0);
            }
        } else {
            maxDepth = 8;
        }

        if (property.containsKey(MAX_QUANTITY_PAGE) && !property.getProperty(MAX_QUANTITY_PAGE).equals("")) {
            try {
                maxQuantityPage = Integer.parseInt(property.getProperty(MAX_QUANTITY_PAGE));
            } catch (NumberFormatException e) {
                log.error(String.format("Property %s must be number", MAX_QUANTITY_PAGE));
                System.exit(0);
            }
        } else {
           maxQuantityPage = 10_000;
        }

        if (property.containsKey(LIMIT_SORT) && !property.getProperty(LIMIT_SORT).equals("")) {
            try {
                limitSort = Integer.parseInt(property.getProperty(LIMIT_SORT));
            } catch (NumberFormatException e) {
                log.error(String.format("Property %s must be number", LIMIT_SORT));
                System.exit(0);
            }
        } else {
            limitSort = 10;
        }

        if (property.containsKey(ALL_STATISTIC_FILE_NAME) && !property.getProperty(ALL_STATISTIC_FILE_NAME).equals("")) {
            allStatisticFileName = property.getProperty(ALL_STATISTIC_FILE_NAME);
        } else {
            allStatisticFileName = "allStatistic";
        }

        if (property.containsKey(SORT_STATISTIC_FILE_NAME) && !property.getProperty(SORT_STATISTIC_FILE_NAME).equals("")) {
            sortStatisticFileName = property.getProperty(SORT_STATISTIC_FILE_NAME);
        } else {
           sortStatisticFileName = "sortStatistic";
        }

        if (property.containsKey(WORDS) && !property.getProperty(WORDS).equals("")) {
            words = property.getProperty(WORDS).split(",");
        } else {
            log.error(String.format("Property %s not found or null", WORDS));
            System.exit(0);
        }
    }

}
