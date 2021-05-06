package model.card.SpellTrapCards;

import model.Game.Game;
import model.card.Card;
import model.card.SpellTrapCard;
import model.card.SpellTrapCards.AbilitiesOfSpell.*;
import model.card.SpellTrapCards.AbilitiesOfTraps.CallOfTheHaunted;
import model.card.SpellTrapCards.AbilitiesOfTraps.MindCrush;
import model.card.SpellTrapCards.AbilitiesOfTraps.TimeSeal;

public class SpellTrapCardController {
    public void spellCardController(Card selectedCard, Game game){
        if(selectedCard.getCardName().equals("Monster Reborn")){
            MonsterReborn.ability();
        }else if(selectedCard.getCardName().equals("Terraforming")){
            Terraforming.ability(game);
        }else if(selectedCard.getCardName().equals("Pot of Greed")){
            PotOfGreed.ability(game);
        }else if(selectedCard.getCardName().equals("Raigeki")){
            Raigeki.ability(game);
        }else if(selectedCard.getCardName().equals("Change of Heart")){
            ChangeOfHeart.ability(game);
        }else if(selectedCard.getCardName().equals("Harpie’s Feather Duster")){
            HarpieFeatherDuster.ability(game);
        }else if(selectedCard.getCardName().equals("Swords of Revealing Light")){
            SwordOfRevealingLight.ability();
        }else if(selectedCard.getCardName().equals("Dark Hole")){
            DarkHole.ability(game);
        }else if(selectedCard.getCardName().equals("Supply Squad")){
            SupplySquad.ability(game);
        }else if(selectedCard.getCardName().equals("Spell Absorption")){
            SpellAbsorption.ability();
        }else if(selectedCard.getCardName().equals("Messenger of peace")){
            MessengerOfPeace.ability();
        }else if(selectedCard.getCardName().equals("Twin Twisters")){
            TwinTwisters.ability(game);
        }else if(selectedCard.getCardName().equals("Mystical space typhoon")){
            MysticalSpaceTyphon.ability(game);
        }else if(selectedCard.getCardName().equals("Ring of Defense")){
            RingOfDefense.ability();
        }else if(selectedCard.getCardName().equals("Yami")){
            Yami.ability(game);
        }else if(selectedCard.getCardName().equals("Forest")){
            Forest.ability(game);
        }else if(selectedCard.getCardName().equals("Closed Forest")){
            ClosedForest.ability(game);
        }else if(selectedCard.getCardName().equals("UMIIRUKA")){
            Umiiruka.ability(game);
        }else if(selectedCard.getCardName().equals("Sword of Dark Destruction")){
            SwordOfDarkDestruction.ability(game);
        }else if(selectedCard.getCardName().equals("Black Pendant")){
            BlackPendant.ability(game);
        }else if(selectedCard.getCardName().equals("United We Stand")){
            UnitedWeStand.ability(game);
        }else if(selectedCard.getCardName().equals("Magnum Shield")){
            MagnumShield.ability(game);
        }else if(selectedCard.getCardName().equals("Advanced Ritual Art")){
            AdvancedRitual.ability();
        }else if(selectedCard.getCardName().equals("Mind Crush")){
            MindCrush.ability(game);
        }else if(selectedCard.getCardName().equals("Time Seal")){
            TimeSeal.ability();
        }else if(selectedCard.getCardName().equals("Call of the Haunted")){
            CallOfTheHaunted.ability(game);
        }
    }

    public void deleteSpellTrapCard(Card selectedCard, Game game) {
        SpellTrapCard spellTrapCard = (SpellTrapCard) selectedCard;
        game.getGameBoardOfPlayerOfThisTurn().getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
    }
}
