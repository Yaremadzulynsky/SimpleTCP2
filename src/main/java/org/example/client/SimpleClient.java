package org.example.client;

import org.example.dataPackets.DataPackage;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    public Socket socket;
    public static Scanner in = new Scanner(System.in);

    public SimpleClient(int port) throws IOException {
        this.socket = ClientInterface.instantiateSocket(port);
    }

    public static DataPackage<String> terminate()   {
        return new DataPackage<>("Client.terminate()", "terminate");
    }
    public static void sendPacket(DataPackage dataPackage, int port)  {

        while (true) {
            try {


                Socket socket = ClientInterface.instantiateSocket(port);
                ClientInterface.sendDataPacket(dataPackage, socket);
                ClientInterface.sendDataPacket(SimpleClient.terminate(), socket);
                break;
            } catch (ConnectException e) {
                System.out.println("Server is not running. Waiting 10 seconds for server to start...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void runClient() {

        while (true) {

            System.out.println("Enter a message to send to the server: ");
            String input = in.nextLine();

            DataPackage dataPacket = null;
            if(input.compareToIgnoreCase("Server.Terminate()") == 0)    {
                dataPacket = new DataPackage("Server.Terminate()", "Server.Terminate()");
            }
            else {

                dataPacket = new DataPackage("message", input);
            }

            ClientInterface.sendDataPacket(dataPacket, socket);

            if (input.equals("terminate")) {
                break;
            }

        }

    }

}
