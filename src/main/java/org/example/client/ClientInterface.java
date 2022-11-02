package org.example.client;

import org.example.dataPackets.DataPackage;
import org.example.dataPackets.DataTransferInterface;

import java.io.IOException;
import java.net.Socket;

public interface ClientInterface {

    /**
     * This method simply sends the data packet to the specified socket.
     * @param dataPacket The data packet to be sent.
     * @param socket The socket to which the data packet will be sent.
     * @throws IOException If an error occurs while sending the data packet.
     */
    static void sendDataPacket(DataPackage dataPacket, Socket socket) {

        try {

            DataTransferInterface.sendDataPacket(dataPacket, socket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This method simply instantiates a new socket on the specified port.
     * @param port The port on which the socket will be instantiated.
     * @return The socket.
     * @throws IOException If an error occurs while instantiating the socket.
     */
    static Socket instantiateSocket(int port) throws IOException {

        return new Socket("localhost", port);

    }

}
