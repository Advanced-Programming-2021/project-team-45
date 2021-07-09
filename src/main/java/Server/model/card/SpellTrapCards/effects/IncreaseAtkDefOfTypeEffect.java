package Server.model.card.SpellTrapCards.effects;

import Server.model.game.Chain;
import Server.model.game.fields.MonsterField;
import Server.model.card.MonsterCard;
import Server.model.card.MonsterType;

import java.util.ArrayList;

public class IncreaseAtkDefOfTypeEffect extends Effect {

    private final int attack;
    private final int defence;
    private final MonsterType monsterType;

    public IncreaseAtkDefOfTypeEffect(int attack , int defence, MonsterType monsterType) {
        this.attack = attack;
        this.defence = defence;
        this.monsterType = monsterType;
    }

    @Override
    public void activate(Chain chain) {
        MonsterField monsterField1 = chain.getPlayerGameBoard().getMonsterField();
        MonsterField monsterField2 = chain.getOpponentGameBoard().getMonsterField();

        ArrayList<MonsterCard> allMonsters = monsterField1.getMonstersOnField();
        allMonsters.addAll(monsterField2.getMonstersOnField());

        for (MonsterCard monster : allMonsters) {
            if (monster.getType() == monsterType) {
                monster.increaseAttack(attack);
                monster.increaseDefense(defence);
            }
        }
    }
}