
import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSClient {
   @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/SimpleJMSQueue")
    private static Queue queue;

    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, 0);

            MessageProducer producer = session.createProducer(queue);

            Scanner scanner = new Scanner(System.in);
            String input;

            do {
                System.out.print("Enter two number. Use ',' to seperate each number. To end the program press enter : ");
                input = scanner.nextLine();

         
                    String[] range = input.split(",");
                    long start = Long.parseLong(range[0].trim());
                    long end = Long.parseLong(range[1].trim());

                    TextMessage message = session.createTextMessage();
                    message.setText(start + "," + end);
                    producer.send(message);

                    System.out.println("Sending message : " + input);
                

            } while (!input.equalsIgnoreCase("q"));
                producer.send(session.createMessage()); // Sending an empty message to signal the end

        } catch (Exception e) {
            e.printStackTrace();
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