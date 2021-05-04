package model.card.SpellCards;

import model.Game.Game;
import model.card.Card;
import model.card.SpellCards.AbilitiesOfSpell.*;

public class SpellCardController {
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
            ChangeOfHeart.ability();
        }else if(selectedCard.getCardName().equals("Harpie’s Feather Duster")){
            HarpieFeatherDuster.ability(game);
        }else if(selectedCard.getCardName().equals("Swords of Revealing Light")){
            SwordOfRevealingLight.ability();
        }else if(selectedCard.getCardName().equals("Dark Hole")){
            DarkHole.ability(game);
        }else if(selectedCard.getCardName().equals("Supply Squad")){
            SupplySquad.ability();
        }else if(selectedCard.getCardName().equals("Spell Absorption")){
            SpellAbsorption.ability();
        }else if(selectedCard.getCardName().equals("Messenger of peace")){
            MessengerOfPeace.ability();
        }else if(selectedCard.getCardName().equals("Twin Twisters")){
            TwinTwisters.ability();
        }else if(selectedCard.getCardName().equals("Mystical space typhoon")){
            MysticalSpaceTyphon.ability(game);
        }else if(selectedCard.getCardName().equals("Ring of Defense")){
            RingOfDefense.ability();
        }else if(selectedCard.getCardName().equals("Yami")){
            Yami.ability();
        }else if(selectedCard.getCardName().equals("Forest")){
            Forest.ability();
        }else if(selectedCard.getCardName().equals("Closed Forest")){
            ClosedForest.ability();
        }else if(selectedCard.getCardName().equals("UMIIRUKA")){
            Umiiruka.ability();
        }else if(selectedCard.getCardName().equals("Sword of Dark Destruction")){
            SwordOfDarkDestruction.ability();
        }else if(selectedCard.getCardName().equals("Black Pendant")){
            BlackPendant.ability();
        }else if(selectedCard.getCardName().equals("United We Stand")){
            UnitedWeStand.ability();
        }else if(selectedCard.getCardName().equals("Magnum Shield")){
            MagnumShield.ability();
        }else if(selectedCard.getCardName().equals("Advanced Ritual Art")){
            AdvancedRitual.ability();
        }
    }
}
