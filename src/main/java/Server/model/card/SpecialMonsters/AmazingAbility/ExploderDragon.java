package Server.model.card.SpecialMonsters.AmazingAbility;

import Server.model.game.Game;
import Server.model.game.GameBoard;
import Server.model.card.Card;
import Server.model.card.DefensePosition;
import Server.model.card.MonsterCard;
import Server.model.card.PositionMonsters;

public class ExploderDragon {

    public static int ability(Card selectedOrTargetCard, Game game) {
        int result = 0;
        MonsterCard invaderCard = (MonsterCard) game.getSelectedCard();
        MonsterCard exploderDragon = (MonsterCard) selectedOrTargetCard;
        GameBoard playerGameBoard = game.getPlayerGameBoard();
        GameBoard opponentGameBoard = game.getOpponentGameBoard();
        if(invaderCard.getPosition() == PositionMonsters.ATTACK)
            result = abilityInAttackPosition(invaderCard, exploderDragon, playerGameBoard, opponentGameBoard);
        else result = abilityInDefensePosition(invaderCard, exploderDragon, playerGameBoard, opponentGameBoard, game);
        return result;
    }

    private static int abilityInAttackPosition(MonsterCard invaderCard,MonsterCard exploderDragon,GameBoard playerGameBoard, GameBoard opponentGameBoard) {
        int result = 0;
        if(invaderCard.getAttack() >= exploderDragon.getAttack()) {
            playerGameBoard.getMonsterField().deleteAndDestroyMonster(invaderCard);
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(exploderDragon);
            result = 21;

        } else if(invaderCard.getAttack() == exploderDragon.getAttack()) {
            playerGameBoard.getMonsterField().deleteAndDestroyMonster(invaderCard);
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(exploderDragon);
            result = 22;

        } else {
            playerGameBoard.getMonsterField().deleteAndDestroyMonster(invaderCard);
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(exploderDragon);
            result = 23;
        }
        return result;
    }

    public static int abilityInDefensePosition(MonsterCard invaderCard, MonsterCard exploderDragon,GameBoard playerGameBoard, GameBoard opponentGameBoard, Game game) {
        int result = 0;
        if(exploderDragon.getDefenceMode() == DefensePosition.DO) {
            if(exploderDragon.getDefense() < invaderCard.getAttack()) {
                playerGameBoard.getMonsterField().deleteAndDestroyMonster(invaderCard);
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(exploderDragon);
                result = 24;
            } else if(exploderDragon.getDefense() == invaderCard.getAttack()) result = 25;
            else {
                invaderCard.attackMonster(exploderDragon);
                result = 25;
            }

        } else {
            game.setLastOpponentMonsterCard(exploderDragon);
            if(exploderDragon.getDefense() < invaderCard.getAttack()) {
                playerGameBoard.getMonsterField().deleteAndDestroyMonster(invaderCard);
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(exploderDragon);
                result = 26;
            } else if(exploderDragon.getDefense() == invaderCard.getAttack()) result = 27;
            else {
                invaderCard.attackMonster(exploderDragon);
                result = 28;
            }
        }
        return result;
    }
}
