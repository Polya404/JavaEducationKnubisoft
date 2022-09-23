package knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class FilesImplTest {
    FilesImpl files = new FilesImpl();

    @Test
    public void contentEquals() {

        Path pathDir = FileSystems.getDefault().getPath("").toAbsolutePath();
        String filename1 = "src/test/java/knubisoft/tasks/algorithm/collection/test1.txt";
        String filename2 = "src/test/java/knubisoft/tasks/algorithm/collection/test2.txt";
        String filename3 = "src/test/java/knubisoft/tasks/algorithm/collection/test3.txt";
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
    public void copyDirectoryToDirectory() throws IOException {
        File dir1 = new File("src/main/java/knubisoft/tasks/algorithm/collection/testDir1");
        File dir2 = new File("src/main/java/knubisoft/tasks/algorithm/collection/testDir2");
        files.copyDirectoryToDirectory(dir1, dir2);
        Assertions.assertEquals(2, Objects.requireNonNull(dir2.listFiles()).length);
    }

    @SneakyThrows
    @Test
    public void toString1(){
        String s = files.toString(new File("src/main/java/knubisoft/tasks/algorithm/collection/testDir1/testFile1.txt").toURI().toURL(), StandardCharsets.UTF_8);
        Assertions.assertEquals("Hello everyone", s);
    }

    @Test
    @SneakyThrows
    public void toString2(){
        InputStream inputStream = new ByteArrayInputStream(files.toString(new File("src/main/java/knubisoft/tasks/algorithm/collection/testDir1/testFile1.txt").toURI().toURL(), StandardCharsets.UTF_8).getBytes());
        Assertions.assertEquals("Hello everyone", files.toString(inputStream, StandardCharsets.UTF_8));
    }

    @Test
    @SneakyThrows
    public void toByteArray() {
        File f = new File("src/main/java/knubisoft/tasks/algorithm/collection/testDir1/testFile1.txt");
        URL url = f.toURI().toURL();
        byte[] b1 = files.toString(url, StandardCharsets.UTF_8).getBytes();
        Assertions.assertArrayEquals(b1, files.toByteArray(url));
    }

    @Test
    public void readLines(){
        File f = new File("src/main/java/knubisoft/tasks/algorithm/collection/testDir1/testFile1.txt");

    }

    @Test
    public void isEmptyDirectory() throws IOException {
        Path pathDir = FileSystems.getDefault().getPath("").toAbsolutePath();
        String filename1 = "src/test/java/com/knubisoft/tasks/algorithm/collection";
        Assertions.assertFalse(files.isEmptyDirectory(new File(filename1)));
    }
}
