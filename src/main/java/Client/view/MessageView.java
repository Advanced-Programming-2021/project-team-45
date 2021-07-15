package Client.view;

import Client.ClientServer.ClientLobbyServer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageView extends HBox {
    private static Stage optionPopUpWindow;
    private boolean isOwnerMessage;
    private LobbyMenuGui lobbyMenuGui;
    private static ClientLobbyServer clientLobbyServer;

    private int id;
    private String message;
    private boolean isPinned;

    public MessageView(String userName, int id, String message, boolean isPinned, String loggedInUserName) {
        super();
        this.id = id;
        this.message = message;
        this.isPinned = isPinned;

        if (loggedInUserName.equals(userName))
            isOwnerMessage = true;
        else isOwnerMessage = false;

        this.setPrefSize(800, 20);
        if (isOwnerMessage) {
            this.setAlignment(Pos.CENTER_LEFT);
            this.setStyle("-fx-background-color: #54b74d");
        }
        else {
            this.setAlignment(Pos.CENTER_RIGHT);
            this.setStyle("-fx-background-color: #ffff");
        }

        Button optionButton = new Button();
        optionButton.setOnAction(e -> showOption());
        ImageView imageView = new ImageView(new Image("src\\main\\resources\\Client\\view\\chatButtonImages\\options.png"));
        imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);
        optionButton.setGraphic(imageView);
        Text text = new Text(message);

        this.setSpacing(10);
        if (isOwnerMessage)
            this.getChildren().addAll(optionButton, text);
        else this.getChildren().add(text);
    }

    public static void setClientLobbyServer(ClientLobbyServer clientLobbyServer) {
        MessageView.clientLobbyServer = clientLobbyServer;
    }

    public void setLobbyMenuGui(LobbyMenuGui lobbyMenuGui) {
        this.lobbyMenuGui = lobbyMenuGui;
    }

    private void showOption() {
        optionPopUpWindow = new Stage();
        optionPopUpWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: black");
        Button deleteButton = new Button("delete");
        setButtonStyle(deleteButton);
        deleteButton.setOnAction(e -> deleteMessage());

        Button editButton = new Button("edit");
        setButtonStyle(editButton);
        TextArea textArea = new TextArea(message);
        textArea.setPrefWidth(200);
        textArea.setPrefHeight(10);
        editButton.setOnAction(e -> editMessage(textArea.getText()));
        Button pinButton = new Button();
        setButtonStyle(pinButton);
        if (isPinned) pinButton.setText("unpin message");
        else pinButton.setText("pin message");
        pinButton.setOnAction(e -> pinMessage());

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(deleteButton, editButton, textArea,pinButton);
        borderPane.setCenter(vBox);

        optionPopUpWindow.setScene(new Scene(borderPane, 250, 250));
        optionPopUpWindow.setTitle("options");
        optionPopUpWindow.showAndWait();
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: red; -fx-text-fill: white");
    }

    private void deleteMessage() {
        optionPopUpWindow.close();
        clientLobbyServer.deleteMessageErrorHandler(id);
        lobbyMenuGui.refreshMessages();
    }

    private void editMessage(String newMessage) {
        optionPopUpWindow.close();
        clientLobbyServer.editMessageErrorHandler(id, newMessage);
        lobbyMenuGui.refreshMessages();
    }

    private void pinMessage() {
        optionPopUpWindow.close();
        if (isPinned) {
            clientLobbyServer.setIsPinnedMessageById(id, false);
            isPinned = false;
        } else {
            clientLobbyServer.setIsPinnedMessageById(id, true);
            isPinned = true;
        }
    }
}