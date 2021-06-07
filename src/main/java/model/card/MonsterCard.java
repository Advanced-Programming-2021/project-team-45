package model.card;

import au.com.bytecode.opencsv.CSVReader;
import model.user.User;


import java.io.FileReader;
import java.io.IOException;


public class MonsterCard extends Card {
    protected int level;
    protected MonsterAttribute attribute;
    protected MonsterType type;
    protected int attack;
    protected int defense;
    protected PositionMonsters position;
    protected DefensePosition defenceMode;
    private boolean wasAttackedInThisTurn = false;
    private SpecialMonsterEnum specialMonsterEnum;

    //////////////////////// set enum in constructor

    private SpecialMonsterEnum special = null;

    public MonsterCard(String cardName) throws IOException {
        super(cardName);
        String[] data = dataAboutAMonster(cardName);
        this.level = (int) Double.parseDouble(data[1]);
        setAttribute(data[2]);
        setType(data[3]);
        this.cardType = data[4];
        this.attack = (int) Double.parseDouble(data[5]);
        this.defense = (int) Double.parseDouble(data[6]);
        this.cardDescription = data[7];
        this.price = (int) Double.parseDouble(data[8]);
        checkSpecialMonster(this);
        this.speed = setMonsterSpeed(this);
    }

    public MonsterCard(String cardName, String cardDescription, String cardType, int price, String ownerUsername, int speed,
                       int level, MonsterAttribute attribute, MonsterType type, int attack, int defense,
                       PositionMonsters position, DefensePosition defenceMode, boolean wasAttackedInThisTurn,
                       SpecialMonsterEnum specialMonsterEnum, SpecialMonsterEnum special) {

        super(cardName, cardDescription, cardType, price, ownerUsername, speed);
        this.level = level;
        this.attribute = attribute;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.position = position;
        this.defenceMode = defenceMode;
        this.wasAttackedInThisTurn = wasAttackedInThisTurn;
        this.specialMonsterEnum = specialMonsterEnum;
        this.special = special;
    }

    public static String[][] allDataAboutMonster() {
        String[][] data = new String[42][9];
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("src/main/resources/Monster.csv"));
            String[] nextLine;
            int a = 0, b = 0;
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

    private String[] dataAboutAMonster(String cardNAme) {
        String[][] data = allDataAboutMonster();
        int answer = 0;
        for (int i = 0; i < 42; i++) {
            if (data[i][0].equals(cardNAme)) {
                answer = i;
                break;
            }
        }
        if (answer == 0) {
            return new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1"};
        } else return data[answer];
    }

    public void setSpecialMonsterEnum(SpecialMonsterEnum specialMonsterEnum) {
        this.specialMonsterEnum = specialMonsterEnum;
    }

    public void increaseAttack(int num) {
        this.attack += num;
    }

    public void decreaseAttack(int num) {
        this.attack -= num;
    }

    public void increaseDefense(int num) {
        this.defense += num;
    }

