package model.card.SpellTrapCards.effects.Continiuous;

import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.card.SpellTrapCards.effects.Effect;
import model.game.Game;
import model.game.fields.SpellTrapField;

import java.util.ArrayList;
import java.util.HashMap;

public class MessengerOfPeace extends Effect {

    private boolean isActive = false;
    public static HashMap<SpellTrapCard, MessengerOfPeace> messengerOfPeaceHashMap = new HashMap<>();

    @Override
    public void activate(Game game) {
        isActive = true;
    }

    MessengerOfPeace(SpellTrapCard card) {
        messengerOfPeaceHashMap.putIfAbsent(card, this);
    }

    public static MessengerOfPeace isThereMessengerOfPeaceOnField(SpellTrapField field) {
        ArrayList<SpellTrapCard> cards = field.getSpellTrapsArrayList();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardName().equals("Messenger of peace")) {
                if(messengerOfPeaceHashMap.get(cards.get(i))==null){
                    new MessengerOfPeace(cards.get(i));
                    return messengerOfPeaceHashMap.get(cards.get(i));
                }else {
                    return messengerOfPeaceHashMap.get(cards.get(i));
                }
            }
        }
        return null;
    }

    public int doActivityInAttack(Game game){
        if(game.getSelectedCard() instanceof MonsterCard){
            if(((MonsterCard) game.getSelectedCard()).getAttack()>=1500){
                return 1;
            }else {
                return -1;
            }
        }
        return -1;
    }


}
