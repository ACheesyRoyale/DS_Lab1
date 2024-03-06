package org.example.servers;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    // port server listens on
    int port;

    // socket
    DatagramSocket serverSocket;

    SocketAddress socketAddress;

    String hostname = "localhost";

    public UDPServer() {
        this.port = 1234;
        init();
    }

    public UDPServer(int port) {
        this.port = port;
        init();
    }

    public UDPServer(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        init();
    }

    // try to start the socket
    public void init() {
        try {
            socketAddress = new InetSocketAddress(hostname, port);
            System.out.println(socketAddress);
            this.serverSocket = new DatagramSocket(socketAddress);
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
                byte[] buffer = new byte[256];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(request);

                new UDPServerThread(request, serverSocket).start();
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
