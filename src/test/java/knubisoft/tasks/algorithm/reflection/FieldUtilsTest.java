package knubisoft.tasks.algorithm.reflection;

import com.knubisoft.base.reflection.ReflectionTasks;
import com.knubisoft.base.reflection.ReflectionTasksImpl;
import com.knubisoft.base.reflection.model.InheritedEntryModel;
import com.knubisoft.base.string.StringTasks;
import com.knubisoft.base.string.StringTasksImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.LowerCase;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class FieldUtilsTest {
    FieldUtils fieldUtils = new FieldUtilsImpl();

    @SneakyThrows
    @Test
    void getField1() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.Test");
        assertEquals(clazz.getDeclaredField("x"), fieldUtils.getField(clazz, "x"));
        assertEquals(clazz.getDeclaredField("y"), fieldUtils.getField(clazz, "y"));
        assertNotEquals(clazz.getDeclaredField("x"), fieldUtils.getField(clazz, "z"));
    }

    @SneakyThrows
    @Test
    void getField2() {

    }

    @SneakyThrows
    @Test
    void getDeclaredField() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.Test");
        assertEquals(clazz.getDeclaredField("x"), fieldUtils.getDeclaredField(clazz, "x"));
        assertEquals(clazz.getDeclaredField("y"), fieldUtils.getDeclaredField(clazz, "y"));
        assertNotEquals(clazz.getDeclaredField("x"), fieldUtils.getDeclaredField(clazz, "z"));
    }

    @SneakyThrows
    @Test
    void getAllFields() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(4, fieldUtils.getAllFields(clazz).length);
        assertEquals(0, fieldUtils.getAllFields(ReflectionTasks.class).length);
        assertEquals(0, fieldUtils.getAllFields(ReflectionTasksImpl.class).length);
        assertEquals(0, fieldUtils.getAllFields(StringTasks.class).length);
        assertEquals(0, fieldUtils.getAllFields(StringTasksImpl.class).length);
        assertEquals(4, fieldUtils.getAllFields(InheritedEntryModel.class).length);
        assertThrows(NoSuchElementException.class, () -> fieldUtils.getAllFields(null));
    }

    @SneakyThrows
    @Test
    void getFieldsWithAnnotation() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.Test");
        assertEquals(2, fieldUtils.getFieldsWithAnnotation(clazz, Deprecated.class).length);
        assertEquals(1, fieldUtils.getFieldsWithAnnotation(clazz, LowerCase.class).length);
        assertNotEquals(2, fieldUtils.getFieldsWithAnnotation(clazz, LowerCase.class).length);
        assertNotEquals(1, fieldUtils.getFieldsWithAnnotation(clazz, Deprecated.class).length);
    }
}
