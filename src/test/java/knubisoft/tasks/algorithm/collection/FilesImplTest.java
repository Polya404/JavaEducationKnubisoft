package knubisoft.tasks.algorithm.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FilesImplTest {
    FilesImpl files = new FilesImpl();

    @Test
    public void contentEquals() {

        Path pathDir = FileSystems.getDefault().getPath("").toAbsolutePath();
        String filename1 = "src/test/java/com/knubisoft/tasks/algorithm/collection/test1.txt";
        String filename2 = "src/test/java/com/knubisoft/tasks/algorithm/collection/test2.txt";
        String filename3 = "src/test/java/com/knubisoft/tasks/algorithm/collection/test3.txt";
        String s = pathDir.toAbsolutePath().toString();
        File file1 = new File(s, File.separator.concat(filename1));
        File file2 = new File(s, File.separator.concat(filename2));
        File file3 = new File(s, File.separator.concat(filename3));
        try {
            Assertions.assertTrue(files.contentEquals(file1, file1));
            Assertions.assertTrue(files.contentEquals(file2, file3));
            Assertions.assertFalse(files.contentEquals(file1, file3));
            Assertions.assertFalse(files.contentEquals(file2, file1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copyDirectoryToDirectory() {

    }

    @Test
    public void toByteArray() {

    }

    @Test
    public void readLines(){

    }

    @Test
    public void isEmptyDirectory(){
        Path pathDir = FileSystems.getDefault().getPath("").toAbsolutePath();
        String filename1 = "src/test/java/com/knubisoft/tasks/algorithm/collection";
        Assertions.assertFalse(files.isEmptyDirectory(new File(filename1)));
    }
}
