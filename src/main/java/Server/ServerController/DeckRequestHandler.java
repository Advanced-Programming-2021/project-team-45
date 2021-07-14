package Server.ServerController;

import java.net.Socket;

public class DeckRequestHandler extends RequestHandler{
    public DeckRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        return null;
    }
}
