package Server.model.card.SpellTrapCards.effects;

import Server.model.game.Chain;

public abstract class Effect {
    public abstract void activate(Chain chain);

    public boolean canActivate(Chain chain) {
        return true;
    }
}