package Server.model.user;


import Server.model.Shop;
import Server.model.card.Card;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CardInventoryTest {
    private static Shop shop;
    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("amir", "1380", "hajji");
        shop = new Shop("amir");
        shop.buy("Yomi Ship");
        shop.buy("Battle OX");
        user.getUserDeck().createDeck("me", user);
        user.getUserDeck().getDeckByName("me").addCard("Battle OX", false, user);
        user.getUserDeck().getDeckByName("me").addCard("Yomi Ship", false, user);
    }

    @Test
    void getAllCardsStr() {
        String strExpected1 = "Battle OX:A monster with tremendous power, it destroys enemies with a swing of its axe.";
        String strExpected2 = "Yomi Ship:If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.";
        assertEquals(strExpected1, User.getUserByUsername("amir").getCardInventory().getAllCardsStr().get(0));
        assertEquals(strExpected2, User.getUserByUsername("amir").getCardInventory().getAllCardsStr().get(1));
    }

    @Test
    void getCardCount() {
        User user = User.getUserByUsername("amir");
        assertEquals(1, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));
        shop.buy("Yomi Ship");
        assertEquals(2, User.getUserByUsername("amir").getCardInventory().getCardCount("Yomi Ship"));
        assertEquals(0, User.getUserByUsername("amir").getCardInventory().getCardCount("Fireyarou"));

    }

   @Test
    void addCardToCardInventory(){
        assertEquals(1, User.getUserByUsername("amir").getCardInventory().cardsCount.get("Battle OX"));
        User.getUserByUsername("amir").getCardInventory().addCardToInventory(Card.getCardByName("Battle OX"));
        assertEquals(2, User.getUserByUsername("amir").getCardInventory().cardsCount.get("Battle OX"));
    }

    @Test
    void getCardByCardName() {
        Card card = (Card.getCardByName("Battle OX"));
        assertEquals("Battle OX", card.getCardName());
    }

    @Test
    void doesCardExistToAddToDeck() {
        User user = User.getUserByUsername("amir");
        assertFalse(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "me", "Fireyarou"));
        assertTrue(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "you", "Yomi Ship"));
        assertTrue(user.getCardInventory().doesCardExistToAddToDeck(user.getUserDeck(), "me", "Yomi Ship"));
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("amir");
    }
}