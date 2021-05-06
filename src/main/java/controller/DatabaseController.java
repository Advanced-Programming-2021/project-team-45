package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseController extends Controller {

    public DatabaseController(String username) {
        super(username);
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