package view.gui;

import javafx.scene.layout.*;
import view.gui.elements.GameCard;
import view.gui.elements.GameElementSize;
import view.gui.elements.GetGameElements;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DuelMenuGui extends MenuGui {
    public static void main(String[] args) {
        launch(args);
    }

    private static final String[] cardFields = {
            "player_monster",
            "player_spell",
            "opponent_monster",
            "opponent_spell"
    };

    private static GameController gameController;
    private static Stage stage;
    @FXML
    public Pane fieldPane;

    @Override
    public void start(Stage stage) throws IOException {
        DuelMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("DuelMenuGui.fxml"));
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void initialize() {
        Image image = GetGameElements.getCardFieldImage();
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        fieldPane.setBackground(new Background(backgroundImage));
//        updateGameBoard();


        ArrayList<String> opponentMonsterNames = new ArrayList<>();
        opponentMonsterNames.add("Battle OX");
        opponentMonsterNames.add("Yomi Ship");
        opponentMonsterNames.add("back");
        opponentMonsterNames.add("back");
        opponentMonsterNames.add("back");
        updateFields(opponentMonsterNames, "opponent_monster");
        ArrayList<String> opponentSpellNames = new ArrayList<>();
        opponentSpellNames.add("back");
        opponentSpellNames.add("back");
        opponentSpellNames.add("back");
        opponentSpellNames.add("back");
        opponentSpellNames.add("back");
        updateFields(opponentSpellNames, "opponent_spell");
        ArrayList<String> playerMonsterNames = new ArrayList<>();
        playerMonsterNames.add("Suijin");
        playerMonsterNames.add("Curtain of the dark ones");
        playerMonsterNames.add("Feral Imp");
        playerMonsterNames.add("Hero of the east");
        playerMonsterNames.add("Battle warrior");
        updateFields(playerMonsterNames, "player_monster");
        updateFields(opponentSpellNames, "player_spell");



    }

    public void updateGameBoard() {
        fieldPane.getChildren().removeAll();
        for (String field : cardFields) {
            updateFields(gameController.getFieldCards(field), field);
        }
    }

    public void updateFields(ArrayList<String> cardNames, String fieldName) {
        int startX = GameElementSize.CARD_START_X.getSize();
        int startY = GameElementSize.getYSizeByName(fieldName);
        for (int i = 0; i < cardNames.size(); i++) {
            fieldPane.getChildren().add(new GameCard(fieldPane, startX + (GameElementSize.CARD_DISTANCE.getSize() +
                    GameElementSize.CARD_WIDTH.getSize()) * i, startY, cardNames.get(i)));
        }
    }

}
