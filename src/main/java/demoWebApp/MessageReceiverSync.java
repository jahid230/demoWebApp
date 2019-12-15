package demoWebApp;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.Enumeration;
 
@Stateless
public class MessageReceiverSync {
 
    @Resource(mappedName = "jms/JMSConnectionFactory")
    private ConnectionFactory connectionFactory;
 
    @Resource(mappedName = "jms/myQueue")
    Queue myQueue;
 
    private String message;
    private String m;
 
    public String receiveMessage() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser queueBrowser = session.createBrowser(myQueue);
            Enumeration enumeration = queueBrowser.getEnumeration();
            while (enumeration.hasMoreElements()) {
                TextMessage o = (TextMessage) enumeration.nextElement();
                m= "Receive " + o.getText();
             }
            session.close();
            connection.close();
            return m;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "";
    }
}