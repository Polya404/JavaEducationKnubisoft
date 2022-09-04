package knubisoft.tasks.algorithm.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsImplTest {
    UtilsImpl utils = new UtilsImpl();
    @Test
    public void invertMap(){

    }

    @Test
    public void union(){
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("4");
        list2.add("5");
        list2.add("6");
        Assertions.assertEquals(6, utils.union(list1,list2).size());
    }

    @Test
    public void isEqualList(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(0);
        list1.add(12);
        List<Integer> list2 = new ArrayList<>();
        list2.add(12);
        list2.add(0);
        list2.add(1);
        List<String> list3 = new ArrayList<>();
        list3.add("true");
        list3.add("java");
        list3.add("12");
        List<Integer> list4 = new ArrayList<>();
        list4.add(1);
        list4.add(0);
        list4.add(12);
        Assertions.assertTrue(utils.isEqualList(list1,list4));
        Assertions.assertTrue(utils.isEqualList(list2,list2));
        Assertions.assertFalse(utils.isEqualList(list1,list3));
        Assertions.assertFalse(utils.isEqualList(list3,list4));
    }
}
