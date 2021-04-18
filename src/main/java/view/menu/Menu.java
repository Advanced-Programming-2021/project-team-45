package view.menu;

import controller.Controller;
import model.user.User;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    protected static final Scanner scanner;

    private final String name;
    private final Menu parentMenu;
    private User user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setSubMenus(HashMap<MenuName, Menu> subMenus) {
        this.subMenus = subMenus;
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
