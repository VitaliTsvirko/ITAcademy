package presentation.ProducerConsumer.BlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Vitali Tsvirko
 */
public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Берем данные: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
