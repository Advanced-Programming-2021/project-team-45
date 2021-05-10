package model.game.fields;

import model.card.Card;
import model.card.MonsterCard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MonsterFieldTest {

    private MonsterField monsterField;
    private Graveyard graveyard;
    @BeforeAll
    public void set() {
        this.graveyard = new Graveyard();
        this.monsterField = new MonsterField(this.graveyard);
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Horn Imp"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Fireyarou"));
    }


    @Test
    void addMonsterToField() {
        assertFalse(monsterField.doesCardExist("Axe Raider"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Axe Raider"));
        assertTrue(monsterField.doesCardExist("Axe Raider"));
    }

    @Test
    void getMonstersOnField() {
        ArrayList<MonsterCard> expectedMonstersOnField = new ArrayList<>();
        expectedMonstersOnField.add((MonsterCard) Card.getCardByName("Horn Imp"));
        expectedMonstersOnField.add((MonsterCard) Card.getCardByName("Fireyarou"));
        expectedMonstersOnField.add((MonsterCard) Card.getCardByName("Axe Raider"));

        assertEquals(expectedMonstersOnField, monsterField.getMonstersOnField());
    }

    @Test
    void getMonsterPositionArray() {
        MonsterCard[] monsters = new MonsterCard[10];
        monsters[0] = (MonsterCard) Card.getCardByName("Horn Imp");
        monsters[1] = (MonsterCard) Card.getCardByName("Fireyarou");
        monsters[2] = (MonsterCard) Card.getCardByName("Axe Raider");

        assertArrayEquals(monsters, monsterField.getMonsterPositionsArray());
    }

    @Test
    void getMonsterCardFromPlayerMonsterField() {
        assertSame((MonsterCard) Card.getCardByName("Fireyarou"), monsterField.getMonsterCardFromPlayerMonsterField(2));
        assertNotSame((MonsterCard) Card.getCardByName("Horn Imp"), monsterField.getMonsterCardFromPlayerMonsterField(3));
        assertNull(monsterField.getMonsterCardFromPlayerMonsterField(8));
    }

    @Test
    void getMonsterCardOpponentFromMonsterField() {
        assertSame((MonsterCard) Card.getCardByName("Horn Imp"), monsterField.getMonsterCardOpponentFromMonsterField(1));
        assertSame((MonsterCard) Card.getCardByName("Fireyarou"), monsterField.getMonsterCardOpponentFromMonsterField(3));
        assertNull(monsterField.getMonsterCardOpponentFromMonsterField(6));
    }

    @Test
    void getMonster() {
        assertSame((MonsterCard) Card.getCardByName("Fireyarou"), monsterField.getMonster(2));
        assertNotSame((MonsterCard) Card.getCardByName("Horn Imp"), monsterField.getMonster(4));
    }

    @Test
    void deleteAttackedHistory() {

    }

    @Test
    void deleteAndDestroyMonsters() {
        assertTrue(monsterField.doesCardExist("Axe Raider"));
        monsterField.deleteAndDestroyMonster((MonsterCard) Card.getCardByName("Axe Raider"));
        assertFalse(monsterField.doesCardExist("Axe Raider"));
        assertTrue(graveyard.doesCardExist("Axe Raider"));
    }

    @Test
    void getNumberOfMonstersInField() {
        assertEquals(2, monsterField.getNumberOfMonstersInField());
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Battle OX"));
        assertEquals(3, monsterField.getNumberOfMonstersInField());
    }

    @Test
    void isThisCellOfPlayerMonsterFieldEmpty() {
        assertFalse(monsterField.isThisCellOfPlayerMonsterFieldEmpty(2));
        assertTrue(monsterField.isThisCellOfPlayerMonsterFieldEmpty(7));
    }

    @Test
    void isThisCellOfOpponentMonsterFieldEmpty() {
        assertFalse(monsterField.isThisCellOfOpponentMonsterFieldEmpty(2));
        assertTrue(monsterField.isThisCellOfOpponentMonsterFieldEmpty(6));
    }

    @Test
    void isFull() {
        assertFalse(monsterField.isFull());
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Wattkid"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Haniwa"));
        assertTrue(monsterField.isFull());
    }

    @Test
    void isFullPlace() {
        assertTrue(monsterField.isFull(4));
        assertFalse(monsterField.isFull(6));
    }

    @Test
    void deleteMonster() {
        assertTrue(monsterField.doesCardExist("Haniwa"));
        monsterField.deleteMonster((MonsterCard) Card.getCardByName("Haniwa"));
        assertFalse(monsterField.doesCardExist("Haniwa"));
        assertTrue(graveyard.doesCardExist("Haniwa"));
    }

    @Test
    void deleteAndDestroyAllMonsters() {
        assertEquals(4, monsterField.getNumberOfMonstersInField());
        monsterField.deleteAndDestroyAllMonsters();
        assertEquals(0, monsterField.getNumberOfMonstersInField());
    }

    @AfterAll
    public void setAfterTest() {
        graveyard = null;
        monsterField = null;
    }
}