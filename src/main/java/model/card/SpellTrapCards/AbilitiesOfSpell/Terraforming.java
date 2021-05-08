package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;
import model.card.Card;

public class Terraforming {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        if(playerGameBoard.getHand().getCardsInHand().size() < 6){
            Card card = playerGameBoard.getDeckField().getFieldCard();
            playerGameBoard.getHand().addCard(card);
        }
    }
}
