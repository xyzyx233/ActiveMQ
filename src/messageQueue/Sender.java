package messageQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
    private static final int SEND_NUMBER = 5; 
	// ConnectionFactory �����ӹ�����JMS ������������
    private ConnectionFactory connectionFactory;
    // Connection ��JMS �ͻ��˵�JMS Provider ������
    private Connection connection = null;
    // Session�� һ�����ͻ������Ϣ���߳�
    private Session session;
    // Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.
    private Destination destination;
    // MessageProducer����Ϣ������
    private MessageProducer producer;
    // TextMessage message;
    // ����ConnectionFactoryʵ�����󣬴˴�����ActiveMq��ʵ��jar
    public void go(String mes,String Rec){
        try {
        	connectionFactory= new ActiveMQConnectionFactory(
        	            ActiveMQConnection.DEFAULT_USER,
        	            ActiveMQConnection.DEFAULT_PASSWORD,
        	            "tcp://localhost:61616");
            // ����ӹ����õ����Ӷ���
            connection = connectionFactory.createConnection();
            // ����
            connection.start();
            // ��ȡ��������
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // ��ȡsessionע�����ֵxingbo.xu-queue��һ����������queue��������ActiveMq��console����
            destination = session.createQueue(Rec);
            // �õ���Ϣ�����ߡ������ߡ�
            producer = session.createProducer(destination);
            // ���ò��־û����˴�ѧϰ��ʵ�ʸ�����Ŀ����
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // ������Ϣ���˴�д������Ŀ���ǲ��������߷�����ȡ
            sendMessage(session, producer,mes);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
	public void sendMessage(Session session, MessageProducer producer,String mes)
            throws Exception {
            TextMessage message = session
                    .createTextMessage(mes);
            // ������Ϣ��Ŀ�ĵط�
            producer.send(message);
    }
}
