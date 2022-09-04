package knubisoft.base.list;

import java.util.*;

public class ListTasksImpl implements ListTasks {
    @Override
    public List<String> addElements(String... elements) {
        return new ArrayList<>(List.of(elements));
    }

    @Override
    public List<String> getElementsByIndexes(List<String> elements, int[] indexes) throws IllegalArgumentException {
        if (indexes == null) {
            throw new IllegalArgumentException("Indexes cannot be null");
        }
        for (int index : indexes) {
            if (index < 0 || index > elements.size()) {
                throw new IllegalArgumentException("value cannot be less then zero or bigger then size of list");
            }
            elements.add(elements.size(), elements.get(index));
        }
        return elements;
    }

    @Override
    public ArrayList<String> addElementsByIndexes(ArrayList<String> elements, int[] indexes) throws IllegalArgumentException {
        if (indexes == null || elements == null) {
            throw new IllegalArgumentException();
        }
        for (int index : indexes) {
            if (index < 0 || index > elements.size() + 1) {
                throw new IllegalArgumentException();
            }
            elements.add(index, elements.get(index));
            System.out.println(elements);
        }
        return elements;
    }

    @Override
    public LinkedList<String> setElementsByIndexes(LinkedList<String> elements, int[] indexes) {
        if (indexes == null || elements == null) {
            throw new IllegalArgumentException();
        }
        for (int index : indexes) {
            if (index < 0 || index > elements.size() + 1) {
                throw new IllegalArgumentException();
            }
            elements.set(index, elements.get(index));
            System.out.println(elements);
        }
        return elements;
    }

    @Override
    public int getListSize(List<String> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public List<Long> merge(List<Integer> first, List<Long> second, List<String> third) throws IllegalArgumentException, NullPointerException, RuntimeException {
        if (first == null || second == null || third == null) {
            throw new RuntimeException();
        }
        List<Long> res = new ArrayList<>();
        for (Integer integer : first) {
            if (integer == null) {
                throw new NullPointerException();
            }
            res.add(Long.valueOf(integer));
        }
        res.addAll(second);
        for (String s : third) {
            if (s == null) {
                throw new NullPointerException();
            }
            res.add(Long.parseLong(s));
        }
        System.out.println(res);
        return res;
    }

    @Override
    public int findMaxValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        List<Integer> array = new ArrayList<>();
        array.addAll(first);
        array.addAll(second);
        array.addAll(third);
        int max = array.get(0);
        for (Integer integer : array) {
            if (integer > max) {
                max = integer;
            }
        }
        //return Collections.max(array);
        return max;
    }

    @Override
    public int findMinValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        List<Integer> array = new ArrayList<>();
        array.addAll(first);
        array.addAll(second);
        array.addAll(third);
        int min = array.get(0);
        for (Integer integer : array) {
            if (integer < min) {
                min = integer;
            }
        }
        return min;
    }

    @Override
    public int multiplyMax2Elements(List<Integer> first, List<Integer> second, List<Integer> third) throws RuntimeException {
        if (first == null || second == null || third == null) {
            throw new RuntimeException();
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(first);
        list.addAll(second);
        list.addAll(third);
        int[] res = new int[2];
        int max = Integer.MIN_VALUE;
        int count = 0;
        while (count != 2) {
            for (int i = 0, j = count; i < list.size(); i++) {
                if (list.get(i) >= max) {
                    if (j != 0 && res[j - 1] >= list.get(i)) {
                        max = list.get(i);
                    }
                    if (j == 0) {
                        max = list.get(i);
                    }
                }
                res[j] = max;
            }
            max = Integer.MIN_VALUE;
            count++;
        }
        int sum = 1;
        for (int i : res) {
            if (i >= Integer.MAX_VALUE) {
                return 1;
            }
            sum *= i;
        }
        return sum;
    }

    @Override
    public List<String> removeNulls(List<String> list) throws RuntimeException {
        //list.stream().filter(s -> (s != null && s.length() > 0)).toList()
        if (list == null) {
            throw new RuntimeException();
        }
        List<String> s = new ArrayList<>();
        for (String i : list) {
            if (i != null && i.length() > 0) {
                s.add(i);
            }
        }
        return s;
    }

    @Override
    public List<Integer> flatMapWithoutNulls(List<List<Integer>> list) throws NoSuchElementException {
        if (list == null) {
            throw new NoSuchElementException();
        }
        List<Integer> res = new ArrayList<>();
        for (List<Integer> l : list) {
            res.addAll(l.stream().filter(Objects::nonNull).toList());
        }
        return res;
    }

    @Override
    public List<Integer> cloneIds(List<Integer> originalIds) throws NoSuchElementException {
        if (originalIds == null) {
            throw new NoSuchElementException();
        }
        List<Integer> res = new ArrayList<>();
        for (Integer i : originalIds) {
            if (i != null) {
                res.add(i);
            }
        }
        return res;
    }

    @Override
    public List<String> shuffle(List<String> originalStrings) throws RuntimeException {
        if (originalStrings == null) {
            throw new RuntimeException();
        }
        LinkedList<String> res = new LinkedList<>(originalStrings);
        Collections.shuffle(res);
        return res;
    }

    @Override
    public String getLastElement(LinkedList<String> list) throws NoSuchElementException {
        if (list == null) {
            throw new NoSuchElementException();
        }
        if (list.isEmpty()) {
            return "";
        }
        if (list.getLast() == null) {
            throw new NoSuchElementException();
        } else {
            return list.getLast();
        }

    }

    @Override
    public List<String> compareElements(LinkedList<String> originalCollection, LinkedList<String> additionalCollection) throws IllegalArgumentException {
        if (originalCollection == null || additionalCollection == null) {
            throw new IllegalArgumentException();
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < originalCollection.size(); i++) {
            for (int j = 0; j < additionalCollection.size(); j++) {
                if (originalCollection.get(i).equals(additionalCollection.get(j))) {
                    res.add(originalCollection.get(i));
                }
            }
        }
        return res;
    }
}
