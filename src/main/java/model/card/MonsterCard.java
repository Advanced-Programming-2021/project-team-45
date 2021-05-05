package model.card;

import model.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MonsterCard extends Card {
    protected int level;
    protected MonsterAttribute attribute;
    protected String type;
    protected int attack;
    protected int defense;
    protected PositionMonsters position;
    protected DefensePosition defenceMode;
    private boolean wasAttackedInThisTurn = false;
    private SpecialMonsterEnum specialMonsterEnum;

    //////////////////////// set enum in constructor

    private final SpecialMonsterEnum special = null;

    public MonsterCard(String cardName) throws IOException {
        super(cardName);
        String[] data = dataAboutAMonster(cardName);
        this.level = (int) Double.parseDouble(data[1]);
        setAttribute(data[2]);
        this.type = data[3];
        this.cardType = data[4];
        this.attack = (int) Double.parseDouble(data[5]);
        this.defense = (int) Double.parseDouble(data[6]);
        this.cardDescription = data[7];
        this.price = (int) Double.parseDouble(data[8]);
        checkSpecialMonster(this);
    }

    public static String[][] allDataAboutMonster() {
        String[][] data = new String[42][9];
        try {
            File initialFile = new File("src/main/resources/Monster.xlsx");
            FileInputStream inputStream = new FileInputStream(initialFile);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            int a = 0;
            int b = 0;
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            data[a][b] = (cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            data[a][b] = (String.valueOf(cell.getNumericCellValue()));
                            break;
                    }
                    b++;
                }
                a++;
                b = 0;
            }
            workbook.close();
            inputStream.close();
            return data;
        } catch (IOException e) {
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
                int newLifepoint = card.owner.getLifepoint().getLifepoint() - decreaseFromOpponentLifepoint;
                card.owner.getLifepoint().setLifepoint(newLifepoint);
                card.owner.setLastDamageAmount(decreaseFromOpponentLifepoint);

            } else if (this.attack < card.attack) {
                int decreaseFromAttacker = card.attack - this.attack;
                int newLifepoint = this.owner.getLifepoint().getLifepoint() - decreaseFromAttacker;
                this.owner.getLifepoint().setLifepoint(newLifepoint);
                this.owner.setLastDamageAmount(decreaseFromAttacker);
            }
        } else {
            if (this.attack < card.defense) {
                int decreaseFromAttacker = card.defense - this.attack;
                int newLifepoint = this.owner.getLifepoint().getLifepoint() - decreaseFromAttacker;
                this.owner.getLifepoint().setLifepoint(newLifepoint);
                this.owner.setLastDamageAmount(decreaseFromAttacker);
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

    public MonsterAttribute getAttribute() {
        return attribute;
    }

    public String getType() {
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

    @Override
    public MonsterCard clone() {
        try {
            return new MonsterCard(this.getCardName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
