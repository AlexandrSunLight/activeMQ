/*
 * LoadRunner Java script. (Build: _build_number_)
 * 
 * Script Description: 
 *                     
 */

import lrapi.lr;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.io.IOException;

public class Actions
{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	Connection connection;
	
	public int init() throws Throwable {
		try{
		connection = connectionFactory.createConnection();
		}
		catch(Exception e){}
		
		connection.start();
            	
		return 0;
	}//end of init


	public int action() throws Throwable {
       	
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destinationOne = session.createQueue(lr.eval_string("{FirstQueueName}"));
		MessageConsumer consumer = session.createConsumer(destinationOne);
	
		Message msg = consumer.receive();
		
		Destination destinationTwo = session.createQueue(lr.eval_string("{SecondQueueName}"));
		MessageProducer producer = session.createProducer(destinationTwo);
		producer = session.createProducer(destinationTwo);
		TextMessage message = session.createTextMessage(((TextMessage) msg).getText());
		producer.send(message);
		session.close();
		
		return 0;
	}//end of action


	public int end() throws Throwable {
       	
       	connection.close();
       	
		return 0;
	}//end of end
}
