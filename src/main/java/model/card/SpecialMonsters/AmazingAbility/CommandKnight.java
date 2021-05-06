package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.card.MonsterCard;
import model.card.PositionMonsters;

import java.util.ArrayList;

public class CommandKnight {
    private static ArrayList<MonsterCard> allCardsThatIncreasedAttacks=new ArrayList<>();
    public static void abilityOfCommandKnightAtSummon(Game game) {
        MonsterCard[] monsterCards=game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonstersOnField();
        for(int i=0;i<5;i++){
            if(!monsterCards[i].getCardName().equals("Command Knight")){
                monsterCards[i].setAttack(monsterCards[i].getAttack()+400);
                allCardsThatIncreasedAttacks.add(monsterCards[i]);
            }
        }
    }

    public static void abilityOfCommandKnightAtDefense(Game game,MonsterCard card) {
        if(isCommandKnightAlone(game.getGameBoardOfOpponentPlayerOfThisTurn())){
            if(card.getPosition().equals(PositionMonsters.ATTACK)){
                game.attackToOpponentCardInAttackPosition((MonsterCard) game.getSelectedCard(),card,
                        game.getGameBoardOfPlayerOfThisTurn(),game.getGameBoardOfOpponentPlayerOfThisTurn());
            }else{
                game.attackToOpponentCardInDefensePosition((MonsterCard) game.getSelectedCard(),card,
                        game.getGameBoardOfPlayerOfThisTurn(),game.getGameBoardOfOpponentPlayerOfThisTurn());
            }
        }
    }

    private static boolean isCommandKnightAlone(GameBoard gameBoard){
        MonsterCard[] monsterCards=gameBoard.getMonsterField().getMonstersOnField();
        for(int i=0;i<5;i++){
            if(!monsterCards[i].getCardName().equals("Command Knight")){
                return false;
            }
        }
        return true;
    }

    public static void CommandKnightOnFieldWithSummonMode(MonsterCard selectedCardToSummonOrSet, MonsterField monsterField) {
        MonsterCard[] monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(monsterCards[i].getCardName().equals("Command Knight")){
                selectedCardToSummonOrSet.setAttack(selectedCardToSummonOrSet.getAttack()+400);
            }
        }
    }
}
