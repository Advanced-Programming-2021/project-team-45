package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.MonsterCard;

public class TrapHole {
    public static boolean recognize = true;
    public static void ability(Game game){
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        /*
        boolean recognize:
        get boolean to recognize that opponent summoned a monster
        normally or a special summon
         */
        if(recognize) {
            MonsterCard monsterCard = game.getSummonedMonster;
            if(monsterCard.getAttack() >= 1000) {
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
            }
        }
    }
}
