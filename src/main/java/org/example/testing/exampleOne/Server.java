package org.example.testing.exampleOne;

import org.example.server.SimpleServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {

        SimpleServer server = new SimpleServer(new ServerSocket(8080));
        server.runServer();




    }

}
