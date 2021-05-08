package model.Game;

import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class MonsterField {
    private ArrayList<MonsterCard> monstersOnField = new ArrayList<>();
    private Graveyard graveyard;

    public MonsterField(Graveyard graveyard) {
        this.graveyard = graveyard;
    }


    public ArrayList<MonsterCard> getMonstersOnField() {
        return monstersOnField;
    }

    public void deleteAttackedHistory() {
        ArrayList<MonsterCard> cards = getMonstersOnField();
        for (int i = 0; i < 5; i++) {
            cards[i].setWasAttackedInThisTurn(false);
        }
    }

    public MonsterCard getMonsterCardFromMonsterFieldInPlayerMode(int cardPosition) {
        return this.monstersOnField.get(cardPosition - 1);
    }

    public MonsterCard getMonsterCardFromMonsterFieldInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.monstersOnField.get(newPosition - 1);
    }

    public void addMonsterToField(MonsterCard monster) {
        int index = 0;
        while (index < 5) {
            if (this.monstersOnField.get(index) == null) {
                this.monstersOnField.set(index, monster);
                break;
            }
            index++;
        }
    }

    public void deleteAndDestroyMonster(MonsterCard monsterCard) {
        for (int i = 0; i < 5; i++) {
            if (this.monstersOnField.get(i).equals(monsterCard)) {
                this.graveyard.addCardToGraveyard(this.monstersOnField.get(i));
                this.monstersOnField.set(i, null);
            }
        }
    }

    public void deleteAndDestroyAllMonsters() {
        for(int i = 0; i < 5; i++) {
            if(this.monstersOnField.get(i) != null) {
                this.graveyard.addCardToGraveyard(this.monstersOnField.get(i));
                this.monstersOnField.set(i, null);
            }
        }
    }

    public void deleteMonster(MonsterCard monsterCard) {
        for(int i = 0; i < 5; i++) {
            if(this.monstersOnField.get(i).equals(monsterCard)) {
                this.monstersOnField.set(i, null);
                break;
            }
        }
    }
    public int getNumberOfMonstersInField() {
        int numberOfMonsters = 0;
        for (int i = 0; i < 5; i++) {
            if (this.monstersOnField.get(i) != null)
                numberOfMonsters++;
        }
        return numberOfMonsters;
    }

    public boolean isThisCellOfMonsterFieldEmptyInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.monstersOnField.get(newPosition - 1) == null;
    }

    public boolean isThisCellOfMonsterFieldEmptyInPlayerMode(int cardPosition) {
        return this.monstersOnField.get(cardPosition - 1) == null;
    }

    public boolean isFull() {
        int fullPlace = 0;
        for (int i = 0; i < 5; i++) {
            if (this.monstersOnField != null)
                fullPlace++;
        }
        return fullPlace == 5;
    }

    public MonsterCard getMonster(int index) {
        return this.monstersOnField.get(index - 1);
    }

    public boolean doesExistCardInMonsterField(Card card) {
        if (!(card instanceof MonsterCard))
            return false;
        else {
            MonsterCard targetCard = (MonsterCard) card;
            int existence = 0;
            for (int i = 0; i < 5; i++) {
                if (this.monstersOnField.get(i) != null) {
                    if (this.monstersOnField.get(i).getCardName().equals(targetCard.getCardName()))
                        existence++;
                }
            }
            return existence != 0;
        }
    }

    public boolean isItFull(int index) {
        return monstersOnField.get(index) != null;
    }

}
