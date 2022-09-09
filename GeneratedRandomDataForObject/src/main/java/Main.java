import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.util.*;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Map<String, List<List<Person>>> result = new LinkedHashMap<>();
        List<Set<String>> stringList = new ArrayList<>();
        RunGenerate runGenerate = new RunGenerate(3);
        Type type = runGenerate.unpackGenericClass(new GenericClass<>(stringList) {
        }.getType());
        Object populate = runGenerate.populate(type);
        System.out.println(populate);
    }


}

