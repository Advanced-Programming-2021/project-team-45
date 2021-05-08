package model.card.SpellTrapCards.effects;

import model.Game.Game;
import model.Game.MonsterField;
import model.card.MonsterCard;
import model.card.MonsterType;

import java.util.ArrayList;

public class IncreaseAtkDefEffect extends Effect {

    private final int attack;
    private final int defence;
    private final MonsterType monsterType;

    public IncreaseAtkDefEffect(int attack , int defence, MonsterType monsterType) {
        this.attack = attack;
        this.defence = defence;
        this.monsterType = monsterType;
    }


    @Override
    public void activate(Game game) {
        MonsterField monsterField1 = game.getPlayerGameBoard().getMonsterField();
        MonsterField monsterField2 = game.getOpponentGameBoard().getMonsterField();

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