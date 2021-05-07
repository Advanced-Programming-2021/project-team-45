package model.card.SpellTrapCards.effects;

import model.Game.Game;
import model.Game.MonsterField;
import model.card.MonsterCard;
import model.card.MonsterType;

import java.util.ArrayList;

public class IncreaseDefenceEffect extends Effect {

    private final int amount;
    private final MonsterType monsterType;

    public IncreaseDefenceEffect(int amount, MonsterType monsterType) {
        this.amount = amount;
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
                monster.increaseDefense(amount);
            }
        }
    }
}