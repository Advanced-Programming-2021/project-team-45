package model.card;

import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @BeforeAll
    public void set() {
        User user = new User("hajji", "hajji", "hajji");
        user.getCardInventory().addCardToInventory(Card.getCardByName("Silver Fang"));
        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", false, user);
        // MonsterCards
        user.getUserDeck().getDeckByName("deck1").addCard("Battle OX", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Axe Raider", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Horn Imp", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Suijin", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Fireyarou", true, user);
        //spellTrapCards
        user.getUserDeck().getDeckByName("deck1").addCard("Magic Cylinder", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Trap Hole", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Negate Attack", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Mind Crush", true, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Mirror Force", true, user);
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
        assertTrue(user.getUserDeck().getDeckByName("deck1").doesCardExist("Suijin", true));
        user.getUserDeck().getDeckByName("deck1").deleteCard("Suijin", true);
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist("Suijin", true));
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
        assertEquals(2, user.getUserDeck().getDeckByName("deck1").getCardCountInDeck("Horn Imp"));
        assertNotEquals(2, user.getUserDeck().getDeckByName("deck1").getCardCountInDeck("Yomi Ship"));
    }

    @Test
    void isFullFromCard() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().getDeckByName("deck1").isFullFromCard("Yomi Ship"));
        assertFalse(user.getUserDeck().getDeckByName("deck1").isFullFromCard("Fireyarou"));
    }

    @Test
    void isFull() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().getDeckByName("deck1").isDeckFull(true));
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
    public void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}