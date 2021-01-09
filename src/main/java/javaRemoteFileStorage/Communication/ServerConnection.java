package javaRemoteFileStorage.Communication;

import java.net.Socket;

public class ServerConnection {
    Socket socket;

    ServerConnection(Socket socket) {
        this.socket = socket;
    }
}
