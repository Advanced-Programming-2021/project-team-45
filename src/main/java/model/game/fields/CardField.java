package model.game.fields;

import model.card.Card;

public abstract class CardField {

    public abstract boolean doesCardExist(String cardName);

    public abstract Card getCardByName(String cardName);
}