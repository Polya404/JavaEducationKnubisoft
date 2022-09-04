package knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesImpl implements FilesInterface {
    @Override
    public boolean contentEquals(File file1, File file2) throws IOException {
        if (file1.length() == file2.length()) {
            List<String> list1 = Files.readAllLines(Path.of(file1.getPath()));
            List<String> list2 = Files.readAllLines(Path.of(file2.getPath()));
            return list1.equals(list2);
        }
        return false;
    }

    @Override
    public void copyDirectoryToDirectory(File sourceDir, File destinationDir) throws IOException {
        FileUtils.copyDirectory(sourceDir, destinationDir);
    }

    @Override
    public String toString(URL url, Charset encoding) throws IOException {
        return null;
    }

    @Override
    public String toString(InputStream input, Charset charset) throws IOException {
        return null;
    }

    @SneakyThrows
    @Override
    public byte[] toByteArray(URL url) throws IOException {
        File file = new File(String.valueOf(url));
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public String normalize(String fileName) {
        return null;
    }

    @Override
    public List<String> readLines(File file, Charset charset) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(file.getName()))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    @SneakyThrows
    @Override
    public boolean isEmptyDirectory(File directory) {
        Path path = Path.of(directory.getPath());
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directories = Files.newDirectoryStream(path)) {
                return !directories.iterator().hasNext();
            }
        }
        return false;
    }
}
