package knubisoft.tasks.algorithm.search;

public class SearchImpl implements Search {
    @Override
    public int binarySearch(long[] array, long v) {
        long first = 0;
        long last = array.length - 1;
        while (first <= last) {
            int middle = (int) (first + (last - first) / 2);
            if (array[middle] == v) {
                return middle;
            } else if (array[middle] < v) {
                first = middle + 1;
            } else {
                last = middle - 1;
            }
        }
        return -1;
    }
}
