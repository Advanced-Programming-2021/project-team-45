package controller;

import model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @BeforeAll
    public static void beforeAll(){
        User player=new User("hossein","1234","threeC");
        User opponent=new User("harif","324","chagh");

    }


    @Test
    void increaseMoneyAndScore(){

    }

    @Test
    void startGame() {

    }

    @Test
    void surrender() {

    }

    @Test
    void cancel() {
    }

    @Test
    void selectCardErrorHandler() {
    }

    @Test
    void deselectErrorHandler() {
    }

    @Test
    void nextPhaseInController() {
    }

    @Test
    void summonErrorHandler() {
    }

    @Test
    void setCardErrorHandler() {
    }

    @Test
    void changePositionErrorHandler() {
    }

    @Test
    void flipSummonErrorHandler() {
    }

    @Test
    void attackErrorHandler() {
    }

    @Test
    void directAttackErrorHandler() {
    }

    @Test
    void activeEffectErrorHandler() {
    }

    @Test
    void controlGraveyard() {
    }

    @Test
    void controlCardShow() {
    }

    @Test
    void damageOnOpponent() {
    }

    @Test
    void damageOnPlayer() {
    }

    @Test
    void getDefenseTargetCardName() {
    }

    @Test
    void getGame() {
    }

    @Test
    void getYesNoAnswer() {
    }

    @Test
    void getCardsForTribute() {
    }

    @Test
    void numberOfField() {
    }

    @Test
    void getACardFromGraveyardForScanner() {
    }

    @Test
    void getCardFromPlayer() {
    }

    @Test
    void showOutput() {
    }

    @Test
    void getNumberFromPlayer() {
    }

    @Test
    void doesPlayerWantToAddToTheChain() {
    }

    @Test
    void getSpellToAddToChain() {
    }
}