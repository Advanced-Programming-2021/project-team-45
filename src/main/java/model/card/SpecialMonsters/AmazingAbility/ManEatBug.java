package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.MonsterCard;

public class ManEatBug {

    public static void abilityOfmanEatBug(Game game) {
        // bugs -haji
        //int targetMonsterFieldPosition = Integer.parseInt(Menu.scanner.nextLine());

        GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        if(!opponentGameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(targetMonsterFieldPosition)){
            MonsterCard monsterCard = opponentGameBoard.getMonsterField().getMonsterCardFromMonsterFieldInOpponentMode(targetMonsterFieldPosition);
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
        }
    }
}
