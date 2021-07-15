package Client.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OwnerMessageView extends HBox {
    private static Stage optionPopUpWindow;
    private boolean isOwnerMessage;

    public OwnerMessageView(String userName, String message, String loggedInUserName) {
        super();
        if (loggedInUserName.equals(userName))
            isOwnerMessage = true;
        else isOwnerMessage = false;

        this.setPrefWidth(801);
        this.setHeight(50);
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
        this.getChildren().addAll(optionButton, text);
    }

    private void showOption() {
        optionPopUpWindow = new Stage();
        optionPopUpWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        Button deleteButton = new Button("delete");
        Button editButton = new Button("edit");
        Button pinButton = new Button("pin message");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(deleteButton, editButton, pinButton);
        borderPane.setCenter(vBox);

        optionPopUpWindow.setScene(new Scene(borderPane, 250, 250));
        optionPopUpWindow.setTitle("options");
        optionPopUpWindow.showAndWait();
    }
}
