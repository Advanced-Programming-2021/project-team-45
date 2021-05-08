package model.game.fields;
import model.card.Card;
import model.user.User;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsInHand=new ArrayList<>();
    private User handOwner;

    public void addCard(Card card){
        this.cardsInHand.add(card);
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public boolean doesCardExistInThesePlace(int numberOfHandAddress){
        if((this.cardsInHand).get(numberOfHandAddress -1) == null)
            return false;
        else return true;
    }

    public Card getCardFromHand(int numberOfHandAddress){
        return (this.cardsInHand).get(numberOfHandAddress -1);
    }

    public boolean doesCardExistInHand(Card card){
        if(this.cardsInHand.contains(card)) return true;
        else return false;
    }

    public void deleteCard(Card card) {
        ArrayList<Card> targetCard = new ArrayList<>();
        for(int i = 0; i < this.cardsInHand.size(); i++) {
            if(this.cardsInHand.get(i).equals(card)){
                targetCard.add(this.cardsInHand.get(i));
                break;
            }
        }
        this.cardsInHand.remove(targetCard.get(0));
    }

    public void deleteCardWithNumberOfIt(int num){
        this.cardsInHand.remove(num);
    }
}