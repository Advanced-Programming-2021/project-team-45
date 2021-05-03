package model.card.SpellCards;

import model.Game.Game;
import model.card.Card;
import model.card.SpellCards.AbilitiesOfSpell.*;

public class SpellCardController {
    public void spellCardController(Card selectedCard, Game game){
        if(selectedCard.getCardName().equals("Monster Reborn")){
            MonsterReborn.ability();
        }else if(selectedCard.getCardName().equals("Terraforming")){
            Terraforming.ability();
        }else if(selectedCard.getCardName().equals("Pot of Greed")){
            PotOfGreed.ability();
        }else if(selectedCard.getCardName().equals("Raigeki")){

        }else if(selectedCard.getCardName().equals("Change of Heart")){

        }else if(selectedCard.getCardName().equals("Harpie’s Feather Duster")){

        }else if(selectedCard.getCardName().equals("Swords of Revealing Light")){

        }else if(selectedCard.getCardName().equals("Dark Hole")){

        }else if(selectedCard.getCardName().equals("Supply Squad")){

        }else if(selectedCard.getCardName().equals("Spell Absorption")){

        }else if(selectedCard.getCardName().equals("Messenger of peace")){

        }else if(selectedCard.getCardName().equals("Twin Twisters")){

        }else if(selectedCard.getCardName().equals("Mystical space typhoon")){

        }else if(selectedCard.getCardName().equals("Ring of Defense")){

        }else if(selectedCard.getCardName().equals("Yami")){

        }else if(selectedCard.getCardName().equals("Forest")){

        }else if(selectedCard.getCardName().equals("Closed Forest")){

        }else if(selectedCard.getCardName().equals("UMIIRUKA")){

        }else if(selectedCard.getCardName().equals("Sword of Dark Destruction")){

        }else if(selectedCard.getCardName().equals("Black Pendant")){

        }else if(selectedCard.getCardName().equals("United We Stand")){

        }else if(selectedCard.getCardName().equals("Magnum Shield")){

        }else if(selectedCard.getCardName().equals("Advanced Ritual Art")){

        }
    }
}
