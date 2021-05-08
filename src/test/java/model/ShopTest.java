package model;

import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @BeforeEach
    public void set() {
        User user = new User("hajji", "hajji", "hajji");
    }

    @Test
    void doesCardExist() {

    }

    @Test
    void hasEnoughMoney() {

    }

    @Test
    void buy() {
        Shop shop = new Shop("hajji");
        shop.buy("Yomi Ship");
        assertNotNull(User.getUserByUsername("hajji").getCardInventory().getCardByCardName("Yomi Ship"));
        assertNull(User.getUserByUsername("hajji").getCardInventory().getCardByCardName("Battle OX"));
    }
}