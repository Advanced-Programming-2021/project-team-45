package model.user;


import model.card.Card;
import org.junit.jupiter.api.*;

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
        assertEquals(0, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        assertEquals(1, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));

    }

    @Test
    void doesCardExistToAddToDeck() {
        User user = User.getUserByUsername("amir");
        assertTrue(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "me", "Yomi Ship"));
    }

    @Test
    void getCardByCardName() {
        Card card = (Card.getCardByName("Battle OX")).clone();
        assertEquals(card, User.getUserByUsername("amir").getCardInventory().getCardByCardName("Battle OX"));
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("amir");
    }
}