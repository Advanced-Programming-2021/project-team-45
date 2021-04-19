package view.menu;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    protected static final Scanner scanner;

    private final String name;
    private final Menu parentMenu;
    protected String username;
    protected HashMap<MenuName, Menu> subMenus;

    static {
        scanner = new Scanner(System.in);
    }


    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }


    public String getName() {
        return this.name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void showCurrentMenu() {
        System.out.println(this.getName());
    }

    public void exitMenu() {
        parentMenu.show();
        parentMenu.execute();
    }

    public abstract void show();

    public abstract void execute();

}
