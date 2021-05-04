package view.menu;

import controller.GameController;
import controller.Regex;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class DuelMenu extends Menu {

    private final GameController gameController;
    private final String[] DUEL_MENU_REGEX = {
            // i = 0
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
    private boolean isCommandEnded = false;


    public DuelMenu(String username, GameController gameController) {
        super("Duel", new MainMenu(username));
        setUsername(username);
        this.gameController = gameController;
    }


    public void getNextCommand() {
        // regex duel new nist
        isCommandEnded = false;
        while (!isCommandEnded) {
            String input = scanner.nextLine();
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

                }

            } else {
                System.out.println("invalid command");

            }
        }
    }

    public void showGameBoard() {
        String[][] playerGameBoard = gameController.getGame().getPlayerGameBoard().GameBoardOfPlayer();
        String[][] opponentGameBoard = gameController.getGame().getOpponentGameBoard().GameBoardOfPlayer();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (opponentGameBoard[i][j] != null) {
                    System.out.print(opponentGameBoard[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------");
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 12; j++) {
                if (playerGameBoard[4 - i][11 - j] != null) {
                    System.out.print(playerGameBoard[4 - i][11 - j]);
                }
                System.out.println();
            }
        }
        System.out.println(playerGameBoard[0][0] + playerGameBoard[0][1]);
    }

    public void showOpponentGameBoard() {

    }


    private void selectCard(Matcher matcher) {
        if (matcher.find()) {
            String cardType = matcher.group(1);
            boolean isOpponentCard = matcher.group(2).matches("--opponent|-O");
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

        } else if (error == 6) {
            System.out.println("phase: End Phase");

            System.out.println("its" + gameController.getGame().getOpponentOfThisTurn().getNickname() + "'s turn");

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

    public ArrayList<Integer> getCardsForTribute(int n) {
        ArrayList<Integer> cards = new ArrayList<>();
        System.out.println("enter " + n + " cards to tribute: (each in 1 line)");
        for (int i = 0; i < n; i++) {
            cards.add(Integer.parseInt(scanner.nextLine()));
        }
        return cards;
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
        int error = gameController.attackErrorHandler(Integer.parseInt(matcher.group(1)));
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

    public void activeSpellOrTrapInOtherPlayerTurn() {
        System.out.println("now it will be" + gameController.getGame().getOpponentOfThisTurn().getUsername() + "’s turn");
        int answer = gameController.activeSpellOrTrapInOtherPlayerTurnErrorHandler();
        showGameBoard();

        System.out.println("do you want to activate your trap and spell?");

        if (answer == 1||answer==0) {
            System.out.println("now it will be" + gameController.getGame().getOpponentOfThisTurn() + "’s turn");
            showGameBoard();
        } else if (answer == 2) {
            System.out.println("it’s not your turn to play this kind of moves");
            gameController.getGame().changeTurnForSpecials();
        } else if (answer == 3) {
            System.out.println("spell/trap activated");
        }
    }

    public int getNextCommandForActiveSpellOrTrap() {
        String input = scanner.nextLine();
        Matcher matcher = Regex.getMatcher(input, DUEL_MENU_REGEX[0]);
        if (matcher.find()) {
            if (matcher.group(3) != null) {
                if (selectCardSpecial(matcher)) {
                    String newInput = scanner.nextLine();
                    Matcher newMatcher = Regex.getMatcher(newInput, DUEL_MENU_REGEX[0]);
                    if (newMatcher.find() && newMatcher.group(12) != null) {
                        gameController.activateEffectOfTrapAndSpell();
                        return 3;
                    } else return 2;
                } else return 2;
            } else return 2;
        } else return 2;
    }

    private boolean selectCardSpecial(Matcher matcher) {
        if (matcher.find()) {
            String cardType = matcher.group(1);
            boolean isOpponentCard = matcher.group(2).matches("--opponent|-O");
            int cardPosition = Integer.parseInt(matcher.group(3));
            int error = gameController.selectCardErrorHandler(cardType, cardPosition, isOpponentCard);
            if (error == 0) {
                return true;
            } else if (error == 1) {
                return false;
            } else {
                return false;
            }
        }
        return false;
    }


    private void showGraveyard() {
        String answer = gameController.controlGraveyard();
        System.out.println(answer);
    }

    private void showCard() {
        String answer = gameController.controlCardShow();
        System.out.println(answer);
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
    }

}
