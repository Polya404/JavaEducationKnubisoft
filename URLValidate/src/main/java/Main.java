import lombok.SneakyThrows;
import org.apache.commons.validator.UrlValidator;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Main {
    static UrlValidator urlValidator = new UrlValidator();
    static ArrayList<String> array = new ArrayList<>();

    public static void main(String[] args) {
        String url = "https://dou.ua/";
        process(url);
    }

    @SneakyThrows
    public static Elements stepInto(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            //...
        }
        if (doc != null) {
            return doc.select("a");
        }else {
            return new Elements();
        }
    }

    public static void process(String url) {
        Elements links = stepInto(url);

        for (Element link : links) {

            String str = link.attr("href");
            if (urlValidator.isValid(str) && !array.contains(str)) {
                if (str.startsWith("https://dou.ua/")) {
                    array.add(link.baseUri());
                    process(str);
                }
            }
        }
    }
}
