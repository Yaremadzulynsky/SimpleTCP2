package org.example.testing.exampleOne;


import org.example.client.SimpleClient;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException {


            SimpleClient client = new SimpleClient(8080);
            client.runClient();


    }


}
