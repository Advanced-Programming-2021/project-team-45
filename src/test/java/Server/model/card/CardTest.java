package Server.model.card;

import Server.model.Shop;
import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        user.increaseMoney(3000);
        Shop shop = new Shop("hajji");
        shop.buy("Yomi Ship");
    }

    @Test
    void getCardByName() {
        assertNull(Card.getCardByName("hajji"));
        assertNotNull(Card.getCardByName("Yomi Ship"));
        assertEquals("Yomi Ship", Card.getCardByName("Yomi Ship").getCardName());
    }

    @Test
    void getCardName() {
        assertEquals("Axe Raider", Card.getCardByName("Axe Raider").getCardName());
        assertNotEquals("Yomi Ship", Card.getCardByName("Horn Imp").getCardName());
    }

    @Test
    void getCardDescription() {
        assertEquals("A small fiend that dwells in the dark, its single horn makes it a formidable opponent.",
                Card.getCardByName("Horn Imp").getCardDescription());
        assertNotEquals("A small fiend that dwells in the dark, its single horn makes it a formidable opponent.",
                Card.getCardByName("Axe Raider").getCardDescription());
    }

    @Test
    void getPrice() {
        assertEquals(3000, Card.getCardByName("Solemn Warning").getPrice());
        assertNotEquals(1300, Card.getCardByName("Silver Fang").getPrice());
    }

    @Test
    void getOwner() {
        User user = User.getUserByUsername("hajji");
        Card card = user.getCardInventory().getCardByCardName("Yomi Ship");
        assertSame(user, card.getOwner());
    }

    @Test
    void showCard() {
        assertEquals("Name: Horn Imp\n" + "Level: 4\n" + "Type: Fiend\n" + "ATK: 1300\n" + "DEF: 1000\n" +
                "Description: A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\n",
                Card.showCard(Card.getCardByName("Horn Imp")));
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }
}