package view.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.card.Card;
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
import view.gui.elements.GetImage;

import java.io.IOException;
import java.util.ArrayList;

public class DuelMenuGui extends MenuGui {

    private static final String[] CARD_FIELDS = {
            "player_monster",
            "player_spell",
            "opponent_monster",
            "opponent_spell"
    };
    private static GameController gameController;
    private static Stage stage;
    private static String selectedCardName = null;
    private static DuelMenuGui duelMenuGui;
    private static ArrayList<GameCard> gameCards;
    @FXML
    public Pane fieldPane;
    public Label selectedCardDescription;
    public Rectangle selectedCard;

    @Override
    public void start(Stage stage) throws IOException {
        gameCards = new ArrayList<>();
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
        DuelMenuGui.duelMenuGui = this;
//        updateGameBoard();


        ArrayList<String> opponentMonsterNames = new ArrayList<>();
        opponentMonsterNames.add("2DH");
        opponentMonsterNames.add("");
        opponentMonsterNames.add("3Man-Eater Bug_DO");
        opponentMonsterNames.add("");
        opponentMonsterNames.add("4Scanner");
        ArrayList<String> opponentSpellNames = new ArrayList<>();
        opponentSpellNames.add("0opponent_spell");
        opponentSpellNames.add("");
        opponentSpellNames.add("2opponent_spell");
        opponentSpellNames.add("1opponent_spell");
        opponentSpellNames.add("4opponent_spell");
        ArrayList<String> playerMonsterNames = new ArrayList<>();
        playerMonsterNames.add("0Suijin_DH");
        playerMonsterNames.add("");
        playerMonsterNames.add("4Feral Imp_DO");
        playerMonsterNames.add("2Hero of the east");
        playerMonsterNames.add("1Battle warrior");
        ArrayList<String> playerSpellNames = new ArrayList<>();
        playerSpellNames.add("0Mind Crush");
        playerSpellNames.add("2Negate Attack");
        playerSpellNames.add("1Harpie's Feather Duster");
        playerSpellNames.add("3Messenger of peace");
        playerSpellNames.add("");
        updateFields(opponentMonsterNames, "opponent_monster");
        updateFields(opponentSpellNames, "opponent_spell");
        updateFields(playerMonsterNames, "player_monster");
        updateFields(playerSpellNames, "player_spell");

        updateSelectedCard();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setSelectedCard(GameCard card) {
        DuelMenuGui.selectedCardName = card.getCardName();

        ShowGameMessage showGameMessage = new ShowGameMessage();
        showGameMessage.setMessage("Hello World va KIREKHARRR!");
        try {
            showGameMessage.start(stage);
        } catch (IOException ignored) {
        }

//        gameController.selectCardErrorHandler(card.getCardType(), card.getPosition(), card.isOpponent());
    }

    public static String getSelectedCardName() {
        return DuelMenuGui.selectedCardName;
    }

    public static DuelMenuGui getDuelMenuGui() {
        return duelMenuGui;
    }

    public void deSelectCards() {
        for (GameCard card : gameCards) {
            card.deselectCard();
        }
    }

    public void updateGameBoard() {
        fieldPane.getChildren().removeAll();
        for (String fieldName : CARD_FIELDS) {
            updateFields(gameController.getFieldCards(fieldName), fieldName);
        }
    }

    private void updateFields(ArrayList<String> cardData, String fieldName) {
        int startX = GameElementSize.getXSizeByName(fieldName);
        int startY = GameElementSize.getYSizeByName(fieldName);
        for (String cardDatum : cardData) {
            // skip empty position in field:
            if (cardDatum.equals(""))
                continue;
            // set card position:
            int position = Integer.parseInt("" + cardDatum.charAt(0));
            // parse other data from string:
            String data = cardDatum.substring(1);
            // find visibility and rotation:
            boolean isVisible = getVisibility(fieldName, data);
            int rotationDegree = getRotationDegree(fieldName, data);
            // parse name:
            String name;
            if (fieldName.equals(CARD_FIELDS[3])) {
                name = data;
            } else {
                name = data.split("_")[0];
            }
            // create card and set it's position:
            int cardX = startX;
            if (fieldName.startsWith("player"))
                cardX += (GameElementSize.CARD_DISTANCE.getSize() + GameElementSize.CARD_WIDTH.getSize()) * position;
            else
                cardX -= (GameElementSize.CARD_DISTANCE.getSize() + GameElementSize.CARD_WIDTH.getSize()) * position;
            GameCard card = new GameCard(fieldPane, cardX, startY, name, isVisible, rotationDegree);
            card.setPosition(position);
            card.setCardType(fieldName);
            // add card to field:
            fieldPane.getChildren().add(card);
            gameCards.add(card);
        }
    }

    private boolean getVisibility(String fieldName, String cardName) {
        boolean isVisible = true;
        if (fieldName.equals(CARD_FIELDS[3])) {
            isVisible = false;
        } else if (fieldName.equals(CARD_FIELDS[0])) {
            String[] data = cardName.split("_");
            if (data.length > 1) {
                if (data[1].equals("DH")) {
                    isVisible = false;
                }
            }
        } else if (fieldName.equals(CARD_FIELDS[2])) {
            if (cardName.equals("DH")) {
                isVisible = false;
            }
        }
        return isVisible;
    }

    private int getRotationDegree(String fieldName, String cardName) {
        int rotationDegree = 0;
        if (fieldName.equals(CARD_FIELDS[0])) {
            String[] data = cardName.split("_");
            if (data.length > 1) {
                rotationDegree = 270;
            }
        } else if (fieldName.equals(CARD_FIELDS[2])) {
            rotationDegree = 180;
            String[] data = cardName.split("_");
            if (data.length > 1 || cardName.equals("DH")) {
                rotationDegree = 90;
            }
        } else if (fieldName.equals(CARD_FIELDS[3])) {
            rotationDegree = 180;
        }
        return rotationDegree;
    }

    public void updateSelectedCard() {
        Image image;
        if (selectedCardName == null ||
                selectedCardName.equals("DH") ||
                selectedCardName.equals("opponent_spell")) {
            image = GetGameElements.getCardBack();
        } else {
            image = GetImage.getCardImage(selectedCardName);
        }
        selectedCard.setFill(new ImagePattern(image));
//        selectedCardDescription.setText(gameController.controlCardShow());


        if (selectedCardName == null) {
            selectedCardDescription.setText("no card is selected!");
        } else if (selectedCardName.equals("DH") || selectedCardName.equals("opponent_spell")) {
            selectedCardDescription.setText("description not available");
        } else {
            selectedCardDescription.setText(Card.getCardByName(selectedCardName).getCardDescription());
        }
    }

    // این متود ها اضافه شدن تا برنامه صرفا ران شه بتونیم ببریمش جلو بعد اینکه بهشون برسیم اوکیشون میکنیم و در صورت نیاز پاکشون میکنیم
    public ArrayList<Integer> getCardsForTribute(int i) {
        return null;
    }

    public Boolean getYesNoAnswer(String question) {
        return null;
    }

    public String getInputNumberOfFieldForSpecialMonster(String view) {
        return null;
    }

    public String getCardFromGraveYard(String view) {
        return null;
    }

    public String getCardName() {
        return null;
    }

    public void showOutput(String toString) {

    }

    public int getNumber(String view) {
        return 0;
    }

    public void updatePlayerGameBoard() {

    }

    public void updateOpponentGameBoard() {

    }
}
