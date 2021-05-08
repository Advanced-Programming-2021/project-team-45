package model.game.fields;

import model.card.Card;

public abstract class CardField {

    protected final String name;

    public CardField(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public abstract boolean doesCardExist(String cardName);

    public abstract Card getCardByName(String cardName);
}