package Server.ServerController;

import java.net.Socket;

public class ProfileRequestHandler extends RequestHandler {
    private final Socket socket;

    public ProfileRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    }
}
