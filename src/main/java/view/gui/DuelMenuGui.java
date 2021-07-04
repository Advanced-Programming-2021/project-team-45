package view.gui;

import controller.Regex;
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
import java.util.regex.Matcher;

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

        showMessage("kir");

//        gameController.selectCardErrorHandler(card.getCardType(), card.getPosition(), card.isOpponent());
    }

    private static void showMessage(String message) {
        ShowGameMessage showGameMessage = new ShowGameMessage();
        showGameMessage.setMessage(message);
        try {
            showGameMessage.start(stage);
        } catch (IOException ignored) {
        }
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

    public void showOutput(String message) {
        showMessage(message);
    }

    public int getNumber(String view) {
        return 0;
    }

    public void updatePlayerGameBoard() {
        updateGameBoard();
    }

    public void updateOpponentGameBoard() {
        updateGameBoard();
    }

    // methods similar to CLI:
    public void nextPhase() {
        int error = gameController.nextPhaseInController();
        String message = "";
        if (error == 1) {
            message = "phase: draw phase";
//            message = "new card added to the hand : " +
//                    gameController.getGame().getAddedCardInDrawPhase().getCardName();
        } else if (error == 2) {
            message = "phase: Main phase 1";
        } else if (error == 5) {
            message = "phase: End Phase\n" +
                    "its " + gameController.getGame().getPlayerOfThisTurn().getNickname() + "'s turn";
        } else if (error == 0) {
            message = "phase: standby phase";
        } else if (error == 3) {
            message = "phase: battle Phase";
        } else if (error == 4) {
            message = "phase: Main Phase 2";
        }
        showMessage(message);
    }

    public void summonCard() {
        int error = gameController.summonErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can’t summon this card";
        } else if (error == 3) {
            message = "action not allowed in this phase";
        } else if (error == 4) {
            message = "monster card zone is full";
        } else if (error == 5) {
            message = "you already summoned/set on this turn";
        } else if (error == 6) {
            message = "summoned successfully";
        } else if (error == 7) {
            message = "there are not enough cards for tribute";
        } else if (error == 8) {
            message = "there no monsters on this address";
        } else if (error == 9) {
            message = "there is no monster on one of these addresses";
        }
        showMessage(message);
    }

    public void setCard() {
        int error = gameController.setCardErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can’t set this card";
        } else if (error == 3) {
            message = "you can’t do this action in this phase";
        } else if (error == 4) {
            message = "monster card zone is full";
        } else if (error == 5) {
            message = "you already summoned/set on this turn";
        } else if (error == 6) {
            message = "set successfully";
        } else if (error == 7) {
            message = "spell card zone is full";
        }
        showMessage(message);
    }

    public void changePosition(String input) {


        int error = gameController.changePositionErrorHandler(Regex.getMatcher(input, "(attack|defense)"));


        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can’t change this card position";
        } else if (error == 3) {
            message = "you can’t do this action in this phase";
        } else if (error == 4) {
            message = "this card is already in the wanted position";
        } else if (error == 5) {
            message = "you already changed this card position in this turn";
        } else if (error == 6) {
            message = "monster card position changed successfully";
        }
        showMessage(message);
    }

    public void flipSummon() {
        int error = gameController.flipSummonErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can’t change this card position";
        } else if (error == 3) {
            message = "you can’t do this action in this phase";
        } else if (error == 4) {
            message = "you can’t flip summon this card";
        } else if (error == 5) {
            message = "flip summoned successfully";
        }
        showMessage(message);
    }

    public void attackCard(Matcher matcher) {
        int error = 1;


        if (matcher.find())
            error = gameController.attackErrorHandler(Integer.parseInt(matcher.group(1)));


        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can’t attack with this card";
        } else if (error == 3) {
            message = "you can’t do this action in this phase";
        } else if (error == 4) {
            message = "this card already attacked";
        } else if (error == 5) {
            message = "there is no card to attack here";
        } else if (error == 6) {
            message = "your opponent’s monster is destroyed and your opponent receives " +
                    gameController.damageOnOpponent() + " battle damage";
        } else if (error == 7) {
            message = "both you and your opponent monster cards are destroyed and no " +
                    "one receives damage";
        } else if (error == 8) {
            message = "Your monster card is destroyed and you received " +
                    gameController.damageOnPlayer() + " battle damage";
        } else if (error == 9) {
            message = "the defense position monster is destroyed";
        } else if (error == 10) {
            message = "no card is destroyed";
        } else if (error == 11) {
            message = "no card is destroyed and you received " + gameController.damageOnPlayer()
                    + " battle damage";
        } else if (error == 12) {
            message = "the defense position monster " +
                    gameController.getDefenseTargetCardName() + " is destroyed";
        } else if (error == 13) {
            message = "opponent’s monster card was " + gameController.getDefenseTargetCardName()
                    + " and no card is destroyed";
        } else if (error == 14) {
            message = "opponent’s monster card was " + gameController.getDefenseTargetCardName()
                    + " and no card is destroyed and you received " +
                    gameController.damageOnPlayer() + " battle damage";
        }
        showMessage(message);
    }

    public void directAttack() {
        int error = gameController.directAttackErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can’t attack with this card";
        } else if (error == 3) {
            message = "you can’t do this action in this phase";
        } else if (error == 4) {
            message = "this card already attacked";
        } else if (error == 5) {
            message = "you can’t attack the opponent directly";
        } else if (error == 6) {
            message = "you opponent receives " + gameController.damageOnOpponent()
                    + " battle damage";
        }
        showMessage(message);
    }

    public void activateEffect() {
        int error = gameController.activeEffectErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "activate effect is only for spell cards.";
        } else if (error == 3) {
            message = "you can’t activate an effect on this turn";
        } else if (error == 4) {
            message = "you have already activated this card";
        } else if (error == 5) {
            message = "spell card zone is full";
        } else if (error == 6) {
            message = "preparations of this spell are not done yet";
        } else if (error == 7) {
            message = "spell activated";
        }
        showMessage(message);
    }

    public void surrender() {
        gameController.surrender();
    }

    public void cancel() {
        gameController.cancel();
    }
}
