package presentation.ProducerConsumer.WaitNotify;

import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class Consumer implements Runnable {
    private final List<Integer> queue;
    private final int maxSize;

    public Consumer(List<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    System.out.println("\tОчередь пуста, ждем задач...");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -> Берем данные: " + queue.remove(0));
                queue.notifyAll();
            }
        }
    }

}
