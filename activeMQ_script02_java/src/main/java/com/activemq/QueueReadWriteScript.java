package com.activemq;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueReadWriteScript {

    public static void main(String[] args) throws Exception {


        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destinationOne = session.createQueue("Queue_One");
        MessageConsumer consumer = session.createConsumer(destinationOne);

        Message msg = consumer.receive();

        Destination destinationTwo = session.createQueue("Queue_Two");
        MessageProducer producer = session.createProducer(destinationTwo);
        TextMessage message = session.createTextMessage(((TextMessage) msg).getText());
        producer.send(message);
        session.close();


        connection.close();

    }
}
