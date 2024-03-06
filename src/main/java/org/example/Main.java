package org.example;

import org.example.clients.TCPClient;
import org.example.clients.UDPClient;
import org.example.servers.TCPServer;
import org.example.servers.UDPServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("Hello world!");
//        TCPClient[] tcpClients = new TCPClient[3];
//        TCPServer tcpServer = new TCPServer(6868);
//
//        for (int i = 0; i < 3; i++) {
//            tcpClients[i] = new TCPClient("localhost", 6868);
//        }
//
        UDPClient[] udpClients = new UDPClient[3];
        new UDPServer(1234);

        for (int i = 0; i < 3; i++) {
            udpClients[i] = new UDPClient("localhost", 1234);
        }

    }
}