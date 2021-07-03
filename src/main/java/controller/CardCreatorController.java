package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import model.card.*;
import model.card.SpellTrapCards.effects.*;

import java.util.ArrayList;


public class CardCreatorController {

    public static void calculate(ArrayList<CheckBox> allEffects, int price, Spinner<Integer> level, Text value) {
        new calculator().realRun(allEffects, price, level, value);
    }

    public static void createACard(ArrayList<CheckBox> allEffect, int level, String description, int price, String name
            , String type, String attack, String defense) {
        ArrayList<Effect> allEffects = changeToEffect(allEffect);
        if (type.equals("Monster Card")) {
            MonsterCard card = new MonsterCard(name, description, "Normal", price, null,
                    0, level, MonsterAttribute.EARTH, MonsterType.Dragon, Integer.parseInt(attack)
                    , Integer.parseInt(defense), null, null, false,
                    null, null);
            card.setEffects(allEffects);
            Card.allMonsterCards.put(name,card);
            Card.getAllCards().add(card);
        } else {
            String kind = type.equals("Spell Card") ? "Spell" : "Trap";
            boolean bol = kind.equals("Spell");
            SpellTrapCard card=new SpellTrapCard(name, description, "Effect", price, null,
                    0, SpellAndTrapIcon.NORMAL, false, kind, bol, "unlimited", null,
                    allEffects);
            Card.allSpellTrapCards.put(name,card);
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
}

class calculator extends Thread {
    ArrayList<CheckBox> allEffects;
    private Spinner<Integer> level;
    private Text value;
    private int effectprice = 0;

    public void realRun(ArrayList<CheckBox> allEffects, int price, Spinner<Integer> level, Text value) {
        this.allEffects = allEffects;
        this.level = level;
        this.value = value;
        effectprice = price;
        this.start();
    }

    public int getEffectprice() {
        return effectprice;
    }

    @Override
    public void run() {
        int allActives = 0;
        for (CheckBox allEffect : allEffects) {
            if (allEffect.isSelected()) allActives++;
        }
        effectprice = effectprice + allActives * 1000 + 1000 - level.getValue() * 100;
        int newLevel = level.getValue();
        while (true) {
            int newActivate = 0;
            for (CheckBox allEffect : allEffects) {
                if (allEffect.isSelected()) newActivate++;
            }
            if (allActives != newActivate) {
                int diference = newActivate - allActives;
                allActives = newActivate;
                effectprice = effectprice + diference * 1000;
            }
            if (level.getValue() != newLevel) {
                effectprice = effectprice + (newLevel - level.getValue()) * 100;
                newLevel = level.getValue();
            }
            value.setText(String.valueOf(effectprice));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
