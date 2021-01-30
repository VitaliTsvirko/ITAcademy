package presentation.ProducerConsumer.WaitNotify;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class ProducerConsumerWaitNotifyMain {
    public static void main(String[] args) throws InterruptedException {
        int maxSize = 10;
        List<Integer> buffer = new LinkedList<>();

        Thread producer = new Thread(new Producer(buffer, maxSize, 100));
        Thread consumer1 = new Thread(new Consumer(buffer, maxSize));
        Thread consumer2 = new Thread(new Consumer(buffer, maxSize));

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
