package model.card.SpellTrapCards.effects;

import model.card.MonsterType;

import java.util.ArrayList;

public class AddEffects {
    public static ArrayList<Effect> getCardEffects(String cardName) {
        ArrayList<Effect> effects = new ArrayList<>();
        
        if (cardName.equals("Yami")) {
            effects.add(new IncreaseAtkDefOfTypeEffect(200, 200, MonsterType.Fiend));
            effects.add(new IncreaseAtkDefOfTypeEffect(200, 200, MonsterType.SpellCaster));
            effects.add(new IncreaseAtkDefOfTypeEffect(-200, -200, MonsterType.Fairy));

        } else if (cardName.equals("Forest")) {
            effects.add(new IncreaseAtkDefOfTypeEffect(200, 200, MonsterType.Insect));
            effects.add(new IncreaseAtkDefOfTypeEffect(200, 200, MonsterType.Beast));
            effects.add(new IncreaseAtkDefOfTypeEffect(200, 200, MonsterType.Beast_Warrior));

        } else if (cardName.equals("UMIIRUKA")) {
            effects.add(new IncreaseAtkDefOfTypeEffect(500, -400, MonsterType.Aqua));

        }

        return effects;
    }
}