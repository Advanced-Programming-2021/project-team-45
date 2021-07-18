package Server.controller;

import javafx.scene.control.CheckBox;
import Server.model.card.*;
import Server.model.card.SpellTrapCards.effects.*;
import java.util.ArrayList;

public class CardCreatorController {


    private ArrayList<CheckBox> checkBoxes=new ArrayList<>();
    private Card createdCard;
    private String cardName;
    private ArrayList<Effect> allEffects=new ArrayList<>();

    public String getCardName() {
        return cardName;
    }


    public void addCardToTheNetWork(Card card,String name){
        if(card instanceof MonsterCard){
            Card.allMonsterCards.put(name, (MonsterCard) card);
            Card.getAllCards().add(card);
        }else{
            Card.allSpellTrapCards.put(name, (SpellTrapCard) card);
            Card.getAllCards().add(card);
        }
    }

    private static ArrayList<Effect> changeToEffect(ArrayList<CheckBox> allCheckBoxes) {
        ArrayList<Effect> answer = new ArrayList<>();
        for (CheckBox allCheckBox : allCheckBoxes) {
            if (allCheckBox.getText().equals("Advanced Ritual Art Effect")) answer.add(new AdvancedRitualArtEffect());
            else if (allCheckBox.getText().equals("Closed Forest Effect")) answer.add(new ClosedForestEffect());
            else if (allCheckBox.getText().equals("Destroy All Card")) answer.add(new DestroyAllCardsOfFieldEffect());
            else if (allCheckBox.getText().equals("Draw Card Effect")) answer.add(new DrawCardEffect(1));
            else if (allCheckBox.getText().equals("Increase Attack Of Cards")) ;
            else if (allCheckBox.getText().equals("Magic Clynder Effect")) answer.add(new MagicCylinderEffect());
            else if (allCheckBox.getText().equals("Magnum Shield Effect")) answer.add(new MagnumShieldEffect());
            else if (allCheckBox.getText().equals("Mirror Force Effect")) answer.add(new MirrorForceEffect());
            else if (allCheckBox.getText().equals("Mystical Space Typhoon Effect"))
                answer.add(new MysticalSpaceTyphoonEffect());
            else if (allCheckBox.getText().equals("Negate Attack Effect")) answer.add(new NegateAttackEffect());
            else if (allCheckBox.getText().equals("Solemn Warning Effect")) answer.add(new SolemnWarningEffect());
            else if (allCheckBox.getText().equals("Summon From GraveYard")) answer.add(new SummonFromGraveyardEffect());
            else if (allCheckBox.getText().equals("Terraforming")) answer.add(new TerraformingEffect());
            else if (allCheckBox.getText().equals("Torential Tribiute")) answer.add(new TorrentialTributeEffect());
            else if (allCheckBox.getText().equals("TrapHole Effect")) answer.add(new TrapHoleEffect());
            else if (allCheckBox.getText().equals("Twin Twisters Effect")) answer.add(new TwinTwistersEffect());
            else if (allCheckBox.getText().equals("United We Stands")) answer.add(new UnitedWeStandEffect());
        }
        return answer;
    }

    public void createNewCard(Integer level, String description, int price, String name, String type, String attack,
                              String defense) {
        if (type.equals("Monster Card")) {
            MonsterCard card = new MonsterCard(name, description, "Normal", price, null,
                    0, level, MonsterAttribute.EARTH, MonsterType.Dragon, Integer.parseInt(attack)
                    , Integer.parseInt(defense), null, null, false,
                    null, null);
            this.createdCard=card;
            this.cardName=name;
        } else {
            String kind = type.equals("Spell Card") ? "Spell" : "Trap";
            boolean bol = kind.equals("Spell");
            SpellTrapCard card = new SpellTrapCard(name, description, "Effect", price, null,
                    0, SpellAndTrapIcon.NORMAL, false, kind, bol, "unlimited", null,
                    allEffects);
            this.createdCard=card;
            this.cardName=name;
        }
        addCardToTheNetWork(createdCard,name);
    }
}
