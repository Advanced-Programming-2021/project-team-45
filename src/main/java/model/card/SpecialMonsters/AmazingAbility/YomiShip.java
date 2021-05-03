package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.card.Card;
import model.card.DOorDH;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class YomiShip {
    public static int abilityOfYomiShip(Card selectedOrTargetCard, Game game) {
        int result = 0;
        MonsterCard invaderCard = (MonsterCard) game.getSelectedCard();
        MonsterCard yomiShip = (MonsterCard) selectedOrTargetCard;
        invaderCard.attackMonster(yomiShip);
        if(yomiShip.getPosition() == PositionMonsters.ATTACK){
            result = abilityOfYomiShipInAttackPosition(invaderCard, yomiShip, game);
        } else {
            result = abilityOfYomiShipInDefensePosition(invaderCard, yomiShip, game);
        }
        return result;
    }

    private static int abilityOfYomiShipInAttackPosition(MonsterCard invaderCard, MonsterCard yomiShip, Game game){
        int result = 0;
        if(invaderCard.getAttack() > yomiShip.getAttack()){
            result = 20;
            game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(yomiShip);
            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(invaderCard);
        } else if(yomiShip.getAttack() == invaderCard.getAttack()){
            result = 21;
            game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(yomiShip);
            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(invaderCard);
        } else {
            result = 22;
            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(invaderCard);
        }
        return result;
    }

    private static int abilityOfYomiShipInDefensePosition(MonsterCard invaderCard, MonsterCard yomiShip, Game game){
        int result = 0;
        if(yomiShip.getDefenceMode() == DOorDH.DO){
            if(yomiShip.getDefense() < invaderCard.getAttack()){
                result = 23;
                game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(yomiShip);
                game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(invaderCard);
            } else if(yomiShip.getDefense() == invaderCard.getAttack()) result = 10;
            else result = 24;
        } else {
            game.setLastOpponentMonsterCard(yomiShip);
            if(yomiShip.getDefense() < invaderCard.getAttack()){
                result = 12;
                game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(yomiShip);
                game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteADestroyedMonster(invaderCard);
            } else if(yomiShip.getDefense() == invaderCard.getAttack()) result = 13;
            else result = 14;
        }
        return result;
    }
}
