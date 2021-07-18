package Server.controller;

import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import Server.model.card.Card;
import Server.model.card.Deck;
import Server.model.user.User;
import Server.model.user.UserDeck;


import java.util.ArrayList;
import java.util.HashMap;

public class DeckController extends Controller {

    private HashMap<Rectangle, Card> mainDeckHashMap = new HashMap<>();
    private HashMap<Rectangle, Card> sideDeckHashMap = new HashMap<>();
    private Deck deck;
    private Rectangle[][] mainRectanglesArray;
    private Rectangle[][] sideRectanglesArray;

    public Deck getDeck() {
        return deck;
    }

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

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    private boolean numberOfThis(String cardName, ArrayList<Card> cards) {
        int numberOfCards = 0;
        for (Card card : cards) {
            if (card.getCardName().equals(cardName)) numberOfCards++;
        }
        return numberOfCards < 3;
    }

    public Rectangle getRectangleWithIAndJ(int i,int j,boolean isMain){
        if(isMain) return mainRectanglesArray[i][j];
        else return sideRectanglesArray[i][j];
    }

    public void deleteDeck() {
        User.getUserByUsername(username).getUserDeck().deleteDeckFromUserDecks(deck.getName());
    }

    public void deleteCardFromDeck(int field, int field1, boolean field2) {
        Rectangle rectangle=getRectangleWithIAndJ(field,field1,field2);
        if (mainDeckHashMap.get(rectangle) != null) {
            deck.deleteCard(mainDeckHashMap.get(rectangle).getCardName(), false);
            mainDeckHashMap.remove(rectangle);
        }
        if (sideDeckHashMap.get(rectangle) != null) {
            deck.deleteCard(sideDeckHashMap.get(rectangle).getCardName(), true);
            sideDeckHashMap.remove(rectangle);
        }
    }

    public void addCardToSideDeck(String field) {
        boolean bol = false;
        Rectangle[][] rectangles = sideRectanglesArray;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (rectangles[i][j].getAccessibleText().equals("null")) {
                    Card card = user.getCardInventory().getCardByCardName(field);
                    boolean boll = numberOfThis(card.getCardName(), deck.getSideDeck());
                    if (boll) {
                        rectangles[i][j].setAccessibleText("full");
                        deck.addCard(card.getCardName(), true, user);
                        sideDeckHashMap.put(rectangles[i][j], card);
                    } else {

                    }
                    bol = true;
                    break;
                } else {
                    if (i == 1 && j == 5) {
                        break;
                    }
                }
            }
            if (bol) break;
        }
    }

    public void addCardToMainDeck(String field) {
        Rectangle[][] rectangles = mainRectanglesArray;
        boolean bol = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (rectangles[i][j].getAccessibleText().equals("null")) {
                    Card card = user.getCardInventory().getCardByCardName(field);
                    boolean boll = numberOfThis(card.getCardName(), deck.getMainDeck());
                    if (boll) {
                        rectangles[i][j].setAccessibleText("full");
                        deck.addCard(card.getCardName(), false, user);
                        mainDeckHashMap.put(rectangles[i][j], card);
                    } else {
                       // ShowOutput.showOutput("ErrorBox", "your main deck is full from this card");
                    }
                    bol = true;
                    break;
                } else {
                    if (i == 5 && j == 9) {
                       // ShowOutput.showOutput("ErrorBox", "your main deck is full");
                        break;
                    }
                }
            }
            if (bol) break;
        }
    }

    public ArrayList<String> getAllCardsName() {
        ArrayList<String> answer=new ArrayList<>();
        for (Card card : (user.getCardInventory().getCards())) {
            answer.add(card.getCardName());
        }
        return answer;
    }

    public ArrayList<String> getMainCardNames() {
        ArrayList<String> answer=new ArrayList<>();
        for (Card card : (deck.getMainDeck())) {
            answer.add(card.getCardName());
        }
        return answer;
    }

    public ArrayList<String> getSideCardNames() {
        ArrayList<String> answer=new ArrayList<>();
        for (Card card : (deck.getSideDeck())) {
            answer.add(card.getCardName());
        }
        return answer;
    }

    public void putCardToMainHashMap(int field, int field1, String field2) {
        mainDeckHashMap.put(mainRectanglesArray[field][field1],user.getCardInventory().getCardByCardName(field2));
        mainRectanglesArray[field][field1].setAccessibleText("full");
    }

    public void putCardToSideHashMap(int field, int field1, String field2) {
        sideDeckHashMap.put(sideRectanglesArray[field][field1],user.getCardInventory().getCardByCardName(field2));
        sideRectanglesArray[field][field1].setAccessibleText("full");
    }

    public void initialize() {
        mainRectanglesArray=new Rectangle[6][10];
        sideRectanglesArray=new Rectangle[2][10];
        for(int i = 0;i<6;i++){
            for(int j=0;j<10;j++){
                mainRectanglesArray[i][j]=new Rectangle();
                mainRectanglesArray[i][j].setAccessibleText("null");
            }
        }
        for(int i=0;i<2;i++){
            for(int j = 0;j<10;j++){
                sideRectanglesArray[i][j]=new Rectangle();
                sideRectanglesArray[i][j].setAccessibleText("null");
            }
        }
    }
}

