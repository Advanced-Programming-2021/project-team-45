package model.card.SpellTrapCards.effects.Continiuous;

import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.card.SpellTrapCards.effects.Effect;
import model.game.Chain;
import model.game.Game;
import model.game.fields.SpellTrapField;

import java.util.ArrayList;
import java.util.HashMap;

public class MessengerOfPeace extends Effect {

    private boolean isActive = false;
    public static HashMap<SpellTrapCard, MessengerOfPeace> messengerOfPeaceHashMap = new HashMap<>();

    @Override
    public void activate(Chain chain) {
        isActive = true;
    }

    MessengerOfPeace(SpellTrapCard card) {
        if(messengerOfPeaceHashMap.get(card)==null){
            messengerOfPeaceHashMap.put(card,this);
        }
    }

    public static MessengerOfPeace isThereMessengerOfPeaceOnField(SpellTrapField field) {
        ArrayList<SpellTrapCard> cards = field.getSpellTrapsArrayList();
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i)!=null) {
                if (cards.get(i).getCardName().equals("Messenger of peace")) {
                    new MessengerOfPeace(cards.get(i));
                    return messengerOfPeaceHashMap.get(cards.get(i));
                }
            }
        }
        return null;
    }

    public int doActivityInAttack(Game game) {
        if (game.getSelectedCard() instanceof MonsterCard) {
            if (((MonsterCard) game.getSelectedCard()).getAttack() >= 1500) {
                return 1;
            } else {
                return -1;
            }
        }
        return -1;
    }

    public int doActivityInStandByPhase(Game game) {
        boolean answer = game.getGameController().getYesNoAnswer("if you want to destroy " +
                "your messenger of peace and save 100 point of" +
                "your life point please enter 'yes' without quote and if you don't want enter no :");
        if (answer) {
            ArrayList<SpellTrapCard> spellTrapCards = game.getPlayerGameBoard().getSpellTrapField()
                    .getSpellTrapsArrayList();
            for (int i = 0; i < spellTrapCards.size(); i++) {
                if (messengerOfPeaceHashMap.get(spellTrapCards.get(i)) != null) {
                    if (messengerOfPeaceHashMap.get(spellTrapCards.get(i)).equals(this)) {
                        game.getPlayerGameBoard().getSpellTrapField().
                                deleteAndDestroySpellTrap(spellTrapCards.get(i));
                    }
                }
            }

        } else {
            game.getPlayerOfThisTurn().getLifepoint().decreaseLifepoint(100);
        }
        return 0;
    }


}
