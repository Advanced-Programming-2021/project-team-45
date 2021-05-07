package model.user;

import model.card.Deck;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDeckTest {

    @BeforeAll
    public void set() {
        User user = new User("hajji", "hajji", "hajji");
        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().createDeck("deck2", user);
        user.getUserDeck().createDeck("deck3", user);
    }

    @Test
    void createDeck() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().doesDeckExist("deck3"));
        assertFalse(user.getUserDeck().doesDeckExist("deck4"));
    }

    @Test
    void deleteDeckFromUserDecks() {
        User user = User.getUserByUsername("hajji");
        user.getUserDeck().deleteDeckFromUserDecks("deck3");
        assertFalse(user.getUserDeck().doesDeckExist("deck3"));
        user.getUserDeck().createDeck("deck3", user);
    }

    @Test
    void activateDeck() {
        User user = User.getUserByUsername("hajji");
        assertNull(user.getUserDeck().getActiveDeck());
        user.getUserDeck().activateDeck("deck3");
        assertNotNull(user.getUserDeck().getActiveDeck());
    }

    @Test
    void doesActiveDeckExist() {
        User user = User.getUserByUsername("hajji");
        user.getUserDeck().activateDeck("deck3");
        assertTrue(user.getUserDeck().doesActiveDeckExist());
    }

    @Test
    void isActiveDeckValid() {

    }

    @Test
    void getActiveDeck() {
        User user = User.getUserByUsername("hajji");
        user.getUserDeck().activateDeck("deck3");
        Deck deck = user.getUserDeck().getActiveDeck();
        assertSame(user.getUserDeck().getDeckByName("deck3"), deck);
    }

    @Test
    void getDeckByDeckname() {
        User user = User.getUserByUsername("hajji");
        Deck deck = user.getUserDeck().getDeckByName("deck1");
        assertEquals("deck1", deck.getName());
    }

    @Test
    void getActiveDeckStr() {

    }

    @Test
    void getSortedOtherDeckStr() {

    }

    @AfterAll
    public void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}