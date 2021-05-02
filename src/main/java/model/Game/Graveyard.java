package model.Game;

import model.card.Card;

import java.util.ArrayList;

public class Graveyard {
    private ArrayList<Card> graveyardCards=new ArrayList<>();

    public void addCardToGraveyard(Card card){
        graveyardCards.add(card);
    }

    public void deleteCardFromGraveyard(Card card){
        (this.graveyardCards).remove(card);
    }

    public boolean doesCardExistInGraveyard(Card card){
        return (this.graveyardCards).contains(card);
    }

    public Card getCardFromGraveyard(String cardName){
        ArrayList<Card> targetCard = new ArrayList<>();
        for(Card card : this.graveyardCards){
            if((card.getCardName()).equals(cardName))
                targetCard.add(card);
        }
        if(targetCard.size() == 0) return null; else return targetCard.get(0);
    }

    public ArrayList<String> getGraveyardStr(){
        ArrayList<String> graveyardStr = new ArrayList<>();
        if(this.graveyardCards.size() == 0)
            graveyardStr.add("graveyard empty");
        else{
            for(int i = 0; i < (this.graveyardCards).size(); i++){
                Card card = (this.graveyardCards).get(i);
                graveyardStr.add((i + 1) + ". " + card.getCardName() + ":" + card.getCardDescription());
            }
        }
         return graveyardStr;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < this.graveyardCards.size(); i++){
            Card card = this.graveyardCards.get(i);
            stringBuilder.append(i + 1 + ". " + card.getCardName() +":" + card.getCardDescription() + "\n");
        }
        return stringBuilder.toString();
    }
}
