// Imports
import java.io.*;  
import java.net.*;  

// Server class
public class Server {
    public static void main(String[] args){  
    try{  
        ServerSocket mySocket = new ServerSocket(6666); // 
        Socket s = mySocket.accept(); //establishes connection   
        DataInputStream dis = new DataInputStream(s.getInputStream()); //wait for input

        String str = (String) dis.readUTF();
        System.out.println("message= " +str);  
        mySocket.close();  
    }catch(Exception e){
        System.out.println(e);
    }  
}  
}  
