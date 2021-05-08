package model.card.SpellTrapCards.effects;

import model.game.Game;

public class IncreaseAtkDefOfEquippedEffect extends Effect {

    private final int attack;
    private final int defence;

    public IncreaseAtkDefOfEquippedEffect(int attack, int defence) {
        this.attack = attack;
        this.defence = defence;
    }


    @Override
    public void activate(Game game) {

    }
}