package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.card.Card;
import model.card.MonsterCard;
import model.card.PositionMonsters;

public class TheCalculator {

    public static void abilityOfTheCalculator(Card selectedOrTargetCard, Game game){
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        MonsterCard[] monsterCards = gameBoard.getMonsterField().getMonstersOnField();
        int sumLevels = 0;
        for(int i = 0; i < 4; i++){
            if(monsterCards[i] != null && monsterCards[i].getPosition() == PositionMonsters.ATTACK){
                sumLevels += monsterCards[i].getLevel();
            }
        }
        ((MonsterCard) selectedOrTargetCard).setAttack(sumLevels * 300);
    }

}
