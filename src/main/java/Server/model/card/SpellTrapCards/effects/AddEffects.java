package Server.model.card.SpellTrapCards.effects;

import Server.model.card.MonsterType;
import Server.model.card.SpellTrapCard;
import Server.model.card.SpellTrapCards.effects.Continiuous.SpellAbsorption;
import Server.model.card.SpellTrapCards.effects.Continiuous.SupplySquad;
import Server.model.game.fields.CardFieldType;

import java.util.ArrayList;

public class AddEffects {
    public static ArrayList<Effect> getCardEffects(String cardName, SpellTrapCard card) {
        ArrayList<Effect> effects = new ArrayList<>();

        if (cardName.equals("Monster Reborn")) {
            effects.add(new SummonFromGraveyardEffect(CardFieldType.PLAYER_GRAVEYARD, CardFieldType.OPPONENT_GRAVEYARD));

        } else if (cardName.equals("Terraforming")) {
            effects.add(new TerraformingEffect());

        } else if (cardName.equals("Pot of Greed")) {
            effects.add(new DrawCardEffect(2));

        } else if (cardName.equals("Raigeki")) {
            effects.add(new DestroyAllCardsOfFieldEffect(CardFieldType.OPPONENT_MONSTER));

        } else if (cardName.equals("Harpie's Feather Duster")) {
            effects.add(new DestroyAllCardsOfFieldEffect(CardFieldType.OPPONENT_SPELL_TRAP));

        } else if (cardName.equals("Dark Hole")) {
            effects.add(new DestroyAllCardsOfFieldEffect(CardFieldType.PLAYER_MONSTER, CardFieldType.OPPONENT_MONSTER));

        } else if (cardName.equals("Supply Squad")) {
            effects.add(new SupplySquad(card));

        } else if (cardName.equals("Spell Absorption")) {
            effects.add(new SpellAbsorption(card));

        } else if (cardName.equals("Twin Twisters")) {
            effects.add(new TwinTwistersEffect());

        } else if (cardName.equals("Mystical space typhoon")) {
            effects.add(new MysticalSpaceTyphoonEffect());

        } else if (cardName.equals("Yami")) {
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

        } else if (cardName.equals("Advanced Ritual Art")) {
            effects.add(new AdvancedRitualArtEffect());

        } else if (cardName.equals("Magic Cylinder")) {
            effects.add(new MagicCylinderEffect());

        } else if (cardName.equals("Mirror Force")) {
            effects.add(new MirrorForceEffect());

        } else if (cardName.equals("Trap Hole")) {
            effects.add(new TrapHoleEffect());

        } else if (cardName.equals("Torrential Tribute")) {
            effects.add(new TorrentialTributeEffect());

        } else if (cardName.equals("Negate Attack")) {
            effects.add(new NegateAttackEffect());

        } else if (cardName.equals("Solemn Warning")) {
            effects.add(new SolemnWarningEffect());

        } else if (cardName.equals("Call of the Haunted")) {
            effects.add(new SummonFromGraveyardEffect(CardFieldType.PLAYER_GRAVEYARD));

        }

        return effects;
    }
}