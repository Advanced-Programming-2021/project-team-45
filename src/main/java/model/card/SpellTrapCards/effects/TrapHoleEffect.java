package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Chain;
import model.game.ChainStartState;
import model.game.fields.Graveyard;

public class TrapHoleEffect extends Effect {

    @Override
    public void activate(Chain chain) {
        MonsterCard monster = (MonsterCard) chain.getGame().getSelectedCard();
        Graveyard graveyard = chain.getGame().getPlayerGameBoard().getGraveyard();
        graveyard.addCardToGraveyard(monster);
        chain.getGame().deselectCard();
    }

    @Override
    public boolean canActivate(Chain chain) {
        if (chain.getPlayer() == chain.getGame().getOpponent()) {
            Card card = chain.getGame().getSelectedCard();
            if (card instanceof MonsterCard) {
                if (((MonsterCard) card).getAttack() >= 1000) {
                    return chain.getChainStartState() == ChainStartState.MONSTER_FLIP_SUMMON ||
                            chain.getChainStartState() == ChainStartState.MONSTER_SUMMON;
                }
            }
        }
        return false;
    }
}
