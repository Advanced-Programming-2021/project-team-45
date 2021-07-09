package Server.controller;

import com.google.gson.reflect.TypeToken;
import com.gilecode.yagson.YaGson;
import Server.model.card.Card;
import Server.model.user.User;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DatabaseController extends Controller {

    public DatabaseController(String username) {
        super(username);
    }

    public static ArrayList<User> importUsers() {
        String jsonStr = "";
        try {
            jsonStr = new String(Files.readAllBytes(
                    Paths.get("src/main/resources/Server/controller/database/users.json")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        YaGson yaGson = new YaGson();
        Type jsonType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return yaGson.fromJson(jsonStr, jsonType);
    }

    public static void exportUsers() {
        ArrayList<User> users = User.getUsers();
        YaGson yaGson = new YaGson();
        String jsonStr = yaGson.toJson(users);
        try {
            FileWriter writer = new FileWriter("src/main/resources/Server/controller/database/users.json");
            writer.write(jsonStr);
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void importCard(String cardName) {
        String jsonStr = "";
        try {
            jsonStr = new String(Files.readAllBytes(
                    Paths.get("src/main/resources/Server/controller/database/exported.txt")));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        YaGson yaGson = new YaGson();
        Card card = yaGson.fromJson(jsonStr, Card.class);
        card.setOwnerUsername(user.getUsername());
        user.getCardInventory().addCardToInventory(card);
    }

    public void exportCard(String cardName) {
        Card card = user.getCardInventory().getCardByCardName(cardName);
        YaGson yaGson = new YaGson();
        String jsonStr = yaGson.toJson(card);
        try {
            FileWriter writer = new FileWriter("database\\exported.txt");
            writer.write(jsonStr);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}