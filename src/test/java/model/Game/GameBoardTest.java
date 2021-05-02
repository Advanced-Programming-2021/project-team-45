package model.Game;

import model.card.*;
import model.user.User;
import model.user.UserDeck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    @Test
    void gameBoardOfPlayer() throws IOException {
        MonsterCard MCard=new MonsterCard("Axe Raider");
        SpellTrapCard SCard=new SpellTrapCard("Wall of Revealing Light");
        SCard.setPosition(SpellsAndTrapPosition.SUMMON);
        User owner=new User("a","b","c");
        User opponent=new User("q","w","e");
        owner.getUserDeck().createDeck("hello",owner);
        owner.getCardInventory().addCardToCardInventory(MCard);
        owner.getCardInventory().addCardToCardInventory(SCard);
        owner.getUserDeck().activateDeck("hello");
        owner.getUserDeck().getActiveDeck().addCard("Axe Raider",false,owner);
        owner.getUserDeck().getActiveDeck().addCard("Axe Raider",false,owner);
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
        GameBoard gameBoard=new GameBoard(owner,graveyard,hand,monsterField,spellTrapField,fieldZone,deckField,game);
        String[][] answer=gameBoard.GameBoardOfPlayer();
        for(int i=0;i<6;i++){
            for(int j=0;j<12;j++){
                if(answer[i][j]!=null) {
                    System.out.print(answer[i][j]);
                }
            }
            System.out.println();
        }

    }
}