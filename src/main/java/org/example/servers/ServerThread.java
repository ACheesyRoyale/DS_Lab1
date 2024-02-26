package org.example.servers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread{
    Socket clientSocket;
    long updateTime;
    public ServerThread(Socket socket) {
        clientSocket = socket;
        System.out.println("New client connected");
        updateTime = System.nanoTime();
    }

    public void run() {
        while (true) {
            long elapsed = Math.abs(updateTime - System.nanoTime());
            // send time every 10s
            if (elapsed > (10_000_000_000L)) {
                try {

                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println(new Date());


                } catch (IOException ex) {
                    System.out.println("Server Thread error: " + ex.getMessage());
                    ex.printStackTrace();
                }
                updateTime = System.nanoTime();
            }
        }

    }
}
