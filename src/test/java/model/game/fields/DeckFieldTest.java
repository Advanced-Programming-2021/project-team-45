package model.game.fields;

import model.card.Card;
import model.card.SpellTrapCard;
import model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckFieldTest {

    private DeckField deckField;
    @BeforeAll
    public void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        user.getCardInventory().addCardToInventory(Card.getCardByName("Yomi Ship"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Axe Raider"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Fireyarou"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Siver Fang"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Forest"));
        user.getCardInventory().addCardToInventory(Card.getCardByName("Closed Forest"));
        user.getUserDeck().createDeck("deck1", user);
        user.getUserDeck().getDeckByName("deck1").addCard("Yomi Ship",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Axe Raider",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Fireyarou",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Silver Fang",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Forest",false, user);
        user.getUserDeck().getDeckByName("deck1").addCard("Closed Forest",false, user);
        user.getUserDeck().activateDeck("deck1");

        this.deckField = new DeckField(user);

    }

    @Test
    void drawCard() {
        User user = User.getUserByUsername("hajji");
        Card card = this.deckField.drawCard();
        assertFalse(user.getUserDeck().getDeckByName("deck1").doesCardExist(card.getCardName(), false));
    }

    @Test
    void getFieldCard() {
        User user = User.getUserByUsername("hajji");
        Card card = deckField.getFieldSpell();
        assertTrue(card instanceof SpellTrapCard);
        assertTrue(((SpellTrapCard) card).getIcon().equals("Field"));
    }

    @Test
    void getDeck() {
        User user = User.getUserByUsername("hajji");
        assertSame(user.getUserDeck().getDeckByName("deck1"), deckField.getDeck());
    }

}