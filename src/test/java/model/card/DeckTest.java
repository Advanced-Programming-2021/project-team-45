package model.card;

import model.Shop;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @BeforeAll
    public static void set() {
        User user = new User("hajji", "hajji", "hajji");
        Shop shop = new Shop("hajji");
        shop.buy("Battle OX");
        shop.buy("Battle OX");
        shop.buy("Battle OX");
        shop.buy("Yomi Ship");
        shop.buy("Axe Raider");
        shop.buy("Magic Cylinder");
        shop.buy("Trap Hole");
        shop.buy("Silver Fang");
        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        // MonsterCards
        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Axe Raider", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", true, user);
        //spellTrapCards
        user.getUserDeck().getDeckByName("deck1").addCard("Magic Cylinder", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Trap Hole", true, user);
    }

    @Test
    void addCard() {
        User user = User.getUserByUsername("hajji");
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist("Silver Fang", false));
        assertTrue(user.getUserDeck().getDeckByName("deck1").doesCardExist("Silver Fang", true));
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", false, user);
        assertTrue(user.getUserDeck().getDeckByName("deck1").doesCardExist("Silver Fang", false));
    }

    @Test
    void deleteCard() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().getDeckByName("deck1").doesCardExist("Silver Fang", true));
        user.getUserDeck().getDeckByName("deck1").deleteCard("Silver Fang", false);
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist("Silver Fang", false));
    }

    @Test
    void doesCardExist() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().getDeckByName("deck1").doesCardExist("Axe Raider", true));
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist("Scanner", true));
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist("Axe Raider", false));
    }

    @Test
    void getCardCountInDeck() {
        User user = User.getUserByUsername("hajji");
        assertEquals(0, user.getUserDeck().getDeckByName("deck1").getCardCountInDeck("Horn Imp"));
        assertNotEquals(2, user.getUserDeck().getDeckByName("deck1").getCardCountInDeck("Yomi Ship"));
    }

    @Test
    void isFullFromCard() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().getDeckByName("deck1").isFullFromCard("Battle OX"));
        assertFalse(user.getUserDeck().getDeckByName("deck1").isFullFromCard("Fireyarou"));
    }

    @Test
    void isFull() {
        User user = User.getUserByUsername("hajji");
        assertFalse(user.getUserDeck().getDeckByName("deck1").isDeckFull(false));
    }

    @Test
    void isDeckValid() {
        User user = User.getUserByUsername("hajji");
        assertFalse(user.getUserDeck().getDeckByName("deck1").isDeckValid());
    }

    @Test
    void getName() {
        User user = User.getUserByUsername("hajji");
        assertEquals("deck1", user.getUserDeck().getDeckByName("deck1").getName());
    }

    @Test
    void getMainDeck() {

    }

    @Test
    void getSideDeck() {

    }

    @Test
    void getMonsterStr() {

    }

    @Test
    void getSpellsAndTrapStr() {

    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}