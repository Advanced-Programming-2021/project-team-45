package Client.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LobbyMenuGui extends Application {

    private static Pane pane;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("LobbyMenuGui.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        LobbyMenuGui.stage = stage;
    }

    public void play1RoundGame(ActionEvent actionEvent) {

    }
}
