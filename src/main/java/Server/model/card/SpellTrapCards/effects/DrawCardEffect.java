package Server.model.card.SpellTrapCards.effects;

import Server.model.game.Chain;
import Server.model.game.fields.DeckField;
import Server.model.game.fields.Hand;

public class DrawCardEffect extends Effect {

    private final int count;

    public DrawCardEffect(int count) {
        this.count = count;
    }


    @Override
    public void activate(Chain chain) {
        DeckField deckField = chain.getPlayerGameBoard().getDeckField();
        Hand hand = chain.getPlayerGameBoard().getHand();

        for (int i = 0; i < count; i++) {
            hand.addCard(deckField.drawCard());
        }
    }
}