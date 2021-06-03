package view.cli;

import controller.Regex;
import controller.ScoreboardController;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;

public class ScoreboardMenu extends Menu {

    private final String[] SCOREBOARD_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(scoreboard show)$|" +
                    "^(help)$"
    };


    public ScoreboardMenu(String username, Menu parentMenu) {
        super("Scoreboard", parentMenu);
        setUsername(username);

    }

    private void showScoreboard() {
        LinkedHashMap<String, Integer> scoreboard = ScoreboardController.getSortedNicknameScore();
        int rank = 0;
        int lastScore = -1;
        for (String nickname : scoreboard.keySet()) {
            int score = scoreboard.get(nickname);
            if (lastScore != score) rank++;
            lastScore = score;
            System.out.println(rank + "- " + nickname + ": " + score);
        }
    }

    private void help() {
        System.out.println("scoreboard show\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "help");
    }

    @Override
    public void show() {
    }

    @Override
    public void execute(){
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, SCOREBOARD_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                } else if (matcher.group(4) != null) {
                    showScoreboard();

                } else if (matcher.group(5) != null) {
                    help();

                }

            } else {
                System.out.println("invalid command");
            }
        }

        exitMenu();
    }

}
