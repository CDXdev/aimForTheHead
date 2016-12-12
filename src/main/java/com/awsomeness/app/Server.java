package com.awsomeness.app;

import java.io.*;
import java.net.*;

/**
 * Main server class run as a standalone to make the game.
 *
 */
public class IShotYouDownServer {

    /**
     * Constroct a simple server that waits and 
     */
    public IShotYouDownServer(int port) {
        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;

        try {
           echoServer = new ServerSocket(port);
        }
        catch (IOException e) {
           System.out.println(e);
        }   

        try {
            clientSocket = echoServer.accept();
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            while (true) {
                line = is.readLine();
                os.println(line); 
            }
        }   
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
