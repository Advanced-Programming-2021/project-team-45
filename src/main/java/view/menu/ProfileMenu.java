package view.menu;

import controller.Regex;

import java.util.regex.Matcher;

public class ProfileMenu extends Menu {

    private final ProfileController profileController;
    private final String[] PROFILE_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(profile change --nickname \\w+)$|" +
                    "^(profile change --password --current \\w+ --new \\w+)$",
            // i = 1
            "profile change --nickname (\\w+)",
            // i = 2
            "profile change --password --current (\\w+) --new (\\w+)"
    };


    public ProfileMenu(String username, Menu parentMenu) {
        super("Profile", parentMenu);
        setUsername(username);

        profileController = new ProfileController();
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, PROFILE_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    changeNickname(Regex.getMatcher(input, PROFILE_MENU_REGEX[1]));

                } else if (matcher.group(4) != null) {
                    changePassword(Regex.getMatcher(input, PROFILE_MENU_REGEX[1]));

                }

            } else {
                System.out.println("invalid command");
            }
        }

        exitMenu();
    }

    private void changeNickname(Matcher matcher) {
        if (matcher.find()) {
            String nickname = matcher.group(1);
            int error = profileController.changeNicknameErrorHandler(username, nickname);

            if (error == 0) {
                System.out.println("nickname changed successfully!");

            } else if (error == 1) {
                System.out.println("user with nickname " + nickname + " already exists");

            }
        }
    }

    private void changePassword(Matcher matcher) {
        if (matcher.find()) {
            String currentPassword = matcher.group(1);
            String newPassword = matcher.group(2);
            int error = profileController.changePasswordErrorHandler(username, currentPassword, newPassword);

            if (error == 0) {
                System.out.println("password changed successfully!");

            } else if (error == 1) {
                System.out.println("current password is invalid");

            } else if (error == 2) {
                System.out.println("please enter a new password");

            }
        }
    }

}
