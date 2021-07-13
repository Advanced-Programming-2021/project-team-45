package Client.ClientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientListener extends Thread {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final ClientServer clientServer;

    public ClientListener(Socket socket, DataInputStream dataInputStream,
                          DataOutputStream dataOutputStream, ClientServer clientServer) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.clientServer = clientServer;
    }

    @Override
    public void start() {
        try {
            while (true) {
                String serverRequest = dataInputStream.readUTF();
                if (serverRequest.startsWith("SERVER_REQUEST")) {
                    String clientResponse = clientServer.handleServerRequest(serverRequest);
                    dataOutputStream.writeUTF(clientResponse);
                    dataOutputStream.flush();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
