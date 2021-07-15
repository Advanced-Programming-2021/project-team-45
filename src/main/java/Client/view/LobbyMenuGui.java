package Client.view;

import Client.ClientServer.ClientLobbyServer;
import Server.model.Message;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.xml.sax.HandlerBase;

import java.util.ArrayList;


public class LobbyMenuGui extends MenuGui {
    private static ClientLobbyServer clientLobbyServer;
    private static Pane pane;
    private static Stage stage;
    private static Stage showPinnedMessagesPopUpWindow;

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

    @FXML
    void initialize() {
        refreshMessages();
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
        clientLobbyServer.addMessage(textArea.getText());
        textArea.setText(null);
        refreshMessages();
    }

    public void refreshMessages() {
        messagesGridPane.getChildren().clear();
        ArrayList<Message> messages = clientLobbyServer.getAllMessages();
        MessageView.setClientLobbyServer(clientLobbyServer);
        for (int i = 0; i < messages.size(); i++) {
            MessageView messageView = new MessageView(messages.get(i).getSenderUserName(), messages.get(i).getId(),
                    messages.get(i).getMessageText(), messages.get(i).isPinned(), username);
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

    public void showPinnedMessages(MouseEvent mouseEvent) {
        showPinnedMessagesPopUpWindow = new Stage();
        showPinnedMessagesPopUpWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        ArrayList<Message> messages = clientLobbyServer.getAllMessages();
        for (Message message : messages) {
            if (message.isPinned()) {
                HBox hBox = new HBox();
                hBox.setStyle("-fx-background-color: white");

                Text text = new Text(message.getMessageText());
                hBox.getChildren().add(text);
                gridPane.addRow(gridPane.getRowCount() + 1, hBox);
            }
        }

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setStyle("-fx-background: black");
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