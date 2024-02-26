package org.example.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    Socket socket;
    String hostname;

    InputStream input;

    int serverPort;

    public TCPClient(String hostname, int serverPort) {
        this.hostname = hostname;
        this.serverPort = serverPort;
        init();
    }

    private void init() {
        try {
            socket = new Socket(hostname, serverPort);
            input = socket.getInputStream();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("Client socket init error: " + ex.getMessage());
        }

        new Thread(this::run).start();
    }

    public void run() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);
            } catch (IOException ex) {

                System.out.println("I/O error: " + ex.getMessage());
            }
        }
    }

}
