package messageQueue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JTextArea;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver implements Runnable {

    private String url = "tcp://localhost:61616";
    private String user = null;
    private String password = null;
    private String QUEUE = null;
    private JTextArea output;
 
    public Receiver(String queue,JTextArea output) {
        this.QUEUE = queue;
        this.output=output;
    }
 
    @Override
    public void run() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                user, password, url);
        Session session = null;
        Destination receiveQueue;
        try {
            Connection connection = connectionFactory.createConnection();
 
            session = connection
                    .createSession(true, Session.SESSION_TRANSACTED);
            receiveQueue = session.createQueue(QUEUE);
            MessageConsumer consumer = session.createConsumer(receiveQueue);
 
            connection.start();
 
            while (true) {
                Message message = consumer.receive();
 
                if (message instanceof TextMessage) {
                    TextMessage receiveMessage = (TextMessage) message;
                    String string=receiveMessage.getText();
                    output.setText(string);
                } else {
                    session.commit();
                    break;
                }
 
            }
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
    public String getUrl() {
        return url;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
