package Server.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Thread {
    private final int port;

    public ServerController(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = RequestHandler.getRequestHandler(socket, port);
                requestHandler.start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
