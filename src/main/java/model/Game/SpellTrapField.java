package model.Game;

import model.card.SpellTrapCard;
import model.user.User;


import java.util.ArrayList;

public class SpellTrapField {
    private User Owner;
    private SpellTrapCard[] spellTrapCardsOnField=new SpellTrapCard[5];


    public SpellTrapCard[] getSpellTrapCardsOnField() {
        return spellTrapCardsOnField;
    }
}
