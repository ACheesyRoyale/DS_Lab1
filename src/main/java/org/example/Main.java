package org.example;

import org.example.clients.TCPClient;
import org.example.servers.TCPServer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TCPClient[] tcpClients = new TCPClient[3];
        TCPServer tcpServer = new TCPServer(6868);

        for (int i = 0; i < 3; i++) {
            tcpClients[i] = new TCPClient("localhost", 6868);
        }

    }
}