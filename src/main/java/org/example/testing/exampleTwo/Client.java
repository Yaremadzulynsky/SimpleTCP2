package org.example.testing.exampleTwo;

import org.example.client.SimpleClient;
import org.example.dataPackets.DataPackage;
import org.example.server.SimpleServer;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

//        SimpleClient.sendPacket(new DataPackage<>("message", "somedata"), 8080);
        SimpleClient.sendPacket(SimpleServer.terminate(), 8080);

    }

}

