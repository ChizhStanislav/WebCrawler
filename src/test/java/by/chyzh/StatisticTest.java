package by.chyzh;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class StatisticTest {

    private final String rootUrl = "www.tut.by";
    private LinkedHashMap<String, int[]> allData;
    private final int[] totalNumbers = {1, 2, 3, 4, 8};
    private final int limitSort = 10;
    private final String[] words = {"a", "b", "c", "d"};
    private Statistic statistic;


    @Before
    public void before() {
        allData = new LinkedHashMap<>();
        allData.put("www.onliner.by", new int[]{0, 1, 2, 3});
        allData.put("www.yandex.ru", new int[]{1, 2, 3, 4});
        statistic = new Statistic(rootUrl, allData, totalNumbers, limitSort, words);
    }

    @Test
    public void getAllData() {
        List<String[]> allData = statistic.getAllData();
        Assert.assertEquals(3, allData.size());
        Assert.assertEquals(rootUrl, allData.get(0)[0]);
        Assert.assertEquals("www.onliner.by", allData.get(1)[0]);
        Assert.assertEquals("www.yandex.ru", allData.get(2)[0]);

    }

    @Test
    public void getSortData() {
        List<String[]> sortData = statistic.getSortData();
        Assert.assertEquals(2, sortData.size());
        Assert.assertEquals("www.yandex.ru", sortData.get(0)[0]);
        Assert.assertEquals("www.onliner.by", sortData.get(1)[0]);
    }

    @Test
    public void getGeneralizedData() {
        List<String> generalizedData = statistic.getGeneralizedData();
        Assert.assertEquals(6, generalizedData.size());
        Assert.assertEquals(String.format("%s,1,2,3,4,8", rootUrl), generalizedData.get(0));
        Assert.assertEquals("a - 1 hits", generalizedData.get(1));
        Assert.assertEquals("b - 2 hits", generalizedData.get(2));
        Assert.assertEquals("c - 3 hits", generalizedData.get(3));
        Assert.assertEquals("d - 4 hits", generalizedData.get(4));
        Assert.assertEquals("Total - 8 hits", generalizedData.get(5));
    }
}