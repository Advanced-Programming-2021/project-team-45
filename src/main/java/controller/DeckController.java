package controller;


import model.user.UserDeck;


import java.util.ArrayList;

public class DeckController extends Controller{

    public DeckController(String userName){
        super(userName);
    }

    public int createDeckErrorHandler(String deckName){
        UserDeck userDeck = (super.user).getUserDeck();
        if(userDeck.doesDeckExist(deckName)) return 1;
        else{
            (userDeck).createDeck(deckName,super.user);
            return 0;
        }
    }

    public int deleteDeckErrorHandler(String deckName){
        UserDeck userDeck = (super.user).getUserDeck();
        if(!(userDeck.doesDeckExist(deckName))) return 1;
        else{
            userDeck.deleteDeckFromUserDecks(deckName,super.user);
            return 0;
        }
    }

    public int activateDeckErrorHandler(String deckName){
        UserDeck userDeck = (super.user).getUserDeck();
        if(!(userDeck.doesDeckExist(deckName))) return 1;
        else{
            (user.getUserDeck()).activateDeck(deckName);
            return 0;
        }
    }

    public int addCardErrorHandler(String deckName, String cardName, boolean isSideDeck){
        UserDeck userDeck = (super.user).getUserDeck();
        if(!(((super.user).getCardInventory()).doesCardExist(cardName))){
            return 1;

        } else if(!(userDeck.doesDeckExist(deckName))){
            return 2;

        } else if(userDeck.isDeckFull(deckName, isSideDeck)){
            return 3;

        }else if(userDeck.isDeckFullFromCard(deckName, cardName)){
            return 4;
        }
        (userDeck).addCardToDeck(deckName, cardName, isSideDeck, super.user);
        return 0;
    }

    public int removeCardErrorHandler(String deckName, String cardName, boolean isSideDeck){
        UserDeck userDeck = (super.user).getUserDeck();
        if(!(userDeck.doesDeckExist(deckName))){
            return 1;
        } else if(!(userDeck.doesCardExistInDeck(deckName, cardName, isSideDeck))){
            return 2;
        }
        (user.getUserDeck()).deleteCardFromDeck(deckName, cardName, isSideDeck);
        return 0;
    }

    public String getActiveDeckStr(){
        UserDeck userDeck = (super.user).getUserDeck();
        return userDeck.getActiveDeckStr();
    }

    public ArrayList<String> getOtherDeckStr(){
        UserDeck userDeck = (super.user).getUserDeck();
        return userDeck.getSortedOtherDeckStr();
    }

    public int showDeckErrorHandler(String deckName, boolean isSideDeck){
        UserDeck userDeck = (super.user).getUserDeck();
        if(!(userDeck.doesDeckExist(deckName))) return 1;
        else return 0;
    }

    public ArrayList<String> getMonstersStr(String deckName, boolean isSideDeck){
        UserDeck userDeck = (super.user).getUserDeck();
        return userDeck.getMonstersDeckStr(deckName, isSideDeck);
    }

    public ArrayList<String> getSpellAndTrapsStr(String deckName, boolean isSideDeck){
        UserDeck userDeck = (super.user).getUserDeck();
        return (user.getUserDeck()).getSpellAndTrapsDeckStr(deckName, isSideDeck);
    }

    public ArrayList<String> getAllCardsStr(){
        return ((super.user).getCardInventory()).getAllCardsStr();
    }
}
