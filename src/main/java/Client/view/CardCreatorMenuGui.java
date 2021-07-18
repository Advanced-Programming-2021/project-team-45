package Client.view;

import Client.ClientServer.ClientCardCreatorServer;

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
        calculator calculator=new calculator();
        calculator.realRun(checkBoxes, price, Level, Price, Attack, Defense);

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
            if (bol1 && bol) {
                getCardCreatorServer().createCard(Level.getValue(),DescriptionField.getText(),
                        Integer.parseInt(Price.getText()),NameField.getText(),chooseType.getValue()
                        ,Attack.getText(),Defense.getText());
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

class calculator extends Thread {
    ArrayList<CheckBox> allEffects;
    private Spinner<Integer> level;
    private Text value;
    private TextField attack;
    private TextField defense;
    private long effectprice = 0;

    public calculator realRun(ArrayList<CheckBox> allEffects, int price, Spinner<Integer> level, Text value, TextField attack
            , TextField defense) {
        this.allEffects = allEffects;
        this.level = level;
        this.value = value;
        this.attack = attack;
        this.defense = defense;
        effectprice = price;
        this.start();
        return this;
    }

    public long getEffectPrice() {
        return effectprice;
    }

    @Override
    public void run() {
        int allActives = 0;
        for (CheckBox allEffect : allEffects) {
            if (allEffect.isSelected()) allActives++;
        }
        effectprice = 1000 - level.getValue() * 100;
        int newLevel = level.getValue();
        int ATTACK = convertToNumber(attack);
        int DEFENSE = convertToNumber(defense);
        while (true) {
            int newActivate = 0;
            for (CheckBox allEffect : allEffects) {
                if (allEffect.isSelected()) newActivate++;
            }
            if (allActives != newActivate) {
                int diference = newActivate - allActives;
                allActives = newActivate;
                effectprice = (long) (1000 - level.getValue() * 100 + 500 * (long) Math.pow(2.718281828, allActives) -
                        500 + Math.pow(ATTACK, 1) + Math.pow(DEFENSE, 1));
            }
            if (level.getValue() != newLevel) {
                effectprice = effectprice + (newLevel - level.getValue()) * 100L;
                newLevel = level.getValue();
            }
            if (convertToNumber(attack) != ATTACK) {
                effectprice -= Math.pow(ATTACK, 1);
                ATTACK =convertToNumber(attack);
                effectprice += Math.pow(ATTACK, 1);
            }
            if (convertToNumber(defense) != DEFENSE) {
                effectprice -= Math.pow(DEFENSE, 1);
                DEFENSE = convertToNumber(defense);
                effectprice += Math.pow(DEFENSE, 1);
            }
            value.setText(String.valueOf(effectprice));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     int convertToNumber(TextField num) {
        if (num.getText().equals("")) {
            return 0;
        } else {
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(num.getText());
            if (matcher.find()) return Integer.parseInt(num.getText());
            else return 0;
        }
    }
}