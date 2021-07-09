package Server.model.card.SpellTrapCards.effects;

import Server.model.card.Card;
import Server.model.card.SpellTrapCard;
import Server.model.game.Chain;
import Server.model.game.fields.SpellTrapField;

import java.util.ArrayList;

public class TwinTwistersEffect extends Effect {

    private SpellTrapField spellField;

    @Override
    public void activate(Chain chain) {
        initializeFields(chain);

        int count = 0;
        if (spellField.getCount() > 1) {
            if (chain.getGame().getGameController().getYesNoAnswer("do you want to destroy 2 spells or traps?")) {
                count = 2;
            } else if (chain.getGame().getGameController().getYesNoAnswer("do you want to destroy 1 spell or trap?")) {
                count = 1;
            }
        } else if (spellField.getCount() > 0) {
            if (chain.getGame().getGameController().getYesNoAnswer("do you want to destroy 1 spell or trap?")) {
                count = 1;
            }
        }

        chain.getGame().getGameController().showOutput("choose spell and traps to destroy");
        ArrayList<Card> cards = chain.getGame().getGameController().getCardFromPlayer(count, spellField);

        for (Card card : cards) {
            SpellTrapCard spell = (SpellTrapCard) card;
            spellField.deleteAndDestroySpellTrap(spell);
        }
    }

    private void initializeFields(Chain chain) {
        spellField = chain.getOpponentGameBoard().getSpellTrapField();
    }
}
