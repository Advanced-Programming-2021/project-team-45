package view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiTest extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiTest.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("AP Project");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
