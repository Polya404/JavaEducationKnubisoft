package service.impl;

import annotation.Lookup;
import dto.FileReadSource;
import lombok.SneakyThrows;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import service.Convertor;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertorPdf implements Convertor {

    @Override
    public <T> List<T> getDataFromSource(FileReadSource source, Class<T> clazz) {
        File file = source.getFile();
        String string = parsePdfToString(file);
        return anotherWay(string, clazz);
    }

    private <T> List<T> anotherWay(String string, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        Map<String, String> regex = findFieldNameRegex(clazz);
        String commonRegex = String.join(" ", regex.values());
        Pattern pattern = Pattern.compile(commonRegex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            result.add(getEntity(clazz, regex, matcher));
        }
        return result;
    }

    @SneakyThrows
    private <T> T getEntity(Class<T> clazz, Map<String, String> regex, Matcher matcher) {
        T instance = clazz.getConstructor().newInstance();
        for (String name : regex.keySet()) {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, matcher.group(name).replaceAll("\\h", " "));
        }
        return instance;
    }

    private <T> Map<String, String> findFieldNameRegex(Class<T> clazz) {
        Map<String, String> resultMap = new LinkedHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Lookup.class)) {
                resultMap.put(field.getName(), field.getAnnotation(Lookup.class).regex());
            }
        }
        return resultMap;
    }

    @SneakyThrows
    private String parsePdfToString(File file) {
        PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
        parser.parse();
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        return pdfTextStripper.getText(parser.getPDDocument());
    }
}
