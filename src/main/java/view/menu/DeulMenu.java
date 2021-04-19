package view.menu;

import controller.Regex;

import java.util.regex.Matcher;

public class DeulMenu extends Menu {

    private final GameController gameController;
    private final String[] DUEL_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(deck create \\w+)$|" +
                    "^(deck delete \\w+)$|" +
                    "^(deck set-activate \\w+)$|" +
                    "^(deck add-card --card \\w+ --deck \\w+(?: --side)?)$|" +
                    "^(deck rm-card --card \\w+ --deck \\w+(?: --side)?)$|" +
                    "^(deck show --all)$|" +
                    "^(deck show --deck-name \\w+(?: --side)?)$|" +
                    "^(deck show --cards)$",
            // i = 1
            "deck create (\\w+)",
            // i = 2
            "deck delete (\\w+)",
            // i = 3
            "deck set-activate (\\w+)",
            // i = 4
            "deck add-card --card (\\w+) --deck (\\w+)( --side)?",
            // i = 5
            "deck rm-card --card (\\w+) --deck (\\w+)( --side)?",
            // i = 6
            "deck show --deck-name (\\w+)( --side)?"
    };


    public DeulMenu(String username, Menu parentMenu) {
        super("Duel", parentMenu);
        setUsername(username);


    }


    @Override
    public void show() {
    }

    @Override
    public void execute() {
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, DUEL_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                }

            } else {
                System.out.println("invalid command");
            }
        }

        exitMenu();
    }

}
