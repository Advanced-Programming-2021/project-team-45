package model.card.SpellTrapCards.effects;

import model.card.MonsterType;

import java.util.ArrayList;

public class AddEffects {
    public static ArrayList<Effect> getCardEffects(String cardName) {
        ArrayList<Effect> effects = new ArrayList<>();
        
        if (cardName.equals("Yami")) {
            effects.add(new IncreaseAttackEffect(200, MonsterType.Fiend));
            effects.add(new IncreaseDefenceEffect(200, MonsterType.Fiend));
            effects.add(new IncreaseAttackEffect(200, MonsterType.SpellCaster));
            effects.add(new IncreaseDefenceEffect(200, MonsterType.SpellCaster));
            effects.add(new IncreaseAttackEffect(-200, MonsterType.Fairy));
            effects.add(new IncreaseDefenceEffect(-200, MonsterType.Fairy));

        } else if (cardName.equals("Forest")) {
            effects.add(new IncreaseAttackEffect(200, MonsterType.Insect));
            effects.add(new IncreaseDefenceEffect(200, MonsterType.Insect));
            effects.add(new IncreaseAttackEffect(200, MonsterType.Beast));
            effects.add(new IncreaseDefenceEffect(200, MonsterType.Beast));
            effects.add(new IncreaseAttackEffect(200, MonsterType.Beast_Warrior));
            effects.add(new IncreaseDefenceEffect(200, MonsterType.Beast_Warrior));

        } else if (cardName.equals("UMIIRUKA")) {
            effects.add(new IncreaseAttackEffect(500, MonsterType.Aqua));
            effects.add(new IncreaseDefenceEffect(-400, MonsterType.Aqua));

        }

        return effects;
    }
}