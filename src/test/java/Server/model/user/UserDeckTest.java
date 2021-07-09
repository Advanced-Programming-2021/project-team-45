package Server.model.user;

import Server.model.card.Deck;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserDeckTest {

    @BeforeAll
    public static void set() {
        User user = new User("hajji", "hajji", "hajji");
        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().createDeck("deck2", user);
        user.getUserDeck().createDeck("deck3", user);
        user.getUserDeck().activateDeck("deck3");
    }

    @Test
    void getDeckByDeckName() {
        User user = User.getUserByUsername("hajji");
        Deck deck = user.getUserDeck().getDeckByName("deck1");
        assertEquals("deck1", deck.getName());
    }

    @Test
    void doesActiveDeckExist() {
        User user = User.getUserByUsername("hajji");
        assertTrue(user.getUserDeck().doesActiveDeckExist());
    }

    @Test
    void activateDeck() {
        User user = User.getUserByUsername("hajji");
        user.getUserDeck().deleteDeckFromUserDecks("deck3");
        assertNull(user.getUserDeck().getActiveDeck());
        user.getUserDeck().createDeck("deck3", user);
        user.getUserDeck().activateDeck("deck3");
        assertNotNull(user.getUserDeck().getActiveDeck());
    }

    @Test
    void getActiveDeckStr() {
        User user = User.getUserByUsername("hajji");
        String expected = "deck3: main deck 0, side deck 0, valid";
        assertEquals(expected, user.getUserDeck().getActiveDeckStr());
    }

    @Test
    void deleteDeckFromUserDecks() {
        User user = User.getUserByUsername("hajji");
        user.getUserDeck().deleteDeckFromUserDecks("deck3");
        assertFalse(user.getUserDeck().doesDeckExist("deck3"));
        user.getUserDeck().createDeck("deck3", user);
        user.getUserDeck().activateDeck("deck3");
    }

    @Test
    void createDeck() {
        User user = User.getUserByUsername("hajji");
        assertFalse(user.getUserDeck().doesDeckExist("deck4"));
        user.getUserDeck().createDeck("deck4", user);
        assertTrue(user.getUserDeck().doesDeckExist("deck4"));
    }

    @Test
    void isActiveDeckValid() {
        User user = User.getUserByUsername("hajji");
        assertFalse(user.getUserDeck().isActiveDeckValid());
    }

    @Test
    void getSortedOtherDeckStr() {
        User user = User.getUserByUsername("hajji");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("deck1: main deck 0, side deck 0, valid");
        expected.add("deck2: main deck 0, side deck 0, valid");
        expected.add("deck4: main deck 0, side deck 0, valid");

        assertEquals(expected, user.getUserDeck().getSortedOtherDeckStr());
    }

    @Test
    void getActiveDeck() {
        User user = User.getUserByUsername("hajji");
        user.getUserDeck().activateDeck("deck3");
        Deck deck = user.getUserDeck().getActiveDeck();
        assertSame(user.getUserDeck().getDeckByName("deck3"), deck);
    }


    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}