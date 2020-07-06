package by.chyzh;

import by.chyzh.util.JsoupUtil;
import by.chyzh.util.StringUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Log4j
public class WebCrawler {

    private final HashSet<String> uniqueLinks;
    private final String[] words;
    private List<String> queue;
    private final int[] totalHeader;
    private final LinkedHashMap<String, int[]> allData;

    private final String rootUrl;
    private final int maxDepth;
    @Getter
    private int depth = 1;
    private final int maxQuantityPage;
    @Getter
    private int countPage;

    public WebCrawler(String url, int maxDepth, int maxPage, String[] words) {
        this.uniqueLinks = new HashSet<>();
        this.totalHeader = new int[words.length + 1];
        this.queue = Collections.singletonList(url);
        this.allData = new LinkedHashMap<>();
        this.rootUrl = url;
        this.maxDepth = maxDepth;
        this.maxQuantityPage = maxPage;
        this.words = words;
    }

    public List<String[]> getAllData() {

        List<String[]> list = new ArrayList<>();

        LinkedHashMapToListOfArrayString(list, totalHeader.length + 1, rootUrl, totalHeader);

        allData.forEach((key, value) -> LinkedHashMapToListOfArrayString(list, words.length + 1, key, value));

        return list;
    }

    public List<String[]> getSortData(int limitSort) {
        return allData.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> Arrays.stream(o2).sum() - Arrays.stream(o1).sum()))
                .limit(limitSort)
                .map(this::mapEntryToArrayString)
                .collect(Collectors.toList());
    }

    public List<String> generalizedData(){

        List<String> list = new ArrayList<>();

        String s = Arrays.stream(totalHeader)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));

        list.add(rootUrl + "," + s);

        for (int i = 0; i < words.length ; i++) {
            list.add(words[i] + " - " + totalHeader[i] + " hits");
        }

        list.add("Total - " + totalHeader[totalHeader.length - 1] + " hits");

        return list;
    }

    public void launchCrawler() {

        List<String> linksOfNewLevel = new ArrayList<>();

        queue.forEach(link -> {

            if (countPage != maxQuantityPage) {

                Document document;

                try {
                    document = JsoupUtil.getDocument(link);

                    Elements linksOnPage = document.select("a[href]");

                    if (Objects.nonNull(document.body())) {
                        String text = document.body().text();

                        int sum = 0;

                        int[] arrayDataOnceLink = new int[words.length];

                        for (int i = 0; i < words.length; i++) {
                            int countEntryWord = StringUtil.countEntry(text, words[i]);
                            totalHeader[i] += countEntryWord;
                            sum += countEntryWord;
                            arrayDataOnceLink[i] = countEntryWord;
                        }

                        totalHeader[words.length] += sum;
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

    private void LinkedHashMapToListOfArrayString(List<String[]> list, int sizeArray, String rootUrl, int[] totalHeader) {
        String[] arrayTotalHeader = new String[sizeArray];
        arrayTotalHeader[0] = rootUrl;
        for (int i = 0; i < totalHeader.length; i++) {
            arrayTotalHeader[i + 1] = String.valueOf(totalHeader[i]);
        }
        list.add(arrayTotalHeader);
    }

    private String[] mapEntryToArrayString(Map.Entry<String, int[]> value) {
        String[] array = new String[words.length + 1];
        array[0] = value.getKey();
        for (int i = 0; i < value.getValue().length; i++) {
            array[i + 1] = String.valueOf(value.getValue()[i]);
        }
        return array;
    }

}
