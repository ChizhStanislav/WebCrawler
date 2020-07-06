package by.chyzh.util;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsoupUtilTest {

    @Test
    @SneakyThrows
    public void checkGetDocument() {

        Document document = JsoupUtil.getDocument("https://yandex.by/");
        Assert.assertEquals("Яндекс", document.title());
    }
}