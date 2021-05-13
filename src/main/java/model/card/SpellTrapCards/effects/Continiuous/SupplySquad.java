package model.card.SpellTrapCards.effects.Continiuous;

import model.card.SpellTrapCard;
import model.card.SpellTrapCards.effects.Effect;
import model.game.Chain;
import model.game.Game;
import model.game.fields.SpellTrapField;

import java.util.HashMap;

public class SupplySquad extends Effect {

    private boolean isActive=false;
    public static HashMap<SpellTrapCard, SupplySquad> supplySpells;

    public SupplySquad(SpellTrapCard card){
        supplySpells.putIfAbsent(card, this);
    }

    @Override
    public void activate(Chain chain) {
        isActive=true;
    }

    public static SupplySquad isThereSupplySquadInSpellField(SpellTrapField field) {
        SpellTrapCard[] card = field.getSpellTrapCardsPositionsArray();
        for (int i = 0; i < card.length; i++) {
            if (card[i].getCardName().equals("Supply Squad")) {
                if (SupplySquad.supplySpells.get(card[i]) == null) {
                    new SupplySquad(card[i]);
                    return SupplySquad.supplySpells.get(card[i]);

                } else {
                    return SupplySquad.supplySpells.get(card[i]);
                }
            }
        }
        return null;
    }

    public boolean isActive() {
        return isActive;
    }
    
    public void doActivity(Game game){
        if(isActive){
            game.getGameBoardOfOpponentPlayerOfThisTurn().getHand().
                    addCard(game.getGameBoardOfPlayerOfThisTurn().getDeckField().drawCard());

        }
    }
}
