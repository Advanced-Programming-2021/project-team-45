package controller;

import model.user.User;

import java.util.ArrayList;

public class DeckController extends Controller{

    public DeckController(String userName){
        super(userName);
    }

    public int createDeckErrorHandler(String deckName){
        User user = User.getUserByUsername(super.userName);
        if(doesDeckExist(deckName)) return 1;
        else{
            (user.getUserDeck()).createDeck(deckName);
            return 0;
        }
    }

    public int deleteDeckErrorHandler(String deckName){
        User user = User.getUserByUsername(super.userName);
        if(!(doesDeckExist(deckName))) return 1;
        else{
            (user.getUserDeck()).deleteDeckFromUserDecks(deckName);
        }
    }

    public int activateDeckErrorHandler(String deckName){
        User user = User.getUserByUsername(super.userName);
        if(!(doesDeckExist(deckName))) return 1;
        else{
            (user.getUserDeck()).activateDeck(deckName);
        }
    }

    public int addCardErrorHandler(String deckName, String cardName, boolean isSideDeck){
        User user = User.getUserByUsername(super.userName);
        if(!(doesCardExist(cardName))){
            return 1;

        } else if(!(doesDeckExist(deckName))){
            return 2;

        } else if(isDeckFull(deckName, isSideDeck)){
            return 3;

        }else if(isDeckFullFromCard(deckName, cardName)){
            return 4;
        }
        (user.getUserDeck()).addCardToDeck(deckName, cardName, isSideDeck);
        return 0;
    }

    public int removeCardErrorHandler(String deckName, String cardName, boolean isSideDeck){
        User user = User.getUserByUsername(super.userName);
        if(!(doesDeckExist(deckName))){
            return 1;
        } else if(!(doesCardExistInDeck(deckname, cardName, isSideDeck))){
            return 2;
        }
        (user.getUserDeck()).deleteCardFromDeck(deckName, cardName, isSideDeck);
        return 0;
    }

    public String getActiveDeckStr(){
        User user = User.getUserByUsername(super.userName);
        return (user.getUserDeck()).getActiveDeck(super.userName).toString;
    }

    public ArrayList<String> getOtherDeckStr(){
        User user = User.getUserByUsername(super.userName);
        return (user.getUserDeck()).getSortedOtherDeckStr(this.userName);
    }

    public int showErrorHandler(String DeckName){
        if(!(doesDeckExist(deckName))) return 1;
        else return 0;
    }

    public ArrayList<String> getMonstersStr(String deckName, boolean isSideDeck){
        User user = User.getUserByUsername(super.userName);
        return (user.getUserDeck()).getMonstersDeckStr(isSideDeck);
    }

    public ArrayList<String> getSpellAndTrapsStr(String deckName, boolean isSideDeck){
        User user = User.getUserByUsername(super.userName);
        return (user.getUserDeck()).getSpellAndTrapsDeckStr(isSideDeck);
    }

    public ArrayList<String> getAllCards(){
        return getUserCardsStr(this.userName);
    }
}
