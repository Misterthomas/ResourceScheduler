/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourceScheduler;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
class Producer implements Runnable {

    List<Message> sharedQueue = new LinkedList<>();
    private final int SIZE;
    List<Message> list = new LinkedList<>();
    

    public Producer(List<Message> sharedQueue, int size,List<Message> list ) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Send: " + list.get(i));
            try {
                produce(list.get(i));
              
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void produce(Message list) throws InterruptedException {

        //wait if queue is full
        while (sharedQueue.size() == SIZE) {
            synchronized (sharedQueue) {
                System.out.println("Queue is full " + Thread.currentThread().getName()
                                    + " is waiting , size: " + sharedQueue.size());

                Message.MessagePrioritization(Gateway.lastIdProcessed, sharedQueue);
             
                sharedQueue.wait();
                
            }
        }

        //producing element and notify consumers
        synchronized (sharedQueue) {
       
            sharedQueue.add(list);
            
            sharedQueue.notifyAll();
           
        }
    }
}