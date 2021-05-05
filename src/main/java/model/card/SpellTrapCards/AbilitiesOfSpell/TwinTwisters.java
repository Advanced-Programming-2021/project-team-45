package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.SpellTrapCard;

import java.util.ArrayList;

public class TwinTwisters {
    public static void ability(Game game){
        GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
        // input one card from player hand
        Card card;
        if(playerGameBoard.getHand().doesCardExistInHand(card)) {
            playerGameBoard.getHand().deleteCard(card);
        }
        // input: an ArrayList of maximum size 2 from spellTrap cards in field
        ArrayList<SpellTrapCard> targetSpellTrapCards;
        for(SpellTrapCard spellTrapCard : targetSpellTrapCards) {
            if(spellTrapCard.getOwner().equals(game.getPlayerOfThisTurn())) {
                if(playerGameBoard.getSpellTrapField().doesSpellTrapCardExistInField(spellTrapCard))
                    playerGameBoard.getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
            } else {
                GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
                if(opponentGameBoard.getSpellTrapField().doesSpellTrapCardExistInField(spellTrapCard))
                    opponentGameBoard.getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
            }
        }
    }
}
