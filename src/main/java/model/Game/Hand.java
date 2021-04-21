package model.Game;
import model.card.Card;
import model.user.User;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsInHand=new ArrayList<>();
    private User handOwner;

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
}
