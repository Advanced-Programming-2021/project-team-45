package view.menu;

import controller.GameController;
import controller.Regex;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;

public class DuelMenu extends Menu {

    private final GameController gameController;

    private final String[] DUEL_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(select (?:--monster|-M|--spell|-S|--field|-F|--hand|-H)(?: (?:--opponent|-O))? \\d+)$|" +
                    "^(select -d)$|" +
                    "^(next phase)$" +
                    "^(summon)$|" +
                    "^(set)$|" +
                    "^(set (?:--position|-p) (?:attack|defense))$|" +
                    "^(flip-summon)$|" +
                    "^(attack \\d)$|" +
                    "^(attack direct)$|" +
                    "^(activate effect)$|" +
                    "^(show graveyard)$|" +
                    "^(card show (?:--selected|-X))$",
            // i = 1
            "select (--monster|-M|--spell|-S|--field|-F|--hand|-H)(?: (--opponent|-O))? (\\d+)",
            // i = 2
            "attack (\\d)"
    };
    private String selectedCard;


    public DuelMenu(String username, Menu parentMenu) {
        super("Duel", parentMenu);
        setUsername(username);

        gameController = new GameController(username);
    }


    @Override
    public void show() {
    }

    @Override
    public void execute() {
        // regex duel new nist
        gameController.createNewGame();
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

                } else if (matcher.group(4) != null) {
                    int returnedNumber = gameController.selectCardErrorHandler(input);//s
                    if (returnedNumber == 0) System.out.println("card selected");
                    else if (returnedNumber == 1) System.out.println("invalid selection");
                    else System.out.println("no card found in the given position");

                } else if (matcher.group(5) != null) {
                    int returnedNumber = gameController.deSelectErrorHAndler();//s
                    if (returnedNumber == 0) System.out.println("card deselected");
                    else System.out.println("no card is selected yet");

                } else if (matcher.group(6) != null) {
                    int returnedNumber = gameController.nextPhaseInController();

                    if (returnedNumber == 1) {
                        System.out.println("phase: draw phase");

                        System.out.println("new card added to the hand : " +
                                gameController.getGame().getAddedCardInDrawPhase().getCardName());

                    } else if (returnedNumber == 2) {
                        System.out.println("phase: Main phase 1");

                    } else if (returnedNumber == 6) {
                        System.out.println("phase: End Phase");

                        System.out.println("its" + gameController.getGame().getOpponent().getNickname() + "'s turn");

                    } else if (returnedNumber == 0) {
                        System.out.println("phase: standby phase");

                    } else if (returnedNumber == 3) {
                        System.out.println("phase: battle Phase");

                    } else if (returnedNumber == 4) {
                        System.out.println("phase: Main Phase 2");

                    }
                } else if (matcher.group(7) != null) {
                    int returnedNumber = gameController.summonErrorHandler();
                    switch (returnedNumber) {
                        case 1:
                            System.out.println("no card is selected yet");
                            break;
                        case 2:
                            System.out.println("you can’t summon this card");
                            break;
                        case 3:
                            System.out.println("action not allowed in this phase");
                            break;
                        case 4:
                            System.out.println("monster card zone is full");
                            break;
                        case 5:
                            System.out.println("you already summoned/set on this turn");
                            break;
                        case 6:
                            System.out.println("summoned successfully");
                            break;
                        case 7:
                            System.out.println("there are not enough cards for tribute");
                            break;
                        case 8:
                            System.out.println("there no monsters one this address");
                            break;
                        case 9:
                            System.out.println("there is no monster on one of these addresses");
                            break;
                    }
                } else if (matcher.group(8) != null) {
                    int returnedNumber = gameController.setCardErrorHandler();
                    switch (returnedNumber) {
                        case 1:
                            System.out.println("no card is selected yet");
                            break;
                        case 2:
                            System.out.println("you can’t set this card");
                            break;
                        case 3:
                            System.out.println("you can’t do this action in this phase");
                            break;
                        case 4:
                            System.out.println("monster card zone is full");
                            break;
                        case 5:
                            System.out.println("you already summoned/set on this turn");
                            break;
                        case 6:
                            System.out.println("set successfully");
                            break;
                        case 7:
                            System.out.println("spell card zone is full");
                            break;
                    }
                } else if (matcher.group(9) != null) {
                    int returnedNumber = gameController.changePositionErrorHandler();
                    switch (returnedNumber) {
                        case 1:
                            System.out.println("no card is selected yet");
                            break;
                        case 2:
                            System.out.println("you can’t change this card position");
                            break;
                        case 3:
                            System.out.println("you can’t do this action in this phase");
                            break;
                        case 4:
                            System.out.println("this card is already in the wanted position");
                            break;
                        case 5:
                            System.out.println("you already changed this card position in this turn");
                            break;
                        case 6:
                            System.out.println("monster card position changed successfully");
                    }
                } else if (matcher.group(10) != null) {
                    int returnedNumber = gameController.flipSummonErrorHandler();
                    switch (returnedNumber) {
                        case 1:
                            System.out.println("no card is selected yet");
                            break;
                        case 2:
                            System.out.println("you can’t change this card position");
                            break;
                        case 3:
                            System.out.println("you can’t do this action in this phase");
                            break;
                        case 4:
                            System.out.println("you can’t flip summon this card");
                            break;
                        case 5:
                            System.out.println("flip summoned successfully");
                            break;
                    }
                } else if (matcher.group(11) != null) {
                    Matcher matcher1 = Regex.getMatcher(input,DUEL_MENU_REGEX[2]);
                    int returnedNumber = gameController.attackErrorHandler(Integer.parseInt(matcher1.group(1)));


                }

            } else {
                System.out.println("invalid command");
            }
            if (gameController.getGame().getPhase().equals("Main Phase1")) {
                showGameBoard();
            }
        }

        exitMenu();
    }

    private void showGameBoard() {

    }


}
