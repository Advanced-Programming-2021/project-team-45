package model.Game;
import model.card.Card;
import model.user.User;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsInHand=new ArrayList<>();
    private User handOwner;
    // i think it's uncompleted yet. -haji
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public boolean doesCardExistInThesePlace(int numberOfHandAddress){
        if((this.cardsInHand).get(numberOfHandAddress -1) == null)
            return false;
        else return true;
    }

    public Card getCardFromHand(int numberOfHandAddress){
        Card card = (this.cardsInHand).get(numberOfHandAddress -1);
        (this.cardsInHand).set((this.cardsInHand).indexOf(card), null);
        return (this.cardsInHand).get(numberOfHandAddress -1);
    }
}
