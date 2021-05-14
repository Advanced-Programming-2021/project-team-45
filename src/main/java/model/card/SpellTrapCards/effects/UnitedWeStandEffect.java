package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.game.Chain;
import model.game.fields.MonsterField;

import java.util.ArrayList;

public class UnitedWeStandEffect extends Effect {
    @Override
    public void activate(Chain chain) {
        ArrayList<MonsterCard> monsters = chain.getPlayerGameBoard().getMonsterField().getMonstersOnField();
        int count = monsters.size();
        for (MonsterCard monster : monsters) {
            if (monster.getPosition() == PositionMonsters.DEFENSE &&
            monster.getDefenceMode() == DefensePosition.DH) {
                count--;
            }
        }

        MonsterField playerMonsterField = chain.getPlayerGameBoard().getMonsterField();
        ArrayList<Card> input = chain.getGame().getGameController().getCardFromPlayer(1, playerMonsterField);
        MonsterCard monster = (MonsterCard) input.get(0);

        monster.increaseAttack(count * 800);
        monster.increaseDefense(count * 800);
    }
}