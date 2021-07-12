package Server.ServerController;

import Network.PortConfig;

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
}
