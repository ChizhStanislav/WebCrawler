package by.chyzh;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.*;
import java.util.stream.Collectors;

@Log4j
@AllArgsConstructor
public class Statistic {

    private final String rootUrl;
    private final LinkedHashMap<String, int[]> allData;
    private final int[] totalNumbers;
    private final int limitSort;
    private final String[] words;

    public List<String[]> getAllData() {

        List<String[]> list = new ArrayList<>();

        LinkedHashMapToListOfArrayString(list, totalNumbers.length + 1, rootUrl, totalNumbers);

        allData.forEach((key, value) -> LinkedHashMapToListOfArrayString(list, totalNumbers.length, key, value));

        log.info("The crawl is complete");

        return list;
    }

    public List<String[]> getSortData() {
        return allData.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> Arrays.stream(o2).sum() - Arrays.stream(o1).sum()))
                .limit(limitSort)
                .map(this::mapEntryToArrayString)
                .collect(Collectors.toList());
    }

    public List<String> getGeneralizedData() {

        List<String> list = new ArrayList<>();

        String allStatistic = Arrays.stream(totalNumbers)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));

        list.add(String.format("%s,%s", rootUrl, allStatistic));

        for (int i = 0; i < words.length; i++) {
            list.add(words[i] + " - " + totalNumbers[i] + " hits");
        }

        list.add("Total - " + totalNumbers[totalNumbers.length - 1] + " hits");

        return list;
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
        String[] array = new String[value.getValue().length + 1];
        array[0] = value.getKey();
        for (int i = 0; i < value.getValue().length; i++) {
            array[i + 1] = String.valueOf(value.getValue()[i]);
        }
        return array;
    }


}
