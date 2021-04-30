package view.menu;

import controller.DatabaseController;
import controller.Regex;

import java.util.regex.Matcher;

public class ImportExportMenu extends Menu {

    private final DatabaseController databaseController;
    private final String[] IMPORT_EXPORT_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(import card \\w+)$|" +
                    "^(export card \\w+)$",
            // i = 1
            "import card (\\w+)",
            // i = 2
            "export card (\\w+)"
    };


    public ImportExportMenu(String username, Menu parentMenu) {
        super("Import/Export", parentMenu);
        setUsername(username);

        databaseController = new DatabaseController(username);
    }


    private void importCard(Matcher matcher) {
        if (matcher.find()) {
            String cardName = matcher.group(1);

            databaseController.importCard(cardName);
        }
    }

    private void exportCard(Matcher matcher) {
        if (matcher.find()) {
            String cardName = matcher.group(1);

            databaseController.exportCard(cardName);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void execute()  {
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, IMPORT_EXPORT_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    System.out.println("user logged out successfully!");
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                } else if (matcher.group(4) != null) {
                    importCard(Regex.getMatcher(input, IMPORT_EXPORT_MENU_REGEX[1]));

                } else if (matcher.group(5) != null) {
                    exportCard(Regex.getMatcher(input, IMPORT_EXPORT_MENU_REGEX[2]));

                }

            } else {
                System.out.println("invalid command");
            }
        }

        exitMenu();
    }

}
