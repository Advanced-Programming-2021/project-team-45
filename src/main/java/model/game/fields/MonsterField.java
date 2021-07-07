package model.game.fields;

import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class MonsterField extends CardField {
    private MonsterCard[] monsters;

    private final Graveyard graveyard;

    public MonsterField(Graveyard graveyard) {
        super("Monster Field");
        this.graveyard = graveyard;
        monsters = new MonsterCard[5];
        for (int i = 0; i < 5; i++) {
            monsters[i] = null;
        }
    }


    public ArrayList<MonsterCard> getMonstersOnField() {
        ArrayList<MonsterCard> monstersOnField = new ArrayList<>();
        for (MonsterCard monster : monsters) {
            if (monster != null) monstersOnField.add(monster);
        }
        return monstersOnField;
    }

    public MonsterCard[] getMonsterPositionsArray() {
        return monsters;
    }

    public MonsterCard getMonsterCardFromPlayerMonsterField(int cardPosition) {
        return monsters[cardPosition];
    }

    public MonsterCard getMonsterCardOpponentFromMonsterField(int cardPosition) {
        int position = cardPosition;
//        if (cardPosition == 1) {
//            position = cardPosition;
//        } else if (cardPosition % 2 == 0) {
//            position = cardPosition + 1;
//        } else {
//            position = cardPosition - 1;
//        }
        return monsters[position];
    }

    public MonsterCard getMonster(int index) {
        return monsters[index];
    }

    @Override
    public boolean doesCardExist(String cardName) {
        return getCardByName(cardName) != null;
    }

    @Override
    public Card getCardByName(String cardName) {
        for (Card card : monsters) {
            if (card != null) {
                if (card.getCardName().equals(cardName)) {
                    return card;
                }
            }
        }
        return null;
    }

    public void deleteAttackedHistory() {
        for (MonsterCard monster : monsters) {
            if (monster != null) {
                monster.setWasAttackedInThisTurn(false);
            }
        }
    }

    public void addMonsterToField(MonsterCard monster) {
        int index = 0;
        while (index < 5) {
            if (monsters[index] == null) {
                monsters[index] = monster;
                break;
            }
            index++;
        }
    }

    public void deleteAndDestroyMonster(MonsterCard monsterCard) {
        for (int i = 0; i < 5; i++) {
            if (monsters[i] != null) {
                if (monsters[i].equals(monsterCard)) {
                    graveyard.addCardToGraveyard(monsters[i]);
                    monsters[i] = null;
                }
            }
        }
    }

    public void deleteAndDestroyAllMonsters() {
        for (int i = 0; i < 5; i++) {
            if (monsters[i] != null) {
                graveyard.addCardToGraveyard(monsters[i]);
                monsters[i] = null;
            }
        }
    }

    public void deleteMonster(MonsterCard monsterCard) {
        for (int i = 0; i < 5; i++) {
            if (monsters[i].equals(monsterCard)) {
                monsters[i] = null;
                break;
            }
        }
    }

    public int getNumberOfMonstersInField() {
        int numberOfMonsters = 0;
        for (int i = 0; i < 5; i++) {
            if (monsters[i] != null) {
                numberOfMonsters++;
            }
        }
        return numberOfMonsters;
    }

    public boolean isFieldEmpty(int position) {
        return monsters[position] == null;
    }

    public boolean isFull() {
        int monsterCount = getNumberOfMonstersInField();
        return monsterCount >= 5;
    }

    public boolean isFull(int index) {
        return monsters[index] != null;
    }
}