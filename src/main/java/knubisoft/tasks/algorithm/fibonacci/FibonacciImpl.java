package knubisoft.tasks.algorithm.fibonacci;

public class FibonacciImpl implements Fibonacci {
    @Override
    public int generateNFibonacciSequence(int n) {
        //recursion O(2^n)
//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        } else  {
//            return generateNFibonacciSequence(n - 1) + generateNFibonacciSequence(n - 2);
//        }

        // O(n)
        long first = 0;
        long second = 1;
        long result = n;
        for (int i = 1; i < n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return (int) result;


    }

}
