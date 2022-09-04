package knubisoft.tasks.algorithm.collection;

import java.nio.file.Files;
import java.util.*;

public class UtilsImpl implements Utils {
    @Override
    public <K, V> Map<V, K> invertMap(Map<K, V> map) {
        return null;
    }

    @Override
    public <E> List<E> union(List<? extends E> list1, List<? extends E> list2) {
        List<E> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    @Override
    public boolean isEqualList(Collection<?> list1, Collection<?> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        ArrayList<Object> arrayList1 = new ArrayList<>(list1);
        ArrayList<Object> arrayList2 = new ArrayList<>(list2);
        for (int i = 0; i < arrayList1.size(); i++) {
            if (!arrayList1.get(i).equals(arrayList2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
        return null;
    }

    @Override
    public <O> Collection<O> disjunction(Iterable<? extends O> a, Iterable<? extends O> b) {
        return null;
    }

    @Override
    public <O> Collection<O> subtract(Iterable<? extends O> a, Iterable<? extends O> b) {
        return null;
    }

    @Override
    public <E> Comparator<E> chainedComparator(Comparator<E>... comparators) {
        return null;
    }

    @Override
    public boolean isSubCollection(Collection<?> a, Collection<?> b) {
        return false;
    }
}
