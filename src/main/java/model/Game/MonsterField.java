package model.Game;

import model.card.MonsterCard;

public class MonsterField {
    private MonsterCard[] monstersOnField = new MonsterCard[5];

    public void addMonsterToField(MonsterCard monster){
        int index = 0;
        while(index < 5){
            if(this.monstersOnField[index] == null){
                this.monstersOnField[index] = monster;
                break;
            }
            index++;
        }
    }

    public MonsterCard getMonster(int index){
        return this.monstersOnField[index - 1];
    }
    // nemiddonam ina chian -haji
    public String toStringForOpponent(){

    }

    public String toString(){

    }

}
