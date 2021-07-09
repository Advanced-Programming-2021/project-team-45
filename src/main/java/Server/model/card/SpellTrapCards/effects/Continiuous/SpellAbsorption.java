package Server.model.card.SpellTrapCards.effects.Continiuous;

import Server.model.card.Card;
import Server.model.card.SpellTrapCard;
import Server.model.card.SpellTrapCards.effects.Effect;
import Server.model.game.Chain;
import Server.model.game.Game;
import Server.model.game.fields.SpellTrapField;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellAbsorption extends Effect {

    private static HashMap<Card,SpellAbsorption> absorptionHashMap=new HashMap<>();
    private boolean isActive = false;

    @Override
    public void activate(Chain chain) {
        isActive = true;
    }

    public SpellAbsorption(SpellTrapCard card){
        if(absorptionHashMap.get(card)==null){
            absorptionHashMap.put(card,this);
        }
    }

    public void doActivity(Game game) {
        if (isActive) {
            game.getOpponentOfThisTurn().getLifepoint().increaseLifepoint(500);
        }
    }

    public static SpellAbsorption isThereSpellAbsorptionInField(SpellTrapField field) {
        ArrayList<SpellTrapCard> cards = field.getSpellTrapsArrayList();
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i)!=null) {
                if (cards.get(i).getCardName().equals("Spell Absorption")) {
                    new SpellAbsorption(cards.get(i));
                    return absorptionHashMap.get(cards.get(i));
                }
            }
        }
        return null;
    }


}
