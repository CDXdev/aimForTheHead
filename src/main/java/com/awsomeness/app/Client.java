package com.awsomeness.app;

import java.io.*;
import java.net.*;

/**
 * Manage the client side of the game, connect to server start the interface
 * keep track of actions and timing.
 */
public class Client {
    
    /** 
     * Connect to server and start a client game window.
     *
     * @param hostname contains the peer server to connect.
     * @param port number of port to connect on.
     */
    public Client(String hostname, int port) {
        Socket smtpSocket = null;  
        DataOutputStream os = null;
        DataInputStream is = null;

        /*
         * Connect to the server.
         */
        try {
            smtpSocket = new Socket(hostname, port);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }

        if (smtpSocket != null && os != null && is != null) {
            try {
                os.writeBytes("HELO\n");    
                os.writeBytes("MAIL From: k3is@fundy.csd.unbsj.ca\n");
                os.writeBytes("RCPT To: k3is@fundy.csd.unbsj.ca\n");
                os.writeBytes("DATA\n");
                os.writeBytes("From: k3is@fundy.csd.unbsj.ca\n");
                os.writeBytes("Subject: testing\n");
                os.writeBytes("Hi there\n"); // message body
                os.writeBytes("\n.\n");
                os.writeBytes("QUIT");

                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                      break;
                    }
                }
                os.close();
                is.close();
                smtpSocket.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }       
}

