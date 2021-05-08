package model.game.fields;

import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class MonsterField extends CardField {
    private final ArrayList<MonsterCard> monsters;

    private final Graveyard graveyard;

    public MonsterField(Graveyard graveyard) {
        this.graveyard = graveyard;
        monsters = new ArrayList<>();
    }


    public ArrayList<MonsterCard> getMonstersOnField() {
        return monsters;
    }

    public MonsterCard getMonsterCardFromMonsterFieldInPlayerMode(int cardPosition) {
        return monsters.get(cardPosition - 1);
    }

    public MonsterCard getMonsterCardFromMonsterFieldInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1) {
            newPosition = cardPosition;
        } else if (cardPosition % 2 == 0) {
            newPosition = cardPosition + 1;
        } else {
            newPosition = cardPosition - 1;
        }
        return monsters.get(newPosition - 1);
    }

    public MonsterCard getMonster(int index) {
        return monsters.get(index - 1);
    }

    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : monsters) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public void deleteAttackedHistory() {
        for (MonsterCard monster : monsters) {
            monster.setWasAttackedInThisTurn(false);
        }
    }

    public void addMonsterToField(MonsterCard monster) {
        int index = 0;
        while (index < 5) {
            if (monsters.get(index) == null) {
                monsters.set(index, monster);
                break;
            }
            index++;
        }
    }

    public void deleteAndDestroyMonster(MonsterCard monsterCard) {
        for (int i = 0; i < 5; i++) {
            if (monsters.get(i).equals(monsterCard)) {
                graveyard.addCardToGraveyard(this.monsters.get(i));
                monsters.set(i, null);
            }
        }
    }

    public void deleteAndDestroyAllMonsters() {
        for (int i = 0; i < 5; i++) {
            if (monsters.get(i) != null) {
                graveyard.addCardToGraveyard(this.monsters.get(i));
                monsters.set(i, null);
            }
        }
    }

    public void deleteMonster(MonsterCard monsterCard) {
        for (int i = 0; i < 5; i++) {
            if (monsters.get(i).equals(monsterCard)) {
                monsters.set(i, null);
                break;
            }
        }
    }

    public int getNumberOfMonstersInField() {
        int numberOfMonsters = 0;
        for (int i = 0; i < 5; i++) {
            if (monsters.get(i) != null) {
                numberOfMonsters++;
            }
        }
        return numberOfMonsters;
    }

    public boolean isThisCellOfMonsterFieldEmptyInOpponentMode(int cardPosition) {
        int newPosition = 0;
        if (cardPosition == 1) {
            newPosition = cardPosition;
        } else if (cardPosition % 2 == 0) {
            newPosition = cardPosition + 1;
        } else {
            newPosition = cardPosition - 1;
        }
        return this.monsters.get(newPosition - 1) == null;
    }

    public boolean isThisCellOfMonsterFieldEmptyInPlayerMode(int cardPosition) {
        return monsters.get(cardPosition - 1) == null;
    }

    public boolean isFull() {
        int fullPlace = 0;
        for (int i = 0; i < 5; i++) {
            if (monsters != null) {
                fullPlace++;
            }
        }
        return fullPlace == 5;
    }

    public boolean isItFull(int index) {
        return monsters.get(index) != null;
    }

}
