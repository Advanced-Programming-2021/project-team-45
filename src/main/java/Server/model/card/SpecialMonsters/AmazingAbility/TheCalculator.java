package Server.model.card.SpecialMonsters.AmazingAbility;

import Server.model.game.Game;
import Server.model.game.GameBoard;
import Server.model.card.Card;
import Server.model.card.MonsterCard;
import Server.model.card.PositionMonsters;

import java.util.ArrayList;

public class TheCalculator {

    public static void ability(Card selectedOrTargetCard, Game game){
        GameBoard gameBoard = game.getPlayerGameBoard();
        ArrayList<MonsterCard> monsterCards = gameBoard.getMonsterField().getMonstersOnField();
        int sumLevels = 0;
        for(int i = 0; i < monsterCards.size(); i++){
            if(monsterCards.get(i) != null && monsterCards.get(i).getPosition() == PositionMonsters.ATTACK){
                sumLevels += monsterCards.get(i).getLevel();
            }
        }
        ((MonsterCard) selectedOrTargetCard).setAttack(sumLevels * 300);
    }

}
