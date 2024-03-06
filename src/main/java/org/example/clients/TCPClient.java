package org.example.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    Socket socket;
    String serverHostname;

    InputStream input;

    int serverPort;

    public TCPClient(String server, int serverPort) {
        this.serverHostname = server;
        this.serverPort = serverPort;
        init();
    }

    public TCPClient() {
        this.serverHostname = "127.0.0.1";
        this.serverPort = 5000;
        init();
    }

    private void init() {
        try {
            socket = new Socket(serverHostname, serverPort);
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
