package Server.model.game.fields;

import Server.model.Shop;
import Server.model.card.Card;
import Server.model.card.SpellAndTrapIcon;
import Server.model.card.SpellTrapCard;
import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckFieldTest {

    private static DeckField deckField;
    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        Shop shop = new Shop("hajji");

        shop.buy("Yomi Ship");
        shop.buy("Axe Raider");
        shop.buy("Fireyarou");
        shop.buy("Silver Fang");
        shop.buy("Forest");
        shop.buy("Closed Forest");

        /*user.getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Axe Raider"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Fireyarou"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Silver Fang"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Forest"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Closed Forest"));*/

        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Axe Raider",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Fireyarou",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Forest",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Closed Forest",false, user);
        user.getUserDeck().activateDeck("deck1");

        deckField = new DeckField(user);

    }

    @Test
    void drawCard() {
        User user = User.getUserByUsername("hajji");
        Card card = deckField.drawCard();
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist(card.getCardName(), false));
    }

    @Test
    void getFieldCard() {
        User user = User.getUserByUsername("hajji");
        Card card = deckField.getFieldSpell();
        assertTrue(card instanceof SpellTrapCard);
        assertSame(((SpellTrapCard) card).getIcon(), SpellAndTrapIcon.FIELD);
    }

    @Test
    void getDeck() {
        User user = User.getUserByUsername("hajji");
        assertSame(user.getUserDeck().getDeckByName("deck1"), deckField.getDeck());
    }

    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }

}