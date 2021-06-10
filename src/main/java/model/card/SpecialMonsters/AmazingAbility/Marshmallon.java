package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.card.Card;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class Marshmallon {

    public static int ability(Card selectedOrTargetCard, Game game){
        // problem -haji
        int result = 0;
        MonsterCard invaderCard = (MonsterCard) game.getSelectedCard();
        MonsterCard marshmallon = (MonsterCard) selectedOrTargetCard;
        invaderCard.attackMonster(marshmallon);
        if(marshmallon.getPosition() == PositionMonsters.ATTACK){
            result = abilityOfmarshmallonInAttackPosition(invaderCard, marshmallon, game);
        } else {
           result = abilityOfMarshmallonInDefensePosition(invaderCard, marshmallon, game);
        }
        return result;
    }

    private static int abilityOfmarshmallonInAttackPosition(MonsterCard invaderCard, MonsterCard marshmallon, Game game){
        int result = 0;
        if(marshmallon.getAttack() < invaderCard.getAttack()){
            result = 6;
        }
        else if (marshmallon.getAttack() == invaderCard.getAttack()) {
            game.getPlayerGameBoard().getMonsterField().deleteAndDestroyMonster(invaderCard);
            result = 7;
        } else {
            game.getPlayerGameBoard().getMonsterField().deleteAndDestroyMonster(invaderCard);
            result = 8;
        }
        return result;
    }

    private static int abilityOfMarshmallonInDefensePosition(MonsterCard invaderCard, MonsterCard marshmallon, Game game){
        int result = 0;
        if(marshmallon.getDefenceMode() == DefensePosition.DO){
            if(marshmallon.getDefense() < invaderCard.getAttack()) result = 9;
            else if(marshmallon.getDefense() == invaderCard.getAttack()) result = 10;
            else result = 11;
        } else {
            game.setLastOpponentMonsterCard(marshmallon);
            game.getPlayerOfThisTurn().getLifepoint().decreaseLifepoint(1000);
            if(marshmallon.getDefense() < invaderCard.getAttack()) result = 12;
            else if(marshmallon.getDefense() == invaderCard.getAttack()) result = 13;
            else result = 14;
        }
        return result;
    }
}
