package Server.model.card.SpecialMonsters.AmazingAbility;

import Server.model.game.fields.MonsterField;
import Server.model.card.MonsterCard;
import Server.model.card.SpecialMonsterEnum;

import java.util.ArrayList;

public class MirageDragon {


    public static boolean canActiveTrapForMirageDragonOfEnemy(MonsterField monsterField){
        ArrayList<MonsterCard> monsters=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(monsters.get(i).getSpecialMonsterEnum().equals(SpecialMonsterEnum.MIRAGE_DRAGON)){
                return false;
            }
        }
        return true;
    }
}
