/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourceScheduler;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Tomasz Daraszewicz
 */
public class Message implements Iterable<Message> {
    
   
    private int id;
    private String message;
    private int groupID;
    
   
    
    public Message(int id, String message, int groupID) {
        this.id = id;
        this.message = message;
        this.groupID = groupID ;
    }
    
    public Message(int id, String message) {
        this.id = id;
        this.message = message;
        this.setGroupID(0);
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{ID:" + id + ", GroupID: " + groupID + " message:" + message + "}";
    }

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	
	
	public static String completed()
	{
		
		return "The message has been completed";
	}
	
	 public static Comparator<Message> testing = new Comparator<Message>() {

	public int compare(Message m1, Message m2) {

	   int number1 = m2.getGroupID();
	   int number2 = m2.getGroupID();

	   
	   return number1-number2;

	   
	   
   }};
   
   
   
   /**
    * Method used to prioritize the message queue.
    * @param ID , the group id of the last message processed.
    * @param list, list of messages in the queue.
    * @return
    */
   public static int MessagePrioritization(int ID, List<Message> list) {
	   int i = -1;
       for(Message msg : list) {
    	   ++i;
           if(msg.getGroupID() == ID) {
        	   list.add(0,msg);
        	   list.remove(++i);
               return 1;
           }
       }
       return 0;
   }

@Override
public Iterator<Message> iterator() {
	// TODO Auto-generated method stub
	return null;
}


	
	
    
    
}

