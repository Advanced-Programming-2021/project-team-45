package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.card.Card;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class YomiShip {
    public static void abilityOfYomiShip(Card selectedOrTargetCard, Game game) {
        //int result = 0;
        MonsterCard invaderCard = (MonsterCard) game.getSelectedCard();
        MonsterCard yomiShip = (MonsterCard) selectedOrTargetCard;
        invaderCard.attackMonster(yomiShip);
        if(yomiShip.getPosition() == PositionMonsters.ATTACK){
            abilityOfYomiShipInAttackPosition(invaderCard, yomiShip, game);
        } else {
            abilityOfYomiShipInDefensePosition(invaderCard, yomiShip, game);
        }

    }

    private static void abilityOfYomiShipInAttackPosition(MonsterCard invaderCard, MonsterCard yomiShip, Game game){

        if(invaderCard.getAttack() > yomiShip.getAttack()){

            game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(yomiShip);
            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(invaderCard);
        } else if(yomiShip.getAttack() == invaderCard.getAttack()){

            game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(yomiShip);
            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(invaderCard);
        } else {

            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(invaderCard);
        }

    }

    private static void abilityOfYomiShipInDefensePosition(MonsterCard invaderCard, MonsterCard yomiShip, Game game){

        if(yomiShip.getDefenceMode() == DefensePosition.DO){
            if(yomiShip.getDefense() < invaderCard.getAttack()){

                game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(yomiShip);
                game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(invaderCard);
            }

        } else {
            game.setLastOpponentMonsterCard(yomiShip);
            if(yomiShip.getDefense() < invaderCard.getAttack()){
                game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(yomiShip);
                game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster(invaderCard);
            }
//            else if(yomiShip.getDefense() == invaderCard.getAttack()) result = 13;
//            else result = 14;
        }

    }
}
