package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.card.Card;

public class SpellAbsorption {
    public static boolean isASpellActivated = true;
    public static void ability(Card selectedOrTargetCard, Game game){
        if(isASpellActivated) {
            selectedOrTargetCard.getOwner().getLifepoint().increaseLifepoint(500);
            isASpellActivated = false;
        }
    }
}
