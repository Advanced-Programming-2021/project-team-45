package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.card.MonsterCard;
import model.card.SpecialMonsterEnum;

public class MirageDragon {


    public static boolean canActiveTrapForMirageDragonOfEnemy(MonsterField monsterField){
        MonsterCard[] monsters=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(monsters[i].getSpecialMonsterEnum().equals(SpecialMonsterEnum.MIRAGE_DRAGON)){
                return false;
            }
        }
        return true;
    }
}
