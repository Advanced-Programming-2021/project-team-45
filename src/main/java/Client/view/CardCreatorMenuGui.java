package Client.view;

import Client.ClientServer.ClientCardCreatorServer;
import Server.controller.CardCreatorController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public TextField Defense;
    public TextField Attack;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    CardCreatorController cardCreatorController;
    private ClientCardCreatorServer cardCreatorServer;


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

    private ClientCardCreatorServer getCardCreatorServer(){
        if(cardCreatorServer!=null)return cardCreatorServer;
        cardCreatorServer= new ClientCardCreatorServer();
        return cardCreatorServer;
    }

    private void calculatePrice() {
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
        cardCreatorController = new CardCreatorController();
        getCardCreatorServer().calculate(checkBoxes, price, Level, Price, Attack, Defense);

    }


    public void createCard(MouseEvent mouseEvent) {
        boolean bol = !NameField.getText().equals("");
        if (bol && chooseType.getValue() != null) {
            ArrayList<CheckBox> allOnEffects = new ArrayList<>();
            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) allOnEffects.add(checkBox);
            }
            boolean bol1 = true;
            if (chooseType.getValue().equals("Monster Card")) {
                bol1 = false;
                Pattern pattern = Pattern.compile("^\\d+$");
                Pattern pattern1 = Pattern.compile("^[A-Za-z ]+$");
                Matcher matcher3 = pattern1.matcher(NameField.getText());
                bol = matcher3.find();
                Matcher matcher = pattern.matcher(Attack.getText());
                Matcher matcher1 = pattern.matcher(Defense.getText());
                if (matcher.find() && matcher1.find()) bol1 = true;
            }
            if (bol1 && bol && cardCreatorServer.hasEnoughMoney()) {
                cardCreatorServer.createACard(allOnEffects, Level.getValue(), DescriptionField.getText()
                        , Integer.parseInt(Price.getText()), NameField.getText(), chooseType.getValue(),
                        Attack.getText(), Defense.getText());
                ShowOutput.showOutput("successful", "card created successfully");
                back(mouseEvent);
            } else {
                throwAnErrorBox();
            }
        } else {
            throwAnErrorBox();
        }
    }

    private void throwAnErrorBox() {
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Text text = new Text();
        text.setText("please solve errors and try again");
        anchorPane.getChildren().add(text);
        text.setX(5);
        text.setY(100);
        anchorPane.setPrefHeight(200);
        anchorPane.setPrefWidth(200);
        anchorPane.setStyle("-fx-background-color: #6e2020");
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("ErrorBox");
        stage.show();
    }

    public void back(MouseEvent mouseEvent) {
        try {
            new MainMenuGui().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
