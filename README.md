# Final_Project_CMPS413

## ❓What is this?

A chat application utilizing Java and sockets to create a simple chat application with clients, and servers.

This application was created as part of my CMPS 413, computer networking final project.

## ⚙Overview - How it Works!

The application consists of two primary parts, a Server.java file and a Client.java file. In order for the application to function the Server.java file must be run before the Client.java file(See instructions below).

The server is first run to listen to a specific port on the user's localhost(Has not been tested with real servers).

Once the server is running, Client.java may be run and connect to the server where they will send messages via sockets back and forth between the server in order to send messages to each other!

the UserThreads class deals with most of the logic of each client's execution, and the WriteThread and ReadThread files deal with the writing of messages and requests to each clients console, and the reading of messages of other users respectively.

## 🏃‍♂️Running the Project

- Note that all of the below instructions are for windows terminal, bash terminal commands may differ slightly!
- ❗If you experience issues, try running 'javac {filename}.java' on each file, as the files may not have compiled properly.

### 💽Server

- Open a command line window and type 'java Server {portNum}' to start the server
- The text, "Server is listening on {portnum}" should display.
- Start client windows for each user! (see below).
- to quit, simply kill the terminal for the server application.

### 💻Client

- After starting the server, in a terminal, type 'java Client localhost {portNum}'.

- Once connected as a client, you should be able to see a prompt asking you to enter a user.

- Enter your username

- Begin chatting! All messages you send should send to all other users currently connected!

- Typing 'dm' will initiate a prompt for a user to send a message to rather than the whole server.

  - Note, this feature is a (WIP) and does not fully function.

- To stop chatting, enter 'stop' into the console!
