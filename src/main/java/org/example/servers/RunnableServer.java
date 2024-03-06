package org.example.servers;


public class RunnableServer {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: Client requires args < 1 for TCP | 2 for UDP> <hostname> <port>");
            return;
        }

        int type = Integer.parseInt(args[0]);
        String hostname = args[1];
        int port = Integer.parseInt(args[2]);

        if (type == 1) {
            new TCPServer(hostname, port);
        }
        else if (type == 2) {
            new UDPServer(hostname, port);
        }
        else {
            System.out.println("Wrong type argument");
        }



    }
}
