import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        Scanner inFromServer = null;
        try {
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 6789);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new Scanner(clientSocket.getInputStream());

            while (true) {
                System.out.print("Enter number 1 (to end just press enter): ");
                sentence = inFromUser.nextLine();
                if (sentence.isEmpty()) {
                    break; 
                }
                outToServer.writeBytes(sentence + '\n');
                System.out.print("Enter number 2 (to end just press enter): ");
                sentence = inFromUser.nextLine();
                if (sentence.isEmpty()) {
                    break; 
                }
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.nextLine();
                System.out.println("The result is " + modifiedSentence);
            }
        } catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        } finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
                if (inFromUser != null)
                    inFromUser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
