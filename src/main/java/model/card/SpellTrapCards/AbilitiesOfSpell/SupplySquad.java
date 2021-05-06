package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.user.User;

public class SupplySquad {
    public static boolean canUseSupply = true;

    public static void ability(Game game){
        // problem
        if(canUseSupply) {
            game.getGameBoardOfPlayerOfThisTurn().getDeckField().drawCard();
            canUseSupply = false;
        }
    }
}
