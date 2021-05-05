package model.card.SpecialMonsters.AmazingAbility;

import model.card.MonsterCard;

import java.util.ArrayList;

public class Texchanger {
    private boolean canUse=true;

    private static ArrayList<MonsterCard> allTexchanger=new ArrayList<>();

    public static void abilityOfTexchanger(MonsterCard card){
        if(canUse) {
            if (!allTexchanger.contains(card)) {
                allTexchanger.add(card);
                /*
                other statements
                 */
            }else{

            }
        }
    }

    public static void setTrueAllCanUse(){

    }
}
