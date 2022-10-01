package knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

    @SneakyThrows
    @Override
    public String toString(URL url, Charset encoding) throws IOException {
        File f = new File(url.toURI());
        return FileUtils.readFileToString(f, encoding);
    }

    @Override
    public String toString(InputStream input, Charset charset) throws IOException {
        return IOUtils.toString(input, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    @Override
    public byte[] toByteArray(URL url) {
        File file = new File(url.toURI());
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public String normalize(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        return Paths.get(fileName).normalize().toString();
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
    public boolean isEmptyDirectory(File directory) throws IOException{
        Path path = Path.of(directory.getPath());
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directories = Files.newDirectoryStream(path)) {
                return !directories.iterator().hasNext();
            }
        }
        return false;
    }
}
