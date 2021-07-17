package Client.view;

import Client.view.elements.GetImage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MatchMakingMenuGui extends MenuGui {
    private static Stage stage;

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

    }
}
