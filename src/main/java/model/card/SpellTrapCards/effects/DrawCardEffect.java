package model.card.SpellTrapCards.effects;

import model.game.Game;
import model.game.fields.DeckField;
import model.game.fields.Hand;

public class DrawCardEffect extends Effect {

    private final int count;

    public DrawCardEffect(int count) {
        this.count = count;
    }


    @Override
    public void activate(Game game) {
        DeckField deckField = game.getGameBoardOfPlayerOfThisTurn().getDeckField();
        Hand hand = game.getGameBoardOfPlayerOfThisTurn().getHand();

        for (int i = 0; i < count; i++) {
            hand.addCard(deckField.drawCard());
        }
    }
}