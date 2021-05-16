package view.menu;

import controller.Regex;
import controller.ProfileController;

import java.util.regex.Matcher;

public class ProfileMenu extends Menu {

    private final ProfileController profileController;
    private final String[] PROFILE_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(profile change (?:--nickname|-N) \\w+)$|" +
                    "^(help)$",
            // i = 1
            "profile change (?:--nickname|-N) (\\w+)"
    };


    public ProfileMenu(String username, Menu parentMenu) {
        super("Profile", parentMenu);
        setUsername(username);

        profileController = new ProfileController(username);
    }


    private void changeNickname(Matcher matcher) {
        if (matcher.find()) {
            String nickname = matcher.group(1);
            int error = profileController.changeNicknameErrorHandler(nickname);

            if (error == 0) {
                System.out.println("nickname changed successfully!");

            } else if (error == 1) {
                System.out.println("user with nickname " + nickname + " already exists");

            }
        }
    }

    private void changePassword(String input) {
        int inputError = 0;

        String currentPassword = "";
        Matcher currentPasswordMatcher = Regex.getMatcher(input, " (?:--current|-u) (\\w+)");
        if (currentPasswordMatcher.find()) {
            currentPassword = currentPasswordMatcher.group(1);
            inputError++;
        }

        String newPassword = "";
        Matcher newPasswordMatcher = Regex.getMatcher(input, " (--new|-n) (\\w+)");
        if (newPasswordMatcher.find()) {
            newPassword = newPasswordMatcher.group(1);
            inputError++;
        }

        if (inputError < 2) {
            System.out.println("invalid command");

        } else {

            int error = profileController.changePasswordErrorHandler(currentPassword, newPassword);

            if (error == 0) {
                System.out.println("password changed successfully!");

            } else if (error == 1) {
                System.out.println("current password is invalid");

            } else if (error == 2) {
                System.out.println("please enter a new password");

            }
        }
    }

    private void help() {
        System.out.println("profile change --nickname <nickname>\n" +
                "profile change --password --current <current password> --new <new password>\n" +
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
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, PROFILE_MENU_REGEX[0]);

            if (input.startsWith("profile change (?:--password|-P) ")) {
                input = input.replace("profile change (?:--password|-P)", "");
                changePassword(input);

            } else if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                } else if (matcher.group(4) != null) {
                    changeNickname(Regex.getMatcher(input, PROFILE_MENU_REGEX[1]));

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
