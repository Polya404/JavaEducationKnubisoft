package knubisoft.multithreading;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new java.util.concurrent.SynchronousQueue<>();

        new Producer(queue);
        new Consumer(queue);
    }
}

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put(produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer produce() {
        Random random = new Random();
        Integer number = random.nextInt(100);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Producer: created number: " + number);
        return number;
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer number = queue.take();
                consume(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume(Integer number) {
        System.out.println("Consumer processed number: " + number);
    }
}
