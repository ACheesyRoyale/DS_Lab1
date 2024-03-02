package org.example.servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPServerThread extends Thread{
    InetAddress clientAddress;

    int clientPort;

    long updateTime;

    DatagramSocket socket;
    public UDPServerThread(DatagramPacket request, DatagramSocket serverSocket) {
        clientAddress = request.getAddress();
        clientPort = request.getPort();
        socket = serverSocket;
        System.out.println("New client connected");
        updateTime = System.nanoTime();
    }

    public void run() {
        while (true) {
            long elapsed = Math.abs(updateTime - System.nanoTime());
            // send time every 10s
            if (elapsed > (10_000_000_000L)) {
                try {

                    String data = String.valueOf(new Date());
                    byte[] buffer = data.getBytes();

                    DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                    socket.send(response);


                } catch (IOException ex) {
                    System.out.println("Server Thread error: " + ex.getMessage());
                    ex.printStackTrace();
                }
                updateTime = System.nanoTime();
            }
        }

    }
}
