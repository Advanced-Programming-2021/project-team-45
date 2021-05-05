package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;

public class PotOfGreed {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getHand().getCardsInHand().size() < 5){
            playerGameBoard.getDeckField().getCard();
            playerGameBoard.getDeckField().getCard();
        }
    }
}
