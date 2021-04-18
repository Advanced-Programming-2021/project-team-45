package model.card;

public class SpellTrapCard extends Card{
    protected SpellTrapIcon icon;
    protected boolean activated;
    protected boolean isSpell;
    protected SpellsAndTrapPosition position;

    public boolean isActivated(){
        return activated;
    }

    public SpellTrapIcon getIcon() {
        return icon;
    }

    public void summon(){
        this.position=SpellsAndTrapPosition.SUMMON;
    }

    public void set(){
        this.position=SpellsAndTrapPosition.SET;
    }



}
