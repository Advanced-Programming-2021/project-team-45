package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.logging.Level;

public class CardCreatorController {
    public static void calculate(ArrayList<CheckBox> allEffects, int price, Spinner<Integer> level,Text value){
        new calculator().realRun(allEffects,price,level,value);
    }
}

class calculator extends Thread {
    ArrayList<CheckBox> allEffects;
    private Spinner<Integer> level;
    private Text value;
    private int effectprice=0;
    public void realRun(ArrayList<CheckBox> allEffects, int price, Spinner<Integer> level, Text value) {
        this.allEffects = allEffects;
        this.level=level;
        this.value=value;
        effectprice=price;
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
        effectprice=effectprice+allActives*1000+1000-level.getValue()*100;
        int newLevel=level.getValue();
        while (true) {
            int newActivate = 0;
            for (CheckBox allEffect : allEffects) {
                if (allEffect.isSelected()) newActivate++;
            }
            if (allActives != newActivate) {
                int diference = newActivate - allActives;
                allActives=newActivate;
                effectprice=effectprice+diference*1000;
            }
            if(level.getValue()!=newLevel){
                effectprice=effectprice+(newLevel-level.getValue())*100;
                newLevel=level.getValue();
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
