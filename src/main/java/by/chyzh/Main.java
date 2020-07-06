package by.chyzh;

import by.chyzh.util.CsvUtil;
import by.chyzh.util.PropertyUtil;
import lombok.extern.log4j.Log4j;

import java.io.File;

import static by.chyzh.util.PathUtil.PATH_TO_CSV_DIRECTORY;
import static by.chyzh.util.PropertyUtil.*;
import static java.lang.String.format;

@Log4j
public class Main {

    public static void main(String[] args) {

        getConnectToProperty("src/main/resources/config.properties");

        WebCrawler crawler = new WebCrawler(PropertyUtil.getUrl(), PropertyUtil.getMaxDepth(),
                PropertyUtil.getMaxQuantityPage(), PropertyUtil.getWords());

        log.info("Starting a crawl");

        crawler.launchCrawler();

        log.info("The crawl is complete");

        Statistic statistic = new Statistic(PropertyUtil.getUrl(), crawler.getAllData(), crawler.getTotalNumbers(),
                PropertyUtil.getLimitSort(), PropertyUtil.getWords());

        CsvUtil.csvWriter(statistic.getAllData(),
                new File(format("%s%s.csv", PATH_TO_CSV_DIRECTORY, PropertyUtil.getAllStatisticFileName())));

        CsvUtil.csvWriter(statistic.getSortData(),
                new File(format("%s%s.csv", PATH_TO_CSV_DIRECTORY, PropertyUtil.getSortStatisticFileName())));

        log.info(format("Depth: %d", crawler.getDepth() - 1));
        log.info(format("Quantity page: %d", crawler.getCountPage()));

        log.info(format("Top %d links by hits:", PropertyUtil.getLimitSort()));
        statistic.getSortData().stream()
                .map(value -> String.join(",", value))
                .forEach(log::info);

        log.info("Generalized data:");
        statistic.getGeneralizedData().forEach(log::info);

    }

}
