package Client.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Client.view.elements.GetImage;

import java.io.IOException;
import java.util.Random;

public class ShowGameMessage extends Application {
    private static String message;
    public Rectangle outputImage;
    public Label messageLabel;
    public Button okButton;

    @Override
    public void start(Stage prevStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowGameMessage.fxml"));
        Scene scene = new Scene(root, 512, 512);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Attention!");
        stage.setResizable(false);
        stage.getIcons().add(GetImage.getGameIcon());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.toFront();
        stage.requestFocus();

        stage.show();
    }

    @FXML
    public void initialize() {
        Random random = new Random();
        int name = random.nextInt(35);
        Image image = new Image(String.valueOf(this.getClass().getResource("ShowOutputImg/" + name + ".png")));
        outputImage.setFill(new ImagePattern(image));
        messageLabel.setText(message);
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void setMessage(String message) {
        ShowGameMessage.message = message;
    }
}
