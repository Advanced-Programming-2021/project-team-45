package model.card;

import au.com.bytecode.opencsv.CSVReader;
import model.game.Chain;

import model.card.SpellTrapCards.effects.AddEffects;
import model.card.SpellTrapCards.effects.Effect;

import java.io.FileReader;

import java.util.ArrayList;


public class SpellTrapCard extends Card {

    protected SpellAndTrapIcon icon;
    protected boolean activated;
    protected String type;
    protected boolean isSpell;
    protected String status;
    protected SpellsAndTrapPosition position;
    private final ArrayList<Effect> effects;

    public SpellTrapCard(String cardName) {
        super(cardName);
        String[] data = dataAboutASpellOrTrap(cardName);
        this.type = data[1];
        setSpellAndTrapIcon(data[2]);
        this.cardDescription = data[3];
        this.status = data[4];
        this.price = (int) Double.parseDouble(data[5]);
        this.isSpell = isSpell(this);
        effects = AddEffects.getCardEffects(cardName, this);
        this.speed = setSpeedTapAndSpell(this);
    }

    public SpellTrapCard(String cardName, String cardDescription, String cardType, int price, String ownerUsername, int speed,
                         SpellAndTrapIcon icon, boolean activated, String type, boolean isSpell,
                         String status, SpellsAndTrapPosition position, ArrayList<Effect> effects) {

        super(cardName, cardDescription, cardType, price, ownerUsername, speed);
        this.icon = icon;
        this.activated = activated;
        this.type = type;
        this.isSpell = isSpell;
        this.status = status;
        this.position = position;
        this.effects = new ArrayList<>(effects);
    }

    public static String[][] allDataAboutSpellTrap() {
        String[][] data = new String[42][9];
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("src/main/resources/SpellTrap.csv"));
            String[] nextLine;
            int a = 0, b = 0;
            // read one line at a time
            while ((nextLine = reader.readNext()) != null) {
                b = 0;
                for (String token : nextLine) {
                    data[a][b] = token;
                    b++;
                }
                a++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private String[] dataAboutASpellOrTrap(String cardName) {
        String[][] data = allDataAboutSpellTrap();
        int answer = 0;
        for (int i = 0; i < 36; i++) {
            if (data[i][0].equals(cardName)) {
                answer = i;
                break;
            }
        }
        if (answer == 0) {
            return new String[]{"1", "1", "1", "1", "1", "1"};
        } else return data[answer];
    }

    private boolean isSpell(SpellTrapCard card) {
        return !this.type.equals("Trap");
    }

    public boolean isSpell() {
        return this.isSpell;
    }

    public boolean isActivated() {
        return activated;
    }

    public SpellAndTrapIcon getIcon() {
        return icon;
    }

    public void summon() {
        this.position = SpellsAndTrapPosition.SUMMON;
    }

    public void set() {
        this.position = SpellsAndTrapPosition.SET;
    }

    public void activateEffects(Chain chain) {
        for (Effect effect : effects) {
            effect.activate(chain);
        }
        this.activated = true;
    }

    public boolean canActivateEffects(Chain chain) {
        for (Effect effect : effects) {
            if (!effect.canActivate(chain)) return false;
        }
        return true;
    }

    public SpellsAndTrapPosition getPosition() {
        return position;
    }

    public void setPosition(SpellsAndTrapPosition position) {
        this.position = position;
    }

    private void setSpellAndTrapIcon(String icon) {
        if (icon.equals("Normal")) {
            this.icon = SpellAndTrapIcon.NORMAL;
        } else if (icon.equals("Continuous")) {
            this.icon = SpellAndTrapIcon.CONTINUOUS;
        } else if (icon.equals("Quick-play")) {
            this.icon = SpellAndTrapIcon.QUICK_PLAY;
        } else if (icon.equals("Field")) {
            this.icon = SpellAndTrapIcon.FIELD;
        } else if (icon.equals("Equip")) {
            this.icon = SpellAndTrapIcon.EQUIP;
        } else if (icon.equals("Counter")) {
            this.icon = SpellAndTrapIcon.COUNTER;
        } else if (icon.equals("Ritual")) {
            this.icon = SpellAndTrapIcon.RITUAL;
        }
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    private int setSpeedTapAndSpell(SpellTrapCard card) {
        SpellAndTrapIcon check = card.getIcon();
        if (card.isSpell) {
            boolean bol = check.equals(SpellAndTrapIcon.QUICK_PLAY);
            if (bol) {
                return 2;
            } else {
                return 1;
            }
        } else {
            if (card.getCardName().equals("Solemn Warning")) {
                return 3;
            } else {
                return 2;
            }
        }
    }

    @Override
    public SpellTrapCard clone() {
        return new SpellTrapCard(this.cardName, this.cardDescription, this.cardType, this.price, this.ownerUsername, this.speed,
                this.icon, this.activated, this.type, this.isSpell, this.status, this.position, this.effects);
    }
}
