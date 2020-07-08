package by.chyzh;

import by.chyzh.crawler.WebCrawler;
import by.chyzh.statistic.Statistic;
import by.chyzh.writer.CsvWriter;
import by.chyzh.variables.PropertyReader;
import lombok.extern.log4j.Log4j;
import static java.lang.String.format;

@Log4j
public class Main {

    public static void main(String[] args) {

        PropertyReader propertyReader = new PropertyReader();
        propertyReader.getConnectToProperty("/config.properties");

        WebCrawler crawler = new WebCrawler(propertyReader.getRootUrl(), propertyReader.getMaxDepth(),
                propertyReader.getMaxQuantityPage(), propertyReader.getWords());

        log.info("Starting a crawl");

        crawler.launchCrawler();

        Statistic statistic = new Statistic(propertyReader.getRootUrl(), crawler.getAllData(), crawler.getTotalHitByOneWord(),
                propertyReader.getLimitSort(), propertyReader.getWords());

        CsvWriter.write(statistic.getAllData(), format("%s.csv", propertyReader.getAllStatisticFileName()));

        CsvWriter.write(statistic.getSortData(), format("%s.csv", propertyReader.getSortStatisticFileName()));

        log.info("Finishing a crawl");
        log.info(format("Depth: %d", crawler.getDepth() - 1));
        log.info(format("Quantity page: %d", crawler.getCountPage()));

        log.info(format("Top %d links by hits:", propertyReader.getLimitSort()));
        statistic.getSortData().stream()
                .map(value -> String.join(",", value))
                .forEach(log::info);

        log.info("Generalized data:");
        statistic.getGeneralizedData().forEach(log::info);

    }

}
