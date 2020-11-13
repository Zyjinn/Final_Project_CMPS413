// * Imports
import java.io.*;
import java.net.*;

// * Class for the ReadThread which will read input from the server and print 
// * to the console
public class ReadThread extends Thread{
    // Parameters 
    private BufferedReader reader;
    private Socket socket;
    private Client client;

    // * Primary constructor 
    public ReadThread(Socket socket, Client client){
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input)); 
        
            // ! I/O exception
        } catch (IOException e) {
            System.out.println("Error in input stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            try {
                String chatResponse = reader.readLine(); // keep reading input
                System.out.println(chatResponse); // print read input

                // check if user has a username
                if (client.getUsername() != null) {
                    System.out.println("[" + client.getUsername() + "]:");
                }

                // ! Failure to get input from server
            } catch (IOException e) {
                System.out.println("Error reading input from server: " + e.getMessage());
                e.printStackTrace();
                break; // stop getting input from server if it fails
            }
            }
        }
    }