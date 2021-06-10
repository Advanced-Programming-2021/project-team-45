package model.card.SpecialMonsters.AmazingAbility;

import model.card.Card;
import model.card.MonsterCard;
import model.game.Game;
import model.game.fields.CardField;
import model.game.fields.Hand;

import java.io.IOException;
import java.util.ArrayList;

public class Tricky {

    public static void ability(Game game,MonsterCard tricky){
        CardField[] cardFields={game.getPlayerGameBoard().getHand()};
        ArrayList<Card> deletedCard=game.getGameController().getCardFromPlayer(1,cardFields);
        if(deletedCard.get(0)!=null) {
            game.getPlayerGameBoard().getHand().deleteCard(deletedCard.get(0));
            game.getPlayerGameBoard().getMonsterField().addMonsterToField(tricky);
        }
    }

    public static Card isThereTricky(Hand hand){
        ArrayList<Card> cards=hand.getCardsInHand();
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getCardName().equals("The Tricky")){
                return cards.get(i);
            }
        }
        try {
            return new MonsterCard("-1");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
