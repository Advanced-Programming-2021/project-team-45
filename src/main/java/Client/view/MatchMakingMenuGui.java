package Client.view;

import Client.ClientServer.ClientLobbyServer;
import Client.view.elements.GetImage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MatchMakingMenuGui extends MenuGui {
    private static MatchMakingMenuGui matchMakingMenuGui;
    private static ClientLobbyServer clientLobbyServer;
    private static Stage stage;

    static {
        clientLobbyServer = new ClientLobbyServer();
    }

    public static MatchMakingMenuGui getMatchMakingMenuGui() {
        if (matchMakingMenuGui == null)
            matchMakingMenuGui = new MatchMakingMenuGui();
        return matchMakingMenuGui;
    }

    public void startCoinTossMenu(String opponentUsername, boolean isWinner) {
        CoinTossMenu coinTossMenu = new CoinTossMenu();
        CoinTossMenu.setUsernames(username, opponentUsername);
        CoinTossMenu.setWinner(isWinner);
        try {
            coinTossMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        MatchMakingMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MatchMakingMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
        stage.getIcons().add(GetImage.getGameIcon());
        stage.show();
    }

    @FXML
    void initialize() {
    }

    public void cancelMatchMaking(MouseEvent mouseEvent) {
        clientLobbyServer.stopMakeMatch();
        // Back to LobbyMenu:
        LobbyMenuGui lobbyMenuGui = LobbyMenuGui.getLobbyMenuGui();
        try {
            lobbyMenuGui.start(stage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
