package model.card.SpecialMonsters.AmazingAbility;

import model.card.Card;
import model.card.DefensePosition;
import model.card.MonsterCard;
import model.game.Game;
import model.game.fields.CardField;
import java.util.ArrayList;


public class Terratiger {
    public static MonsterCard getInput(Game game) {
        CardField[] cardFields = {game.getGameBoardOfPlayerOfThisTurn().getHand()};
        ArrayList<Card> cards = game.getGameController().getCardFromPlayer(1, cardFields);
        if (cards.get(0)!=null) {
            if (cards.get(0) instanceof MonsterCard) {
                if(((MonsterCard) cards.get(0)).getLevel()<=4){
                    return (MonsterCard) cards.get(0);
                }else{
                    game.getGameController().showOutput("this monster level more than");
                    return null;
                }
            } else {
                game.getGameController().showOutput("this card is not a Monster");
                return null;
            }
        }else{
            return null;
        }
    }

    public static void ability(Game game) {
        MonsterCard input = getInput(game);
        if(input!=null) {
            if (!game.getGameBoardOfPlayerOfThisTurn().getMonsterField().isFull()) {
                game.getGameBoardOfPlayerOfThisTurn().getMonsterField().addMonsterToField(input);
                input.setDefenceMode(DefensePosition.DH);
            } else {
                game.getGameController().showOutput("your field is full");
            }
        }
    }

}
