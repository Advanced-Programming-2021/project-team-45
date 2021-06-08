package view.cli;

import controller.GameController;
import controller.Regex;
import model.game.GameBoard;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuelMenu extends Menu {

    private final GameController gameController;
    private final String[] DUEL_MENU_REGEX = {
            // i = 0
            "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(select (?:--monster|-M|--spell|-S|--field|-F|--hand|-H)(?: (?:--opponent|-O))? \\d+)$|" +
                    "^(select -d)$|" +
                    "^(next phase)$|" +
                    "^(summon)$|" +
                    "^(set)$|" +
                    "^(set (?:--position|-p) (?:attack|defense))$|" +
                    "^(flip-summon)$|" +
                    "^(attack \\d)$|" +
                    "^(attack direct)$|" +
                    "^(activate effect)$|" +
                    "^(show graveyard)$|" +
                    "^(card show (?:--selected|-X))$|" +
                    "^(help)$",
            // i = 1
            "select (--monster|-M|--spell|-S|--field|-F|--hand|-H)(?: (--opponent|-O))? (\\d+)",
            // i = 2
            "attack (\\d)"
    };
    private boolean isCommandEnded = false;


    public DuelMenu(String username, GameController gameController) {
        super("Duel", new MainMenu(username));
        setUsername(username);
        this.gameController = gameController;
    }


    public void getNextCommand() {
        isCommandEnded = false;
        while (!isCommandEnded) {
            String[][] playerGameBoard = gameController.getGame().getPlayerGameBoard().GameBoardOfPlayer();
            String[][] opponentGameBoard = gameController.getGame().getOpponentGameBoard().GameBoardOfPlayer();
            printGameBoard(playerGameBoard, opponentGameBoard);
          
            String input = getInput();
            if (input != null) {
                Matcher matcher = Regex.getMatcher(input, DUEL_MENU_REGEX[0]);
                if (matcher.find()) {
                    if (matcher.group(1) != null) {
                        showCurrentMenu();

                    } else if (matcher.group(2) != null) {
                        System.out.println("menu navigation is not possible");

                    } else if (matcher.group(3) != null) {
                        selectCard(Regex.getMatcher(input, DUEL_MENU_REGEX[1]));

                    } else if (matcher.group(4) != null) {
                        deselectCard();

                    } else if (matcher.group(5) != null) {
                        nextPhase();

                    } else if (matcher.group(6) != null) {
                        summonCard();

                    } else if (matcher.group(7) != null) {
                        setCard();

                    } else if (matcher.group(8) != null) {
                        changePosition(input);

                    } else if (matcher.group(9) != null) {
                        flipSummon();

                    } else if (matcher.group(10) != null) {
                        attackCard(Regex.getMatcher(input, DUEL_MENU_REGEX[2]));

                    } else if (matcher.group(11) != null) {
                        directAttack();

                    } else if (matcher.group(12) != null) {
                        activateEffect();

                    } else if (matcher.group(13) != null) {
                        showGraveyard();

                    } else if (matcher.group(14) != null) {
                        showCard();

                    } else if (matcher.group(15) != null) {
                        help();

                    }

                } else {
                    System.out.println("invalid command");

                }
            }
        }
    }

    public Boolean getYesNoAnswer(String question) {
        System.out.println(question + " (yes/no)");
        while (true) {
            String answer = getInput();
            if (answer != null) {
                if (answer.equalsIgnoreCase("yes")) {
                    return true;
                } else if (answer.equalsIgnoreCase("no")) {
                    return false;
                }
            }
            System.out.println("invalid command! try again!!");
        }
    }

    public ArrayList<Integer> getCardsForTribute(int n) {
        ArrayList<Integer> cards = new ArrayList<>();
        System.out.println("enter " + n + " cards to tribute: (each in 1 line)");
        for (int i = 0; i < n; i++) {
            String input = getInput();
            if (input == null) return null;
            cards.add(Integer.parseInt(input));
        }
        return cards;
    }

    public String getCardName() {
        System.out.println("please enter a card Name:");
        String cardName = getInput();
        while (cardName == null) {
            System.out.println("invalid command!");
            cardName = getInput();
        }
        return cardName;
    }

    public void showOutput(String message) {
        System.out.println(message);
    }

    public void updatePlayerGameBoard() {
        String[][] playerGameBoard = gameController.getGame().getPlayerGameBoard().GameBoardOfPlayer();
        String[][] opponentGameBoard = gameController.getGame().getOpponentGameBoard().GameBoardOfPlayer();
        printGameBoard(playerGameBoard, opponentGameBoard);
    }

    public void updateOpponentGameBoard() {
        String[][] playerGameBoard = gameController.getGame().getOpponentGameBoard().GameBoardOfPlayer();
        String[][] opponentGameBoard = gameController.getGame().getPlayerGameBoard().GameBoardOfPlayer();
        printGameBoard(playerGameBoard, opponentGameBoard);
    }

    private void printGameBoard(String[][] playerGameBoard, String[][] opponentGameBoard) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (opponentGameBoard[i][j] != null) {
                    System.out.print(opponentGameBoard[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (i != 5) {
                    if (playerGameBoard[5 - i][11 - j] != null) {
                        System.out.print(playerGameBoard[5 - i][11 - j]);
                    }
                } else {
                    System.out.print(playerGameBoard[0][0] + playerGameBoard[0][1]);
                    break;
                }
            }
            System.out.println();
        }
    }

    public void showGameWinner(String username, int playerWins, int opponentWins) {
        System.out.println(username + " won the game and the score is: " + playerWins + "-" + opponentWins);
    }

    public void showMatchWinner(String username, int playerWins, int opponentWins) {
        System.out.println(username + " won the the whole match with score: " + playerWins + "-" + opponentWins);
    }

    private String getInput() {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("cancel")) {
            cancel();
            return null;

        } else if (input.equalsIgnoreCase("surrender")) {
            surrender();
            return null;

        } else {
            return input;
        }
    }

    private void selectCard(Matcher matcher) {
        if (matcher.find()) {
            String cardType = matcher.group(1);
            boolean isOpponentCard = false;
            if (matcher.group(2) != null) {
                isOpponentCard = matcher.group(2).matches("--opponent|-O");
            }
            int cardPosition = Integer.parseInt(matcher.group(3));

            int error = gameController.selectCardErrorHandler(cardType, cardPosition, isOpponentCard);
            if (error == 0) {
                System.out.println("card selected");

            } else if (error == 1) {
                System.out.println("invalid selection");

            } else {
                System.out.println("no card found in the given position");

            }
        }
    }

    private void deselectCard() {
        int error = gameController.deselectErrorHandler();
        if (error == 0) {
            System.out.println("card deselected");

        } else {
            System.out.println("no card is selected yet");

        }
    }

    private void nextPhase() {
        int error = gameController.nextPhaseInController();

        isCommandEnded = true;

        if (error == 1) {
            System.out.println("phase: draw phase");

            System.out.println("new card added to the hand : " +
                    gameController.getGame().getAddedCardInDrawPhase().getCardName());

        } else if (error == 2) {
            System.out.println("phase: Main phase 1");

        } else if (error == 5) {
            System.out.println("phase: End Phase");
            System.out.println("its " + gameController.getGame().getPlayerOfThisTurn().getNickname() + "'s turn");

        } else if (error == 0) {
            System.out.println("phase: standby phase");

        } else if (error == 3) {
            System.out.println("phase: battle Phase");

        } else if (error == 4) {
            System.out.println("phase: Main Phase 2");

        }
    }

    private void summonCard() {
        int error = gameController.summonErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t summon this card");

        } else if (error == 3) {
            System.out.println("action not allowed in this phase");

        } else if (error == 4) {
            System.out.println("monster card zone is full");

        } else if (error == 5) {
            System.out.println("you already summoned/set on this turn");

        } else if (error == 6) {
            System.out.println("summoned successfully");
            isCommandEnded = true;

        } else if (error == 7) {
            System.out.println("there are not enough cards for tribute");

        } else if (error == 8) {
            System.out.println("there no monsters on this address");

        } else if (error == 9) {
            System.out.println("there is no monster on one of these addresses");

        }
    }

    private void setCard() {
        int error = gameController.setCardErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t set this card");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("monster card zone is full");

        } else if (error == 5) {
            System.out.println("you already summoned/set on this turn");

        } else if (error == 6) {
            System.out.println("set successfully");
            isCommandEnded = true;

        } else if (error == 7) {
            System.out.println("spell card zone is full");

        }
    }

    private void changePosition(String input) {
        int error = gameController.changePositionErrorHandler(Regex.getMatcher(input, "(attack|defense)"));
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t change this card position");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("this card is already in the wanted position");

        } else if (error == 5) {
            System.out.println("you already changed this card position in this turn");

        } else if (error == 6) {
            System.out.println("monster card position changed successfully");
            isCommandEnded = true;

        }
    }

    private void flipSummon() {
        int error = gameController.flipSummonErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t change this card position");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("you can’t flip summon this card");

        } else if (error == 5) {
            System.out.println("flip summoned successfully");
            isCommandEnded = true;

        }
    }

    private void attackCard(Matcher matcher) {
        int error = 1;
        if (matcher.find())
            error = gameController.attackErrorHandler(Integer.parseInt(matcher.group(1)));

        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t attack with this card");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("this card already attacked");

        } else if (error == 5) {
            System.out.println("there is no card to attack here");

        } else if (error == 6) {
            System.out.println("your opponent’s monster is destroyed and your opponent receives\n" +
                    gameController.damageOnOpponent() + "battle damage");
            isCommandEnded = true;

        } else if (error == 7) {
            System.out.println("both you and your opponent monster cards are destroyed and no\n" +
                    "one receives damage");
            isCommandEnded = true;

        } else if (error == 8) {
            System.out.println("Your monster card is destroyed and you received" +
                    gameController.damageOnPlayer() + " battle\n" +
                    "damage");
            isCommandEnded = true;

        } else if (error == 9) {
            System.out.println("the defense position monster is destroyed");
            isCommandEnded = true;

        } else if (error == 10) {
            System.out.println("no card is destroyed");
            isCommandEnded = true;

        } else if (error == 11) {
            System.out.println("no card is destroyed and you received" + gameController.damageOnPlayer()
                    + " battle damage");
            isCommandEnded = true;

        } else if (error == 12) {
            System.out.println("the defense position monster " +
                    gameController.getDefenseTargetCardName() + " is destroyed");
            isCommandEnded = true;

        } else if (error == 13) {
            System.out.println("opponent’s monster card was " + gameController.getDefenseTargetCardName()
                    + " and no card is destroyed");
            isCommandEnded = true;

        } else if (error == 14) {
            System.out.println("opponent’s monster card was " + gameController.getDefenseTargetCardName()
                    + "and no card is destroyed and you received" +
                    gameController.damageOnPlayer() + " battle damage");
            isCommandEnded = true;

        }
    }

    private void directAttack() {
        int error = gameController.directAttackErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t attack with this card");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("this card already attacked");

        } else if (error == 5) {
            System.out.println("you can’t attack the opponent directly");

        } else if (error == 6) {
            System.out.println("you opponent receives" + gameController.damageOnOpponent()
                    + " battle damage");
            isCommandEnded = true;

        }
    }

    private void activateEffect() {
        int error = gameController.activeEffectErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("activate effect is only for spell cards.");

        } else if (error == 3) {
            System.out.println("you can’t activate an effect on this turn");

        } else if (error == 4) {
            System.out.println("you have already activated this card");

        } else if (error == 5) {
            System.out.println("spell card zone is full");

        } else if (error == 6) {
            System.out.println("preparations of this spell are not done yet");

        } else if (error == 7) {
            System.out.println("spell activated");
            isCommandEnded = true;

        }
    }

    private void surrender() {
        gameController.surrender();
        isCommandEnded = true;
    }

    private void cancel() {
        gameController.cancel();
    }

    private void showGraveyard() {
        String answer = gameController.controlGraveyard();
        System.out.println(answer);
    }

    private void showCard() {
        String answer = gameController.controlCardShow();
        System.out.println(answer);
    }

    private void help() {
        System.out.println("select <card address>\n" +
                "next phase\n" +
                "summon\n" +
                "set\n" +
                "set --position attack/defense\n" +
                "flip-summon\n" +
                "attack <number>\n" +
                "attack direct\n" +
                "activate effect\n" +
                "show graveyard\n" +
                "card show --selected\n" +
                "surrender\n" +
                "cancel\n" +
                "menu show-current\n" +
                "menu enter <menu name>\n" +
                "help");
    }

    public String getInputNumberOfFieldForSpecialMonster(String view) {
        System.out.println(view);
        Pattern nums = Pattern.compile("^\\d$");
        while (true) {
            String input = getInput();
            if (input != null) {
                Matcher matcher = nums.matcher(input);
                if (matcher.find()) {
                    if (Integer.parseInt(input) < 6 && Integer.parseInt(input) > 0) {
                        return input;
                    }
                }
            }
            System.out.println("your input is incorrect, please try again");
        }
    }

    public String getCardFromGraveYard(String view) {
        System.out.println(view);
        String cardName = getInput();
        while (cardName == null) {
            System.out.println("invalid command! try again.");
            cardName = getInput();
        }
        return cardName;
    }

    public int getNumber(String view) {
        System.out.println(view);
        return scanner.nextInt();
    }

    @Override
    public void show() {
        System.out.println("phase: draw phase");
        gameController.getGame().drawPhase();
        System.out.println("new card added to the hand : " +
                gameController.getGame().getAddedCardInDrawPhase().getCardName());
    }

    @Override
    public void execute() {
    }
}
