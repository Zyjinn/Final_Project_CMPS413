// declare inside package?

// imports
import java.io.*;
import java.net.*;
// * Class for the UserThread, which handles the behavior of user threads inside of a hashset in the Server.java file
public class UserThread extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

    // * UserThread constructor
    public UserThread(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    // * Run the usersThreads
    public void run() {
        // Try catch to catch exceptions, duh
        try {
            // Open an input stream
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Setup an output stream
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            listUsers(); // list all users

            // add a username based on the user's input
            String username = reader.readLine();
            server.addUserName(username);

            // Broadcast a message that a new user has connected
            String serverMessage = "User: " + "[" + username + "]: has connected!";
            server.broadcast(serverMessage, this);
        
            // get initial message
            String clientMessage;
            clientMessage = reader.readLine();
            if(clientMessage.equals("dm")){
                // TODO Get the username to dm
                // List users to give choices
                listUsers();
                String dmUser = reader.readLine();
                server.directMessage(clientMessage, this);

                // TODO Send the DM
            }
            else {
                serverMessage = username + ":" + clientMessage;
                server.broadcast(serverMessage, this);
                
            }
            serverMessage = username + ":" + clientMessage;
            server.broadcast(serverMessage, this);

            // Continue to send mgs until the client message is not "end"
            while (!clientMessage.equals("end")){
                clientMessage = reader.readLine();

                // Send a direct message
                if(clientMessage.equals("dm")){

                }
                else {
                    serverMessage = "[" + username + "]: " + clientMessage;
                    server.broadcast(serverMessage, this);
                    
                }
            }

            // delete a user and close the socket
            server.deleteUser(username, this);
            socket.close();

            // User leaves the chat message
            serverMessage = username + " Has left the chat...";
            server.broadcast(serverMessage, this);
        
        // ! Exception found in users 
        } catch (IOException e){
            
            System.out.println("Error encountered in user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // * ListUsers() Lists all users online (called as each user joins)
	private void listUsers() {
        if (server.connectedUsers()) {
            writer.println("Users: " + server.getUserNames());
        } else {
            writer.println("No other users online");
        }
    }
    

    // * sendMessage() sends a message to the client
	public void sendMessage(String message) {
        writer.println(message);
	}

}