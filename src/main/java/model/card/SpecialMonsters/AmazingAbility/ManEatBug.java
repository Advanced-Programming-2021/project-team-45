package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;

public class ManEatBug {
    public static void ability(Game game) {
        int targetMonsterFieldPosition = game.getGameController().getNumberFromPlayer(
                "Man-Eater Bug special effect activated! " +
                        "Please enter the position of opponent's monster card that you want to destroy.");
        GameBoard opponentGameBoard = game.getOpponentGameBoard();
        if (!opponentGameBoard.getMonsterField().
                isFieldEmpty(targetMonsterFieldPosition)) {
            MonsterCard monsterCard = opponentGameBoard.
                    getMonsterField().getMonsterCardOpponentFromMonsterField(targetMonsterFieldPosition);
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
        }
    }
}
