package model.card;

import java.util.ArrayList;
import java.util.HashMap;

public class AbilitiesOfMonsters {

 /*
      Name Of Effect Monsters
   ****************************
        Command Knight
        Yomi ship
        Suijin
        Man-Eater Bug
        Gate Guardian
        Scanner
        Marshmallon
        Beast King Barbaros
        Texchanger
        The Calculator
        Mirage Dragon
        Herald Of Creation
        Exploder Dragon
        Terratiger, the Empowered Warrior
        The Tricky
    }

  */
    public void abilityOfMonster(MonsterCard card){
        switch (card.cardName){
            case "Command Knight":
                commandKnight();
                break;
            case "Yomi ship":
                yomiShip();
                break;
            case "Suijin":
                suijin();
                break;
            case "Man-Eater Bug":
                eaterMan();
                break;
            case "Gate Guardian":
                gateGuardian();
                break;
            case "Scanner":
                scanner();
                break;
            case "Marshmallon":
                marshmallon();
                break;
            case "Beast King Barbaros":
                barbaros();
                break;
            case "TexChanger":
                texChanger();
                break;
            case "The Calculator":
                calculator();
                break;
            case "Mirage Dragon":
                dragon();
                break;
            case "Herald Of Creation":
                herald();
                break;
            case "Exploder Dragon":
                exploder();
                break;
            case "Terratiger, the Empowered Warrior":
                warior();
                break;
            case "The Tricky":
                tricky();
                break;
        }
    }
    public void commandKnight(){}
    public void yomiShip(){}
    public void suijin(){}
    public void eaterMan(){}
    public void gateGuardian(){}
    public void scanner(){}
    public void marshmallon(){}
    public void barbaros(){}
    public void texChanger(){}
    public void calculator(){}
    public void dragon(){}
    public void herald(){}
    public void exploder(){}
    public void warior() {}
    public void tricky() {}
}
