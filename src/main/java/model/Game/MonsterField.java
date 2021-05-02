package model.Game;

import model.card.Card;
import model.card.MonsterCard;

public class MonsterField {
    private MonsterCard[] monstersOnField = new MonsterCard[5];
    private Graveyard graveyard;

    public MonsterField(Graveyard graveyard) {
        this.graveyard = graveyard;
    }


    public MonsterCard[] getMonstersOnField() {
        return monstersOnField;
    }

    public void deleteAttackedHistory() {
        MonsterCard[] cards = getMonstersOnField();
        for (int i = 0; i < 5; i++) {
            cards[i].setWasAttackedInThisTurn(false);
        }
    }

    public MonsterCard getMonsterCardFromMonsterFieldInPlayerMode(int cardPosition) {
        return this.monstersOnField[cardPosition - 1];
    }

    public MonsterCard getMonsterCardFromMonsterFieldInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1)
            newPosition = cardPosition;
        else if (cardPosition % 2 == 0)
            newPosition = cardPosition + 1;
        else newPosition = cardPosition - 1;
        return this.monstersOnField[newPosition - 1];
    }

    public void addMonsterToField(MonsterCard monster) {
        int index = 0;
        while (index < 5) {
            if (this.monstersOnField[index] == null) {
                this.monstersOnField[index] = monster;
                break;
            }
            index++;
        }
    }

    public void deleteADestroyedMonster(MonsterCard monsterCard) {
        //bug
        for (int i = 0; i < 5; i++) {
            if (this.monstersOnField[i].getCardName().equals(monsterCard.getCardName())) {
                this.graveyard.addCardToGraveyard(this.monstersOnField[i]);
                this.monstersOnField[i] = null;
            }
        }
    }

    public int getNumberOfMonstersInField() {
        int numberOfMonsters = 0;
        for (int i = 0; i < 5; i++) {
            if (this.monstersOnField[i] != null)
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
        return this.monstersOnField[newPosition - 1] == null;
    }

    public boolean isThisCellOfMonsterFieldEmptyInPlayerMode(int cardPosition) {
        return this.monstersOnField[cardPosition - 1] == null;
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
        return this.monstersOnField[index - 1];
    }

    public boolean doesExistCardInMonsterField(Card card) {
        if (!(card instanceof MonsterCard))
            return false;
        else {
            MonsterCard targetCard = (MonsterCard) card;
            int existence = 0;
            for (int i = 0; i < 5; i++) {
                if (this.monstersOnField[i] != null) {
                    if (this.monstersOnField[i].getCardName().equals(targetCard.getCardName()))
                        existence++;
                }
            }
            return existence != 0;
        }
    }

    public boolean isItFull(int index) {
        index = (index + 1) / 2 - 1;
        return monstersOnField[index] != null;
    }

}
