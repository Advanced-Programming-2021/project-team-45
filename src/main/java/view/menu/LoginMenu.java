package view.menu;

import controller.LoginController;
import controller.Regex;

import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {

    private final LoginController loginController;
    private final String[] LOGIN_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(user login (?:--username|-U) \\w+ (?:--password|-P) \\w+)$",
            // i = 2
            "user login (?:--username|-U) (\\w+) (?:--password|-P) (\\w+)"
    };


    public LoginMenu() {
        super("Login Menu", null);

        loginController = new LoginController();
    }


    private void createUser(String input) {
        int inputError = 0;

        String username = "";
        Matcher usernameMatcher = Regex.getMatcher(input, " (?:--username|-U) (\\w+)");
        if (usernameMatcher.find()) {
            username = usernameMatcher.group(1);
            inputError++;
        }

        String nickname = "";
        Matcher nicknameMatcher = Regex.getMatcher(input, " (?:--nickname|-N) (\\w+)");
        if (nicknameMatcher.find()) {
            nickname = nicknameMatcher.group(1);
            inputError++;
        }

        String password = "";
        Matcher passwordMatcher = Regex.getMatcher(input, " (?:--password|-P) (\\w+)");
        if (passwordMatcher.find()) {
            password = passwordMatcher.group(1);
            inputError++;
        }

        if (inputError < 3) {
            System.out.println("invalid command");

        } else {
            int error = loginController.createUserErrorHandler(username, nickname, password);

            if (error == 0) {
                System.out.println("user created successfully!");

            } else if (error == 1) {
                System.out.println("user with username " + username + " exists");

            } else if (error == 2) {
                System.out.println("user with nickname " + nickname + " exists");

            }
        }
    }

    private boolean loginUser(String input) {
        int inputError = 0;

        String username = "";
        Matcher usernameMatcher = Regex.getMatcher(input, " (?:--username|-U) (\\w+)");
        if (usernameMatcher.find()) {
            username = usernameMatcher.group(1);
            inputError++;
        }

        String password = "";
        Matcher passwordMatcher = Regex.getMatcher(input, " (?:--password|-P) (\\w+)");
        if (passwordMatcher.find()) {
            password = passwordMatcher.group(1);
            inputError++;
        }

        if (inputError < 2) {
            System.out.println("invalid command");

        } else {
            int error = loginController.loginUserErrorHandler(username, password);

            if (error == 0) {
                System.out.println("user logged in successfully!");
                return true;

            } else if (error == 1) {
                System.out.println("Username and password didn't match!");

            }
        }
        return false;
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() throws IOException {
        Menu nextMenu = null;
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, LOGIN_MENU_REGEX[0]);

            if (input.startsWith("user create ")) {
                input = input.replace("user create", "");
                createUser(input);

            } else if (input.startsWith("user login ")) {
                input = input.replace("user login", "");
                if (loginUser(input)) {
                    Matcher usernameMatcher = Regex.getMatcher(input, " (?:--username|-U) (\\w+)");
                    if (usernameMatcher.find()) username = usernameMatcher.group(1);
                    nextMenu = new MainMenu(username, this);
                    break;
                }

            } else if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("please login first");

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

    @Override
    public void exitMenu() {
        System.exit(1);
    }

}
