package model.user;

import java.util.ArrayList;

public class User {

    private static ArrayList<User> users;

    private final String username;
    private String password;
    private String nickname;
    private int money;
    private Score score;
    private Lifepoint lifepoint;
    private CardInventory cardInventory;
    private UserDeck userDeck;

    static {
        users = new ArrayList<>();
    }


    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;

        this.money = 0;
        this.score = new Score();
        this.lifepoint = new Lifepoint();
        this.cardInventory = new CardInventory();
        this.userDeck = new UserDeck();

        users.add(this);
    }


    public static boolean doesUserExist(String username) {
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

    public Score getScore() {
        return this.score;
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
