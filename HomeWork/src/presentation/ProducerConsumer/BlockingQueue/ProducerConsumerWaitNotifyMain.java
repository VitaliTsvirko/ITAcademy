package presentation.ProducerConsumer.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Vitali Tsvirko
 */
public class ProducerConsumerWaitNotifyMain {
    public static void main(String[] args) throws InterruptedException {
        int maxSize = 10;
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(maxSize);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer1 = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer(queue));

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
