package org.example.servers;

import java.io.IOException;
import java.net.*;

public class TCPServer {
    // port server listens on
    int port;

    // socket
    ServerSocket serverSocket;

    String hostname = "127.0.0.1";

    SocketAddress socketAddress;

    public TCPServer(int port) {
        this.port = port;
        init();
    }

    public TCPServer(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        init();
    }

    public TCPServer() {
        this.port = 5000;
        init();
    }

    // try to start the socket
    public void init() {
        try {
            socketAddress = new InetSocketAddress(hostname, port);
            serverSocket = new ServerSocket();
            serverSocket.bind(socketAddress);
            System.out.println("Server is listening on " + hostname + ", port " + port);
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
