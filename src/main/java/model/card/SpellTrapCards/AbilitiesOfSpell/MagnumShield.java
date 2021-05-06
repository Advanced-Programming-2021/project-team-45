package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class MagnumShield {

    public static void ability(Game game){
        // input one card of Type Warrior from field
        MonsterCard monsterCard;
        if(monsterCard.getPosition() == PositionMonsters.ATTACK)
            monsterCard.increaseAttack(monsterCard.getDefense());
        else
            monsterCard.increaseDefense(monsterCard.getAttack());
    }
}
