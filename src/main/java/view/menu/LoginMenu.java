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
                    "^(user create (?:--username|-U) \\w+ (?:--nickname|-N) \\w+ (?:--password|-P) \\w+)$|" +
                    "^(user login (?:--username|-U) \\w+ (?:--password|-P) \\w+)$",
            // i = 1
            "user create (?:--username|-U) (\\w+) (?:--nickname|-N) (\\w+) (?:--password|-P) (\\w+)",
            // i = 2
            "user login (?:--username|-U) (\\w+) (?:--password|-P) (\\w+)"
    };


    public LoginMenu() {
        super("Login Menu", null);

        loginController = new LoginController();
    }


    private void createUser(Matcher matcher) {
        if (matcher.find()) {
            String username = matcher.group(1);
            String nickname = matcher.group(2);
            String password = matcher.group(3);
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

    private boolean loginUser(Matcher matcher) {
        if (matcher.find()) {
            String username = matcher.group(1);
            String password = matcher.group(2);
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
    public void execute() {
        Menu nextMenu = null;
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, LOGIN_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("please login first");

                } else if (matcher.group(4) != null) {
                    createUser(Regex.getMatcher(input, LOGIN_MENU_REGEX[1]));

                } else if (matcher.group(5) != null) {
                    Matcher loginMatcher = Regex.getMatcher(input, LOGIN_MENU_REGEX[2]);
                    if (loginUser(loginMatcher)) {
                        String username = loginMatcher.group(1);
                        nextMenu = new MainMenu(username, this);
                        break;
                    }

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
