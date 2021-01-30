package presentation.ProducerConsumer.WaitNotify;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali Tsvirko
 */
public class Producer implements Runnable {
    private final List<Integer> queue;
    private final int maxSize;
    private final int producerRequestNumber;

    public Producer(List<Integer> queue, int maxSize, int producerRequestNumber) {
        this.queue = queue;
        this.maxSize = maxSize;
        this.producerRequestNumber = producerRequestNumber;
    }

     public void run() {
        int producerCounter = 0;

         while (producerCounter < producerRequestNumber) {
             synchronized (queue) {
                 while (queue.size() == maxSize) {
                     try {
                         System.out.println("\tОчередь заполнена, ждем пока заберут данные...");
                         queue.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }

                 Random random = new Random();
                 int i = random.nextInt(1000);
                 System.out.println("Производим данные: " + i);
                 queue.add(i);
                 producerCounter++;
                 queue.notifyAll();

                 try {
                     TimeUnit.MILLISECONDS.sleep(10);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
}