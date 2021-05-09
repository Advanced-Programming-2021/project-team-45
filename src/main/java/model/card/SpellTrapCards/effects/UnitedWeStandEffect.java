package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.game.Game;
import model.game.fields.MonsterField;

import java.util.ArrayList;

public class UnitedWeStandEffect extends Effect {
    @Override
    public void activate(Game game) {
        ArrayList<MonsterCard> monsters = game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonstersOnField();
        int count = monsters.size();
        for (MonsterCard monster : monsters) {
            if (monster.getPosition() == PositionMonsters.DEFENSE &&
            monster.getDefenceMode() == DefensePosition.DH) {
                count--;
            }
        }

        MonsterField playerMonsterField = game.getGameBoardOfPlayerOfThisTurn().getMonsterField();
        ArrayList<Card> input = game.getGameController().getCardFromPlayer(1, playerMonsterField);
        MonsterCard monster = (MonsterCard) input.get(0);

        monster.increaseAttack(count * 800);
        monster.increaseDefense(count * 800);
    }
}