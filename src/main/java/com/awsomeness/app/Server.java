package com.awsomeness.app;

import java.io.*;
import java.net.*;

/**
 * Main server class run as a standalone to make the game.
 */
public class Server {
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private PrintWriter netOutput = null;
    private BufferedReader netInput = null;

    /**
     * Simple ECHO server that waits for connection and sits in a loop 
     * replying the same received message.
     */
    public Server(int port) {

        try {
            //Create the socket, bind to port and listen to connections
            serverSocket = new ServerSocket(port);

        }catch (IOException e) {
            System.out.println("Error creating server socket!");
            System.exit(1);
        }   

        try {
            //Wait for connections...
            clientSocket = serverSocket.accept();

            //Create pipe to send data to the server
            netOutput = new PrintWriter(clientSocket.getOutputStream(),true);

            //Create pipe to read data from the server
            netInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //After a client is connected keep reading the client and send the same back
            while (true) {
                String line = netInput.readLine();
                if(line == null) break;
                System.out.println("Receveid from client : '"+line);               
                netOutput.println("Reply from server :"+line);
            }
        }catch (IOException e) {
            System.out.println(e);
        }

        closeAll();
    }

    /**
     * Close all opened connections.
     */
    public void closeAll(){
        try {
            serverSocket.close();
            clientSocket.close();
            netOutput.close();
            netInput.close();
        }catch (IOException e) {
            System.out.println(e);
        }        
    }
}
