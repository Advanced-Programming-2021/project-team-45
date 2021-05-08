package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;

public class PotOfGreed {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getHand().getCardsInHand().size() < 5){
            playerGameBoard.getDeckField().drawCard();
            playerGameBoard.getDeckField().drawCard();
        }
    }
}
