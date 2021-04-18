package model.card;

import model.user.User;

public class MonsterCard extends Card {
    protected int level;
    protected MonsterAttribute attribute;
    protected String type;
    protected int attack;
    protected int defense;
    protected PositionMonsters position;

    public void increaseAttack(int num){
        this.attack+=num;
    }

    public void decreaseAttack(int num){
        this.attack-=num;
    }

    public void increaseDefense(int num){
        this.defense+=num;
    }

    public void decreaseDefense(int num){
        this.defense-=num;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void changePosition(){
        if(this.position==PositionMonsters.DEFENSE) this.position=PositionMonsters.ATTACK;
        else this.position=PositionMonsters.DEFENSE;
    }

    public void summon(){
        this.position=PositionMonsters.ATTACK;
    }

    public void set(){
        this.position=PositionMonsters.DEFENSE;
    }

    public void attackMonster(MonsterCard card){
        if(card.position==PositionMonsters.ATTACK) {
            if (this.attack > card.attack) {
                int decreaseFromOpponentLifepoint = this.attack - card.attack;
                int newLifepoint = card.owner.getLifepoint().getLifepoint() - decreaseFromOpponentLifepoint;
                card.owner.getLifepoint().setLifepoint(newLifepoint);
                card.attack = -1; //when a monster was destroyed, Its' attack and defence change to -1.
                card.defense = -1;
            } else if (this.attack == card.attack){
                this.attack=-1;
                this.defense=-1;
                card.attack=-1;
                card.defense=-1;
            }
            else{
                int decreaseFromAttacker=card.attack-this.attack;
                int newLifepoint=this.owner.getLifepoint().getLifepoint()-decreaseFromAttacker;
                this.owner.getLifepoint().setLifepoint(newLifepoint);
                this.attack=-1;
                this.defense=-1;
            }
        }
        else{
            if(this.attack> card.defense){
                card.defense=-1;
                card.attack=-1;
            }else if(this.attack< card.defense){
                int decreaseFromAttacker=card.defense-this.attack;
                int newLifepoint=this.owner.getLifepoint().getLifepoint()-decreaseFromAttacker;
                this.owner.getLifepoint().setLifepoint(newLifepoint);
            }
        }
    }

    public void attackOpponent(User Opponent){
        int newLifepoint=Opponent.getLifepoint().getLifepoint()-this.attack;
        if(newLifepoint>0) Opponent.getLifepoint().setLifepoint(newLifepoint);
        else Opponent.getLifepoint().setLifepoint(0);
    }

}
