package com.awsomeness.app;

import java.io.*;
import java.net.*;

/**
 * Manage the client side of the game, connect to server start the interface
 * keep track of actions and timing.
 */
public class Client {

    private Socket clientSocket = null;  
    private PrintWriter netOutput = null;
    private BufferedReader netInput = null;
    private BufferedReader stdInput = null;

    
    /** 
     * Connect to server and start a client game window.
     *
     * @param hostname contains the peer server to connect.
     * @param port number of port to connect on.
     */
    public Client(String hostname, int port) {
        
        /*
         * Connect to the server.
         */
        try {
            //Initiate the connection with hostname at port
            clientSocket = new Socket(hostname, port);

            //Create pipe to send data to the server
            netOutput = new PrintWriter(clientSocket.getOutputStream(),true);

            //Create pipe to read data from the server
            netInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Create pipe to read keyboard input
            stdInput = new BufferedReader(new InputStreamReader(System.in));
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unknown error!!!");
            System.exit(1);
        }

        //Now keeps in a loop reading from the shell and spreding this message to everybody.
        run();
    }


    /**
     * Sticks in a loop reading input from user and sending it to server.
     */
    private void run(){
        String userInput;
        try{
            while ((userInput = stdInput.readLine()) != null) {
                netOutput.println(userInput);
                System.out.println("echo: " + netInput.readLine());
            }
        } catch (IOException e) {
            closeAll();
            System.err.println("Connection droped!");
            System.exit(1);
        } 
    }

    /**
     * Close all the open streams.
     */
    private void closeAll(){
        try{
            netOutput.close();
            netInput.close(); 
            stdInput.close();
        } catch (IOException e) {
            System.err.println("Problens closing connection!");
            System.exit(1);
        } 
    }
}

