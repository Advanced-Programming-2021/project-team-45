package model.card;

import model.user.User;

import java.io.IOException;
import java.util.ArrayList;

public class Card {

    private static final ArrayList<Card> allCards;

    protected String cardName;
    protected String cardDescription;
    protected String cardType;
    protected int price;
    protected String ownerUsername;
    protected int speed;

    static {
        allCards = new ArrayList<>();
        try {
            String[][] Monsters = MonsterCard.allDataAboutMonster();
            String[][] SpellAndTraps = SpellTrapCard.allDataAboutSpellTrap();
            for (int i = 1; i < 42; i++) {
                allCards.add(new MonsterCard(Monsters[i][0]));
            }
            for (int i = 1; i < 36; i++) {
                allCards.add(new SpellTrapCard(SpellAndTraps[i][0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static Card getCardByName(String cardName) {
        ArrayList<Card> allCards;
        allCards = getAllCards();
        for (Card card : allCards) {
            if (card.cardName.equals(cardName)) return card.clone();
        }
        return null;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setOwnerUsername(String username) {
        this.ownerUsername = username;
    }

    public User getOwner() {
        return User.getUserByUsername(ownerUsername);
    }

    public static String showCard(Card card) {
        String answer = "";
        if (card instanceof MonsterCard) {
            MonsterCard monsterCard = (MonsterCard) card;
            answer += "Name: " + monsterCard.cardName + "\n";
            answer += "Level: " + monsterCard.level + "\n";
            answer += "Type: " + monsterCard.type + "\n";
            answer += "ATK: " + monsterCard.attack + "\n";
            answer += "DEF: " + monsterCard.defense + "\n";
            answer += "Description: " + monsterCard.cardDescription + "\n";
        } else if (card instanceof SpellTrapCard){
            SpellTrapCard spellOrTrapCard = (SpellTrapCard) card;
            answer += "Name: " + spellOrTrapCard.cardName + "\n";
            answer += (spellOrTrapCard.isSpell ? "Spell" : "Trap") + "\n";
            answer += "Type: " + spellOrTrapCard.type + "\n";
            answer += "Description: " + spellOrTrapCard.cardDescription + "\n";
        }else if(card==null){
            answer+="you dont have a selected card";
        }
        return answer;
    }

    public String getCardType() {
        return cardType;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public Card clone() {
        return new Card(this.getCardName());
    }
}
