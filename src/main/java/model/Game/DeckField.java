package model.Game;

import model.card.Card;
import model.card.Deck;
import model.user.User;

public class DeckField {
    private User owner;
    private Deck deck;

    public DeckField(User user){
        this.owner = user;
        this.deck = user.getUserDeck().getActiveDeck();
    }

    public Card getCard(){
        return (this.deck).getCard();
    }

    public Card getFieldCard() {
        return (this.deck).getAFieldCard();
    }

    public int getDeckSize(){
        return (this.deck).getMainDeck().size();
    }
}
