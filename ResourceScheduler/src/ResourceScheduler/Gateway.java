/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourceScheduler;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Tomasz Daraszewicz
 */
class Gateway implements Runnable {
	
	
	int Low = 500;
	int High = 1500;
	

    List<Message> sharedQueue = new LinkedList<>();
    @SuppressWarnings("unused")
	// final ?
    private final int SIZE;
    List<Message> list = new LinkedList<>();
    
    public static int lastIdProcessed;

    public Gateway(List<Message> sharedQueue, int size, List<Message> list) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Processed: " + send());
                Random r = new Random();
                int R = r.nextInt(High-Low) + Low;
               // System.out.println("From Gateway" +  Gateway.lastIdProcessed);
                Thread.sleep(R);
            } catch (InterruptedException ex) {
                Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private Message send() throws InterruptedException {
        //wait if queue is empty
        while (sharedQueue.isEmpty()) {
            synchronized (sharedQueue) {
                System.out.println("Queue is empty " + Thread.currentThread().getName()
                                    + " is waiting , size: " + sharedQueue.size());
             
                sharedQueue.wait();
            }
        }

        //Otherwise consume element and notify waiting producer
        synchronized (sharedQueue) {
        	Message firstinlist = sharedQueue.get(0);
        	 lastIdProcessed = firstinlist.getGroupID();
           
            sharedQueue.notifyAll();
            
            
          System.out.println( Message.completed());
       
          
            return (Message) sharedQueue.remove(0);
        }
    }
}
