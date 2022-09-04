package knubisoft.multithreading;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Queue {
    public static void main(String[] args) {
        int count = 5;
        List<Thread> processors = createProcessors(count);
        processors.forEach(Thread::start);
    }

    private static List<Thread> createProcessors(int count) {
        List<BlockingQueue<String>> queues = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            queues.add(new LinkedBlockingDeque<>());
        }
        List<Thread> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            BlockingQueue<String> to = queues.get(i);
            BlockingQueue<String> from = i - 1 < 0 ? queues.get(queues.size() - 1) : queues.get(i - 1);
            result.add(new EventProcessor(String.valueOf(i), to, from));
        }
        queues.get(queues.size() - 1).add("START");
        return result;
    }

    @RequiredArgsConstructor
    static class EventProcessor extends Thread {
        private final String event;
        private final BlockingQueue<String> sendTo;
        private final BlockingQueue<String> readFrom;

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                String value = readFrom.take();
                Thread.sleep(1000);
                System.out.println(value);
                sendTo.add(event);
            }
        }
    }
}
