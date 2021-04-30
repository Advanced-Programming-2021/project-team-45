package model.card;

import model.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MonsterCard extends Card {
    protected int level;
    protected String attribute;
    protected String type;
    protected int attack;
    protected int defense;
    protected PositionMonsters position;
    protected DOorDH defenceMode;
    public MonsterCard(String cardName) throws IOException {
        super(cardName);
        String[] data=dataAboutAMonster(cardName);
        this.level=Integer.parseInt(data[1]);
        this.attribute=data[2];
        this.type=data[3];
        this.cardType=data[4];
        this.attack=Integer.parseInt(data[5]);
        this.defense=Integer.parseInt(data[6]);
        this.cardDescription=data[7];
        this.price=Integer.parseInt(data[8]);
    }

    public String[][] allDataAboutMonster()  {
        String[][] data = new String[42][9];
        try {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Hossein Mohammadi\\Desktop\\AP PROJECT MOLAYEE\\project-team-45\\src\\main\\java\\model\\card\\Monster.xlsx"));
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
        }catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String[] dataAboutAMonster(String cardNAme){
        String[][] data = allDataAboutMonster();
        int answer = 0;
        for (int i = 0; i < 42; i++) {
            if (data[i][0].equals(cardName)) {
                answer = i;
                break;
            }
        }
        if (answer == 0) {
            return new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1"};
        } else return data[answer];
    }


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
                card.owner.setLastDamageAmount(decreaseFromOpponentLifepoint);
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
                this.owner.setLastDamageAmount(decreaseFromAttacker);
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
                this.owner.setLastDamageAmount(decreaseFromAttacker);
            }
        }
    }

    public void attackOpponent(User Opponent){
        int newLifepoint=Opponent.getLifepoint().getLifepoint()-this.attack;
        if(newLifepoint>0) Opponent.getLifepoint().setLifepoint(newLifepoint);
        else Opponent.getLifepoint().setLifepoint(0);
        Opponent.setLastDamageAmount(this.attack);
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
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

    public DOorDH getDefenceMode() {
        return defenceMode;
    }
}
