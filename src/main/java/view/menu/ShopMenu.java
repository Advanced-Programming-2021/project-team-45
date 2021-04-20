package view.menu;

import controller.Regex;
import controller.ShopController;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ShopMenu extends Menu {

    private final ShopController shopController;
    private final String[] SHOP_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(shop buy \\w+)$|" +
                    "^(shop show --all)$",
            // i = 1
            "shop buy (\\w+)"
    };


    public ShopMenu(String username, Menu parentMenu) {
        super("Shop", parentMenu);
        setUsername(username);

        shopController = new ShopController(username);
    }


    private void buyCard(Matcher matcher) throws IOException {
        if (matcher.find()) {
            String cardName = matcher.group(1);
            int error = shopController.buyCardErrorHandler(cardName);

            if (error == 1) {
                System.out.println("there is no card with this name");

            } else if (error == 2) {
                System.out.println("not enough money");

            }
        }
    }

    private void showAllCards() {
        HashMap<String, Integer> cardsPrices = shopController.getCardsPrices();
        for (String cardName : cardsPrices.keySet()) {
            System.out.println(cardName + ":" + cardsPrices.get(cardName));
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() throws IOException {
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, SHOP_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                } else if (matcher.group(4) != null) {
                    buyCard(Regex.getMatcher(input, SHOP_MENU_REGEX[1]));

                } else if (matcher.group(5) != null) {
                    showAllCards();

                }

            } else {
                System.out.println("invalid command");
            }
        }

        exitMenu();
    }

}
