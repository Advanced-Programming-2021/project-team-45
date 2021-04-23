package model.Game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.user.User;


import java.util.ArrayList;

public class SpellTrapField {
    private User Owner;
    private SpellTrapCard[] spellTrapCardsOnField = new SpellTrapCard[5];

    public SpellTrapCard[] getSpellTrapCardsOnField() {
        return spellTrapCardsOnField;
    }

    public void addSpellTrapCard(SpellTrapCard spellTrapCard){
        int index = 0;
        while(index < 5){
            if(this.spellTrapCardsOnField[index] == null){
                this.spellTrapCardsOnField[index] = spellTrapCard;
                break;
            }
            index++;
        }
    }

    public boolean isFull(){
        int fullPlace = 0;
        for(int i = 0; i < 5; i++){
            if(this.spellTrapCardsOnField[i] != null)
                fullPlace++;
        }
        return fullPlace == 5;
    }
    public SpellTrapCard getSpellTrap(int index){
        return this.spellTrapCardsOnField[index - 1];
    }
    // nemiddonam ina chian -haji
    public String toStringForOpponent(){

    }

    public String toString(){

    }
}
