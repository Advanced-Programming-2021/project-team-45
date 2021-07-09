 module project.team {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires opencsv;
    requires javafx.graphics;
    requires java.activation;
    requires org.apache.commons.io;
    requires yagson;
     requires javafx.media;

     opens Client.view to javafx.fxml;
    exports Client.view;
}