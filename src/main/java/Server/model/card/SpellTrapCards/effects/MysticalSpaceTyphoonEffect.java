package Server.model.card.SpellTrapCards.effects;

import Server.model.card.Card;
import Server.model.card.SpellTrapCard;
import Server.model.game.Chain;
import Server.model.game.fields.SpellTrapField;

import java.util.ArrayList;

public class MysticalSpaceTyphoonEffect extends Effect {

    private SpellTrapField spellField;

    @Override
    public void activate(Chain chain) {
        initializeFields(chain);

        chain.getGame().getGameController().showOutput("choose a spell or trap to destroy");
        ArrayList<Card> cards = chain.getGame().getGameController().getCardFromPlayer(1, spellField);
        SpellTrapCard spell = (SpellTrapCard) cards.get(0);

        spellField.deleteAndDestroySpellTrap(spell);
    }

    private void initializeFields(Chain chain) {
        spellField = chain.getOpponentGameBoard().getSpellTrapField();
    }
}
