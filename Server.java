// Imports
import java.io.*;  
import java.net.*;  

// Server class
public class Server {
    public static void main(String[] args){  
    try{  
        ServerSocket myServerSocket = new ServerSocket(6666); // 

        // Allow socket to accept input
        Socket mySocket = myServerSocket.accept(); 
        
        //wait for input
        DataInputStream DataStream = new DataInputStream(mySocket.getInputStream()); 

        String clientMsg = (String) DataStream.readUTF();

        // print the message from the client 
        System.out.println("Client sent message...: " + clientMsg); 

        // Close the connection to the server
        myServerSocket.close(); 
    }catch(Exception e){
        System.out.println(e);
    }  
}  
}  
