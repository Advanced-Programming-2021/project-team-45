module project.team {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires opencsv;

    opens view.gui to javafx.fxml;
    exports view.gui;
}