package model.Game;

import model.card.Deck;
import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.user.User;
import model.user.UserDeck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @BeforeEach
    public void set() throws IOException {

        MonsterCard MCard=new MonsterCard("Axe Raider");
        SpellTrapCard SCard=new SpellTrapCard("Wall of Revealing Light");

        User owner=new User("a","b","c");
        User opponent=new User("q","w","e");
        Deck deck=new Deck("hello",owner);
        owner.getCardInventory().addCardToCardInventory(MCard);
        owner.getCardInventory().addCardToCardInventory(SCard);
        deck.addCard("Axe Raider",false,owner);
        owner.getUserDeck().activateDeck("hello");
        Graveyard graveyard=new Graveyard();
        Hand hand=new Hand();
        MonsterField monsterField=new MonsterField(graveyard);
        SpellTrapField spellTrapField=new SpellTrapField();
        FieldZone fieldZone=new FieldZone(graveyard);
        DeckField deckField=new DeckField(owner);
        Game game=new Game(owner,opponent,10);

        graveyard.addCardToGraveyard(MCard);
        hand.addCard(MCard);
        hand.addCard(SCard);
        monsterField.addMonsterToField(MCard);
        monsterField.addMonsterToField(MCard);
        monsterField.addMonsterToField(MCard);
        monsterField.addMonsterToField(MCard);
        monsterField.addMonsterToField(MCard);
        spellTrapField.addSpellTrapCard(SCard);
        spellTrapField.addSpellTrapCard(SCard);


    }


    @Test
    void gameBoardOfPlayer() {

    }
}