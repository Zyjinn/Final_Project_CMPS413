import java.io.*;  
import java.net.*;  
public class Client {  
public static void main(String[] args) {  
    try{      
        Socket myClientSocket = new Socket("localhost", 6666);  

        DataOutputStream dataStreamOut = new DataOutputStream(myClientSocket.getOutputStream());  
        
        dataStreamOut.writeUTF("Hello Server");  

        dataStreamOut.flush(); // Get rid of everything in dout

        // close the socket and connection
        dataStreamOut.close();
        myClientSocket.close();  

    } catch(Exception e){
        System.out.println(e); // Print an exception if we get one
    }  
}  
}  