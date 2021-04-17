package view.menu;

public class LoginMenu extends Menu {

    private String[] LOGIN_MENU_REGEX = {};

    public LoginMenu() {
        super("Login Menu");

    }


    public void show() {
    }

    public void execute() {
        String input;
        do {
            input = scanner.nextLine();


        } while (!input.equals("menu exit"));

        exitMenu();
    }

    public void exitMenu() {
        System.exit(1);
    }

}
