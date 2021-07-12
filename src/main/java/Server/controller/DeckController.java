package Server.controller;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Server.model.card.Card;
import Server.model.card.Deck;
import Server.model.user.User;
import Server.model.user.UserDeck;
import Client.view.DeckMenuGui;
import Client.view.MainMenuGui;
import Client.view.ShowOutput;
import Client.view.elements.GetImage;


import java.util.ArrayList;
import java.util.HashMap;

public class DeckController extends Controller {

    private HashMap<Rectangle, Card> rectangleCardHashMap = new HashMap<>();
    private HashMap<Rectangle, Card> mainDeckHashMap = new HashMap<>();
    private HashMap<Rectangle, Card> sideDeckHashMap = new HashMap<>();
    private CalculatorOfNumberOfCards calculator;
    private static Rectangle sourceRectangle;
    Deck deck;

    public void createANewDeck(TextField textField, User user) {
        deck = new Deck(textField.getText(), user.getUsername());
    }

    public DeckController(String userName) {
        super(userName);
    }

    public int createDeckErrorHandler(String deckName) {
        UserDeck userDeck = user.getUserDeck();
        if (userDeck.doesDeckExist(deckName)) {
            return 1;
        } else {
            userDeck.createDeck(deckName, user);
            return 0;
        }
    }

    public int deleteDeckErrorHandler(String deckName) {
        UserDeck userDeck = user.getUserDeck();
        if (!(userDeck.doesDeckExist(deckName))) {
            return 1;
        } else {
            userDeck.deleteDeckFromUserDecks(deckName);
            return 0;
        }
    }

    public int activateDeckErrorHandler(String deckName) {
        UserDeck userDeck = user.getUserDeck();
        if (!(userDeck.doesDeckExist(deckName))) {
            return 1;
        } else {
            user.getUserDeck().activateDeck(deckName);
            return 0;
        }
    }

    public int addCardErrorHandler(String deckName, String cardName, boolean isSideDeck) {
        UserDeck userDeck = user.getUserDeck();
        if (!(user.getCardInventory().doesCardExistToAddToDeck(userDeck, deckName, cardName))) {
            return 1;

        } else if (!(userDeck.doesDeckExist(deckName))) {
            return 2;

        } else if (userDeck.getDeckByName(deckName).isDeckFull(isSideDeck)) {
            return 3;

        } else if (userDeck.getDeckByName(deckName).isFullFromCard(cardName)) {
            return 4;
        }
        userDeck.getDeckByName(deckName).addCard(cardName, isSideDeck, user);
        return 0;
    }

    public int removeCardErrorHandler(String deckName, String cardName, boolean isSideDeck) {
        UserDeck userDeck = (super.user).getUserDeck();
        if (!(userDeck.doesDeckExist(deckName))) {
            return 1;
        } else if (!(userDeck.getDeckByName(deckName).doesCardExist(cardName, isSideDeck))) {
            return 2;
        }
        user.getUserDeck().getDeckByName(deckName).deleteCard(cardName, isSideDeck);
        return 0;
    }

    public String getActiveDeckStr() {
        UserDeck userDeck = user.getUserDeck();
        return userDeck.getActiveDeckStr();
    }

    public ArrayList<String> getOtherDeckStr() {
        return user.getUserDeck().getSortedOtherDeckStr();
    }

    public int showDeckErrorHandler(String deckName, boolean isSideDeck) {
        UserDeck userDeck = user.getUserDeck();
        if (!(userDeck.doesDeckExist(deckName))) {
            return 1;
        } else {
            return 0;
        }
    }

    public ArrayList<String> getMonstersStr(String deckName, boolean isSideDeck) {
        UserDeck userDeck = user.getUserDeck();
        return userDeck.getDeckByName(deckName).getMonstersStr(isSideDeck);
    }

    public ArrayList<String> getSpellAndTrapsStr(String deckName, boolean isSideDeck) {
        UserDeck userDeck = user.getUserDeck();
        return userDeck.getDeckByName(deckName).getSpellAndTrapStr(isSideDeck);
    }

    public ArrayList<String> getAllCardsStr() {
        return user.getCardInventory().getAllCardsStr();
    }

