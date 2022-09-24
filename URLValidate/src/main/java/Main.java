
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executor = Executors.newCachedThreadPool();
    static String urlGeneral = "https://freemaxpictures.com";

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            executor.execute(new Process(urlGeneral));
        }
        executor.shutdown();
    }


}
