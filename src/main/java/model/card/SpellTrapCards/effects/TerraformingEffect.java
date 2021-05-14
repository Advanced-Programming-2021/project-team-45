package model.card.SpellTrapCards.effects;

import model.game.Chain;
import model.game.fields.DeckField;
import model.game.fields.Hand;

public class TerraformingEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        DeckField deckField = chain.getPlayerGameBoard().getDeckField();
        Hand hand = chain.getPlayerGameBoard().getHand();

        if (deckField.doesFieldSpellExist()) {
            hand.addCard(deckField.getFieldSpell());
        }
    }
}
