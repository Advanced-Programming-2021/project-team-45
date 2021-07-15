package Client.view;

import Client.ClientServer.ClientLobbyServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LobbyMenuGui extends MenuGui {
    private static ClientLobbyServer clientLobbyServer;
    private static Pane pane;
    private static Stage stage;

    static {
        clientLobbyServer = new ClientLobbyServer();
    }

    @FXML
    public GridPane messagesGridPane;
    public TextArea textArea;

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("LobbyMenuGui.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        LobbyMenuGui.stage = stage;
    }

    public static void startCoinToss(String opponentUsername, boolean isWinner) {
        CoinTossMenu coinTossMenu = new CoinTossMenu();
        CoinTossMenu.setUserNames(username, opponentUsername);
        CoinTossMenu.setWinner(isWinner);
        
        try {
            coinTossMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play1RoundGame(ActionEvent actionEvent) {
        clientLobbyServer.makeMatch(1);
    }

    public void play3RoundGame(ActionEvent actionEvent) {
        clientLobbyServer.makeMatch(3);
    }

    public void sendMessage(MouseEvent mouseEvent) {
        OwnerMessageView ownerMessageView = new OwnerMessageView(username, textArea.getText(), username);
        messagesGridPane.addRow(messagesGridPane.getRowCount() + 1,ownerMessageView);
        textArea.setText(null);
    }

    public void refreshMessages(MouseEvent mouseEvent) {
        messagesGridPane.getChildren().clear();
    }
}
