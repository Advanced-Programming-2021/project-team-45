package Server.model.card.SpecialMonsters.AmazingAbility;

import Server.model.card.Card;
import Server.model.card.DefensePosition;
import Server.model.card.MonsterCard;
import Server.model.game.Game;
import Server.model.game.fields.CardField;
import java.util.ArrayList;


public class Terratiger {
    public static MonsterCard getInput(Game game) {
        CardField[] cardFields = {game.getPlayerGameBoard().getHand()};
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
            if (!game.getPlayerGameBoard().getMonsterField().isFull()) {
                game.getPlayerGameBoard().getMonsterField().addMonsterToField(input);
                input.setDefenceMode(DefensePosition.DH);
            } else {
                game.getGameController().showOutput("your field is full");
            }
        }
    }

}
