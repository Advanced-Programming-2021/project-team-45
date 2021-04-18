package view.menu;

import controller.Regex;
import model.user.User;

import java.util.HashMap;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {

    private final LoginController loginController;
    private final String[] LOGIN_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                "^(menu show-current)$|" +
                "^(menu enter \\w+)$|" +
                "^(user create --username \\w+ --nickname \\w+ --password \\w+)$|" +
                "^(user login --username \\w+ --password \\w+)$",
            // i = 1
            "user create --username (\\w+) --nickname (\\w+) --password (\\w+)",
            // i = 2
            "user login --username (\\w+) --password (\\w+)"
    };


    public LoginMenu() {
        super("Login Menu", null);

        loginController = new LoginController();
    }


    public void show() {
    }

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
                    User user = loginUser(Regex.getMatcher(input, LOGIN_MENU_REGEX[2]));
                    if (user != null) {
                        nextMenu = new MainMenu(user, this);
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

    private void createUser(Matcher matcher) {
        if (matcher.find()) {
            String username = matcher.group(1);
            String nickname = matcher.group(2);
            String password = matcher.group(3);

            if (createUserErrorHandler(username, nickname)) {
                System.out.println("user created successfully!");
                loginController.createUser(username, nickname, password);

            }
        }
    }

    private boolean createUserErrorHandler(String username, String nickname) {
        if (loginController.doesUsernameExist(username)) {
            System.out.println("user with username " + username + " exists");
            return false;

        } else if (loginController.doesNicknameExist(nickname)) {
            System.out.println("user with nickname " + nickname + " exists");
            return false;

        }
        return true;
    }

    private User loginUser(Matcher matcher) {
        if (matcher.find()) {
            String username = matcher.group(1);
            String password = matcher.group(2);

            if (loginUserErrorHandler(username, password)) {
                System.out.println("user logged in successfully!");
                return loginController.getUserByUsername(username);

            }
        }
        return null;
    }

    private boolean loginUserErrorHandler(String username, String password) {
        if (!loginController.isUserPasswordCorrect(username, password)) {
            System.out.println("Username and password didn't match!");
            return false;

        }
        return true;
    }

    public void exitMenu() {
        System.exit(1);
    }

}
