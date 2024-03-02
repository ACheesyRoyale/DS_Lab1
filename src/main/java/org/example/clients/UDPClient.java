package org.example.clients;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;


public class UDPClient {
    DatagramSocket socket;
    String hostname;

    InetAddress address;

    int serverPort;

    public UDPClient(String hostname, int serverPort) {
        this.hostname = hostname;
        this.serverPort = serverPort;
        init();
    }

    public UDPClient() {
        this.hostname = "127.0.0.1";
        this.serverPort = 1234;
        init();
    }

    private void init() {
        try {
            address = InetAddress.getByName(hostname);
            socket = new DatagramSocket();
            DatagramPacket request = new DatagramPacket(new byte[1], 1, address, serverPort);
            socket.send(request);

        }
        catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("Client socket init error: " + ex.getMessage());
        }
        catch (SecurityException ex) {
            System.out.println("Security exception: " + ex.getMessage());
        }

        new Thread(this::run).start();
    }

    public void run() {
        while (true) {
            try {
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String quote = new String(buffer, 0, response.getLength());

                System.out.println(quote);
                System.out.println();

                Thread.sleep(10_001);
            } catch (IOException ex) {

                System.out.println("I/O error: " + ex.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
