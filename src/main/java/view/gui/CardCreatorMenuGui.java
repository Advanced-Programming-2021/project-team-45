package view.gui;

import controller.CardCreatorController;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.ArrayList;

public class CardCreatorMenuGui extends MenuGui {

    public int price;
    private static Stage stage;
    private static AnchorPane anchorPane;
    public CheckBox AdvancedRitualArt;
    public CheckBox ClosedForest;
    public CheckBox DestroyAllCards;
    public CheckBox DrawCard;
    public CheckBox IncreaseAttack;
    public CheckBox MagicCylinder;
    public CheckBox MagnumShield;
    public CheckBox MirrorForce;
    public CheckBox MysticalSpaceTyphoon;
    public CheckBox NegateAttack;
    public CheckBox SummonFromGraveyard;
    public CheckBox SolemnWarning;
    public CheckBox Terraform;
    public CheckBox TorrentialTribiute;
    public CheckBox TrapHole;
    public CheckBox TwinTwisters;
    public CheckBox UnitedWeStands;
    public Spinner<Integer> Level;
    public Text Price;
    public TextField NameField;
    public TextField DescriptionField;
    public ChoiceBox<String> chooseType;


    @Override
    public void start(Stage stage) throws Exception {
        CardCreatorMenuGui.stage = stage;
        anchorPane = FXMLLoader.load(getClass().getResource("CardCreatorMenuGui.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        chooseType.getItems().add("Monster Card");
        chooseType.getItems().add("Spell Card");
        chooseType.getItems().add("Trap Card");
        calculatePrice();
    }

    private void calculatePrice() {
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(AdvancedRitualArt);
        checkBoxes.add(ClosedForest);
        checkBoxes.add(DestroyAllCards);
        checkBoxes.add(DrawCard);
        checkBoxes.add(IncreaseAttack);
        checkBoxes.add(MagicCylinder);
        checkBoxes.add(MagnumShield);
        checkBoxes.add(MirrorForce);
        checkBoxes.add(MysticalSpaceTyphoon);
        checkBoxes.add(NegateAttack);
        checkBoxes.add(SummonFromGraveyard);
        checkBoxes.add(SolemnWarning);
        checkBoxes.add(Terraform);
        checkBoxes.add(TorrentialTribiute);
        checkBoxes.add(TrapHole);
        checkBoxes.add(TwinTwisters);
        checkBoxes.add(UnitedWeStands);
        CardCreatorController.calculate(checkBoxes, price, Level, Price);

    }



    public void createCard(ActionEvent actionEvent) {
        boolean bol = !NameField.getText().equals("");
        if (bol && chooseType.getValue() != null) {

        }else{
            throwAnErrorBox();
        }
    }

    private void throwAnErrorBox(){
        Stage stage=new Stage();
        AnchorPane anchorPane=new AnchorPane();
        Text text=new Text();
        text.setText("please complete all parts of the form to create a new card");
        anchorPane.getChildren().add(text);
        text.setX(5);
        text.setY(100);
        anchorPane.setPrefHeight(200);
        anchorPane.setPrefWidth(200);
        anchorPane.setStyle("-fx-background-color: #6e2020");
        Scene scene=new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("ErrorBox");
        stage.show();
    }
}
