package model.card;

import model.user.User;

import java.util.ArrayList;

public class Deck {
    private String name;
    private User user;
    private ArrayList<Card> mainDeckCards;
    private ArrayList<Card> sideDeckCards;
    private ArrayList<Card> allCards;

    public Deck(String name,User user){
        this.name = name;
        this.user = user;
        this.mainDeckCards = new ArrayList<>();
        this.sideDeckCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getMainDeckCards() {
        return mainDeckCards;
    }

    public ArrayList<Card> getSideDeckCards() {
        return sideDeckCards;
    }

    public boolean isFullFromCard(String cardName){
        int numberOfCard = 0;
        for(Card card : this.mainDeckCards){
            if((card.getCardName()).equals(cardName))
                numberOfCard++;
        }
        for(Card card : this.sideDeckCards){
            if((card.getCardName()).equals(cardName))
                numberOfCard++;
        }
        if(numberOfCard > 3) return true;else return false;
    }

    public void addCard(String cardName, boolean isSideDeck, User user){
        Card card = (user.getCardInventory()).getCardByCardName(cardName);
        if(isSideDeck){
            (this.sideDeckCards).add(card);
            (user.getCardInventory()).deleteCardFromCardInventory(card);
        }
    }

    public boolean doesCardExist(String cardName, boolean isSideDeck){
        int existence = 0;
        if(isSideDeck){
            for(Card card : this.sideDeckCards){
                if((card.getCardName()).equals(cardName))
                    existence++;
            }
        } else{
            for(Card card : this.mainDeckCards){
                if((card.getCardName()).equals(cardName))
                    existence++;
            }
        }
        if(existence == 0) return false; else return true;
    }

    public void deleteCard(String cardName, boolean isSideDeck){
        ArrayList<Card> targetCard = new ArrayList<>();
        if(isSideDeck){
            for(Card card : this.sideDeckCards){
                if((card.getCardName()).equals(cardName))
                    targetCard.add(card);
            }
        } else{
            for(Card card : this.mainDeckCards){
                if((card.getCardName()).equals(cardName))
                    targetCard.add(card);
            }
        }
        if(isSideDeck) (this.sideDeckCards).remove(targetCard.get(0));
        else (this.mainDeckCards).remove(targetCard.get(0));
        ((this.user).getCardInventory()).addCardToCardInventory(targetCard.get(0));
    }

    @Override
    public String toString() {
        String validity;
        if((this.mainDeckCards).size() < 60 && (this.sideDeckCards).size() < 15) validity = "valid";
        else validity = "invalid";

        return this.name + ": main deck " + (this.mainDeckCards).size() +
                ", side deck " + (this.sideDeckCards).size() + ", " + validity;
    }
}
