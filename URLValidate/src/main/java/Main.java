
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executor = Executors.newCachedThreadPool();
    static String urlGeneral = "https://freemaxpictures.com";

    public static void main(String[] args) {
        Process process = new Process(urlGeneral);
        process.process(urlGeneral);
        System.out.println(process.getValidRef().size());
    }


}
