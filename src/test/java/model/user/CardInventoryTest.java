package model.user;


import model.card.Card;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardInventoryTest {
    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("amir", "1380", "hajji");
        user.getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Battle OX"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        user.getUserDeck().createDeck("me", user);
        user.getUserDeck().getDeckByName("me").addCard("Battle OX", false, user);
        user.getUserDeck().getDeckByName("me").addCard("Yomi Ship", false, user);
    }

   @Test
    void addCardToCardInventory(){
        assertEquals(1, User.getUserByUsername("amir").getCardInventory().cardsCount.get("Battle OX"));
        User.getUserByUsername("amir").getCardInventory().addCardToInventory(Card.getCardByName("Battle OX"));
        assertEquals(2, User.getUserByUsername("amir").getCardInventory().cardsCount.get("Battle OX"));
    }

    @Test
    void getCardCount() {
        User user = User.getUserByUsername("amir");
        assertEquals(2, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        assertEquals(3, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));
        assertEquals(0, User.getUserByUsername("amir").getCardInventory().getCardCount("Fireyarou"));

    }

    @Test
    void doesCardExistToAddToDeck() {
        User user = User.getUserByUsername("amir");
        assertFalse(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "me", "Fireyarou"));
        assertTrue(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "you", "Yomi Ship"));
        assertTrue(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "me", "Yomi Ship"));
    }

    @Test
    void getCardByCardName() {
        Card card = (Card.getCardByName("Battle OX"));
        assertTrue(card.equals(User.getUserByUsername("amir").getCardInventory().getCardByCardName("Battle OX").getCardName()));
    }

    @Test
    void getAllCardsStr() {
        String strExpected = "Battle OX:A monster with tremendous power, it destroys enemies with a swing of its axe.," +
                " Yomi Ship:If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.";
        ArrayList<String> expected = new ArrayList<>();
        expected.add(strExpected);
        assertEquals(expected, User.getUserByUsername("amir").getCardInventory().getAllCardsStr());
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("amir");
    }
}