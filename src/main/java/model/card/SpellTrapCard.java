package model.card;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class SpellTrapCard extends Card {

    protected SpellAndTrapIcon icon;
    protected boolean activated;
    protected String type;
    protected boolean isSpell;
    protected String status;
    protected SpellsAndTrapPosition position;

    public SpellTrapCard(String cardName) {
        super(cardName);
        String[] data = dataAboutASpellOrTrap(cardName);
        this.type = data[1];
        setSpellAndTrapIcon(data[2]);
        this.cardDescription = data[3];
        this.status = data[4];
        this.price = (int) Double.parseDouble(data[5]);
        this.isSpell = isASpell(this);
    }

    public static String[][] allDataAboutSpellTrap() {
        String[][] data = new String[36][6];
        try {
            File initialFile = new File("src/main/resources/SpellTrap.xlsx");
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

    private boolean isASpell(SpellTrapCard card) {
        return !this.type.equals("Trap");
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

    @Override
    public SpellTrapCard clone() {
        return new SpellTrapCard(this.getCardName());
    }
}
