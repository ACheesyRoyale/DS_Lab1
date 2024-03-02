package org.example.clients;

public class RunnableClient {
    public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Syntax: Client requires args < 1 for TCP | 2 for UDP> <hostname> <port>");
//            return;
//        }

//        int type = Integer.parseInt(args[0]);
//        String hostname = args[1];
//        int port = Integer.parseInt(args[2]);

        int type = 1;
        String hostname = "localhost";
        int port = 6868;

        if (type == 1) {
            TCPClient tcpClient = new TCPClient(hostname, port);
        }
        else if (type == 2) {
            UDPClient UDPClient = new UDPClient(hostname, port);
        }
        else {
            System.out.println("Wrong type argument");
        }



    }


}
