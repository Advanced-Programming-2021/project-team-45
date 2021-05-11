package model.card.SpellTrapCards.effects;

import model.game.Game;
import model.game.fields.DeckField;
import model.game.fields.Hand;

public class TerraformingEffect extends Effect {
    @Override
    public void activate(Game game) {
        DeckField deckField = game.getGameBoardOfPlayerOfThisTurn().getDeckField();
        Hand hand = game.getGameBoardOfPlayerOfThisTurn().getHand();

        if (deckField.doesFieldSpellExist()) {
            hand.addCard(deckField.getFieldSpell());
        }
    }
}
