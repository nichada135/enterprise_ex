package ex3;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JMSServer implements MessageListener {

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

           //Queue queue = session.createTemporaryQueue();
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new JMSServer());
            connection.start();
            System.out.println("Waiting for requests...");
            System.in.read();

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

    @Override
    public void onMessage(Message message) {
    try {
        if (message instanceof TextMessage) {
            String[] range = ((TextMessage) message).getText().split(",");
            long start = Long.parseLong(range[0].trim());
            long end = Long.parseLong(range[1].trim());

            int primeCount = countPrimesInRange(start, end);

            System.out.println("The number of primes between  " + start + " and " + end + " is " + primeCount);

        } else {    
            System.out.println("Received unknown message type.");
        }
    } catch (JMSException e) {
        e.printStackTrace();
    }
}
    private int countPrimesInRange(long start, long end) {
        AtomicInteger count = new AtomicInteger(0);

        for (long number = start; number <= end; number++) {
            if (isPrime(number)) {
                count.incrementAndGet();
            }
        }

        return count.get();
    }

    private boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }

        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
