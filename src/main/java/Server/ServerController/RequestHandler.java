package Server.ServerController;

import Network.PortConfig;
import Server.controller.DatabaseController;
import Server.model.user.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public abstract class RequestHandler extends Thread {
    protected final Socket socket;
    protected final FieldParser fieldParser;

    public RequestHandler(Socket socket) {
        this.socket = socket;
        fieldParser = new FieldParser();
    }

    public static RequestHandler getRequestHandler(Socket socket, int port) {
        RequestHandler requestHandler = null;
        if (port == PortConfig.LOGIN_PORT.getPort()) {
            requestHandler = new LoginRequestHandler(socket);
        } else if (port == PortConfig.PROFILE_PORT.getPort()) {
            requestHandler = new ProfileRequestHandler(socket);
        } else if (port == PortConfig.DUEL_PORT.getPort()) {
            requestHandler = new DuelRequestHandler(socket);
        } else if (port == PortConfig.SHOP_PORT.getPort()) {
            requestHandler = new ShopRequestHandler(socket);
        } else if (port == PortConfig.CARD_CREATOR_PORT.getPort()) {
            requestHandler = new CardCreatorRequestHandler(socket);
        } else if (port == PortConfig.DECK_PORT.getPort()) {
            requestHandler = new DeckRequestHandler(socket);
        } else if (port == PortConfig.DeckStarter_PORT.getPort()) {
            requestHandler = new DeckStarterRequestHandler(socket);
        } else if (port == PortConfig.LOBBY_PORT.getPort()) {
            requestHandler = new LobbyRequestHandler(socket);
        }
        return requestHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String request = dataInputStream.readUTF();
                // Update user data:
                addUserServerSendRequest(request);
                String result = handle(request);
                if (result != null) {
                    dataOutputStream.writeUTF(result);
                    dataOutputStream.flush();
                }
            } catch (SocketException | EOFException exception) {
                // connection is lost
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    protected abstract String handle(String request);

    private void addUserServerSendRequest(String request) {
        String[] requestParts = request.split("\n");
        if (!requestParts[0].equals("null")) {
            User user = DatabaseController.getUserByToken(requestParts[0]);
            String[] localSocketAddress = socket.getLocalSocketAddress().toString().split(":");
            String host = localSocketAddress[0].replace("/", "");
            // Add user host to database:
            DatabaseController.addUserSendRequest(user, new ServerSendRequest(host));
        }
    }
}
