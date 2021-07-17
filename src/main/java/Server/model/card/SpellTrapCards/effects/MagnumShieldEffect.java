package Server.model.card.SpellTrapCards.effects;

import Server.model.card.Card;
import Server.model.card.MonsterCard;
import Server.model.card.MonsterType;
import Server.model.card.PositionMonsters;
import Server.model.game.Chain;
import Server.model.game.fields.MonsterField;

import java.util.ArrayList;

public class MagnumShieldEffect extends Effect {
    @Override
    public void activate(Chain chain) {
//        MonsterField playerMonsterField = chain.getPlayerGameBoard().getMonsterField();
//        ArrayList<Card> input = chain.getGame().getGameController().getCardFromPlayer(1, playerMonsterField);
//        MonsterCard monster = (MonsterCard) input.get(0);
//
//        if (monster.getType() == MonsterType.Warrior) {
//            if (monster.getPosition() == PositionMonsters.ATTACK) {
//                monster.increaseAttack(monster.getDefense());
//            } else {
//                monster.increaseDefense(monster.getAttack());
//            }
//        }
    }
}