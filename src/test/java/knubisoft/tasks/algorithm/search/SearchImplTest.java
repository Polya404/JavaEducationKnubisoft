package knubisoft.tasks.algorithm.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchImplTest {
    @Test
    void binarySearch(){
        SearchImpl search = new SearchImpl();
        long[] arr = new long[]{1,3,7,8,12,46,78,81};
        long target = 46;
        long target1 = 1;
        long target2 = 81;
        long target3 = 83;
        Assertions.assertEquals(5,search.binarySearch(arr, target));
        Assertions.assertEquals(0,search.binarySearch(arr, target1));
        Assertions.assertEquals(7,search.binarySearch(arr, target2));
        Assertions.assertEquals(-1,search.binarySearch(arr, target3));
    }
}
