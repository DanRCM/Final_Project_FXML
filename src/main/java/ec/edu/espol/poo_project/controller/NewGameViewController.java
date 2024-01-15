package ec.edu.espol.poo_project.controller;

import ec.edu.espol.poo_project.model.Ficha;
import ec.edu.espol.poo_project.model.Juego;
import ec.edu.espol.poo_project.util.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NewGameViewController implements Initializable {

    Juego juego;
    @FXML
    private HBox mano0;
    @FXML
    private HBox mano1;
    @FXML
    private FlowPane tablero;
    @FXML
    private AnchorPane apNewGameView;
    @FXML
    private GridPane gp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gp.setPrefWidth(Utilitaria.widthWindow);
        gp.setPrefHeight(Utilitaria.heightWindow);
        BackgroundImage backgroundImage = new BackgroundImage(Utilitaria.cargarImagen("/util/BackGrounds/BackgroundNewGame 1.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(Utilitaria.widthWindow, Utilitaria.heightWindow, false, false, false, false));
        apNewGameView.setBackground(new Background(backgroundImage));
        juego = new Juego(Utilitaria.obtenerNombreDialogo());
        juego.actualizar(mano0, mano1, tablero);
        if (juego.getTurno() == 1) {
            juego.jugarFichaComputer();
            juego.actualizar(mano0, mano1, tablero);
            juego.gameOver();
            juego.pasarTurno();
        }
    }

    @FXML
    private void select0(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode instanceof ImageView && juego.getTurno() == 0) {
            Ficha ficha_jugada = Juego.obtenerFichaClick(clickedNode);
            if (juego.jugarFicha(ficha_jugada)) {
                juego.actualizar(mano0, mano1, tablero);
                if (juego.gameOver()) {
                    juego.pasarTurno();
                    juego.jugarFichaComputer();
                    juego.actualizar(mano0, mano1, tablero);
                    juego.gameOver();
                    juego.pasarTurno();
                }
            }
        }
    }

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        App.setRoot("Menu", Utilitaria.widthWindow, Utilitaria.heightWindow);
    }
}
