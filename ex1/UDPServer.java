import java.io.*; 
import java.net.*; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
  
class UDPServer { 
  public static void main(String args[]) throws Exception 
    { 
      DatagramSocket serverSocket = new DatagramSocket(9876);
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];
		  while(true) 
      { 
        System.out.println("The server is waiting ");
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentTime.format(formatter);
        sendData = formattedDateTime.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);
        } 
    } 
} 