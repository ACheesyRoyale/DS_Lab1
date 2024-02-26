package org.example.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    // port server listens on
    int port;

    // socket
    ServerSocket serverSocket;

    public TCPServer(int port) {
        this.port = port;
        init();
    }

    // try to start the socket
    public void init() {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
        }
        catch(IOException ex){
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        new Thread(this::run).start();
    }

    // run the server
    public void run() {
        while (true) {
            try {
                // accept client-request, if there is one.
                Socket clientSocket = serverSocket.accept();

                new ServerThread(clientSocket).start();
            }

            catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }

        }
    }

    public int getPort() {
        return port;
    }



    public void setPort(int port) {
        this.port = port;
    }


}
