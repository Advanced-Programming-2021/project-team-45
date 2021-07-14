package Client.ClientServer;

import Client.view.LobbyMenuGui;
import Network.PortConfig;
import com.gilecode.yagson.YaGson;

public class ClientLobbyServer extends ClientServer {
    private ClientListener clientListener;

    public ClientLobbyServer() {
        super(PortConfig.LOBBY_PORT.getPort(), "Lobby");
        clientListener = new ClientListener(sendRequest.getSocket(), sendRequest.getDataInputStream(),
                sendRequest.getDataOutputStream(), this);

        // TODO: fix this shit:
//        clientListener.start();
    }

    // Sending Request to server:
    public void makeMatch(int rounds) {
        sendRequest.getMethodResult("makeMatch", rounds);
    }

    // Response to server:
    @Override
    protected String handleServerRequest(String serverRequest) {
        String[] parts = serverRequest.split("\n");
        String methodName = parts[1];
        Object[] fields = getObjects(parts[2]);
        Object answer = "success";

        if (methodName.equals("startCoinToss")) {
            LobbyMenuGui.startCoinToss((String) fields[0], (boolean) fields[1]);
        }

        return getAnswer(answer);
    }

    private Object[] getObjects(String serverRequestJson) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(serverRequestJson, Object[].class);
    }

    private String getAnswer(Object answer) {
        if (answer.equals("success"))
            return "success";
        YaGson yaGson = new YaGson();
        return answer.getClass().getName() + "\n"
                + yaGson.toJson(answer);
    }
}
