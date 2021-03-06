// Imports
import java.io.*;  
import java.net.*;  
import java.util.*;

// import jdk.internal.util.xml.impl.Input;

// Server class
public class Server {
    // Server class params.
    private int port; // Port to connect to
    private Set<String> users = new HashSet<>(); // store all of the users
    private Set<UserThread> usersThreads = new HashSet<>(); // store all user threads
    private Map<String, UserThread> userThreadRel = new HashMap<String, UserThread>();
    private UserThread newUser;

    // * Constructor for the Server class 
    public Server(int port) { 
    this.port = port;
    }

    // Execute the chat server
    public void execute() {
        // Create a socket for the server at port specified
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port: " + port);

            while (true) {
                // Accept incoming sockets
                Socket socket = serverSocket.accept();
                System.out.println("User has connected to the server at port: " + port);

                // Create a new user thread using the socket accepted
                UserThread newUser = new UserThread(socket, this);
                usersThreads.add(newUser);
                newUser.start();
            }
        } catch (IOException e){
            // Print error msg.
            System.out.println("Server has encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // * Main function
    public static void main(String[] args) {
        // Nothing was given as args.
        if (args.length < 1) {
            System.out.println("java, Server {port-num}");
            System.exit(0); // exit prog. with exit code 0
        }

        // get the port num from user
        int port = Integer.parseInt(args[0]); 

        Server chatServer = new Server(port); // create a server object
        chatServer.execute(); // start the server
    }

    // * broadcast sends a message from user to all clients, except current
    void broadcast(String message, UserThread currentUser){
        for (UserThread user : usersThreads) {
            // If not the exception user, send the message
            if (user != currentUser) {
                user.sendMessage(message);
            }
        }
    }

    // * directMessage sends a message from the current user to the user specified
    void directMessage(String message, UserThread currentUser, String targetUser){
        if (!userThreadRel.containsKey(targetUser)){
            System.out.println("User not found");
        }
        else{
            // Get the thread of our target user
            UserThread targetThread = userThreadRel.get(targetUser);
            System.out.println("Target username = " + targetUser);
            System.out.println("Target Thread name = " + targetThread);
            

                    // Loop through all threads
            for (UserThread user: usersThreads){

                // Check if this thread matches the target one
                if (targetThread.equals(user)){
                    user.sendMessage(message);
                    System.out.println("Sent a message to user " + "[" + targetUser + "]");
                }
            }

        }


    }

    // * addUserName adds a user to the users hashset
    void addUserName(String username) {
        users.add(username); // add user to hashset of users
        userThreadRel.put(username, newUser);
        System.out.println(userThreadRel.get(username));
    }

    // * deleteUser deletes the specified user and their thread
    void deleteUser(String username, UserThread user) {
        boolean removed = users.remove(username); // remove the username 

        // remove from theads
        if (removed) {
            usersThreads.remove(username); 
            System.out.println("User" + "[" + username + "]: Left the chat");
        }
    }

    // * return all users in the hashset
    Set<String> getUserNames() {
        return this.users;
    }

    // * returns true if there are users connected other than the current
    boolean connectedUsers() {
        return !this.users.isEmpty();
    }

}
