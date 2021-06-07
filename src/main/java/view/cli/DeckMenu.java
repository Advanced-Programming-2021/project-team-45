package view.cli;

import controller.DeckController;
import controller.Regex;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class DeckMenu extends Menu {

    private final DeckController deckController;
    private final String[] DECK_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(deck create \\w+)$|" +
                    "^(deck delete \\w+)$|" +
                    "^(deck set-activate \\w+)$|" +
                    "^(deck show (?:--all|-A))$|" +
                    "^(deck show (?:--cards|-c))$|" +
                    "^(help)$",
            // i = 1
            "deck create (\\w+)",
            // i = 2
            "deck delete (\\w+)",
            // i = 3
            "deck set-activate (\\w+)"
    };


    public DeckMenu(String username, Menu parentMenu) {
        super("Deck", parentMenu);
        setUsername(username);

        deckController = new DeckController(username);
    }


    private void createDeck(Matcher matcher) {
        if (matcher.find()) {
            String deckName = matcher.group(1);
            int error = deckController.createDeckErrorHandler(deckName);

            if (error == 0) {
                System.out.println("deck created successfully!");

            } else if (error == 1) {
                System.out.println("deck with name " + deckName + " already exists");

            }
        }
    }

    private void deleteDeck(Matcher matcher) {
        if (matcher.find()) {
            String deckName = matcher.group(1);
            int error = deckController.deleteDeckErrorHandler(deckName);

            if (error == 0) {
                System.out.println("deck deleted successfully");

            } else if (error == 1) {
                System.out.println("deck with name " + deckName + " does not exist");

            }
        }
    }

    private void activateDeck(Matcher matcher) {
        if (matcher.find()) {
            String deckName = matcher.group(1);
            int error = deckController.activateDeckErrorHandler(deckName);

            if (error == 0) {
                System.out.println("deck activated successfully");

            } else if (error == 1) {
                System.out.println("deck with name " + deckName + " does not exist");

            }
        }
    }

    private void addCard(String input) {
        int inputError = 0;

        String cardName = "";
        Matcher cardNameMatcher = Regex.getMatcher(input, " (?:--card|-C) ([\\w| ]+)");
        if (cardNameMatcher.find()) {
            cardName = cardNameMatcher.group(1).trim();
            inputError++;
        }

        String deckName = "";
        Matcher deckNameMatcher = Regex.getMatcher(input, " (?:--deck|-D) (\\w+)");
        if (deckNameMatcher.find()) {
            deckName = deckNameMatcher.group(1);
            inputError++;
        }

        if (inputError < 2) {
            System.out.println("invalid command");

        } else {
            boolean isSideDeck = Regex.getMatcher(input, "--side|-s").find();
            int error = deckController.addCardErrorHandler(deckName, cardName, isSideDeck);

            if (error == 0) {
                System.out.println("card added to deck successfully");

            } else if (error == 1) {
                System.out.println("card with name " + cardName + " does not exist");

            } else if (error == 2) {
                System.out.println("deck with name " + deckName + " does not exist");

            } else if (error == 3) {
                if (isSideDeck) System.out.println("side deck is full");
                else System.out.println("main deck is full");

            } else if (error == 4) {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);

            }
        }
    }

    private void removeCard(String input) {
        int inputError = 0;

        String cardName = "";
        Matcher cardNameMatcher = Regex.getMatcher(input, " (?:--card|-C) ([\\w| |-]+)");
        if (cardNameMatcher.find()) {
            cardName = cardNameMatcher.group(1);
            inputError++;
        }

        String deckName = "";
        Matcher deckNameMatcher = Regex.getMatcher(input, " (?:--deck|-D) (\\w+)");
        if (deckNameMatcher.find()) {
            deckName = deckNameMatcher.group(1);
            inputError++;
        }

        if (inputError < 2) {
            System.out.println("invalid command");

        } else {
            boolean isSideDeck = Regex.getMatcher(input, "--side|-s").find();
            int error = deckController.removeCardErrorHandler(deckName, cardName, isSideDeck);

            if (error == 0) {
                System.out.println("card removed from deck successfully");

            } else if (error == 1) {
                System.out.println("deck with name " + deckName + " does not exist");

            } else if (error == 2) {
                if (isSideDeck) {
                    System.out.println("card with name " + cardName + " does not exist in side deck");
                } else {
                    System.out.println("card with name " + cardName + " does not exist in main deck");
                }

            }
        }
    }

    private void showAllDecks() {
        String activeDeckStr = deckController.getActiveDeckStr();
        ArrayList<String> otherDecksStr = deckController.getOtherDeckStr();

        System.out.println("Decks:");
        System.out.println("Active deck:");
        if (!activeDeckStr.equals("")) System.out.println(activeDeckStr);
        System.out.println("Other decks:");
        for (int i = 0; i < otherDecksStr.size(); i++) {
            System.out.println(otherDecksStr.get(i));
        }
    }

    private void showDeck(String input) {
        String deckName = "";
        Matcher deckNameMatcher = Regex.getMatcher(input, " (?:--deck-name|-d) (\\w+)");
        if (deckNameMatcher.find()) {
            deckName = deckNameMatcher.group(1);
        }

        if (deckName.equals("")) {
            System.out.println("invalid command");

        } else {
            boolean isSideDeck = Regex.getMatcher(input, "--side|-s").find();
            int error = deckController.showDeckErrorHandler(deckName, isSideDeck);

            if (error == 0) {
                System.out.println("Deck: " + deckName);
                if (isSideDeck) System.out.println("Side deck:");
                else System.out.println("Main deck:");
                ArrayList<String> monsters = deckController.getMonstersStr(deckName, isSideDeck);
                System.out.println("Monsters:");
                for (String monster : monsters) {
                    System.out.println(monster);
                }
                ArrayList<String> spellAndTraps = deckController.getSpellAndTrapsStr(deckName, isSideDeck);
                System.out.println("Spell and Traps:");
                for (String spellAndTrap : spellAndTraps) {
                    System.out.println(spellAndTrap);
                }

            } else if (error == 1) {
                System.out.println("deck with name " + deckName + " does not exist");

            }
        }
    }

    private void showCards() {
        ArrayList<String> allCardsStr = deckController.getAllCardsStr();
        for (String cardStr : allCardsStr) {
            System.out.println(cardStr);
        }
    }

    private void help() {
        System.out.println("deck create <deck name>\n" +
                "deck delete <deck name>\n" +
                "deck set-activate <deck name>\n" +
                "deck add-card --card <card name> --deck <deck name> --side(optional)\n" +
                "deck rm-card --card <card name> --deck <deck name> --side(optional)\n" +
                "deck show --all\n" +
                "deck show --deck-name <deck name> --side(optional)\n" +
                "deck show --cards\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "help");
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, DECK_MENU_REGEX[0]);

            if (input.startsWith("deck add-card ")) {
                input = input.replace("deck add-card", "");
                addCard(input);

            } else if (input.startsWith("deck rm-card ")) {
                input = input.replace("deck rm-card", "");
                removeCard(input);

            } else if (Regex.getMatcher(input, "^deck show (?:--deck-name|-d) ").find()) {
                showDeck(input);

            } else if (Regex.getMatcher(input, "^deck show (?:--side|-s) (?:--deck-name|-d) ").find()) {
                showDeck(input);

            } else if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                } else if (matcher.group(4) != null) {
                    createDeck(Regex.getMatcher(input, DECK_MENU_REGEX[1]));

                } else if (matcher.group(5) != null) {
                    deleteDeck(Regex.getMatcher(input, DECK_MENU_REGEX[2]));

                } else if (matcher.group(6) != null) {
                    activateDeck(Regex.getMatcher(input, DECK_MENU_REGEX[3]));

                } else if (matcher.group(7) != null) {
                    showAllDecks();

                } else if (matcher.group(8) != null) {
                    showCards();

                } else if (matcher.group(9) != null) {
                    help();

                }

            } else {
                System.out.println("invalid command");
            }
        }

        exitMenu();
    }

}
