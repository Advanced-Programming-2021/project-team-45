package Server.model.card.SpellTrapCards.effects;

import Server.model.card.MonsterCard;
import Server.model.game.Chain;
import Server.model.game.ChainStartState;
import Server.model.game.fields.MonsterField;

public class SolemnWarningEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        // reduce lifepoint and deActive summon:
        chain.getPlayer().getLifepoint().decreaseLifepoint(2000);
        chain.deActiveMonster();
        // delete and destroy monster:
        MonsterCard monster = (MonsterCard) chain.getGame().getSelectedCard();
        MonsterField monsterField = chain.getGame().getPlayerGameBoard().getMonsterField();
        monsterField.deleteAndDestroyMonster(monster);
    }

    @Override
    public boolean canActivate(Chain chain) {
        return chain.getChainStartState() == ChainStartState.MONSTER_SUMMON &&
                chain.getPlayer().getLifepoint().getLifepoint() > 2000;
    }
}
