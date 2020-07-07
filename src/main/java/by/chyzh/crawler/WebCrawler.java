package by.chyzh.crawler;

import by.chyzh.util.JsoupUtil;
import by.chyzh.util.StringUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

@Log4j
public class WebCrawler {

    private final HashSet<String> uniqueLinks;
    private final String[] words;
    private List<String> queue;
    @Getter
    private final int[] totalHitByOneWord;
    @Getter
    private final LinkedHashMap<String, int[]> allData;
    private final int maxDepth;
    @Getter
    private int depth = 1;
    private final int maxQuantityPage;
    @Getter
    private int countPage;

    public WebCrawler(String url, int maxDepth, int maxPage, String[] words) {
        this.uniqueLinks = new HashSet<>();
        this.totalHitByOneWord = new int[words.length + 1];
        this.queue = Collections.singletonList(url);
        this.allData = new LinkedHashMap<>();
        this.maxDepth = maxDepth;
        this.maxQuantityPage = maxPage;
        this.words = words;
    }

    public void launchCrawler() {

        List<String> linksOfNewLevel = new ArrayList<>();

        queue.forEach(link -> {

            Document document;

            if (countPage < maxQuantityPage) {

                try {
                    document = JsoupUtil.getDocument(link);

                    Elements linksOnPage = document.select("a[href]");

                    if (Objects.nonNull(document.body())) {
                        String text = document.body().text();

                        int totalSumHitByAllWords = 0;

                        int[] arrayDataOnceLink = new int[words.length];

                        for (int i = 0; i < words.length; i++) {
                            int countEntryWord = StringUtil.countEntryWordToText(text, words[i]);
                            totalHitByOneWord[i] += countEntryWord;
                            totalSumHitByAllWords += countEntryWord;
                            arrayDataOnceLink[i] = countEntryWord;
                        }

                        totalHitByOneWord[words.length] += totalSumHitByAllWords;
                        allData.put(link, arrayDataOnceLink);
                    }

                    for (Element page : linksOnPage) {
                        String attr = page.attr("abs:href");
                        if (uniqueLinks.add(attr)) {
                            linksOfNewLevel.add(attr);
                        }
                    }

                    countPage++;

                    log.info("Depth: " + depth + "," + " Quantity pages: " + countPage);

                } catch (IllegalArgumentException e) {
                    log.error("This link is invalid: " + link);
                } catch (IOException e) {
                    log.error("This link not available: " + link);
                }
            }
        });

        depth++;

        queue = linksOfNewLevel;

        if (depth <= maxDepth && countPage < maxQuantityPage && queue.size() != 0) {
            launchCrawler();
        }
    }
}
