package com.awsomeness.app;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * Print usage help.
     */
    public static void printHelp(){
        System.err.println("Usage:");
        System.err.println("      java IShotYouDown <server|client> <hostname> <port>");
    }

    /**
     * Parse, check command line arguments and run either a server or a client 
     * application.
     *
     * @params args the array containing string for mode, port, and server name.
     */
    public static void main( String[] args )
    {
        /*
         * Check if 3 arguments are present.
         */
        if(args.length != 3){
            printHelp();
            System.exit(1);
        }
        
        String actionMode = args[0];
        String serverName = args[1];
        int port = 0;
        
        /*
         * Parse integer, abort in case o parse error printing help.
         */
        try{
            port = Integer.parseInt(args[2]);
        }catch(NumberFormatException e){
            System.err.println("Error, expecting port is not parsible into int : "+args[2]);
            printHelp();
            System.exit(1);
        }

        /*
         * Take the action depending on the actionMode parameter.
         */
        if(actionMode.equals("server")){
            IShotYouDownServer server = new IShotYouDownServer(port);
        }else if(actionMode.equals("client")){
            IShotYouDownClient client = new IShotYouDownClient(serverName, port);            
        }else {
            System.err.println("Error, expecting port is not parsible into int : "+args[2]);
            printHelp();
            System.exit(1);
        }

    }
}
