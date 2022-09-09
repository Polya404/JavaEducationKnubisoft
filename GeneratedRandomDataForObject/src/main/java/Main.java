import GeneratorUtil.GeneratorUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    static GeneratorUtil generator = new GeneratorUtil();
    static int amountObject = 3;

    @SneakyThrows
    public static void main(String[] args) {
        Map<String, List<List<Person>>> result = new LinkedHashMap<>();
        List<Set<String>> stringList = new ArrayList<>();
        Object populate = populate(unpackGenericClass(new GenericClass<>(stringList) {
        }.getType()));
        System.out.println(populate);
    }

    private static Type unpackGenericClass(Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType().equals(GenericClass.class) ?
                parameterizedType.getActualTypeArguments()[0] : type;
    }

    @SneakyThrows
    private static Object populate(Type type) {
        if (type instanceof ParameterizedType) {
            return createCollection((ParameterizedType) type);
        }
        if (isSimpleType(type)) {
            return generator.getSimpleGeneratedObject(Class.forName(type.getTypeName()));
        } else {
            return createObject((Class<?>) type);
        }
    }

    @SneakyThrows
    private static Object createCollection(ParameterizedType type) {
        Object collectionGeneratedObject = generator.getCollectionGeneratedObject(Class.forName(type.getRawType().getTypeName()));
        Type[] types = type.getActualTypeArguments();

        if (types.length == 2) {
            Map map = (Map) collectionGeneratedObject;
            for (int i = 0; i < amountObject; i++) {
                map.put(populate(types[0]), populate(types[1]));
            }
            return map;
        } else {
            Collection collection = (Collection) collectionGeneratedObject;
            for (int i = 0; i < amountObject; i++) {
                collection.add(populate(types[0]));
            }
            return collection;
        }
    }

    @SneakyThrows
    private static Object createObject(Class<?> type) {
        Object instance = null;
        for (int i = 0; i < amountObject; i++) {
            instance = Class.forName(type.getName()).getDeclaredConstructor().newInstance();
            Field[] fields = Class.forName(type.getName()).getDeclaredFields();
            for (Field f : fields) {
                f = instance.getClass().getDeclaredField(f.getName());
                f.setAccessible(true);
                f.set(instance, populate(f.getGenericType()));
            }
        }

        return instance;
    }

    private static boolean isSimpleType(Object x) {
        return generator.getSimpleClasses().contains(x);
    }
}

