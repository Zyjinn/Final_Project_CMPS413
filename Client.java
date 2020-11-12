// Imports
import java.io.*;  
import java.net.*;  

// * Class for client
public class Client {
    // Params
    private String hostname;
    private int portNum;
    private String username;

    // * Primary Constructor for Client class
    public Client(String hostname, int portNum){
        this.hostname = hostname;
        this.portNum = portNum;
    }

    // * Execute the client
    public void execute() {
        try { 
            Socket socket = new Socket(hostname, portNum);

            System.out.println("Connected to the chat server successfully!");

            // Start a thread to read input
            new ReadThread(socket, this).start();

            // Start a thread to write input
            new WriteThread(socket, this).start();
        
        // ! Can't find the server
        } catch (UnknownHostException e) {
            System.out.println("The server specified was not found: " + e.getMessage());

        // ! Input error
        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }

    }

    // * setter for the username parameter for the client
    void setUsername(String username) {
        this.username = username;
    }

    // * getter for the username parameter for the client
    String getUsername() {
        return this.username;
    }

    
// * Main function to operate client initialization and chatting 
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