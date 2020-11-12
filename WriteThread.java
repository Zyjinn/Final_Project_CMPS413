// ! Imports
import java.io.*;
import java.net.*;

// * This thread class is used to write user from clients to the server
// * use 'stop' to quit the infinite loop
public class WriteThread extends Thread {
    // Params
    private PrintWriter writer;
    private Socket socket;
    private Client client;

    // * Primary class constructor
    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            // ! Trouble getting output stream
        } catch (IOException e){
            System.out.println("Error getting the output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        Console console = System.console(); // create a console obj. Use to get input

        // Set the username of the client
        String username = console.readLine("\nEnter your username!: ");
        client.setUsername(username);
        writer.println(username);

        // Print msg typed into console 
        String msg;
        msg = console.readLine("[" + username + "]: ");
        writer.println(msg);

        // Continue to print msgs until the user types 'stop' into the console
        while (!msg.equals("stop")){
            msg = console.readLine("[" + username + "]: ");
            writer.println(msg);
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error when writing to server: " + e.getMessage());
        }
    }
}