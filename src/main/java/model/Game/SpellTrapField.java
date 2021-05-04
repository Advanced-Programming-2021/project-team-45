package model.Game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.user.User;


import java.util.ArrayList;

public class SpellTrapField {
    private User owner;
    private Graveyard graveyard;
    public SpellTrapField(User owner, Graveyard graveyard){
        this.owner = owner;
        this.graveyard = graveyard;
    }

    private SpellTrapCard[] spellTrapCardsOnField = new SpellTrapCard[5];

    public SpellTrapCard[] getSpellTrapCardsOnField() {
        return spellTrapCardsOnField;
    }

    public void addSpellTrapCard(SpellTrapCard spellTrapCard) {
        int index = 0;
        while (index < 5) {
            if (this.spellTrapCardsOnField[index] == null) {
                this.spellTrapCardsOnField[index] = spellTrapCard;
                break;
            }
            index++;
        }
    }

    public void deleteAndDestroyAllSpellTrapCards(){
        for(int i = 0; i < 5; i++){
            if(this.spellTrapCardsOnField[i] != null){
                this.graveyard.addCardToGraveyard(this.spellTrapCardsOnField[i]);
                this.spellTrapCardsOnField[i] = null;
            }
        }
    }

    public boolean isFull() {
        int fullPlace = 0;
        for (int i = 0; i < 5; i++) {
            if (this.spellTrapCardsOnField[i] != null)
                fullPlace++;
        }
        return fullPlace == 5;
    }

    public boolean isThisCellOfSpellTrapFieldEmptyInPlayerMode(int cardPosition) {
        return this.spellTrapCardsOnField[cardPosition - 1] == null;
    }

    public boolean isThisCellOfSpellTrapFieldEmptyInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.spellTrapCardsOnField[newPosition - 1] == null;
    }

    public boolean isItFull(int index) {

        return this.spellTrapCardsOnField[index] != null;
    }

    public SpellTrapCard getSpellTrapCardInPlayerMode(int cardPosition) {
        return this.spellTrapCardsOnField[cardPosition - 1];
    }

    public SpellTrapCard getSpellTrapCardInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.spellTrapCardsOnField[newPosition - 1];
    }

}
