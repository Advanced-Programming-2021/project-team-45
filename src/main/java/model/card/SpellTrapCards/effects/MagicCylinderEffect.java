package model.card.SpellTrapCards.effects;

import model.card.MonsterCard;
import model.game.Chain;
import model.game.ChainStartState;

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
