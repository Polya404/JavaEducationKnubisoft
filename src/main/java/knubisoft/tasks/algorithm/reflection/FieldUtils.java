//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package knubisoft.tasks.algorithm.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Add implementation class and tests
 */
public interface FieldUtils {

    Field getField(Class<?> cls, String fieldName) throws ClassNotFoundException, NoSuchFieldException;

    Field getDeclaredField(Class<?> cls, String fieldName) throws ClassNotFoundException, NoSuchFieldException;

    Field[] getAllFields(Class<?> cls) throws NoSuchFieldException, ClassNotFoundException;

    Field[] getFieldsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) throws ClassNotFoundException;

}
