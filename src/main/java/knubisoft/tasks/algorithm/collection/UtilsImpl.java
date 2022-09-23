package knubisoft.tasks.algorithm.collection;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.comparators.ComparatorChain;

import java.util.*;
import java.util.stream.Collectors;

public class UtilsImpl implements Utils {
    @Override
    public <K, V> Map<V, K> invertMap(Map<K, V> map) {
        if (map == null)
            throw new NullPointerException();
        Map<K, V> returnMap = new HashMap<>();
        for (Map.Entry<K, V> item : map.entrySet()) {
            if (returnMap.containsKey(item.getValue())) {
                returnMap.put((K) item.getValue(), null);
            } else
                returnMap.put((K) item.getValue(), (V) item.getKey());
        }
        return (Map<V, K>) returnMap;
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
        if (map == null)
            throw new NullPointerException();
        Map<K, V> m = MapUtils.synchronizedMap(map);
        Set<K> s = m.keySet();

        synchronized (m) {
            Iterator<K> i = s.iterator();
            while (i.hasNext()) {
                m.get(i.next());
            }
        }
        return m;
    }

    @Override
    public <O> Collection<O> disjunction(Iterable<? extends O> a, Iterable<? extends O> b) {
        if (a == null || b == null)
            throw new NullPointerException();
        List<O> collection = new ArrayList<>();
        Iterator<? extends O> iteratorA = a.iterator();
        Iterator<? extends O> iteratorB = b.iterator();
        O iteratorC = null;

        while (iteratorA.hasNext()) {
            O nextA = iteratorA.next();
            while (iteratorB.hasNext()) {
                O nextB = iteratorB.next();
                if (nextA.equals(nextB)) {
                    iteratorC = nextB;
                    iteratorB.remove();
                }
            }
            iteratorB = b.iterator();

            if (nextA.equals(iteratorC))
                iteratorA.remove();
        }

        collection.addAll((Collection<? extends O>) a);
        collection.addAll((Collection<? extends O>) b);

        return collection.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public <O> Collection<O> subtract(Iterable<? extends O> a, Iterable<? extends O> b) {
        if (a == null || b == null)
            throw new NullPointerException();
        Iterator<? extends O> iteratorA = a.iterator();
        Iterator<? extends O> iteratorB = b.iterator();
        while (iteratorA.hasNext()) {
            O nextA = iteratorA.next();
            while (iteratorB.hasNext()) {
                O nextB = iteratorB.next();
                if (nextA.equals(nextB)) {
                    iteratorA.remove();
                    break;
                }
            }
            iteratorB = b.iterator();
        }
        return (Collection<O>) a;
    }

    @Override
    public <E> Comparator<E> chainedComparator(Comparator<E>... comparators) {
        ComparatorChain<E> chain = new ComparatorChain<>();
        for (Comparator<E> comparator : comparators) {
            if (comparator == null) {
                throw new NullPointerException();
            }
            chain.addComparator(comparator);
        }
        return chain;
    }

    @Override
    public boolean isSubCollection(Collection<?> a, Collection<?> b) {
        if (a == null || b == null)
            throw new NullPointerException();
        Iterator<?> iteratorA = a.iterator();
        Iterator<?> iteratorB = b.iterator();
        boolean res = false;
        for (; iteratorA.hasNext(); ) {
            Object collectionA = iteratorA.next();
            for (; iteratorB.hasNext(); ) {
                Object collectionB = iteratorB.next();
                if (collectionA.equals(collectionB)) {
                    iteratorB.remove();
                    res = true;
                    break;
                }
                else
                    res = false;
            }
        }
        return res;
    }
}
