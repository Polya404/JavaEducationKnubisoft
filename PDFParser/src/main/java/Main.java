import dto.FileReadSource;
import model.RowStructure;
import service.Convertor;
import service.impl.ConvertorPdf;

import java.io.File;
import java.util.List;

public class Main {
    private static final Convertor convertor = new ConvertorPdf();

    public static void main(String[] args) {
        File file = new File("PdfParser/src/main/resources/sample.pdf");
        FileReadSource source = new FileReadSource(file);
        List<RowStructure> rowStructureList =
                convertor.getDataFromSource(source, RowStructure.class);
        rowStructureList.forEach(System.out::println);
    }

}
