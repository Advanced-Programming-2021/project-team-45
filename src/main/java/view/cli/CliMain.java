package view.cli;

public class CliMain {
    public static void main(String[] args) {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.show();
        loginMenu.execute();
    }
}
