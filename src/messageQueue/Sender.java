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
	// ConnectionFactory ：连接工厂，JMS 用它创建连接
    private ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS Provider 的连接
    private Connection connection = null;
    // Session： 一个发送或接收消息的线程
    private Session session;
    // Destination ：消息的目的地;消息发送给谁.
    private Destination destination;
    // MessageProducer：消息发送者
    private MessageProducer producer;
    // TextMessage message;
    // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
    public void go(String mes,String Rec){
        try {
        	connectionFactory= new ActiveMQConnectionFactory(
        	            ActiveMQConnection.DEFAULT_USER,
        	            ActiveMQConnection.DEFAULT_PASSWORD,
        	            "tcp://localhost:61616");
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue(Rec);
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构造消息，此处写死，项目就是参数，或者方法获取
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
            // 发送消息到目的地方
            producer.send(message);
    }
}
