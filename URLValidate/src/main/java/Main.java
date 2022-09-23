import lombok.SneakyThrows;
import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Main {
    static UrlValidator urlValidator = new UrlValidator();
    static ArrayList<String> array = new ArrayList<>();  // ссылки которые уже были использованы
    static String urlGeneral = "https://freemaxpictures.com";
    static ArrayList<String> validRef = new ArrayList<>(); // валидные ссылки

    public static void main(String[] args) {
        process(urlGeneral);
        System.out.println(validRef);
    }

    @SneakyThrows
    public static Elements stepInto(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
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

        for (Element link : links) {  // проходим по всем элементам

            String str = link.attr("href"); // получаем ссылку
            if (urlValidator.isValid(str) && !array.contains(str)) {  // если ссылка валидна и мы еще не заходили по ней
                if (str.startsWith(urlGeneral)) { // если ссылка принадлежит изначальному сайту
                    array.add(link.baseUri());  // добавляем ссылку в массив ссылок котрые уже были использованы
                    process(str); // заходим по ссылке чтоб снова проверить все ссылки на странице
                    validRef.add(str); // добавляем в массив с валидными ссылками
                }
            }
        }
    }
}
