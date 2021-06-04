package model.card.SpellTrapCards.effects;

import model.card.MonsterCard;
import model.game.Chain;
import model.game.ChainStartState;
import model.game.fields.MonsterField;

public class SolemnWarningEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        // reduce lifepoint and deActive summon:
        chain.getPlayer().getLifepoint().decreaseLifepoint(2000);
        chain.deActiveMonster();
        // delete and destroy monster:
        MonsterCard monster = (MonsterCard) chain.getGame().getSelectedCard();
        MonsterField monsterField = chain.getGame().getGameBoardOfPlayerOfThisTurn().getMonsterField();
        monsterField.deleteAndDestroyMonster(monster);
    }

    @Override
    public boolean canActivate(Chain chain) {
        return chain.getChainStartState() == ChainStartState.MONSTER_SUMMON &&
                chain.getPlayer().getLifepoint().getLifepoint() > 2000;
    }
}
