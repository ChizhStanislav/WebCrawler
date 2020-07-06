package by.chyzh.util;

import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@UtilityClass
public class JsoupUtil {

    public static Document getDocument(String url) throws IOException {

        return Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
    }
}
