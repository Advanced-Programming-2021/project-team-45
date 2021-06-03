package view.cli;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    public static final Scanner scanner;

    private final String name;
    private final Menu parentMenu;
    protected String username;
    protected HashMap<MenuName, Menu> subMenus;

    static {
        scanner = new Scanner(System.in);
    }


    protected Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }


    protected String getName() {
        return this.name;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected String getUsername() {
        return this.username;
    }

    protected void showCurrentMenu() {
        System.out.println(this.getName());
    }

    protected void exitMenu() {
        parentMenu.show();
        parentMenu.execute();
    }

    public static int inputInt() {
        return scanner.nextInt();
    }
    public static String inputString(){
        return scanner.nextLine();
    }

    public abstract void show();

    public abstract void execute();

}
