package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;

import java.io.FileWriter;
import java.io.IOException;

public class DatabaseController extends Controller {

    public DatabaseController(String username) {
        super(username);
    }


    private void writeToExportedCards(String json) {
        try {
            FileWriter writer = new FileWriter("..\\..\\resources\\ImportedCards.txt");
            writer.write(json);
            writer.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    public void importCard(String cardName) {

    }

    public void exportCard(String cardName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Card card = user.getCardInventory().getCardByCardName(cardName);
        String jsonString;

        if (card instanceof MonsterCard) {
            MonsterCard monsterCard = (MonsterCard) card;
            jsonString = gson.toJson(monsterCard);
        } else {
            SpellTrapCard spellTrapCard = (SpellTrapCard) card;
            jsonString = gson.toJson(spellTrapCard);
        }

        writeToExportedCards(jsonString);
    }

}
