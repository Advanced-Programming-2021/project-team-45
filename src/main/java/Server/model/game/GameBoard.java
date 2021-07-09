package Server.model.game;

import Server.model.game.fields.*;
import Server.model.user.User;


public class GameBoard {
    private final User owner;
    private final DeckField deckField;
    private final Graveyard graveyard;
    private final Hand hand;
    private final MonsterField monsterField;
    private final SpellTrapField spellTrapField;
    private final FieldZone fieldZone;

    public GameBoard(User owner, Game game) {
        this.owner = owner;
        this.graveyard = new Graveyard(game);
        this.hand = new Hand();
        this.monsterField = new MonsterField(graveyard);
        this.spellTrapField = new SpellTrapField(owner, graveyard);
        this.fieldZone = new FieldZone(graveyard);
        this.deckField = new DeckField(owner);
        this.hand.setHandAtFirst(this.deckField);
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public SpellTrapField getSpellTrapField() {
        return spellTrapField;
    }

    public User getOwner() {
        return owner;
    }

    public Hand getHand() {
        return hand;
    }

    public FieldZone getFieldZone() {
        return fieldZone;
    }

    public DeckField getDeckField() {
        return deckField;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }
}
