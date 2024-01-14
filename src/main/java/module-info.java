module ec.edu.espol.poo_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.poo_project.controller to javafx.fxml;
    exports ec.edu.espol.poo_project.controller;
    requires javafx.mediaEmpty;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.swingEmpty;
}
