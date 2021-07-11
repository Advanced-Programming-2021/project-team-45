package Server.ServerController;

import java.net.Socket;

public class ProfileRequestHandler extends RequestHandler {

    public ProfileRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        return null;
    }
}
