package Client.view;

import Client.ClientServer.ClientLobbyServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


public class LobbyMenuGui extends MenuGui {
    private static LobbyMenuGui lobbyMenuGui;
    private static ClientLobbyServer clientLobbyServer;
    private static Pane pane;
    private static Stage stage;
    private static Stage showPinnedMessagesPopUpWindow;
    @FXML
    public GridPane messagesGridPane;
    @FXML
    public Text onlineUsersText;
    @FXML
    public TextArea textArea;
    @FXML
    public TextField userNameText;
    static {
        clientLobbyServer = new ClientLobbyServer();
    }


    public static LobbyMenuGui getLobbyMenuGui() {
        if (lobbyMenuGui == null)
            lobbyMenuGui = new LobbyMenuGui();
        return lobbyMenuGui;
    }

    public static void startMatchMakingMenuGui() {
        MatchMakingMenuGui matchMakingMenuGui = MatchMakingMenuGui.getMatchMakingMenuGui();
        MatchMakingMenuGui.setUsername(username);
        try {
            matchMakingMenuGui.start(stage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("LobbyMenuGui.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        LobbyMenuGui.stage = stage;
    }

    @FXML
    void initialize() {
        updateMessages();
    }

    public void play1RoundGameWithAnotherUser() {
        String opponentUserName = userNameText.getText();
        userNameText.setText(null);
        int answer = clientLobbyServer.makeMatchWithAnotherUser(opponentUserName, 1);
        if (answer == 1)
            ShowOutput.showOutput("Error", "there is no user with this username");
        else if (answer == 2)
            ShowOutput.showOutput("Error", "you must play with another user except yourself!");
        else if (answer == 3)
            ShowOutput.showOutput("Error", "the user is offline now");
        else {
            startMatchMakingMenuGui();
            // wait for opponent answer;
        }
    }

    public void play3RoundGameWithAnotherUser() {
        String opponentUserName = userNameText.getText();
        userNameText.setText(null);
        int answer = clientLobbyServer.makeMatchWithAnotherUser(opponentUserName, 3);
        if (answer == 1)
            ShowOutput.showOutput("Error", "there is no user with this username");
        else if (answer == 2)
            ShowOutput.showOutput("Error", "you must play with another user except yourself!");
        else if (answer == 3)
            ShowOutput.showOutput("Error", "the user is offline now");
        else if (answer == 4)
            ShowOutput.showOutput("Error", "the user waits for another game");
        else {
            startMatchMakingMenuGui();
            // wait for opponent answer;
        }
    }

    public void askForDuel(String opponentUserName, int rounds) {
        boolean answer = GetInput.getYesNoAnswer("do you want to play with " + opponentUserName + " in " + rounds + " rounds?");
        if (answer)
            clientLobbyServer.startMatchWithAnotherUser(opponentUserName, username, rounds);
        else
            clientLobbyServer.refuseMatch(opponentUserName, username, rounds);
    }

    public void play1RoundGame(ActionEvent actionEvent) {
        clientLobbyServer.makeMatch(1);
        startMatchMakingMenuGui();
    }

    public void play3RoundGame(ActionEvent actionEvent) {
        clientLobbyServer.makeMatch(3);
        startMatchMakingMenuGui();
    }

    public void sendMessage(MouseEvent mouseEvent) {
        clientLobbyServer.addMessage(textArea.getText());
        textArea.setText(null);
        updateMessages();
    }

    public void updateMessages() {
        messagesGridPane.getChildren().clear();
        ArrayList<Object[]> messagesData = clientLobbyServer.getAllMessagesData();
        MessageView.setClientLobbyServer(clientLobbyServer);
        for (int i = 0; i < messagesData.size(); i++) {
            Object[] objects = messagesData.get(i);
            MessageView messageView = new MessageView((String) objects[0], (int) objects[1],
                    (String) objects[2], (boolean) objects[3], username);
            messageView.setLobbyMenuGui(this);
            messagesGridPane.addRow(i, messageView);
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        MainMenuGui mainMenuGui = new MainMenuGui();
        try {
            mainMenuGui.start(stage);
        } catch (Exception ignored) {
        }
    }

    public void startLobbyCoinTossMenu(String opponentUsername, boolean isWinner) {
        CoinTossMenu coinTossMenu = new CoinTossMenu();
        CoinTossMenu.setUsernames(username, opponentUsername);
        CoinTossMenu.setWinner(isWinner);
        try {
            coinTossMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPinnedMessages(MouseEvent mouseEvent) {
        showPinnedMessagesPopUpWindow = new Stage();
        showPinnedMessagesPopUpWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        ArrayList<Object[]> messagesData = clientLobbyServer.getAllMessagesData();
        for (Object[] data : messagesData) {
            if ((boolean) data[3]) {
                HBox hBox = new HBox();
                hBox.setStyle("-fx-background-color: grey");

                Text text = new Text("senderUserName: " + data[0] + " -> " + data[2]);
                text.setFont(new Font("Arial", 13));
                text.setStyle("-fx-fill: white;");
                hBox.getChildren().add(text);
                gridPane.addRow(gridPane.getRowCount() + 1, hBox);
            }
        }

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setStyle("-fx-background: black; -fx-background-color: black");
        borderPane.setCenter(scrollPane);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button backButton = new Button("back");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
        backButton.setOnAction(e -> showPinnedMessagesPopUpWindow.close());
        hBox.getChildren().add(backButton);
        hBox.setStyle("-fx-background-color: black");
        borderPane.setBottom(hBox);

        showPinnedMessagesPopUpWindow.setScene(new Scene(borderPane, 250, 250));
        showPinnedMessagesPopUpWindow.setTitle("pinned messages");
        showPinnedMessagesPopUpWindow.showAndWait();
    }
}
