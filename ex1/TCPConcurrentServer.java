//TCPConcurrentServer.java 
import java.io.*;
import java.net.*;
import java.util.*;
public class TCPConcurrentServer {
    public static void main(String argv[]) {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(1667); 
            System.out.println("Server Waiting for client connection at port number 1667");
        } catch (IOException e) {
            System.out.println("Cannot create a welcome socket");
            System.exit(1);
        }
        while (true) {
            try {
                System.out.println("The server is waiting ");
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);
                clientSentence = inFromClient.readLine();
                capitalizedSentence = clientSentence.toUpperCase();
                outToClient.println(capitalizedSentence);
                connectionSocket.close();
            } catch (IOException e) {
                System.out.println("Error during communication with the client");
            }
        }
    }
}