    public void decreaseDefense(int num) {
        this.defense -= num;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void changePosition() {
        if (this.position == PositionMonsters.DEFENSE) {
            this.position = PositionMonsters.ATTACK;
        } else {
            this.position = PositionMonsters.DEFENSE;
        }
    }

    public void summon() {
        this.position = PositionMonsters.ATTACK;
    }

    public void set() {
        this.position = PositionMonsters.DEFENSE;
    }

    public void attackMonster(MonsterCard card) {
        if (card.position == PositionMonsters.ATTACK) {
            if (this.attack > card.attack) {
                int decreaseFromOpponentLifepoint = this.attack - card.attack;
                int newLifepoint = card.getOwner().getLifepoint().getLifepoint() - decreaseFromOpponentLifepoint;
                card.getOwner().getLifepoint().setLifepoint(newLifepoint);
                card.getOwner().setLastDamageAmount(decreaseFromOpponentLifepoint);

            } else if (this.attack < card.attack) {
                int decreaseFromAttacker = card.attack - this.attack;
                int newLifepoint = this.getOwner().getLifepoint().getLifepoint() - decreaseFromAttacker;
                this.getOwner().getLifepoint().setLifepoint(newLifepoint);
                this.getOwner().setLastDamageAmount(decreaseFromAttacker);
            }
        } else {
            if (this.attack < card.defense) {
                int decreaseFromAttacker = card.defense - this.attack;
                int newLifepoint = this.getOwner().getLifepoint().getLifepoint() - decreaseFromAttacker;
                this.getOwner().getLifepoint().setLifepoint(newLifepoint);
                this.getOwner().setLastDamageAmount(decreaseFromAttacker);
            }
        }
    }

    public void attackOpponent(User Opponent) {
        int newLifepoint = Opponent.getLifepoint().getLifepoint() - this.attack;
        if (newLifepoint > 0) Opponent.getLifepoint().setLifepoint(newLifepoint);
        else Opponent.getLifepoint().setLifepoint(0);
        Opponent.setLastDamageAmount(this.attack);
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public SpecialMonsterEnum getSpecial() {
        return special;
    }

    public int getLevel() {
        return level;
    }

    public PositionMonsters getPosition() {
        return position;
    }

    public void setPosition(PositionMonsters position) {
        this.position = position;
    }

    public DefensePosition getDefenceMode() {
        return defenceMode;
    }

    public void setDefenceMode(DefensePosition defenceMode) {
        this.defenceMode = defenceMode;
    }

    public MonsterAttribute getAttribute() {
        return attribute;
    }

    public MonsterType getType() {
        return type;
    }

    public boolean isWasAttackedInThisTurn() {
        return wasAttackedInThisTurn;
    }

    public void setWasAttackedInThisTurn(boolean wasAttackedInThisTurn) {
        this.wasAttackedInThisTurn = wasAttackedInThisTurn;
    }

    public SpecialMonsterEnum getSpecialMonsterEnum() {
        return specialMonsterEnum;
    }

    private void checkSpecialMonster(MonsterCard card) {
        if (card.getCardName().equals("Beast King Barbaros")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.BEAST_KING_BARBAROS);
        } else if (card.getCardName().equals("Command knight")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.COMMAND_KNIGHT);
        } else if (card.getCardName().equals("Yomi Ship")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.YOMI_SHIP);
        } else if (card.getCardName().equals("Suijin")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.SUIJIN);
        } else if (card.getCardName().equals("Crab Turtle")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.CRAB_TURTLE);
        } else if (card.getCardName().equals("Skull Guardian")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.SKULL_GUARDIAN);
        } else if (card.getCardName().equals("Man-Eater Bug")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.MAN_EATER_BUG);
        } else if (card.getCardName().equals("Scanner")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.SCANNER);
        } else if (card.getCardName().equals("Marshmallon")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.MARSHMALLON);
        } else if (card.getCardName().equals("Texchanger")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.TEXCHANGER);
        } else if (card.getCardName().equals("The Calculator")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.THE_CALCULATOR);
        } else if (card.getCardName().equals("Mirage Dragon")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.MIRAGE_DRAGON);
        } else if (card.getCardName().equals("Herald of Creation")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.HERALD_OF_CREATION);
        } else if (card.getCardName().equals("Exploder Dragon")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.EXPLODER_DRAGON);
        } else if (card.getCardName().equals("Terratiger, the Empowered Warrior")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.TERRATIGER);
        } else if (card.getCardName().equals("The Tricky")) {
            card.setSpecialMonsterEnum(SpecialMonsterEnum.THE_TRICKY);
        } else {
            card.setSpecialMonsterEnum(null);
        }
    }

    private void setAttribute(String attribute) {
        if (attribute.equals("EARTH")) {
            this.attribute = MonsterAttribute.EARTH;
        } else if (attribute.equals("WIND")) {
            this.attribute = MonsterAttribute.WIND;
        } else if (attribute.equals("WATER")) {
            this.attribute = MonsterAttribute.WATER;
        } else if (attribute.equals("DARK")) {
            this.attribute = MonsterAttribute.DARK;
        } else if (attribute.equals("FIRE")) {
            this.attribute = MonsterAttribute.FIRE;
        } else if (attribute.equals("LIGHT")) {
            this.attribute = MonsterAttribute.LIGHT;
        }
    }

    private void setType(String type) {
        if (type.equals("Beast-Warrior")) {
            this.type = MonsterType.Beast_Warrior;
        } else if (type.equals("Warrior")) {
            this.type = MonsterType.Warrior;
        } else if (type.equals("Aqua")) {
            this.type = MonsterType.Aqua;
        } else if (type.equals("Fiend")) {
            this.type = MonsterType.Fiend;
        } else if (type.equals("Beast")) {
            this.type = MonsterType.Beast;
        } else if (type.equals("Pyro")) {
            this.type = MonsterType.Pyro;
        } else if (type.equals("Spell Caster")) {
            this.type = MonsterType.SpellCaster;
        } else if (type.equals("Thunder")) {
            this.type = MonsterType.Thunder;
        } else if (type.equals("Dragon")) {
            this.type = MonsterType.Dragon;
        } else if (type.equals("Machine")) {
            this.type = MonsterType.Machine;
        } else if (type.equals("Rock")) {
            this.type = MonsterType.Rock;
        } else if (type.equals("Insect")) {
            this.type = MonsterType.Insect;
        } else if (type.equals("Cyberse")) {
            this.type = MonsterType.Cyberse;
        } else if (type.equals("Fairy")) {
            this.type = MonsterType.Fairy;
        } else if (type.equals("Sea Serpent")) {
            this.type = MonsterType.Sea_Serpent;
        }
    }

    private int setMonsterSpeed(MonsterCard card) {
        if (card.getSpecial() == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public MonsterCard clone() {
        return new MonsterCard(this.cardName, this.cardDescription, this.cardType, this.price, this.ownerUsername, this.speed,
                this.level, this.attribute, this.type, this.attack, this.defense, this.position, this.defenceMode,
                this.wasAttackedInThisTurn, this.specialMonsterEnum, this.special);
    }
}
