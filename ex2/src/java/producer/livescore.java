
package producer;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import java.util.Scanner;

public class livescore {

    @Resource(mappedName = "jms/SimpleJMSTopic")
    private static Topic topic;

    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination dest = (Destination) topic;
            MessageProducer producer = session.createProducer(dest);
            TextMessage message = session.createTextMessage();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter Live Score : ");
                String footballResult = scanner.nextLine();

                if ("exit".equalsIgnoreCase(footballResult)) {
                    break;
                }

                message.setText("updated!: " + footballResult);
                producer.send(message);
            }

        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
