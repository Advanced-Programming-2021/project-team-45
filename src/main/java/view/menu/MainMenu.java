package view.menu;

import controller.Regex;
import model.user.User;

import java.util.HashMap;
import java.util.regex.Matcher;

public class MainMenu extends Menu {

    private final String[] MAIN_MENU_REGEX = {
            // i = 0
            "^(menu exit|user logout)$|" +
                "^(menu show-current)$|" +
                "^(menu enter Duel)$|" +
                "^(menu enter Deck)$|" +
                "^(menu enter Scoreboard)$|" +
                "^(menu enter Profile)$|" +
                "^(menu enter Shop)$|" +
                "^(menu enter Import/Export)$"
    };


    public MainMenu(User user, Menu parentMenu) {
        super("Main Menu", parentMenu);
        setUser(user);

        subMenus = new HashMap<>();
        subMenus.put(MenuName.DUEL, new DuelMenu(user, this));
        subMenus.put(MenuName.DECK, new DeckMenu(user, this));
        subMenus.put(MenuName.SCOREBOARD, new ScoreboardMenu(user, this));
        subMenus.put(MenuName.PROFILE, new ProfileMenu(user, this));
        subMenus.put(MenuName.SHOP, new ShopMenu(user, this));
        subMenus.put(MenuName.IMPORT_EXPORT, new ImportExportMenu(user, this));
    }


    public void show() {
    }

    public void execute() {
        Menu nextMenu = null;
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, MAIN_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    System.out.println("user logged out successfully!");
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    nextMenu = subMenus.get(MenuName.DUEL);
                    break;

                } else if (matcher.group(4) != null) {
                    nextMenu = subMenus.get(MenuName.DECK);
                    break;

                } else if (matcher.group(5) != null) {
                    nextMenu = subMenus.get(MenuName.SCOREBOARD);
                    break;

                } else if (matcher.group(6) != null) {
                    nextMenu = subMenus.get(MenuName.PROFILE);
                    break;

                } else if (matcher.group(7) != null) {
                    nextMenu = subMenus.get(MenuName.SHOP);
                    break;

                } else if (matcher.group(8) != null) {
                    nextMenu = subMenus.get(MenuName.IMPORT_EXPORT);
                    break;

                }

            } else {
                System.out.println("invalid command");
            }
        }

        if (nextMenu == null) {
            exitMenu();
        } else {
            nextMenu.show();
            nextMenu.execute();
        }
    }

}
