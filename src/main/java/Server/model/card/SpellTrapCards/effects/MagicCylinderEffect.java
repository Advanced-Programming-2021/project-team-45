package Server.model.card.SpellTrapCards.effects;

import Server.model.card.MonsterCard;
import Server.model.game.Chain;
import Server.model.game.ChainStartState;

public class MagicCylinderEffect extends Effect {

    @Override
    public void activate(Chain chain) {
        chain.deActiveMonster();
        int attack = ((MonsterCard) chain.getGame().getSelectedCard()).getAttack();
        chain.getGame().getPlayerOfThisTurn().getLifepoint().decreaseLifepoint(attack);
    }

    @Override
    public boolean canActivate(Chain chain) {
        return chain.getChainStartState() == ChainStartState.MONSTER_ATTACK;
    }
}
