// Imports
import java.io.*;  
import java.net.*;  

// * Class for client, which sends input to the server in portnum at hostname specified
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
// * Given: hostname portNum
// * Return: start a client
    public static void main(String[] args) {  
        if (args.length <2) return; // ! not enough args

        String hostname = args[0];
        int portNum = Integer.parseInt(args[1]);

        Client myClient = new Client(hostname, portNum);
        myClient.execute(); 
    }  
}  