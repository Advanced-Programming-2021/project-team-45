module project.team {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires opencsv;
    requires javafx.graphics;
    requires java.activation;

    opens view.gui to javafx.fxml;
    exports view.gui;
}