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

        } else if (cardName.equals("Closed Forest")) {
            effects.add(new ClosedForestEffect());

        } else if (cardName.equals("UMIIRUKA")) {
            effects.add(new IncreaseAtkDefOfTypeEffect(500, -400, MonsterType.Aqua));

        } else if (cardName.equals("Sword of Dark Destruction")) {
            effects.add(new IncreaseAtkDefOfEquippedEffect(400, -200,
                    MonsterType.Fiend, MonsterType.SpellCaster));

        } else if (cardName.equals("Black Pendant")) {
            // all types
            effects.add(new IncreaseAtkDefOfEquippedEffect(500, 0, MonsterType.Beast_Warrior,
                    MonsterType.Warrior, MonsterType.Aqua, MonsterType.Fiend, MonsterType.Beast, MonsterType.Pyro,
                    MonsterType.SpellCaster, MonsterType.Thunder, MonsterType.Dragon, MonsterType.Machine,
                    MonsterType.Rock, MonsterType.Insect, MonsterType.Cyberse, MonsterType.Fairy, MonsterType.Sea_Serpent));

        } else if (cardName.equals("United We Stand")) {
            effects.add(new UnitedWeStandEffect());

        } else if (cardName.equals("Magnum Shield")) {
            effects.add(new MagnumShieldEffect());
            
        }

        return effects;
    }
}