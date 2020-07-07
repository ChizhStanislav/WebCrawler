package by.chyzh;

import by.chyzh.crawler.WebCrawler;
import by.chyzh.statistic.Statistic;
import by.chyzh.writer.CsvWriter;
import by.chyzh.variables.PropertyReader;
import lombok.extern.log4j.Log4j;
import java.io.File;
import static by.chyzh.variables.PropertyReader.*;
import static java.lang.String.format;

@Log4j
public class Main {

    public static void main(String[] args) {

        getConnectToProperty("src\\main\\resources\\config.properties");

        WebCrawler crawler = new WebCrawler(PropertyReader.getRootUrl(), PropertyReader.getMaxDepth(),
                PropertyReader.getMaxQuantityPage(), PropertyReader.getWords());

        log.info("Starting a crawl");

        crawler.launchCrawler();

        Statistic statistic = new Statistic(PropertyReader.getRootUrl(), crawler.getAllData(), crawler.getTotalHitByOneWord(),
                PropertyReader.getLimitSort(), PropertyReader.getWords());

        CsvWriter.write(statistic.getAllData(),
                new File(format("%s%s.csv", PATH_TO_CSV_DIRECTORY, PropertyReader.getAllStatisticFileName())));

        CsvWriter.write(statistic.getSortData(),
                new File(format("%s%s.csv", PATH_TO_CSV_DIRECTORY, PropertyReader.getSortStatisticFileName())));

        log.info("Finishing a crawl");
        log.info(format("Depth: %d", crawler.getDepth() - 1));
        log.info(format("Quantity page: %d", crawler.getCountPage()));

        log.info(format("Top %d links by hits:", PropertyReader.getLimitSort()));
        statistic.getSortData().stream()
                .map(value -> String.join(",", value))
                .forEach(log::info);

        log.info("Generalized data:");
        statistic.getGeneralizedData().forEach(log::info);

    }

}
