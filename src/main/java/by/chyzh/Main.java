package by.chyzh;

import by.chyzh.util.CsvUtil;
import lombok.extern.log4j.Log4j;

import static by.chyzh.util.PropertyUtil.*;

@Log4j
public class Main {

    public static void main(String[] args) {

        getConnectToProperty("src/main/resources/config.properties");

        WebCrawler crawler = new WebCrawler(url, maxDepth, maxQuantityPage, words);

        log.info("Starting a crawl");

        crawler.launchCrawler();

        log.info("The crawl is complete");

        CsvUtil.csvWriter(crawler.getAllData(), nameCsvFile);

        log.info("Depth: " + (crawler.getDepth() - 1));
        log.info("Quantity page: " + crawler.getCountPage());

        log.info("Top hits: " + limitSort);
        crawler.getSortData(limitSort).stream()
                .map(value -> String.join(",", value))
                .forEach(log::info);

        log.info("Generalized data: " + limitSort);
        crawler.generalizedData().forEach(log::info);

    }

}
