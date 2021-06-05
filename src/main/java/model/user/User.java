package model.user;

import controller.DatabaseController;

import java.util.ArrayList;

public class User {

    private static final ArrayList<User> users;

    private final String username;
    private String password;
    private String nickname;
    private int money;
    private int score;
    private Lifepoint lifepoint;
    private CardInventory cardInventory;
    private UserDeck userDeck;
    private int lastDamageAmount;

    static {
        users = DatabaseController.importUsers();
    }


    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.money = 100000000;
        this.score = 0;
        this.lifepoint = new Lifepoint();
        this.cardInventory = new CardInventory();
        this.userDeck = new UserDeck();

        users.add(this);
    }


    public static ArrayList<User> getUsers() {
        return users;
    }

    public static boolean doesNicknameExist(String nickname) {
        for (User user : users) {
            if (user.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesUsernameExist(String username) {
        User user = getUserByUsername(username);
        return !(user == null);
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void deleteUserByUsername(String username) {
        User user = User.getUserByUsername(username);
        users.remove(user);
    }

    public static boolean isUserPassCorrect(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        } else {
            return user.isPasswordCorrect(password);
        }
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void decreaseScore(int score) {
        this.score -= score;
    }

    public Lifepoint getLifepoint() {
        return this.lifepoint;
    }

    public CardInventory getCardInventory() {
        return this.cardInventory;
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }

    public int getMoney() {
        return this.money;
    }

    public void increaseMoney(int money) {
        this.money += money;
    }

    public void decreaseMoney(int money) {
        this.money -= money;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public int getLastDamageAmount() {
        return lastDamageAmount;
    }

    public void setLastDamageAmount(int lastDamageAmount) {
        this.lastDamageAmount = lastDamageAmount;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof User)) {
            return false;
        } else {
            User user = (User) object;
            return user.getUsername().equals(this.username);
        }
    }


}
