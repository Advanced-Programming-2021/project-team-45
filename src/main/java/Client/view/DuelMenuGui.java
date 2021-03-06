package Client.view;

import Client.ClientServer.ClientDuelServer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import Client.view.elements.GameCard;
import Client.view.elements.GameElementSize;
import Client.view.elements.GetGameElements;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Client.view.elements.GetImage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class DuelMenuGui extends MenuGui {
    private static final String[] CARD_FIELDS = {
            "player_monster",
            "player_spell",
            "opponent_monster",
            "opponent_spell"
    };
    private static ClientDuelServer clientDuelServer;
    private static Stage stage;
    private static Stage showGraveyardPopupWindow;
    private static Stage pausePopupWindow;
    private static String selectedCardName = null;
    private static DuelMenuGui duelMenuGui;
    private static ArrayList<GameCard> gameCards;
    // select card fields:
    private static boolean isCardSelectMode;
    private static ArrayList<GameCard> selectedCards;
    private static int selectCardsCount;
    private static String[] selectCardFieldNames;
    private static String selectCardMethodName;
    private static final Method[] duelMenuMethods;
    private static boolean isTurn;
    @FXML
    public Pane fieldPane;
    @FXML
    public Label selectedCardDescription;
    @FXML
    public Rectangle selectedCard;
    @FXML
    public ProgressBar opponentProgressBar;
    @FXML
    public Text opponentNickNameText;
    @FXML
    public Text opponentUserNameText;
    @FXML
    public ProgressBar playerProgressBar;
    @FXML
    public Text playerNickNameText;
    @FXML
    public Text playerUserNameText;
    @FXML
    public Label phaseLabel;
    @FXML
    public Button nextPhaseButton;
    @FXML
    public Button summonButton;
    @FXML
    public Button setButton;
    @FXML
    public Button changePositionButton;
    @FXML
    public Button attackButton;
    @FXML
    public Button activateEffectButton;
    @FXML
    public Button surrenderButton;
    @FXML
    public Button showGraveyardButton;

    static {
        duelMenuMethods = DuelMenuGui.class.getDeclaredMethods();
    }

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
        createShortCut();
    }

    @FXML
    private void initialize() {
        clientDuelServer = new ClientDuelServer();
        Image image = GetGameElements.getCardFieldImage();
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        fieldPane.setBackground(new Background(backgroundImage));
        DuelMenuGui.duelMenuGui = this;
        updateGameBoard();
        updateSelectedCard();
    }

    public static void setSelectedCard(GameCard card) {
        DuelMenuGui.selectedCardName = card.getCardName();
        if (isTurn)
            clientDuelServer.selectCardErrorHandler(card.getCardType(), card.getPosition(), card.isOpponent());
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
        if (duelMenuGui == null)
            duelMenuGui = new DuelMenuGui();
        return duelMenuGui;
    }

    public static void selectCards(String methodName, String[] fieldNames, int count) {
        isCardSelectMode = true;
        selectedCards = new ArrayList<>();
        selectCardMethodName = methodName;
        selectCardFieldNames = fieldNames;
        selectCardsCount = count;
    }

    public static boolean isCardSelectMode() {
        return isCardSelectMode;
    }

    public static void addToSelectedCards(GameCard card) {
        boolean isMatched = false;
        for (String fieldName : selectCardFieldNames) {
            if (card.getFieldName().equals(fieldName)) {
                isMatched = true;
                break;
            }
        }
        if (isMatched) {
            selectedCards.add(card);
            endSelection();
        } else {
            String message = "Invalid card selection! please select from fields: ";
            message += Arrays.toString(selectCardFieldNames);
            showMessage(message);
        }
    }

    public static void endSelection() {
        if (selectedCards.size() == selectCardsCount) {
            isCardSelectMode = false;
            Method method = getMethodByName(selectCardMethodName);
            DuelMenuGui duelMenuGui = DuelMenuGui.getDuelMenuGui();
            try {
                method.invoke(duelMenuGui, selectedCards);
            } catch (IllegalAccessException | InvocationTargetException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static boolean isTurn() {
        return isTurn;
    }

    private static Method getMethodByName(String methodName) {
        for (Method method : duelMenuMethods) {
            if (method.getName().equals(methodName))
                return method;
        }
        return null;
    }

    public void deSelectCards() {
        for (GameCard card : gameCards) {
            card.deselectCard();
        }
    }

    public void updateGameBoard() {
        clientDuelServer.updateGameData();

        fieldPane.getChildren().clear();
        for (String fieldName : CARD_FIELDS) {
            updateFields(clientDuelServer.getGameData().getFieldCards(fieldName), fieldName);
        }
        updateHands(clientDuelServer.getGameData().getPlayerHand(), false);
        updateHands(clientDuelServer.getGameData().getOpponentHand(), true);
        updatePlayerLifePoint();
        updateOpponentLifePoint();
        updateDecks();
        updatePhase();
        setButtonsEnable();
    }

    private void setButtonsEnable() {
        DuelMenuGui.isTurn = clientDuelServer.getGameData().isTurn();
        boolean isDisabled = !clientDuelServer.getGameData().isTurn();
        nextPhaseButton.setDisable(isDisabled);
        summonButton.setDisable(isDisabled);
        setButton.setDisable(isDisabled);
        changePositionButton.setDisable(isDisabled);
        attackButton.setDisable(isDisabled);
        activateEffectButton.setDisable(isDisabled);
        surrenderButton.setDisable(isDisabled);
        showGraveyardButton.setDisable(isDisabled);
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

    private void updateHands(ArrayList<String> fieldCards, boolean isOpponent) {
        int cardX = 0;
        if (isOpponent) {
            cardX = 512;
        } else cardX = 30;
        for (int i = 0; i < fieldCards.size(); i++) {
            GameCard card;
            if (isOpponent) {
                cardX -= (GameElementSize.CARD_DISTANCE.getSize() + GameElementSize.CARD_WIDTH.getSize());
                card = new GameCard(fieldPane, cardX, GameElementSize.OPPONENT_HAND_CARD_START_Y.getSize(),
                        fieldCards.get(i), false, 180);
                card.setCardType("opponent_hand");
            } else {
                cardX += (GameElementSize.CARD_DISTANCE.getSize() + GameElementSize.CARD_WIDTH.getSize());
                card = new GameCard(fieldPane, cardX, GameElementSize.PLAYER_HAND_CARD_START_Y.getSize(),
                        fieldCards.get(i), true, 0);
                card.setCardType("player_hand");
            }
            card.setPosition(i);
            fieldPane.getChildren().add(card);
            gameCards.add(card);
        }
    }

    private void updateOpponentLifePoint() {
        opponentNickNameText.setText("opponent nickname: " + clientDuelServer.getGameData().getOpponentNickname());
        opponentUserNameText.setText("opponent username: " + clientDuelServer.getGameData().getOpponentUsername());
        opponentProgressBar.setProgress((clientDuelServer.getGameData().getOpponentLifePoint() / (double) 8000));
        if (opponentProgressBar.getProgress() >= 0.6)
            opponentProgressBar.setStyle("-fx-accent: green");
        else if (opponentProgressBar.getProgress() <= 0.6 && opponentProgressBar.getProgress() >= 0.3)
            opponentProgressBar.setStyle("-fx-accent: yellow");
        else if (opponentProgressBar.getProgress() <= 0.3)
            opponentProgressBar.setStyle("-fx-accent: red");
    }

    private void updatePlayerLifePoint() {
        playerNickNameText.setText("player nickname: " + clientDuelServer.getGameData().getPlayerNickname());
        playerUserNameText.setText("player username: " + clientDuelServer.getGameData().getPlayerUsername());
        playerProgressBar.setProgress((clientDuelServer.getGameData().getPlayerLifePoint() / (double) 8000));
        if (playerProgressBar.getProgress() >= 0.6)
            playerProgressBar.setStyle("-fx-accent: green");
        else if (playerProgressBar.getProgress() <= 0.6 && playerProgressBar.getProgress() >= 0.3)
            playerProgressBar.setStyle("-fx-accent: yellow");
        else if (playerProgressBar.getProgress() <= 0.3)
            playerProgressBar.setStyle("-fx-accent: red");
    }

    private void updateDecks() {
        int playerDeckSize = clientDuelServer.getGameData().getPlayerDeckSize();
        int opponentDeckSize = clientDuelServer.getGameData().getOpponentDeckSize();
        GameCard playerDeck = new GameCard(fieldPane, 0);
        GameCard opponentDeck = new GameCard(fieldPane, 180);

        Label playerDeckLabel = new Label(String.valueOf(playerDeckSize));
        playerDeckLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0);" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-family: Arial;");
        playerDeckLabel.toFront();
        Label opponentDeckLabel = new Label(String.valueOf(opponentDeckSize));
        opponentDeckLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0);" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-family: Arial;");
        opponentDeckLabel.toFront();

        StackPane playerDeckStackPane = new StackPane();
        playerDeckStackPane.getChildren().addAll(playerDeck, playerDeckLabel);
        playerDeckStackPane.setLayoutX(GameElementSize.PLAYER_DECK_X.getSize());
        playerDeckStackPane.setLayoutY(GameElementSize.PLAYER_DECK_Y.getSize());
        StackPane opponentDeckStackPane = new StackPane();
        opponentDeckStackPane.getChildren().addAll(opponentDeck, opponentDeckLabel);
        opponentDeckStackPane.setLayoutX(GameElementSize.OPPONENT_DECK_X.getSize());
        opponentDeckStackPane.setLayoutY(GameElementSize.OPPONENT_DECK_Y.getSize());

        fieldPane.getChildren().addAll(playerDeckStackPane, opponentDeckStackPane);
    }

    private void updatePhase() {
        String phase = clientDuelServer.getGameData().getGamePhase();
        phaseLabel.setText(phase);
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
                selectedCardName.equals("opponent_spell") ||
                selectedCardName.equals("opponent_hand")) {
            image = GetGameElements.getCardBack();
        } else {
            image = GetImage.getCardImage(selectedCardName);
        }
        selectedCard.setFill(new ImagePattern(image));

        selectedCardDescription.setText(clientDuelServer.controlCardShow());
    }

    public void showGraveyard() {
        showGraveyardPopupWindow = new Stage();
        showGraveyardPopupWindow.initModality(Modality.APPLICATION_MODAL);
        showGraveyardPopupWindow.getIcons().add(GetImage.getGameIcon());
        showGraveyardPopupWindow.setTitle("Graveyard");

        BorderPane borderPane = new BorderPane();
        Text text = new Text("your graveyard");
        text.setFont(new Font("Arial", 14));
        text.setStyle("-fx-fill: white");
        HBox hBox = new HBox(text);
        hBox.setStyle("-fx-background-color: black");
        hBox.setAlignment(Pos.CENTER);
        borderPane.setTop(hBox);

        ScrollPane scrollPane = getGraveyardCardsList();
        borderPane.setCenter(getGraveyardCardsList());

        Button button = new Button("ok");
        button.setOnAction(e -> showGraveyardPopupWindow.close());
        button.setStyle("-fx-background-color: red");
        HBox hBox1 = new HBox(button);
        hBox1.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox1);

        Scene scene = new Scene(borderPane, 130, 450);
        showGraveyardPopupWindow.setResizable(false);
        showGraveyardPopupWindow.setScene(scene);
        showGraveyardPopupWindow.showAndWait();
    }

    private ScrollPane getGraveyardCardsList() {
        GridPane gridPane = new GridPane();
        ArrayList<String> graveyardCards = clientDuelServer.getGameData().getPlayerGraveyardCards();
        if (graveyardCards.size() == 0) {
            Text text = new Text("there is no card at graveyard");
            gridPane.add(text, 0, 10);
            text.setStyle("-fx-fill: white");
            text.setFont(new Font("Bold", 14));
        } else {
            for (int i = graveyardCards.size() - 1; i >= 0; i--) {
                int y = graveyardCards.size() - 1 - i;
                ImageView imageView = new ImageView(GetImage.getCardImage(graveyardCards.get(i)));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                Text text = new Text(y + 1 + ".");
                text.setStyle("-fx-fill: white");
                text.setFont(new Font("Bold", 14));
                gridPane.add(text, 0, y);
                gridPane.add(imageView, 1, y);
            }
        }
        gridPane.setStyle("-fx-background-color: black");
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setStyle("-fx-background-color: black");
        return scrollPane;
    }

    // methods similar to CLI:
    public void nextPhase() {
        int error = clientDuelServer.nextPhaseInController();
        String message = "";
        if (error == 1) {
            message = "phase: draw phase\n" +
                    "new card added to the hand : " +
                    clientDuelServer.getAddedCardNameInDrawPhase();
        } else if (error == 2) {
            message = "phase: Main phase 1";
        } else if (error == 5) {
            message = "phase: End Phase\n" +
                    "its " + clientDuelServer.getThisTurnPlayerNickname() + "'s turn";
        } else if (error == 0) {
            message = "phase: standby phase";
        } else if (error == 3) {
            message = "phase: battle Phase";
        } else if (error == 4) {
            message = "phase: Main Phase 2";
        }
        showMessage(message);
        updateGameBoard();
    }

    public void summonCard() {
        int error = clientDuelServer.summonErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can???t summon this card";
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
        updateGameBoard();
    }

    public void setCard() {
        int error = clientDuelServer.setCardErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can???t set this card";
        } else if (error == 3) {
            message = "you can???t do this action in this phase";
        } else if (error == 4) {
            message = "monster card zone is full";
        } else if (error == 5) {
            message = "you already summoned/set on this turn";
        } else if (error == 6) {
            message = "set successfully";
        } else if (error == 7) {
            message = "spell card zone is full";
        }
        MusicPlayer.playSetCardMusic();
        showMessage(message);
        updateGameBoard();
    }

    public void changePosition() {
        int error = clientDuelServer.changePositionErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can???t change this card position";
        } else if (error == 3) {
            message = "you can???t do this action in this phase";
        } else if (error == 4) {
            message = "this card is already in the wanted position";
        } else if (error == 5) {
            message = "you already changed this card position in this turn";
        } else if (error == 6) {
            message = "monster card position changed successfully";
        }
        showMessage(message);
        updateGameBoard();
    }

    public void attackCard(ArrayList<GameCard> cards) {
        int monsterPosition = cards.get(0).getPosition();
        int error = clientDuelServer.attackErrorHandler(monsterPosition);
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can???t attack with this card";
        } else if (error == 3) {
            message = "you can???t do this action in this phase";
        } else if (error == 4) {
            message = "this card already attacked";
        } else if (error == 5) {
            message = "there is no card to attack here";
        } else if (error == 6) {
            message = "your opponent???s monster is destroyed and your opponent receives " +
                    clientDuelServer.damageOnOpponent() + " battle damage";
        } else if (error == 7) {
            message = "both you and your opponent monster cards are destroyed and no " +
                    "one receives damage";
        } else if (error == 8) {
            message = "Your monster card is destroyed and you received " +
                    clientDuelServer.damageOnPlayer() + " battle damage";
        } else if (error == 9) {
            message = "the defense position monster is destroyed";
        } else if (error == 10) {
            message = "no card is destroyed";
        } else if (error == 11) {
            message = "no card is destroyed and you received " + clientDuelServer.damageOnPlayer()
                    + " battle damage";
        } else if (error == 12) {
            message = "the defense position monster " +
                    clientDuelServer.getDefenseTargetCardName() + " is destroyed";
        } else if (error == 13) {
            message = "opponent???s monster card was " + clientDuelServer.getDefenseTargetCardName()
                    + " and no card is destroyed";
        } else if (error == 14) {
            message = "opponent???s monster card was " + clientDuelServer.getDefenseTargetCardName()
                    + " and no card is destroyed and you received " +
                    clientDuelServer.damageOnPlayer() + " battle damage";
        }
        showMessage(message);
        updateGameBoard();
    }

    public void directAttack() {
        int error = clientDuelServer.directAttackErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "you can???t attack with this card";
        } else if (error == 3) {
            message = "you can???t do this action in this phase";
        } else if (error == 4) {
            message = "this card already attacked";
        } else if (error == 5) {
            message = "you can???t attack the opponent directly";
        } else if (error == 6) {
            message = "you opponent receives " + clientDuelServer.damageOnOpponent()
                    + " battle damage";
        }
        showMessage(message);
        updateGameBoard();
    }

    public void activateEffect() {
        int error = clientDuelServer.activeEffectErrorHandler();
        String message = "";
        if (error == 1) {
            message = "no card is selected yet";
        } else if (error == 2) {
            message = "activate effect is only for spell cards.";
        } else if (error == 3) {
            message = "you can???t activate an effect on this turn";
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
        updateGameBoard();
    }

    public void surrender() {
        MusicPlayer.playLoseMusic();
        clientDuelServer.surrender();
        updateGameBoard();
    }

    public void cancel() {
        clientDuelServer.cancel();
        updateGameBoard();
    }

    public ArrayList<Integer> getCardsForTribute(int i) {
        while (true) {
            try {
                String input = GetInput.getStringAnswerPopupWindow("Tribute Summon",
                        "Please enter the position of " + i + " cards with \"1\" space between them to tribute. " +
                                "Leave field empty to cancel summon.");
                String[] numbers = input.split(" ");
                ArrayList<Integer> positions = new ArrayList<>();
                // add (position - 1) to ArrayList because zero-indexed fields:
                for (String num : numbers)
                    positions.add(Integer.parseInt(num) - 1);
                return positions;
            } catch (NumberFormatException exception) {
                showMessage("Invalid input format. Try again!");
            }
        }
    }

    public String getCardFromGraveYard(String view) {
        return GetInput.getStringAnswerPopupWindow("Attention!", view);
    }

    public String getCardName() {
        return GetInput.getStringAnswerPopupWindow("Attention!", "Please enter a card name:");
    }

    public int getNumber(String view) {
        while (true) {
            String input = GetInput.getStringAnswerPopupWindow("Attention!", view);
            try {
                int answer = Integer.parseInt(input);
                return answer;
            } catch (NumberFormatException ignored) {
                showOutput("Invalid number format. Try again!");
            }
        }
    }

    public void showOutput(String message) {
        showMessage(message);
    }

    public void updatePlayerGameBoard() {
        updateGameBoard();
    }

    public void updateOpponentGameBoard() {
        updateGameBoard();
    }

    public void showGameWinner(String winnerUsername, int playerWins, int opponentWins) {
        String message = winnerUsername + " won the game and the score is: " + playerWins + "-" + opponentWins;
        showMessage(message);
    }

    public void showMatchWinner(String winnerUsername, int playerWins, int opponentWins) {
        String message = winnerUsername + " won the the whole match with score: " + playerWins + "-" + opponentWins;
        showMessage(message);
    }

    public void endGame() {
        LobbyMenuGui lobbyMenuGui = LobbyMenuGui.getLobbyMenuGui();
        LobbyMenuGui.setUsername(username);
        try {
            lobbyMenuGui.start(stage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // button methods that aren't in CLI methods:
    public void attack(MouseEvent mouseEvent) {
        if (!clientDuelServer.isThereAnyMonsterOnOpponentMonsterField()) {
            directAttack();
        } else {
            selectCards("attackCard", new String[]{"opponent_monster"}, 1);
        }
        updateGameBoard();
    }

    public void pause(MouseEvent mouseEvent) {
        pausePopupWindow = new Stage();
        pausePopupWindow.setTitle("Pause");
        pausePopupWindow.getIcons().add(GetImage.getGameIcon());
        pausePopupWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        Button resumeButton = new Button("resume");
        Button muteButton = new Button();
        if (MusicPlayer.isDuelMenuMute()) muteButton.setText("unmute");
        else muteButton.setText("mute");
        Button exitButton = new Button("exit");
        resumeButton.setOnAction(e -> pausePopupWindow.close());
        muteButton.setOnAction(e -> {
            if (MusicPlayer.isDuelMenuMute()) {
                MusicPlayer.unMuteDuelMenuMusic();
                muteButton.setText("mute");
            } else {
                MusicPlayer.muteDuelMenuMusic();
                muteButton.setText("unmute");
            }
        });
        exitButton.setOnAction(e -> exitToMainMenu());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(resumeButton, muteButton, exitButton);
        vBox.setAlignment(Pos.CENTER);
        setStyles(resumeButton, exitButton, muteButton, vBox, borderPane);
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 250, 250);
        pausePopupWindow.setScene(scene);
        pausePopupWindow.setResizable(false);
        pausePopupWindow.setTitle("pause");
        pausePopupWindow.showAndWait();
    }

    private void setStyles(Button resumeButton, Button exitButton, Button muteButton, VBox vBox, BorderPane borderPane) {
        vBox.setStyle("-fx-background-color: black");
        vBox.setSpacing(10);
        resumeButton.setMinWidth(70);
        resumeButton.setMinHeight(20);
        resumeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: orange");

        exitButton.setMinWidth(70);
        exitButton.setMinHeight(20);
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: orange");

        muteButton.setMinWidth(70);
        muteButton.setMinHeight(20);
        muteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: orange");

    }

    public void exitToMainMenu() {
        pausePopupWindow.close();
        MusicPlayer.muteDuelMenuMusic();
        MainMenuGui mainMenuGui = new MainMenuGui();
        MainMenuGui.setUsername(username);
        MusicPlayer.unMuteMainMenu();
        try {
            mainMenuGui.start(stage);
        } catch (Exception ignored) {
        }
    }


    private void createShortCut() {
        stage.getScene().getAccelerators().put(KeyCombination.keyCombination("CTRL+SHIFT+C"),
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new CheatMenu().start(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void increaseLpCheat(String input) {
        String lpStr = input.replace("increase --LP ", "");
        clientDuelServer.increaseLpCheat(Integer.parseInt(lpStr));
    }

    public void setWinnerCheat(String input) {
        String winnerNickname = input.replace("duel set-winner ", "");
        clientDuelServer.setWinnerCheat(winnerNickname);
    }

    public Boolean getYesNoAnswer(String question) {
        return GetInput.getYesNoAnswer(question);
    }

    public void playWinMusic() {
        MusicPlayer.playWinMusic();
    }

    public void playLoseMusic() {
        MusicPlayer.playLoseMusic();
    }
}
