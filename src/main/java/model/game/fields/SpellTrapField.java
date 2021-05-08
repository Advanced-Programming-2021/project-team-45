package model.game.fields;

import model.card.Card;
import model.card.SpellTrapCard;
import model.user.User;

import java.util.ArrayList;

public class SpellTrapField extends CardField {

    private final User owner;
    private final Graveyard graveyard;
    private final ArrayList<SpellTrapCard> spellTraps;

    public SpellTrapField(User owner, Graveyard graveyard) {
        this.owner = owner;
        this.graveyard = graveyard;
        spellTraps = new ArrayList<>();
    }

    public SpellTrapCard[] getSpellTrapCardsOnField() {
        return spellTraps;
    }

    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : spellTraps) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public void addSpellTrapCard(SpellTrapCard spellTrapCard) {
        int index = 0;
        while (index < 5) {
            if (spellTraps.get(index) == null) {
                spellTraps.get(index) = spellTrapCard;
                break;
            }
            index++;
        }
    }

    public void deleteAndDestroySpellTrap(SpellTrapCard spellTrapCard) {
        for (int i = 0; i < 5; i++) {
            if (this.spellTraps.get(i).equals(spellTrapCard)) {
                this.graveyard.addCardToGraveyard(spellTrapCard);
                this.spellTraps.get(i) = null;
            }
        }
    }

    public void deleteAndDestroyAllSpellTrapCards() {
        for (int i = 0; i < 5; i++) {
            if (this.spellTraps.get(i) != null) {
                this.graveyard.addCardToGraveyard(this.spellTraps[i]);
                this.spellTraps.get(i) = null;
            }
        }
    }

    public boolean isFull() {
        int fullPlace = 0;
        for (int i = 0; i < 5; i++) {
            if (this.spellTraps.get(i) != null)
                fullPlace++;
        }
        return fullPlace == 5;
    }

    public boolean isThisCellOfSpellTrapFieldEmptyInPlayerMode(int cardPosition) {
        return this.spellTraps[cardPosition - 1] == null;
    }

    public boolean isThisCellOfSpellTrapFieldEmptyInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.spellTraps[newPosition - 1] == null;
    }

    public boolean isItFull(int index) {

        return this.spellTraps[index] != null;
    }

    public boolean doesSpellTrapCardExistInField(SpellTrapCard spellTrapCard) {
        boolean result = false;
        for (int i = 0; i < 5; i++) {
            if (this.spellTraps[i].equals(spellTrapCard)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public SpellTrapCard getSpellTrapCardInPlayerMode(int cardPosition) {
        return this.spellTraps[cardPosition - 1];
    }

    public SpellTrapCard getSpellTrapCardInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.spellTraps[newPosition - 1];
    }

}
