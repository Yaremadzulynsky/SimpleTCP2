package org.example.testing.exampleTwo;

import org.example.server.ServerInterface;
import org.example.server.SimpleServer;

import java.io.IOException;

public class Server{

    public static void main(String[] args) throws IOException {

        SimpleServer.receivePacket(8080);

    }

}
