package controller;

import model.user.User;

public class AIController {
    private User AI;
    public AIController(){
        AI=createAIUser();
        AI.getCardInventory().setAICardInventory();
        AI.getUserDeck().createDeck("AIDECK",User.getUserByUsername("AI"));
        AI.getUserDeck().activateDeck("AIDECK");
        AI.getUserDeck().getActiveDeck().setDeckForAI();
    }

    private User createAIUser(){
        return new User("AI","1234","artificial intelligence");
    }


}
