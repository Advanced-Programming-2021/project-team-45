package model.card.SpecialMonsters.AmazingAbility;

import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class SuijinAbility {
    private boolean canUse = true;
    private static ArrayList<MonsterCard> allSuijinUseInGame = new ArrayList<>();

    public static int suijinAbility(Card selectedCard) {
        if (!allSuijinUseInGame.contains(selectedCard)) {
            allSuijinUseInGame.add((MonsterCard) selectedCard);
            //after defense kill attacker
        } else {
            //doesn't have a special ability just do common things
        }
    }
}
