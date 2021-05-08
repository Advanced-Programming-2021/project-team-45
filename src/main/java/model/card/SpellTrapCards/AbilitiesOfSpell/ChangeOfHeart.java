package model.card.SpellTrapCards.AbilitiesOfSpell;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;

public class ChangeOfHeart {

    private static int cardPosition = 0;
    private static MonsterCard targetMonsterCard;

    public static void ability(Game game){
        // input one card from opponent  monster field
        GameBoard playerGameBoard  = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        MonsterCard monsterCard;
        targetMonsterCard = monsterCard;
        for(int i = 0; i < 5; i++) {
            if(opponentGameBoard.getMonsterField().getMonstersOnField().get(i).equals(monsterCard)) {
                cardPosition = i;
                break;
            }
        }
        if(!playerGameBoard.getMonsterField().isFull()) {
            playerGameBoard.getMonsterField().addMonsterToField(monsterCard);
            opponentGameBoard.getMonsterField().deleteMonster(monsterCard);
        }
    }

    public static void deActiveAbility(Game game) {
        GameBoard playerGameBoard  = game.getGameBoardOfPlayerOfThisTurn();
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();

        opponentGameBoard.getMonsterField().addMonsterToField(targetMonsterCard);
        playerGameBoard.getMonsterField().deleteMonster(targetMonsterCard);
    }
}