    public void setScrollBar(ListView listView, DeckMenuGui deckMenuGui) {
        ArrayList<Card> allCards = User.getUserByUsername(MainMenuGui.getUsername()).getCardInventory().getCards();
        for (Card allCard : allCards) {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(GetImage.getCardImage(allCard.getCardName())));
            rectangleCardHashMap.put(rectangle, allCard);
            rectangle.setHeight(300);
            rectangle.setWidth(listView.getWidth() - 10);
            listView.getItems().add(rectangle);
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Stage stage = new Stage();
                    AnchorPane anchorPane = new AnchorPane();
                    Button button = new Button();
                    Button button1 = new Button();
                    addButtons(anchorPane, button, button1, rectangle, deckMenuGui);
                }
            });
            onMouseMoved(rectangle);
        }
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    private void onMouseMoved(Rectangle rectangle) {
        rectangle.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Label label = DeckMenuGui.getDescription();
                label.setText(rectangleCardHashMap.get(rectangle).getCardDescription());
                DeckMenuGui.getPictureOfCard().setFill(new ImagePattern
                        (GetImage.getCardImage(rectangleCardHashMap.get(rectangle).getCardName())));
            }
        });
    }

    private void addButtons(AnchorPane anchorPane, Button button, Button button1, Rectangle rectangle
            , DeckMenuGui deckMenuGui) {
        DeckMenuGui.chooseForAddToDeck(anchorPane, button, button1);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addCardToMainDeck(rectangle, deckMenuGui);
            }
        });
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addCardToSideDeck(rectangle, deckMenuGui);
            }
        });

    }

    private void addCardToSideDeck(Rectangle rectangle, DeckMenuGui deckMenuGui) {
        boolean bol = false;
        Rectangle[][] rectangles = deckMenuGui.getSideRectangles();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (rectangles[i][j].getAccessibleText().equals("null")) {
                    Card card = (rectangleCardHashMap.get(rectangle));
                    boolean boll = numberOfThis(card.getCardName(), deck.getSideDeck());
                    if (boll) {
                        rectangles[i][j].setFill(rectangle.getFill());
                        rectangles[i][j].setAccessibleText("full");
                        deck.addCard(card.getCardName(), true, user);
                        sideDeckHashMap.put(rectangles[i][j], card);
                    } else {
                        ShowOutput.showOutput("ErrorBox", "your side deck is full from this card");
                    }
                    bol = true;
                    break;
                } else {
                    if (i == 1 && j == 5) {
                        ShowOutput.showOutput("ErrorBox", "your side deck is full");
                        break;
                    }
                }
            }
            if (bol) break;
        }
    }

    private void addCardToMainDeck(Rectangle rectangle, DeckMenuGui deckMenuGui) {
        Rectangle[][] rectangles = deckMenuGui.getMainRectangles();
        boolean bol = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (rectangles[i][j].getAccessibleText().equals("null")) {
                    Card card = rectangleCardHashMap.get(rectangle);
                    boolean boll = numberOfThis(card.getCardName(), deck.getMainDeck());
                    if (boll) {
                        rectangles[i][j].setAccessibleText("full");
                        rectangles[i][j].setFill(rectangle.getFill());
                        deck.addCard(card.getCardName(), false, user);
                        mainDeckHashMap.put(rectangles[i][j], card);
                    } else {
                        ShowOutput.showOutput("ErrorBox", "your main deck is full from this card");
                    }
                    bol = true;
                    break;
                } else {
                    if (i == 5 && j == 10) {
                        ShowOutput.showOutput("ErrorBox", "your main deck is full");
                        break;
                    }
                }
            }
            if (bol) break;
        }
    }

    private boolean numberOfThis(String cardName, ArrayList<Card> cards) {
        int numberOfCards = 0;
        for (Card card : cards) {
            if (card.getCardName().equals(cardName)) numberOfCards++;
        }
        return numberOfCards < 3;
    }

    public void handlerOfCards(Rectangle rectangle) {

        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mainDeckHashMap.get(rectangle) != null) {
                    deck.deleteCard(mainDeckHashMap.get(rectangle).getCardName(), false);
                    mainDeckHashMap.remove(rectangle);
                }
                if (sideDeckHashMap.get(rectangle) != null) {
                    deck.deleteCard(sideDeckHashMap.get(rectangle).getCardName(), true);
                    sideDeckHashMap.remove(rectangle);
                    System.out.println("kos");
                }
                rectangle.setFill(Color.color(0, 0, 1, 0));
                rectangle.setAccessibleText("null");
            }
        });

        rectangle.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (rectangle.getAccessibleText().equals("full")) {
                    Label label = DeckMenuGui.getDescription();
                    if (mainDeckHashMap.get(rectangle) != null) {
                        label.setText(mainDeckHashMap.get(rectangle).getCardDescription());
                        DeckMenuGui.getPictureOfCard().setFill(new ImagePattern(GetImage.
                                getCardImage(mainDeckHashMap.get(rectangle).getCardName())));
                    } else {
                        label.setText(sideDeckHashMap.get(rectangle).getCardDescription());
                        DeckMenuGui.getPictureOfCard().setFill(new ImagePattern(GetImage.getCardImage(sideDeckHashMap
                                .get(rectangle).getCardName())));
                    }
                }
            }
        });
        continueForDragAndDrop(rectangle);
    }

    private void continueForDragAndDrop(Rectangle rectangle) {
        rectangle.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString()) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                }
            }
        });

        rectangle.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (rectangle.getAccessibleText().equals("null")) {
                    rectangle.setFill(sourceRectangle.getFill());
                    boolean bool = DeckMenuGui.mainSideRectangles.get(sourceRectangle).equals("main");
                    if (DeckMenuGui.mainSideRectangles.get(rectangle).equals("main")) {
                        if (bool) {
                            mainDeckHashMap.put(rectangle, mainDeckHashMap.get(sourceRectangle));
                            mainDeckHashMap.remove(sourceRectangle);
                        } else {
                            mainDeckHashMap.put(rectangle, sideDeckHashMap.get(sourceRectangle));
                            sideDeckHashMap.remove(sourceRectangle);
                            deck.deleteCard(mainDeckHashMap.get(rectangle).getCardName(), true);
                            deck.addCard(mainDeckHashMap.get(rectangle).getCardName(), false,
                                    User.getUserByUsername(username));
                        }
                    } else {
                        if (bool) {
                            sideDeckHashMap.put(rectangle, mainDeckHashMap.get(sourceRectangle));
                            mainDeckHashMap.remove(sourceRectangle);
                            deck.deleteCard(sideDeckHashMap.get(rectangle).getCardName(), false);
                            deck.addCard(sideDeckHashMap.get(rectangle).getCardName(), true,
                                    User.getUserByUsername(username));
                        } else {
                            sideDeckHashMap.put(rectangle, sideDeckHashMap.get(sourceRectangle));
                            sideDeckHashMap.remove(sourceRectangle);
                        }
                    }
                    sourceRectangle.setAccessibleText("null");
                    rectangle.setAccessibleText("full");
                    sourceRectangle.setFill((Color.color(0, 0, 1, 0)));
                    sourceRectangle = null;
                } else {
                    sourceRectangle = null;
                    ShowOutput.showOutput("error box", "your destination must be null to drag and drop card");
                }
            }
        });

        rectangle.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard db = rectangle.startDragAndDrop(TransferMode.ANY);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString("BitCoin");
                db.setContent(clipboardContent);
                sourceRectangle = rectangle;
                mouseEvent.consume();
            }
        });
    }

    public void initializeMainAndSide(Rectangle[][] mainRectangles, Rectangle[][] sideRectangles) {
        ArrayList<Card> mainCards = deck.getMainDeck();
        ArrayList<Card> sideCards = deck.getSideDeck();
        int k = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (k == mainCards.size()) {
                    break;
                } else {
                    mainRectangles[i][j].setFill(new ImagePattern(GetImage.getCardImage(mainCards.get(k)
                            .getCardName())));
                    mainRectangles[i][j].setAccessibleText("full");
                    mainDeckHashMap.put(mainRectangles[i][j], mainCards.get(k));
                    k++;
                }
            }
            if (k == mainCards.size() || k == 60) break;
        }
        k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (k == sideCards.size()) {
                    break;
                } else {
                    sideRectangles[i][j].setFill(new ImagePattern(GetImage.getCardImage(sideCards.get(k)
                            .getCardName())));
                    sideRectangles[i][j].setAccessibleText("full");
                    sideDeckHashMap.put(sideRectangles[i][j], sideCards.get(k));
                    k++;
                }
            }
            if (k == sideCards.size() || k == 15) break;
        }
    }

    public void deleteDeck() {
        User.getUserByUsername(username).getUserDeck().deleteDeckFromUserDecks(deck.getName());
        calculator.stop();
    }

    public void setNumberOfCards(Text numberOfCards) {
        calculator = new CalculatorOfNumberOfCards(numberOfCards, User.getUserByUsername(username), deck.getName());
        calculator.start();
    }

    public void stopCounting() {
        calculator.stop();
    }
}

class CalculatorOfNumberOfCards extends Thread {

    private Text numberOfCards;
    private User user;
    private String deckName;

    public CalculatorOfNumberOfCards(Text numberOfCards, User user, String deckName) {
        this.numberOfCards = numberOfCards;
        this.user = user;
        this.deckName = deckName;
    }

    @Override
    public void run() {
        while (true) {
            int allCards = user.getUserDeck().getDeckByName(deckName).getMainDeck().size() +
                    user.getUserDeck().getDeckByName(deckName).getSideDeck().size();
            numberOfCards.setText(String.valueOf(allCards));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}