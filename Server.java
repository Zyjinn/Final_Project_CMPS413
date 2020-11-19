// Imports
import java.io.*;  
import java.net.*;  
import java.util.*;

// Server class
public class Server {
    // Server class params.
    private int port; // Port to connect to
    private Set<String> users = new HashSet<>(); // store all of the users
    private Set<UserThread> usersThreads = new HashSet<>(); // store all user threads

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
    void directMessage(String message, String targetUser){
        // Loop through all threads
        for (UserThread user: usersThreads){
            
            // get the username of the current thread
            String currentUsername = user.getName();
            if (currentUsername == targetUser){
                user.sendMessage(message);
            }
        }
    }

    // * addUserName adds a user to the users hashset
    void addUserName(String username) {
        users.add(username); // add user to hashset of users
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
