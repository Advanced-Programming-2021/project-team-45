package view.cli;

import model.ArtificialIntelligence;
import controller.GameController;
import controller.MainMenuController;
import controller.Regex;

import java.util.HashMap;
import java.util.regex.Matcher;

public class MainMenu extends Menu {

    private final MainMenuController mainMenuController;
    private final String[] MAIN_MENU_REGEX = {
            // i = 0
            "^(menu exit|user logout)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter Deck)$|" +
                    "^(menu enter Scoreboard)$|" +
                    "^(menu enter Profile)$|" +
                    "^(menu enter Shop)$|" +
                    "^(menu enter Import/Export)$|" +
                    "^(help)$"
    };


    public MainMenu(String username) {
        super("Main Menu", new LoginMenu());
        setUsername(username);

        mainMenuController = new MainMenuController(username);

        subMenus = new HashMap<>();
        subMenus.put(MenuName.DECK, new DeckMenu(username, this));
        subMenus.put(MenuName.SCOREBOARD, new ScoreboardMenu(username, this));
        subMenus.put(MenuName.PROFILE, new ProfileMenu(username, this));
        subMenus.put(MenuName.SHOP, new ShopMenu(username, this));
        subMenus.put(MenuName.IMPORT_EXPORT, new ImportExportMenu(username, this));
    }


    private GameController startGame(String input) {
        int inputError = 0;

        Matcher newGameMatcher = Regex.getMatcher(input, " (--new|-n)");
        if (newGameMatcher.find()) {
            inputError++;
        }

        int rounds = 0;
        Matcher roundsMatcher = Regex.getMatcher(input, " (?:--rounds|-R) (\\d+)");
        if (roundsMatcher.find()) {
            rounds = Integer.parseInt(roundsMatcher.group(1));
            inputError++;
        }

        String opponentUsername = "";
        Matcher opponentUsernameMatcher = Regex.getMatcher(input, " (?:--second-player|-o) (\\w+)");
        if (opponentUsernameMatcher.find()) {
            opponentUsername = opponentUsernameMatcher.group(1);
            inputError++;
        }

        if (opponentUsername.equals("")) {
            Matcher aiMatcher=Regex.getMatcher(input, " (?:--ai|-I)");
            if (aiMatcher.find()) {
                new ArtificialIntelligence();
                opponentUsername="AI";
                inputError++;
            }
        }

        if (inputError < 3) {
            System.out.println("invalid command");

        } else {
            int error = mainMenuController.startGameErrorHandler(opponentUsername, rounds);

            if (error == 0) {
                if (opponentUsername.equals("")) {
                    return new GameController(username, "AI", rounds);
                }
                return new GameController(username, opponentUsername, rounds);

            } else if (error == 1) {
                System.out.println("there is no player with this username");

            } else if (error == 2) {
                System.out.println(username + " has no active deck");

            } else if (error == 3) {
                System.out.println(opponentUsername + " has no active deck");

            } else if (error == 4) {
                System.out.println(username + "'s deck is invalid");

            } else if (error == 5) {
                System.out.println(opponentUsername + "'s deck is invalid");

            } else if (error == 6) {
                System.out.println("number of rounds is not supported");

            }
        }

        return null;
    }

    private void help() {
        System.out.println("user logout\n" +
                "menu show-current\n" +
                "menu enter Deck\n" +
                "menu enter Scoreboard\n" +
                "menu enter Profile\n" +
                "menu enter Shop\n" +
                "menu enter Import/Export\n" +
                "duel --new --second-player <player2 username> --rounds <1/3>\n" +
                "duel --new --ai --rounds <1/3>\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "menu enter <menu name>\n" +
                "help");
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        Menu nextMenu = null;
        GameController gameController = null;
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, MAIN_MENU_REGEX[0]);

            if (input.startsWith("duel ")) {
                gameController = startGame(input);
                if (gameController != null) {
                    break;
                }

            } else if (matcher.find()) {
                if (matcher.group(1) != null) {
                    System.out.println("user logged out successfully!");
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    nextMenu = subMenus.get(MenuName.DECK);
                    break;

                } else if (matcher.group(4) != null) {
                    nextMenu = subMenus.get(MenuName.SCOREBOARD);
                    break;

                } else if (matcher.group(5) != null) {
                    nextMenu = subMenus.get(MenuName.PROFILE);
                    break;

                } else if (matcher.group(6) != null) {
                    nextMenu = subMenus.get(MenuName.SHOP);
                    break;

                } else if (matcher.group(7) != null) {
                    nextMenu = subMenus.get(MenuName.IMPORT_EXPORT);
                    break;

                } else if (matcher.group(8) != null) {
                    help();

                }

            } else {
                System.out.println("invalid command");
            }
        }

        if (gameController != null) {
            gameController.startGame();
        } else {
            if (nextMenu == null) {
                exitMenu();
            } else {
                nextMenu.show();
                nextMenu.execute();
            }
        }
    }
}
