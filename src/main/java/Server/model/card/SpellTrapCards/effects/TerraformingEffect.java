package Server.model.card.SpellTrapCards.effects;

import Server.model.game.Chain;
import Server.model.game.fields.DeckField;
import Server.model.game.fields.Hand;

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
