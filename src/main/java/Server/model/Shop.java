package Server.model;

import Server.model.card.Card;
import Server.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {

    private final User user;
    private static ArrayList<Card> allCards;
    private static HashMap<String, Integer> shopInventory;
    private static HashMap<String, Boolean> cardsStatus;

    static {
        allCards = Card.getAllCards();
        shopInventory = new HashMap<>();
        cardsStatus = new HashMap<>();

        for (Card card : allCards) {
            shopInventory.put(card.getCardName(), 10);
            cardsStatus.put(card.getCardName(), false);
        }
    }


    public Shop(String username) {
        user = User.getUserByUsername(username);
    }

    public static HashMap<String, Integer> getShopInventory() {
        return shopInventory;
    }

    public static HashMap<String, Boolean> getCardsStatus() {
        return cardsStatus;
    }


    public static boolean doesCardExist(String cardName) {
        for (Card card : allCards) {
            if (card.getCardName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    public static int getCardInventoryInShop(String cardName) {
        return shopInventory.get(cardName);
    }

    public static void increaseCardInventoryInShop(String cardName, int number) {
        int inventory = shopInventory.get(cardName);
        shopInventory.replace(cardName, inventory + number);
    }

    public static void decreaseCardInventoryInShop(String cardName, int number) {
        int inventory = shopInventory.get(cardName);
        shopInventory.replace(cardName, Math.max((inventory - number), 0));
    }

    public static boolean isCardBanned(String cardName) {
        return cardsStatus.get(cardName);
    }

    public static void setIsCardBanned(String cardName, boolean isBanned) {
        cardsStatus.replace(cardName, isBanned);
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


    public boolean hasEnoughMoney(String cardName) {
        Card card = Card.getCardByName(cardName);
        return user.getMoney() >= card.getPrice();
    }

    public void buy(String cardName) {
        Card card = Card.getCardByName(cardName);

        assert card != null;
        card.setOwnerUsername(user.getUsername());
        int inventory = shopInventory.get(cardName);
        if (inventory > 1)
            shopInventory.replace(cardName, inventory - 1);
        user.getCardInventory().addCardToInventory(card);
        user.decreaseMoney(card.getPrice());
    }

    public void increaseMoney(int money) {
        user.increaseMoney(money);
    }
}
