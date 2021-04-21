package model.user;

import model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class CardInventory {

    private final ArrayList<Card> cardInventory;
    private final ArrayList<Card> boughtCards;

    public CardInventory(){
        this.cardInventory = new ArrayList<>();
        this.boughtCards = new ArrayList<>();
    }

    public void addCardToCardInventory(Card card){
        (this.cardInventory).add(card);
    }

    public void addCardToBoughtCards(Card newCard){
        int existence = 0;
        for(Card card : this.boughtCards){
            if((card.getCardName()).equals(newCard.getCardName()))
                existence++;
        }
        if(existence == 0) (this.boughtCards).add(newCard);
    }

    public void deleteCardFromCardInventory(Card card){
        (this.cardInventory).remove(card);
    }

    public boolean doesCardExist(String cardName){
        int existence = 0;
        for(Card card : this.cardInventory){
            if((card.getCardName()).equals(cardName))
                existence++;
        }
        if(existence == 0) return false; else return true;
    }

    public Card getCardByCardName(String cardName){
        ArrayList<Card> targetCard = new ArrayList<>();
        for(Card card : this.cardInventory){
            if((card.getCardName()).equals(cardName))
                targetCard.add(card);
        }
        return targetCard.get(0);
    }

    public ArrayList<String> getAllCardsStr(){
        ArrayList<String> cardsStr = new ArrayList<>();
        for(Card card : this.boughtCards){
            cardsStr.add(card.getCardName() + ":" + card.getCardDescription());
        }
        Collections.sort(cardsStr);
        return cardsStr;
    }
}
