package org.example.server;

import lombok.Data;
import org.example.client.ClientInterface;
import org.example.client.SimpleClient;
import org.example.dataPackets.DataPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.AbstractMap;
import java.util.function.Consumer;

@Data
public class SimpleServer implements ServerInterface {

    private ServerSocket socket;
    private Consumer consumer;
    private static String terminator = "terminate";
    private static boolean closeServer = false;
    private static int port = 8080;

    /**
     * Creates a new server with the given port and consumer.
     *
     * @param serverSocket The server socket to use.
     */
    public SimpleServer(ServerSocket serverSocket) {
        this.socket = serverSocket;
    }

    public static Object receivePacket(int port)  {

        Socket clientSocket = null;

        try {
            ServerSocket serverSocket = ServerInterface.instantiateSocket(port);
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ServerInterface.receiveDataPacket(clientSocket);
    }

    public static DataPackage<String> terminate()   {
        return new DataPackage<>("Server.terminate()", "terminate");
    }
    public void runServer() {

        while (!closeServer) {

//            if(closeServer) {break;}

            try {
                Socket clientSocket = socket.accept();
//                System.out.println("Client connected" + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();

            } catch (Exception e) {

//                System.out.println("Server: " + e.getMessage());

            }

        }

    }


    @Data
    public static class ClientHandler implements Runnable {

        private final Socket clientSocket;

        /**
         * @param socket The client socket.
         */
        public ClientHandler(Socket socket) {

            this.clientSocket = socket;

        }

        @Override
        public void run() {

            while (true) {

                try {

                    DataPackage dataPackage = ServerInterface.receiveDataPacket(clientSocket);

                    if(dataPackage.getHeader().compareToIgnoreCase("Server.terminate()") == 0)  {
                        closeServer = true;
                        clientSocket.close();
                        SimpleClient.sendPacket(SimpleServer.terminate(), port);
                        break;
                    }

                    if (dataPackage.getHeader().compareToIgnoreCase(terminator) == 0) {
//                        System.out.println("Client Socket Terminated");
                        clientSocket.close();
                        break;
                    }

                    if (dataPackage.getHeader().equals("message") && !dataPackage.getData().equals("terminate")) {
                        System.out.println("Received data package: " + dataPackage.getData());
                    }


//                    System.out.print("\n");

                } catch (SocketException e) {

//                    System.out.println("Client disconnected");

                    break;

                } catch (Exception e) {

//                    System.out.println("ClientHandler: " + e.getMessage());
                    break;

                }

            }

        }
    }
}
