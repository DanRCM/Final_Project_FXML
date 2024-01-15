/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.poo_project.controller;

import ec.edu.espol.poo_project.util.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.control.Slider;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author danrcm
 */
public class OptionsViewController implements Initializable {

    @FXML
    private VBox vbOp;
    @FXML
    private AnchorPane apOptions;
    @FXML
    private Slider slider;

    @FXML
    public void switchToMenu() throws IOException {
        App.setRoot("Menu", Utilitaria.widthWindow, Utilitaria.heightWindow);
    }

    private void volumeSetup() {
        MediaPlayer music = App.getMusicPlayer();
        music.volumeProperty().unbind(); //eliminar bind anterior
        slider.valueProperty().set(music.volumeProperty().get()); //poner slider acorde a volumen
        App.getMusicPlayer().volumeProperty().bind(slider.valueProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volumeSetup();
        Utilitaria.crearOpcionesBox();
        BackgroundImage backgroundImage = new BackgroundImage(Utilitaria.cargarImagen("/util/BackGrounds/BackgroundOptions.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(Utilitaria.widthWindow, Utilitaria.heightWindow, false, false, false, false));
        // Establecer el BackgroundImage en el AnchorPane
        apOptions.setBackground(new Background(backgroundImage));
        vbOp.getChildren().add(Utilitaria.crearOpcionesBox());
    }
}
