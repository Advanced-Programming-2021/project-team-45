package Client.ClientServer;

import Network.PortConfig;

import java.util.LinkedHashMap;

public class ClientScoreBoardServer extends ClientServer{

    public ClientScoreBoardServer() {
        super(PortConfig.SCOREBOARD_PORT.getPort(), "ScoreboardController");
    }

    public LinkedHashMap<String, Integer> getSortedNicknameScore() {
        Object answer = sendRequest.getMethodResult("getSortedNicknameScore");
        return (LinkedHashMap<String, Integer>) answer;
    }

    public String getNickname(String userName) {
        Object answer = sendRequest.getMethodResult("getNickname", userName);
        return (String) answer;
    }
}
