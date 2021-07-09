package Server.model.card.SpellTrapCards.effects.Continiuous;

import Server.model.card.SpellTrapCard;
import Server.model.card.SpellTrapCards.effects.Effect;
import Server.model.game.Chain;
import Server.model.game.Game;
import Server.model.game.fields.SpellTrapField;

import java.util.HashMap;

public class SupplySquad extends Effect {

    private boolean isActive=false;
    public static HashMap<SpellTrapCard, SupplySquad> supplySpells=new HashMap<>();

    public SupplySquad(SpellTrapCard card){
        if(supplySpells.get(card)==null){
            supplySpells.put(card,this);
        }
    }

    @Override
    public void activate(Chain chain) {
        isActive=true;
    }

    public static SupplySquad isThereSupplySquadInSpellField(SpellTrapField field) {
        SpellTrapCard[] card = field.getSpellTrapCardsPositionsArray();
        for (int i = 0; i < card.length; i++) {
            if(card[i]!=null) {
                if (card[i].getCardName().equals("Supply Squad")) {
                    new SupplySquad(card[i]);
                    return supplySpells.get(card[i]);
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
            game.getOpponentGameBoard().getHand().
                    addCard(game.getPlayerGameBoard().getDeckField().drawCard());

        }
    }
}
