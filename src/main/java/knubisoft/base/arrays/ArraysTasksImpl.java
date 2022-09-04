package knubisoft.base.arrays;

import java.util.HashSet;

public class ArraysTasksImpl implements ArraysTasks {
    @Override
    public int[] reverse(int[] array) {
        int tmp;
        for (int i = (array.length - 1), j = 0; i > (array.length / 2) - 1; i--, j++) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return array;
    }

    @Override
    public int[] mergeArrays(int[] array1, int[] array2) {
        int[] array = new int[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            array[i] = array1[i];
        }
        for (int i = array1.length, j = 0; i < array.length; i++, j++) {
            array[i] = array2[j];
        }
        return array;
    }

    @Override
    public int[] findMax3InArray(int[] array) {
        if (array.length == 0) {
            return array;
        }
        if (array.length < 3) {
            return array;
        }
        int[] res = new int[3];
        int max = Integer.MIN_VALUE;
        int count = 0;
        while (count != 3) {
            for (int i = 0, j = count; i < array.length; i++) {
                if (array[i] > max) {
                    if (j != 0 && res[j - 1] > array[i]) {
                        max = array[i];
                    }
                    if (j == 0) {
                        max = array[i];
                    }
                }
                res[j] = max;
            }
            max = Integer.MIN_VALUE;
            count++;
        }
        return res;
    }

    @Override
    public int findLongestIncreasingContinuesSubsequence(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int len = 1, max = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] < array[i])
                len++;
            else {
                max = Math.max(len, max);
                len = 1;
            }
        }
        return Math.max(max, len);
    }

    @Override
    public int sumOfAllUniqueElements(int[] array) {
        int res = 0;
        HashSet<Integer> count = new HashSet<>();
        for (int num : array) {
            if (count.contains(num)) {
            } else {
                count.add(num);
                res += num;
            }
        }
        return res;
    }

    @Override
    public int[] moveZeroes(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp;
            if (array[i - 1] == 0 && array[i] != 0) {
                tmp = array[i - 1];
                array[i - 1] = array[i];
                array[i] = tmp;
                i--;
            }
            if (i - 1 >= 0 && array[i - 1] == 0 && array[i] != 0) {
                i--;
            }
        }
        return array;
    }

    @Override
    public int findFinalValue(int[] nums, int original) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int num : nums) {
                if (num == original) {
                    original *= 2;
                    flag = true;
                }
            }
        }
        return original;
    }

    @Override
    public String longestCommonPrefix(String[] words) {
        if(words.length == 0){
            return "";
        }
        String t = words[0];
        if(words.length == 1)
            return t;

        for (int i = 0; i < words[0].length(); i++){
            for (String s : words)
                if(i >= s.length() || s.charAt(i) != t.charAt(i))
                    return t.substring(0,i);
        }
        return t;
    }

    @Override
    public int missingNumber(int[] array) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    needIteration = true;
                }
            }
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[0] != 0) {
                return 0;
            }
            if (array[i + 1] != array[i] + 1) {
                return array[i] + 1;
            }
        }
        return array[array.length - 1] + 1;
    }

    private static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;

    }

    @Override
    public boolean containsDuplicate(int[] array) {
        HashSet<Integer> count = new HashSet<>();
        for (int num : array) {
            if (count.contains(num)) {
                return true;
            } else {
                count.add(num);
            }
        }
        return false;
    }
}
