package model.game.fields;

import controller.GameController;
import model.Shop;
import model.card.Card;
import model.card.MonsterCard;
import model.game.Game;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MonsterFieldTest {

    private static MonsterField monsterField;
    private static Shop shop1;
    private static Shop shop2;
    private static Graveyard graveyard;
    private static GameController gameController;
    private static Game game;

    @BeforeAll
    public static void set() {
        User user = new User("hajji", "hajji", "hajji");
        User user1 = new User("hossein", "hossein", "hossein");

        shop1 = new Shop("hajji");
        shop2 = new Shop("hossein");

        shop1.buy("Battle OX");
        shop1.buy("Axe Raider");
        shop1.buy("Yomi Ship");
        shop1.buy("Horn Imp");
        shop1.buy("Silver Fang");
        shop1.buy("Suijin");
        shop1.buy("Fireyarou");

        shop2.buy("Battle OX");
        shop2.buy("Axe Raider");
        shop2.buy("Yomi Ship");
        shop2.buy("Horn Imp");
        shop2.buy("Silver Fang");
        shop2.buy("Suijin");
        shop2.buy("Fireyarou");

        user.getUserDeck().createDeck("deck1", user);
        user1.getUserDeck().createDeck("deck1", user1);

        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Axe Raider", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Suijin", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Fireyarou", false, user);

        user1.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Axe Raider", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Suijin", false, user);
        user1.getUserDeck().getDeckByName("deck1").addCard("Fireyarou", false, user);

        user.getUserDeck().activateDeck("deck1");
        user1.getUserDeck().activateDeck("deck1");

        gameController = new GameController("hajji", "hossein", 3);
        game = new Game(User.getUserByUsername("hajji"), User.getUserByUsername("hossein"), 3, gameController);
        graveyard = new Graveyard(game);
        monsterField = new MonsterField(graveyard);
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Horn Imp"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Fireyarou"));
    }

    @Test
    void getMonster() {
        assertEquals("Fireyarou", monsterField.getMonster(2).getCardName());
        assertEquals("Horn Imp", monsterField.getMonster(1).getCardName());
    }

    @Test
    void getMonsterCardFromPlayerMonsterField() {
        assertEquals("Fireyarou", monsterField.getMonsterCardFromPlayerMonsterField(2).getCardName());
        assertNull(monsterField.getMonsterCardFromPlayerMonsterField(3));
    }


    @Test
    void getMonstersOnField() {
        assertEquals("Horn Imp", monsterField.getMonstersOnField().get(0).getCardName());
        assertEquals("Fireyarou", monsterField.getMonstersOnField().get(1).getCardName());
    }

    @Test
    void isFull() {
        assertFalse(monsterField.isFull());
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Axe Raider"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Wattkid"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Haniwa"));
        assertTrue(monsterField.isFull());
    }

    @Test
    void getMonsterPositionArray() {

        assertEquals("Horn Imp", monsterField.getMonsterPositionsArray()[0].getCardName());
        assertEquals("Fireyarou", monsterField.getMonsterPositionsArray()[1].getCardName());
        assertEquals("Axe Raider", monsterField.getMonsterPositionsArray()[2].getCardName());
        assertEquals("Wattkid", monsterField.getMonsterPositionsArray()[3].getCardName());
        assertEquals("Haniwa", monsterField.getMonsterPositionsArray()[4].getCardName());
    }


    @Test
    void isFullPlace() {
        assertTrue(monsterField.isFull(4));
    }



    @Test
    void addMonsterToField() {
        monsterField.deleteMonster(monsterField.getMonster(5));
        assertFalse(monsterField.doesCardExist("Haniwa"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Bitron"));
        assertTrue(monsterField.doesCardExist("Bitron"));
    }

    @Test
    void getNumberOfMonstersInField() {
        assertEquals(5, monsterField.getNumberOfMonstersInField());
    }

    @Test
    void isThisCellOfOpponentMonsterFieldEmpty() {
        assertFalse(monsterField.isFieldEmpty(2));
    }

    @Test
    void deleteAndDestroyMonsters() {
        assertTrue(monsterField.doesCardExist("Wattkid"));
        monsterField.deleteAndDestroyMonster(monsterField.getMonster(4));
        assertFalse(monsterField.doesCardExist("Wattkid"));
    }

    @Test
    void isThisCellOfPlayerMonsterFieldEmpty() {
        assertTrue(monsterField.isFieldEmpty(4));
    }

    @Test
    void deleteAndDestroyAllMonsters() {
        assertEquals(4, monsterField.getNumberOfMonstersInField());
        monsterField.deleteAndDestroyAllMonsters();
        assertEquals(0, monsterField.getNumberOfMonstersInField());
    }

    @Test
    void getMonsterCardOpponentFromMonsterField() {
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Silver Fang"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Yomi Ship"));
        monsterField.addMonsterToField((MonsterCard) Card.getCardByName("Horn Imp"));

        assertEquals("Silver Fang", monsterField.getMonsterCardOpponentFromMonsterField(1).getCardName());
        assertEquals("Yomi Ship", monsterField.getMonsterCardOpponentFromMonsterField(3).getCardName());
    }

    @Test
    void deleteMonster() {
        assertTrue(monsterField.doesCardExist("Horn Imp"));
        monsterField.deleteMonster(monsterField.getMonster(3));
        assertFalse(monsterField.doesCardExist("Horn Imp"));
        assertTrue(graveyard.doesCardExist("Horn Imp"));
    }

    @AfterAll
    public static void setAfterTest() {
        game = null;
        gameController = null;
        User.deleteUserByUsername("hajji");
        User.deleteUserByUsername("hoseein");
        graveyard = null;
        monsterField = null;
    }
}