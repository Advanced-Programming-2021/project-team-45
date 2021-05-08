package model.card;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterCardTest {

    @BeforeAll
    public void setBeforeTest() {

    }

    @Test
    void increaseAttack() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Battle OX");
        monsterCard.increaseAttack(200);
        assertEquals(1900, monsterCard.getAttack());
        monsterCard.decreaseAttack(200);
    }

    @Test
    void decreaseAttack() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Horn Imp");
        monsterCard.decreaseAttack(200);
        assertEquals(1100, monsterCard.getAttack());
        monsterCard.increaseAttack(200);
    }

    @Test
    void increaseDefense() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Fireyarou");
        monsterCard.increaseDefense(200);
        assertEquals(1200, monsterCard.getDefense());
        monsterCard.decreaseAttack(200);
    }

    @Test
    void decreaseDefense() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Suijin");
        monsterCard.decreaseDefense(200);
        assertEquals(2200, monsterCard.getDefense());
        monsterCard.increaseDefense(200);
    }

    @Test
    void setAttack() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Haniwa");
        monsterCard.setAttack(600);
        assertEquals(600, monsterCard.getAttack());
        monsterCard.setAttack(500);
    }

    @Test
    void setDefense() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Axe Raider");
        monsterCard.setDefense(1000);
        assertEquals(1000, monsterCard.getDefense());
        monsterCard.setDefense(1150);
    }

    @Test
    void changePosition() {

    }

    @Test
    void summon() {

    }

    @Test
    void set() {

    }

    @Test
    void attackMonster() {

    }

    @Test
    void attackOpponent() {

    }

    @Test
    void getAttack() {

    }

    @Test
    void getDefense() {

    }

    @Test
    void getSpecial() {

    }

    @Test
    void getLevel() {

    }

    @Test
    void getPosition() {

    }

    @Test
    void getDefenceMode() {

    }

    @Test
    void setDefenceMode() {

    }

    @Test
    void getAttribute() {

    }

    @Test
    void getType() {

    }

    void getSpecialMonsterEnum() {

    }

    @AfterAll
    public void setAfterTest() {

    }
}