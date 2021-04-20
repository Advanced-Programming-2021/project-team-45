package model.card;

import model.user.User;

import java.io.IOException;
import java.util.ArrayList;

public class Card {
    protected String cardName;
    protected String cardDescription;
    protected String cardType;
    protected int price;
    protected User owner;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public static ArrayList<Card> getAllCards() throws IOException {
        String[][] Monsters=new MonsterCard("fake").allDataAboutMonster();
        String[][] SpellAndTraps=new SpellTrapCard("fake").allDataAboutSpellTrap();
        ArrayList<Card> allCards=new ArrayList<>();
        for(int i=1;i<42;i++){
            allCards.add(new MonsterCard(Monsters[i][0]));
        }
        for(int i=1;i<36;i++){
            allCards.add(new SpellTrapCard(SpellAndTraps[i][0]));
        }
        return allCards;
    }

    public static Card getCardByName(String cardName) throws IOException {
        ArrayList<Card> allCards=getAllCards();
        for (Card allCard : allCards) {
            if (allCard.cardName.equals(cardName)) return allCard;
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

    public User getOwner() {
        return owner;
    }


}
