/**
 * 
 */
package ResourceScheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Tomasz Daraszewicz
 */
public class MessageFactory {

	
	/**
	 * Creates n random messages (with 4 chars) grouped into m groups.
	 * Some groups may not contain messages.
	 */
	public static List<Message> generateList (int n, int m){

		
		
		if (m > n) m = n;
		
		List<Message> list = new LinkedList<>();
		String message = null;
		
		for (int i = 0; i < n; i++){
	
			long range = 1234567L;
			Random r = new Random();
			long number = (long)(r.nextDouble()*range);
			message = Long.toString(number,16).toUpperCase();
			
			int j = (int)(m*Math.random());
			list.add(new Message(i+1, message, j+1));
		}
		
		return list;
	}
	
		
}
