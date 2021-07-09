package Server.model.card.SpellTrapCards.effects;

import Server.model.card.Card;
import Server.model.card.MonsterCard;
import Server.model.game.Chain;
import Server.model.game.ChainStartState;
import Server.model.game.fields.Graveyard;

public class TrapHoleEffect extends Effect {

    @Override
    public void activate(Chain chain) {
        MonsterCard monster = (MonsterCard) chain.getGame().getSelectedCard();
        Graveyard graveyard = chain.getGame().getPlayerGameBoard().getGraveyard();
        graveyard.addCardToGraveyard(monster);
        chain.getGame().deselectCard();

        chain.deActiveMonster();
    }

    @Override
    public boolean canActivate(Chain chain) {
        if (chain.getPlayer() == chain.getGame().getOpponentOfThisTurn()) {
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
