/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourceScheduler;

import java.util.LinkedList;
import java.util.List;



public class Main {

    public static void main(String args[]) {

    	List<Message> list = new LinkedList<>();
    	List<Message> sharedQueue = new LinkedList<>();
        int size = 4;
    	list = MessageFactory.generateList(10, 5);
    
        System.out.println(list);

        Producer producer = new Producer(sharedQueue, size, list);
        Gateway gateway = new Gateway(sharedQueue, size, list);

        new Thread((producer),"Producer").start();
        new Thread((gateway), "Gateway").start();
        
        
    }
}