package view.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DeckStarterMenuGui extends MenuGui{
    @FXML
    public ChoiceBox chooseActiveDeck;
    public ChoiceBox chooseEditDeck;
    public TextField nameOfDeck;
    private static AnchorPane anchorPane;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane= FXMLLoader.load(getClass().getResource("DeckStarterMenuGui.fxml"));
        Scene scene=new Scene(anchorPane);
        stage.setScene(scene);
        DeckStarterMenuGui.stage=stage;
    }

    @FXML
    void initialize(){

    }

    public void editADeck(MouseEvent mouseEvent) {

    }

    public void createANewDeck(MouseEvent mouseEvent) {

    }
}
