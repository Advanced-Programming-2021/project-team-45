package model.user;

import model.card.Card;

import java.util.ArrayList;

public class CardInventory {
    private ArrayList<Card> cardInventory;

    public CardInventory(){
        this.cardInventory = new ArrayList<>();
    }

    public void addCardToCardInventory(Card card){
        (this.cardInventory).add(card);
    }

    public void deleteCardFromCardInventory(Card card){
        (this.cardInventory).remove(card);
    }
}
