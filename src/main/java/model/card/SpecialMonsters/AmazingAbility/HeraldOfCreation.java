package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;

public class HeraldOfCreation {
    private static boolean canUse = true;
    public static void abilityOfHeraldOfCreation(Game game) {
        if(canUse) {
            // one input card from hand
            Card card;
            GameBoard playerGameBoard = game.getGameBoardOfPlayerOfThisTurn();
            playerGameBoard.getHand().deleteCard(card);
            // input one monster Card with minimum level of 7 from player graveyard
            MonsterCard monsterCard;
            playerGameBoard.getHand().addCard(monsterCard);
        }
    }
}
