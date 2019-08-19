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
	Destination destination = session.createQueue("queueOne");
	MessageConsumer consumer = session.createConsumer(destination);

	MessageListener listner = message -> {
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Received message" + " " + textMessage.getText() + "'");
			}
		} catch (JMSException e) {
			System.out.println("Caught:" + e);
			e.printStackTrace();
		}
	};
	Destination destination1 = session.createQueue("queueTwo");
	MessageProducer producer = session.createProducer(destination1);
       	
		return 0;
	}//end of action


	public int end() throws Throwable {
       	
       	connection.close();
       	
		return 0;
	}//end of end
}
