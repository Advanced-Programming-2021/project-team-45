package model.card.SpellTrapCards.effects;

import model.game.Chain;
import model.game.ChainStartState;

public class NegateAttackEffect extends Effect {

    @Override
    public void activate(Chain chain) {
        chain.getGame().nextPhase();
        chain.deActiveMonster();
        chain.getGame().getGameController().showOutput("phase: Main Phase 2");
    }

    @Override
    public boolean canActivate(Chain chain) {
        return chain.getChainStartState() == ChainStartState.MONSTER_ATTACK;
    }
}
