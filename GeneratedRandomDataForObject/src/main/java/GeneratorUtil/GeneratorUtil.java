package GeneratorUtil;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorUtil {
    private static final List<String> ALPHABET = List.of("a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m");
    private static final int QUANTITY_SYMBOLS = 6;
    private static Map<Class<?>, Supplier<Object>> objectsSimpleMap;
    private static Map<Class<?>, Supplier<Object>> objectsCollectionsFrameworkMap;
    private final Random random = new Random();

    public GeneratorUtil() {
        if (objectsSimpleMap == null) {
            loadObjectsToMap();
        }
        if (objectsCollectionsFrameworkMap == null) {
            loadCollectionsFrameworkToMap();
        }
    }

    /**
     * this method adds random values of wrapper objects
     * of primitive data types to the map
     */
    private void loadObjectsToMap() {
        objectsSimpleMap = new LinkedHashMap<>();
        objectsSimpleMap.put(String.class, this::getStringRandom);
        objectsSimpleMap.put(Integer.class, () -> random.nextInt(QUANTITY_SYMBOLS));
        objectsSimpleMap.put(Long.class, random::nextLong);
        objectsSimpleMap.put(Float.class, random::nextFloat);
        objectsSimpleMap.put(Double.class, random::nextDouble);
        objectsSimpleMap.put(Boolean.class, random::nextBoolean);
        objectsSimpleMap.put(Character.class, () -> ALPHABET.get(random.nextInt(ALPHABET.size())));
    }

    /**
     * this method returns a wrapper object of the primitive data type
     * @param clazz String or Integer or Long or Float or Double or Boolean or Character
     * @return wrapper object of the primitive data type
     */
    public Object getSimpleGeneratedObject(Class<?> clazz) {
        return objectsSimpleMap.get(clazz).get();
    }

    /**
     * @return a set of wrapper objects of primitive data types
     */
    public Set<Class<?>> getSimpleClasses() {
        return objectsSimpleMap.keySet();
    }

    /**
     * this method creates a string with a random
     * order of letters from the English alphabet with a
     * length of 6 characters
     * @return string
     */
    private String getStringRandom() {
        Supplier<String> stringSupplier = () -> ALPHABET.get(random.nextInt(ALPHABET.size()));
        return Stream.generate(stringSupplier)
                .limit(QUANTITY_SYMBOLS)
                .collect(Collectors.joining());
    }

    /**
     * this method adds collection objects to the map
     */
    private void loadCollectionsFrameworkToMap() {
        objectsCollectionsFrameworkMap = new LinkedHashMap<>();
        objectsCollectionsFrameworkMap.put(List.class, ArrayList::new);
        objectsCollectionsFrameworkMap.put(Map.class, LinkedHashMap::new);
        objectsCollectionsFrameworkMap.put(Set.class, HashSet::new);
        objectsCollectionsFrameworkMap.put(Queue.class, ArrayDeque::new);
    }

    /**
     * this method returns the implementation of one
     * of the collections depending on the input arguments
     * @param clazz collection
     * @return collection
     */
    public Object getCollectionGeneratedObject(Class<?> clazz) {
        return objectsCollectionsFrameworkMap.get(clazz).get();
    }
}
