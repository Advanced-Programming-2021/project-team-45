package view.cli;

public class MainCli {
    public static void main(String[] args) {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.show();
        loginMenu.execute();
    }
}
