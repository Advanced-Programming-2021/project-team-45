package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.user.User;

public class SupplySquad {
    public boolean canUseSupply = true;

    public static void ability(Game game){
        // problem
        game.getGameBoardOfPlayerOfThisTurn().getDeckField().drawCard();
    }
}
