
package asynconsumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String footballResult = textMessage.getText();
                System.out.println("updated!: " + footballResult);
            } catch (Exception e) {
                System.err.println("Error processing message: " + e.toString());
            }
        }
    }
}
