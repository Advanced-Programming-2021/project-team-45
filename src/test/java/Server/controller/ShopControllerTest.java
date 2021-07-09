package Server.controller;

import Server.model.card.Card;
import Server.model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShopControllerTest {

    private static ShopController shopController;

    @BeforeAll
    public static void setBeforeTest() {
        User user = new User("hajji", "hajji", "hajji");
        user.decreaseMoney(98000);
        shopController = new ShopController("hajji");
    }

    @Test
    void buyCardErrorHandler() throws IOException {
        assertEquals(1, shopController.buyCardErrorHandler("hajji"));
        assertEquals(0, shopController.buyCardErrorHandler("Yomi Ship"));
    }

    @Test
    void getCardsPrices() {
        HashMap<String, Integer> expected = new HashMap<>();
        for (Card card : Card.getAllCards()) {
            String cardName = card.getCardName();
            Integer cardPrice = card.getPrice();
            expected.put(cardName, cardPrice);
        }

        assertEquals(expected, shopController.getCardsPrices());
    }


    @AfterAll
    public static void setAfterTest() {
        User.deleteUserByUsername("hajji");
    }

}