package knubisoft.base.reflection;

import knubisoft.base.reflection.model.EntryModel;
import knubisoft.base.reflection.model.InheritedEntryModel;
import knubisoft.base.string.StringTasks;
import knubisoft.base.string.StringTasksImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReflectionTasksTest {

    ReflectionTasks instance = new ReflectionTasksImpl();

    @Test
    public void createNewInstanceForClassSuccessful() throws ClassNotFoundException{
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(InheritedEntryModel.class, instance.createNewInstanceForClass(clazz).getClass());
        assertEquals(InheritedEntryModel.class,
                instance.createNewInstanceForClass(InheritedEntryModel.class).getClass());
    }

    @Test
    public void createNewInstanceForClassFail() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(clazz));
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.createNewInstanceForClass(null));
    }

    @Test
    public void findImplementationForInterfaceSuccessful() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(clazz));
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(EntryModel.class));
    }

    @Test
    public void findImplementationForInterfaceFail() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(clazz));
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.findImplementationForInterface(null));
    }

    @Test
    public void findAllFieldsForClassSuccessful() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(4, instance.findAllFieldsForClass(clazz).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasksImpl.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasksImpl.class).size());
        assertEquals(4, instance.findAllFieldsForClass(InheritedEntryModel.class).size());
    }

    @Test
    public void findAllFieldsForClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.findAllFieldsForClass(null));
    }

    @Test
    public void countPrivateMethodsInClassSuccessful() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(2, instance.countPrivateMethodsInClass(clazz));
        assertEquals(0, instance.countPrivateMethodsInClass(StringTasks.class));
        assertEquals(2, instance.countPrivateMethodsInClass(EntryModel.class));
    }

    @Test
    public void countPrivateMethodsInClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.countPrivateMethodsInClass(null));
    }

    @Test
    public void isMethodHasAnnotation() {
        InheritedEntryModel inheritedEntryModel = new InheritedEntryModel("1");
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
            Assertions.assertTrue(instance.isMethodHasAnnotation(clazz.getMethod("builder"), instance.createNewInstanceForClass(clazz).getClass()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void evaluateMethodByNameSuccessful() {
        assertEquals(int.class,
                instance.evaluateMethodByName(knubisoft.base.reflection.model.Test.class, "test1"));
        assertEquals(String.class,
                instance.evaluateMethodByName(knubisoft.base.reflection.model.Test.class, "test2"));
        assertEquals(boolean.class,
                instance.evaluateMethodByName(knubisoft.base.reflection.model.Test.class, "test3"));
    }

    @Test
    public void evaluateMethodByNameArgsSuccessful() {
        assertEquals("dlroW ,olleH",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "reverseString","Hello, World"));
        assertEquals("He, Worldllo",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "insertStringInMiddle",
                        "Hello", ", World"));
        assertEquals("g",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "uniqueCharacters",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"));
    }

    @Test
    public void evaluateMethodByNameArgsFail() {
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(null, "builder", "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(), null, "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(),
                        "insertStringInMiddle", null));
    }

    @Test
    public void changePrivateFieldValue(){
        knubisoft.base.reflection.Test test = new knubisoft.base.reflection.Test();
        Assertions.assertEquals("val", instance.changePrivateFieldValue(test, "x", "val"));
        Assertions.assertEquals(1, instance.changePrivateFieldValue(test, "x", 1));
        Assertions.assertEquals("vl", instance.changePrivateFieldValue(test, "x", "vl"));
        Assertions.assertEquals(false, instance.changePrivateFieldValue(test, "x", false));
    }
}
