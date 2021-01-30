package presentation.ProducerConsumer.BlockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali Tsvirko
 */
public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

     public void run() {
         while (true) {
             Random random = new Random();
             int i = random.nextInt(1000);
             try {
                 queue.put(i);
                 System.out.println("Производим данные: " + i);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             try {
                 TimeUnit.MILLISECONDS.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

         }
     }
}