package model.card.SpellTrapCards.AbilitiesOfTraps;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class MirrorForce {
    public static void ability(Game game){
        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        for(MonsterCard monsterCard : opponentGameBoard.getMonsterField().getMonstersOnField()) {
            if(monsterCard != null) {
                if(monsterCard.getPosition() == PositionMonsters.ATTACK)
                    opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
            }
        }
    }
}