package Server.ServerController;

import java.net.Socket;

public class LoginRequestHandler extends RequestHandler {
    private final Socket socket;

    public LoginRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    }
}
