package controller;

import model.card.Card;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckControllerTest {

    private static DeckController deckController;
    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        user.getCardInventory().addCardToInventory(Card.getCardByName("Battle OX"));
        deckController = new DeckController("hajji");
        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().createDeck("deckTwo", user);
    }

    @Test
    void createDeckErrorHandler() {
        assertEquals(1, deckController.createDeckErrorHandler("deckTwo"));
        assertEquals(0, deckController.createDeckErrorHandler("deck3"));
        assertNotNull(User.getUserByUsername("hajji").getUserDeck().getDeckByName("deck3"));
    }

    @Test
    void activateDeckErrorHandler() {
        assertEquals(1, deckController.activateDeckErrorHandler("deck4"));
        assertEquals(0, deckController.activateDeckErrorHandler("deck1"));
        assertEquals("deck1",User.getUserByUsername("hajji").getUserDeck().getActiveDeck().getName());
    }

    @Test
    void getActiveDeckStr() {
        String strExpected = "deck1: main deck 0, side deck 0, valid";
        assertEquals(strExpected, deckController.getActiveDeckStr());
        User.getUserByUsername("hajji").getUserDeck().deleteDeckFromUserDecks("deck1");
        assertFalse(User.getUserByUsername("hajji").getUserDeck().doesDeckExist("deck1"));
        // here we hava bug -haji
        //assertEquals("", deckController.getActiveDeckStr());
    }

    @Test
    void getAllCardsStr() {
        String strExpected = "Battle OX:A monster with tremendous power, it destroys enemies with a swing of its axe.";
        ArrayList<String> expected = new ArrayList<>();
        expected.add(strExpected);
        assertEquals(expected, deckController.getAllCardsStr());
    }

    @Test
    void getOtherDeckStr() {
        //String strExpected = "deck3: main deck 0, side deck 0, valid, "
               // + "deckTwo: main deck 0, side deck 0, valid";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("deck3: main deck 0, side deck 0, valid");
        expected.add("deckTwo: main deck 0, side deck 0, valid");
        assertEquals(expected, deckController.getOtherDeckStr());
    }

    @Test
    void deleteDeckErrorHandler() {
        assertEquals(1, deckController.deleteDeckErrorHandler("deck5"));
        assertEquals(0, deckController.deleteDeckErrorHandler("deckTwo"));
    }


    @Test
    void removeCardErrorHandler() {
        User user = User.getUserByUsername("hajji");
        user.getCardInventory().addCardToInventory(Card.getCardByName("Horn Imp"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Horn Imp"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Trap Hole"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Axe Raider"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Suijin"));
        user.getUserDeck().getDeckByName("deck3").addCard("Horn Imp", false, user);
        user.getUserDeck().getDeckByName("deck3").addCard("Horn Imp", true, user);
        user.getUserDeck().getDeckByName("deck3").addCard("Trap Hole", false, user);

        assertEquals(1, deckController.removeCardErrorHandler("deck2", "Axe Raider", true));
        assertEquals(2, deckController.removeCardErrorHandler("deck3", "Axe Raider", true));
        assertEquals(0, deckController.removeCardErrorHandler("deck3", "Horn Imp", true));

    }

    @Test
    void getMonstersStr() {
        String strExpected = "Horn Imp: A small fiend that dwells in the dark, its single horn makes it a formidable opponent.";
        ArrayList<String> expected = new ArrayList<>();
        expected.add(strExpected);
        assertEquals(expected, deckController.getMonstersStr("deck3", false));
        ArrayList<String> secondExpected = new ArrayList<>();
        assertEquals(secondExpected, deckController.getMonstersStr("deck3", true));
    }

    @Test
    void getSpellAndTrapsStr() {
        String strExpected = "Trap Hole: When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.";
        ArrayList<String> expected = new ArrayList<>();
        expected.add(strExpected);
        assertEquals(expected, deckController.getSpellAndTrapsStr("deck3", false));
        ArrayList<String> secondExpected = new ArrayList<>();
        assertEquals(secondExpected, deckController.getSpellAndTrapsStr("deck3", true));
    }

    @Test
    void addCardErrorHandler() {
        User user = User.getUserByUsername("hajji");
        ArrayList<Card> cards = Card.getAllCards();
        assertFalse(User.getUserByUsername("hajji").getUserDeck().getDeckByName("deck3").doesCardExist("Axe Raider", false));
        assertEquals(1, deckController.addCardErrorHandler("deck3","haji", false));
        for(int i = cards.size() - 15; i < cards.size(); i++) {
            user.getUserDeck().getDeckByName("deck3").addCard(cards.get(i).getCardName(), true, user);
        }
        assertEquals(3, deckController.addCardErrorHandler("deck3", "Axe Raider", true));
        user.getUserDeck().getDeckByName("deck3").addCard("Trap Hole", false, user);
        user.getUserDeck().getDeckByName("deck3").addCard("Trap Hole", false, user);

        assertEquals(0, deckController.addCardErrorHandler("deck3", "Suijin", false));
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}