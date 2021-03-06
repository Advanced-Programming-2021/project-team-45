package Server.model.card;

import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MonsterCardTest {

    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
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
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Battle OX");
        monsterCard.setPosition(PositionMonsters.ATTACK);
        monsterCard.changePosition();
        assertEquals(PositionMonsters.DEFENSE, monsterCard.getPosition());
        monsterCard.position = null;
    }

    @Test
    void summon() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Silver Fang");
        monsterCard.summon();
        assertEquals(PositionMonsters.ATTACK, monsterCard.position);
        monsterCard.position = null;
    }

    @Test
    void set() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Crab Turtle");
        monsterCard.setForFirstTime();
        assertEquals(PositionMonsters.DEFENSE, monsterCard.position);
        monsterCard.position = null;
    }

    /*@Test
    void attackMonster() {

    }

    @Test
    void attackOpponent() {

    }*/

    @Test
    void getAttack() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Silver Fang");
        assertEquals(1200, monsterCard.getAttack());
    }

    @Test
    void getDefense() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Crab Turtle");
        assertEquals(2500, monsterCard.getDefense());
    }

    @Test
    void getSpecial() {
        MonsterCard monsterCard1 = (MonsterCard) Card.getCardByName("Axe Raider");
        assertNull(monsterCard1.getSpecial());
    }

    @Test
    void getLevel() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Wattkid");
        assertEquals(3, monsterCard.getLevel());
    }

    @Test
    void getPosition() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Wattkid");
        monsterCard.setPosition(PositionMonsters.ATTACK);
        assertEquals(PositionMonsters.ATTACK, monsterCard.position);
        monsterCard.position = null;
    }

    @Test
    void getDefenceMode() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Wattkid");
        monsterCard.defenceMode = DefensePosition.DO;
        assertEquals(DefensePosition.DO, monsterCard.defenceMode);
        monsterCard.defenceMode = null;
    }

    @Test
    void setDefenceMode() {
        MonsterCard monsterCard = (MonsterCard) Card.getCardByName("Fireyarou");
        monsterCard.setDefenceMode(DefensePosition.DH);
        assertEquals(DefensePosition.DH, monsterCard.defenceMode);
        monsterCard.defenceMode = null;
    }

    @Test
    void getAttribute() {
        MonsterCard monsterCard1 = (MonsterCard) Card.getCardByName("Fireyarou");
        assertEquals(MonsterAttribute.FIRE, monsterCard1.getAttribute());
        MonsterCard monsterCard2 = (MonsterCard) Card.getCardByName("Baby Dragon");
        assertEquals(MonsterAttribute.WIND, monsterCard2.getAttribute());
    }

    @Test
    void getType() {
        MonsterCard monsterCard1 = (MonsterCard) Card.getCardByName("Battle OX");
        assertEquals(MonsterType.Beast_Warrior, monsterCard1.getType());
        MonsterCard monsterCard2 = (MonsterCard) Card.getCardByName("Horn Imp");
        assertEquals(MonsterType.Fiend, monsterCard2.getType());
    }

    void getSpecialMonsterEnum() {
        MonsterCard monsterCard1 = (MonsterCard) Card.getCardByName("Horn Imp");
        assertNull(monsterCard1.getSpecialMonsterEnum());
        MonsterCard monsterCard2 = (MonsterCard) Card.getCardByName("Suijin");
        assertEquals(SpecialMonsterEnum.SUIJIN, monsterCard2.getSpecialMonsterEnum());
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}