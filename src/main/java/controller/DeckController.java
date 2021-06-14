package controller;


import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.user.UserDeck;
import view.gui.elements.GetImage;


import java.util.ArrayList;

public class DeckController extends Controller {

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
        user.getUserDeck().getDeckByName(deckName).deleteCard(cardName, isSideDeck);;
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


    //for gui
    public static void addCardToMainDeck(Rectangle[][] rectangles, Image image){
        boolean bol=false;
        for(int i=0;i<6;i++){
            for(int j=0; j<10;j++){
                if(rectangles[i][j].getFill().equals(Color.color(1, 1, 1, 0))){
                    rectangles[i][j].setFill(new ImagePattern(image));
                    bol=true;
                    break;
                }
            }
            if(bol){
                break;
            }
        }
    }

    public void setScrollBar(ListView listView){
        if(user.getCardInventory()!=null) {
            for (int i = 0; i < user.getCardInventory().getCards().size(); i++) {
                Rectangle rectangle=new Rectangle();
                rectangle.setWidth(214);
                rectangle.setHeight(240);
                System.out.println(user.getCardInventory().getAllCardsStr().get(i));
                rectangle.setFill(new ImagePattern(GetImage.getCardImage(user.getCardInventory()
                        .getCards().get(i).getCardName())));
                listView.getItems().add(rectangle);
            }
        }
    }


}
