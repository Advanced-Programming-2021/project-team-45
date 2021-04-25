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

    public MonsterCard getMonsterCardFromMonsterField(int cardPosition) {
        return this.monstersOnField[cardPosition - 1];
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

    public void deleteAnDestroyedMonster(MonsterCard monsterCard) {
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

    public boolean isThisCellOfMonsterFieldEmpty(int cardPosition) {
        if (this.monstersOnField[cardPosition] == null)
            return true;
        else return false;
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
