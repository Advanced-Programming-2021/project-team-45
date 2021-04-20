package model;

import model.card.Card;
import model.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {

    private final User user;
    private static ArrayList<Card> allCards;

    static {
        try {
            allCards = Card.getAllCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Shop(String username) {
        user = User.getUserByUsername(username);
    }


    public static boolean doesCardExist(String cardName) {
        for (Card card : allCards) {
            if (card.getCardName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<String, Integer> getCardsPrices() {
        HashMap<String, Integer> cardsPrices = new HashMap<>();
        for (Card card : allCards) {
            String cardName = card.getCardName();
            Integer cardPrice = card.getPrice();
            cardsPrices.put(cardName, cardPrice);
        }
        return cardsPrices;
    }


    public boolean hasEnoughMoney(String cardName) throws IOException {
        Card card = Card.getCardByName(cardName);
        assert card != null;
        return user.getMoney() >= card.getPrice();
    }

    public void buy(String cardName) throws IOException {
        Card card = Card.getCardByName(cardName);
        user.getCardInventory().addCardToCardInventory(card);
        user.decreaseMoney(card.getPrice());
    }

}
