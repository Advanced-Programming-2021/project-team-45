package model.card.SpecialMonsters.AmazingAbility;

import model.card.DefensePosition;
import model.card.MonsterCard;
import model.game.Game;

public class Terratiger {
    public static MonsterCard getInput(){
        return null;
    }

    public static void ability(Game game){
        MonsterCard input=getInput();
        if(!game.getGameBoardOfPlayerOfThisTurn().getMonsterField().isFull()){
            game.getGameBoardOfPlayerOfThisTurn().getMonsterField().addMonsterToField(input);
            input.setDefenceMode(DefensePosition.DH);
        }
        else{
            game.getGameController().specialMonsterErrorHandler("your field is full");
        }
    }

}
