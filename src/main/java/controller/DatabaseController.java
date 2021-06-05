package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseController extends Controller {

    public DatabaseController(String username) {
        super(username);
    }


    public static ArrayList<User> importUsers() {
        File file = new File("src/main/resources/controller/database/users.json");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException ignored) {
        }
        String json = fileScanner.nextLine();

        Gson gson = new Gson();
        return gson.fromJson(json,
                new TypeToken<ArrayList<User>>() {
                }.getType());
    }

    public static void exportUsers() {
        ArrayList<User> users = User.getUsers();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        try {
            FileWriter writer = new FileWriter("src/main/resources/controller/database/users.json");
            writer.write(json);
            writer.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();

        }
    }

    private void writeToExportedCards(String json) {
        try {
            FileWriter writer = new FileWriter("database\\exported.txt");
            writer.write(json);
            writer.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    public void importCard(String cardName) {
        File file = new File("database\\exported.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("can not read file!");
        }
        String cardType = fileScanner.nextLine();
        String json = fileScanner.nextLine();
        Card card;
        if (cardType.equals("monster")) {
            card = (new Gson()).fromJson(json, MonsterCard.class);
        } else {
            card = (new Gson()).fromJson(json, SpellTrapCard.class);
        }
        user.getCardInventory().addCardToInventory(card);
    }

    public void exportCard(String cardName) {
        Card card = user.getCardInventory().getCardByCardName(cardName);
        String jsonString;

        if (card instanceof MonsterCard) {
            MonsterCard monsterCard = (MonsterCard) card;
            jsonString = "monster\n" + (new Gson()).toJson(monsterCard);
        } else {
            SpellTrapCard spellTrapCard = (SpellTrapCard) card;
            jsonString = "spell\n" + (new Gson()).toJson(spellTrapCard);
        }

        writeToExportedCards(jsonString);
    }
}