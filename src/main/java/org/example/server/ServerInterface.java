package org.example.server;

import org.example.dataPackets.DataPackage;
import org.example.dataPackets.DataTransferInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public interface ServerInterface {

    /**
     * This method simply receives the data packet from the specified socket.
     *
     * @param socket The socket from which the data packet will be received.
     * @return The data packet received.
     * @throws IOException            If an error occurs while receiving the data packet.
     * @throws ClassNotFoundException If the data packet is not of the correct type.
     */
    static DataPackage receiveDataPacket(Socket socket) {
        try {
            return DataTransferInterface.receiveDataPacket(socket);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method simply instantiates a new serversocket on the specified port.
     *
     * @param port The port on which the server socket will be instantiated.
     * @return The server socket.
     * @throws IOException If an error occurs while instantiating the server socket.
     */
    static ServerSocket instantiateSocket(int port) throws IOException {

        return new ServerSocket(port);

    }

}
