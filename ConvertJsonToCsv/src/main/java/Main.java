import com.github.opendevl.JFlat;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = new String(Files.readAllBytes(Paths.get("ConvertJsonToCsv/src/main/resources/attendees.json")));

        convertJSONToCSV(str);

    }

    /**
     * this method using JFlat library convert json string to csv format
     * @param str - json string
     */
    @SneakyThrows
    private static void convertJSONToCSV(String str) {
        JFlat flatMe = new JFlat(str);

        List<Object[]> json2csv = flatMe.json2Sheet().getJsonAsSheet();

        flatMe.write2csv("ConvertJsonToCsv/src/main/resources/attendees.csv");
    }


}
