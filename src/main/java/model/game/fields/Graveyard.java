package model.game.fields;

import model.card.Card;
import model.card.SpellTrapCards.effects.Continiuous.ContiniouesSpellController;
import model.card.SpellTrapCards.effects.Continiuous.ContinouesSpellActivatePlace;
import model.game.Game;

import java.util.ArrayList;

public class Graveyard extends CardField {

    private ArrayList<Card> graveyard;
    private Game game;

    public Graveyard(Game game) {
        super("Graveyard");
        graveyard = new ArrayList<>();
        this.game=game;
    }



    public ArrayList<Card> getGraveyardCards() {
        return graveyard;
    }

    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : graveyard) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public void addCardToGraveyard(Card card){
        graveyard.add(card);
        ContiniouesSpellController.Controller("Supply Squad",game, ContinouesSpellActivatePlace.GraveYard);
    }

    public void deleteCardFromGraveyard(Card card){
        graveyard.remove(card);
    }

    public ArrayList<String> getGraveyardStr(){
        ArrayList<String> graveyardStr = new ArrayList<>();
        if(this.graveyard.size() == 0)
            graveyardStr.add("graveyard empty");
        else{
            for(int i = 0; i < (this.graveyard).size(); i++){
                Card card = (this.graveyard).get(i);
                graveyardStr.add((i + 1) + ". " + card.getCardName() + ":" + card.getCardDescription());
            }
        }
         return graveyardStr;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < this.graveyard.size(); i++){
            Card card = this.graveyard.get(i);
            stringBuilder.append(i + 1 + ". " + card.getCardName() +":" + card.getCardDescription() + "\n");
        }
        return stringBuilder.toString();
    }
}