package controller;

import model.Shop;

import java.io.IOException;
import java.util.HashMap;

public class ShopController extends Controller {

    private final Shop shop;

    public ShopController(String username) {
        super(username);
        shop = new Shop(username);
    }


    public int buyCardErrorHandler(String cardName) throws IOException {
        if (!Shop.doesCardExist(cardName)) {
            return 1;

        } else if (!shop.hasEnoughMoney(cardName)) {
            return 2;

        } else {
            shop.buy(cardName);
            return 0;

        }
    }

    public HashMap<String, Integer> getCardsPrices() {
        return Shop.getCardsPrices();
    }

}
