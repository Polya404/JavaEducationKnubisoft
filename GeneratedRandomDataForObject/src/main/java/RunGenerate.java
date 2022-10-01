import GeneratorUtil.GeneratorUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class RunGenerate {
    private GeneratorUtil generator = new GeneratorUtil();
    private int amountObject = 1;

    public RunGenerate(int amountObject) {
        this.amountObject = amountObject;
    }

    public RunGenerate() {
    }

    /**
     * this method takes the type of the generic class as input and then casts it to the parameterized type
     * if the parameterized type is equal to the generic class then return the first actual type argument
     * if it's not generic then return the input data type
     *
     * @param type data type
     * @return data type
     */
    public Type unpackGenericClass(Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType().equals(GenericClass.class) ?
                parameterizedType.getActualTypeArguments()[0] : type;
    }

    /**
     * this method checks the incoming data type
     * and returns an object depending on the type
     *
     * @param type data type
     * @return an object of a specific type
     */
    @SneakyThrows
    public Object populate(Type type) {
        if (type instanceof ParameterizedType) {
            return createCollection((ParameterizedType) type);
        }
        if (isSimpleType(type)) {
            return generator.getSimpleGeneratedObject(Class.forName(type.getTypeName()));
        } else {
            return createObject((Class<?>) type);
        }
    }

    /**
     * this method accepts a parameterized collection type checks
     * the number of arguments and determines which map or list
     * interface and creates an object depending on the type
     *
     * @param type collection type
     * @return collection object
     */
    @SneakyThrows
    @SuppressWarnings("All")
    private Object createCollection(ParameterizedType type) {
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

    /**
     * this method takes a custom class type as input and using
     * reflection fills the fields of the class and returns its instance
     * @param type class type
     * @return instance
     */
    @SneakyThrows
    private Object createObject(Class<?> type) {
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

    /**
     * this method checks if an object is a primitive type
     *
     * @param x object
     * @return true or false
     */
    private boolean isSimpleType(Object x) {
        return generator.getSimpleClasses().contains(x);
    }
}
