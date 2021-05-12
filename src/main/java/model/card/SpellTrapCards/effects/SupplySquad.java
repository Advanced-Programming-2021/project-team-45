package model.card.SpellTrapCards.effects;

import model.card.Card;
import model.card.SpellTrapCard;
import model.game.Game;
import model.game.GameBoard;
import model.game.fields.Graveyard;
import model.game.fields.SpellTrapField;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class SupplySquad extends Effect {
    private Graveyard graveyard;
    public static HashMap<SpellTrapCard, SupplySquad> supplySpells;

    @Override
    public void activate(Game game) {

    }

    SupplySquad(Graveyard graveyard){
        this.graveyard= clone(graveyard);
    }

    private Graveyard clone(Graveyard graveyard){
        Graveyard graveyard1=new Graveyard();
        for(Card card: graveyard.getGraveyardCards()){
            graveyard1.addCardToGraveyard(card);
        }
        return graveyard1;
    }

    public static SupplySquad isThereSupplySquadInSpellField(SpellTrapField field,Graveyard graveyard) {
        SpellTrapCard[] card = field.getSpellTrapCardsPositionsArray();
        for (int i = 0; i < card.length; i++) {
            if (card[i].getCardName().equals("Supply Squad")) {
                if (SupplySquad.supplySpells.get(card[i]) == null) {
                    SupplySquad.supplySpells.put(card[i], new SupplySquad(graveyard));
                    return SupplySquad.supplySpells.get(card[i]);

                } else {
                    return SupplySquad.supplySpells.get(card[i]);
                }
            }
        }
        return null;
    }

//    public static boolean isGraveyardSizeChanged(SupplySquad card,) {
//
//    }
}
