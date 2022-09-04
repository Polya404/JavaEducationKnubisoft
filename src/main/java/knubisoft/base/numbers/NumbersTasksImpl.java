package knubisoft.base.numbers;

import java.math.BigInteger;
import java.util.Arrays;

public class NumbersTasksImpl implements NumbersTasks {

    @Override
    public String swapTwoNumbersWithoutUsingTemporaryVariable(int firstNumber, int secondNumber) {
        firstNumber = firstNumber + secondNumber - (secondNumber = firstNumber);
        return Arrays.toString(new int[]{firstNumber, secondNumber});
    }

    @Override
    public boolean isUglyInt(int number) {
        if (number < 0) {
            return false;
        }
        int tmp;
        do {
            tmp = number;
            for (int i : new int[]{2, 3, 5}) {
                if (number % i == 0) {
                    number = number / i;
                    break;
                }
            }
        } while (tmp != number);
        return number == 1;
    }

    @Override
    public int addDigits(int number) {
        int i;
        int x1 = number;
        int[] arr;
        int c = 0;
        if (number == 0) {
            return 0;
        }
        while ((number > 9)) {
            for (; number > 0; number /= 10) {
                c += 1;
            }
            arr = new int[c];
            for (int j = 0; x1 > 0; x1 /= 10, j++) {
                i = x1 % 10;
                arr[j] = i;
            }
            for (int value : arr) {
                number = number + value;
                x1 = number;
            }
        }
        return number;
    }

    @Override
    public boolean isHarshadNumber(int number) {
        if (number == 0) {
            return false;
        }
        int tmp = number;
        int i = 0;
        while (number != 0) {
            i += number % 10;
            number /= 10;
        }
        return tmp % i == 0;
    }

    @Override
    public boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    @Override
    public boolean isArmstrongNumber(int number) {
        String[] strArray = Integer.toString(number).split("");
        int pow = strArray.length;

        int result = 0;

        for (String s : strArray) {
            result += Math.pow(Integer.parseInt(s), pow);
        }
//        if (number == result) {
//            System.out.println(number + " : is Armstrong number");
//        }

        return number == result;
    }

    @Override
    public BigInteger factorial(int number) {
        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 2; i <= number; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    @Override
    public boolean palindrome(int number) {
        if (number == 0) {
            return true;
        }
        int i;
        int x1 = number;
        int[] arr;
        int c = 0;
        for (; number > 0; number /= 10) {
            c += 1;
        }
        arr = new int[c];
        for (int j = 0; x1 > 0; x1 /= 10, j++) {
            i = x1 % 10;
            arr[j] = i;
        }

        for (int k = 0, j = arr.length - 1; k < arr.length; k++, j--) {
            if (arr[k] == arr[j]) {
                if ((arr.length / 2 == k) && (arr[k] == arr[j])) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isAutomorphic(int number) {
        long pow = (long) Math.pow(number, 2);
        long tmp = number;
        StringBuffer i = new StringBuffer();
        while (number != 0) {
            i.append(pow % 10);
            number /= 10;
            pow /= 10;
        }
        int num = Integer.parseInt(i.reverse().toString());
        return tmp == num;
    }
}
