/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.poo_project.model;

import ec.edu.espol.poo_project.util.Utilitaria;
import ec.edu.espol.poo_project.util.UtilitariaView;
import ec.edu.espol.poo_project.controller.App;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author CARLOS THOME
 */
public class Juego {

    private int turno;
    private ArrayList<Ficha> tablero;
    private ArrayList<Jugador> jugadores;

    public Juego(String nom) {
        tablero = new ArrayList<>();
        turno = (Math.random() < 0.5) ? 0 : 1;
        jugadores = new ArrayList<>();
        jugadores.add(new Jugador(Utilitaria.generarMano(), nom));
        jugadores.add(new Jugador(Utilitaria.generarMano(), "Computador"));
    }

    public int getTurno() {
        return turno;
    }

    public int getExtremeI() {
        return tablero.get(0).getLadoI();
    }

    public int getExtremeD() {
        return tablero.get(tablero.size() - 1).getLadoD();
    }

    public void pasarTurno() {
        this.turno = Math.abs(this.turno - 1);
    }

    public void actualizar(HBox mano0, HBox mano1, FlowPane tablero) {
        ImageView i;
        mano0.getChildren().clear();
        mano1.getChildren().clear();
        tablero.getChildren().clear();
        for (Ficha f : this.tablero) {
            i = new ImageView(new Image(f.getImg()));
            UtilitariaView.redimensionarFicha(i);
            tablero.getChildren().add(i);
        }
        for (Ficha f : this.jugadores.get(0).getMano()) {
            i = new ImageView(new Image(f.getImg()));
            UtilitariaView.redimensionarFicha(i);
            mano0.getChildren().add(i);
        }
        for (Ficha f : this.jugadores.get(1).getMano()) {
            i = new ImageView(new Image(f.getImg()));
            UtilitariaView.redimensionarFicha(i);
            mano1.getChildren().add(i);
        }
    }

    public static Ficha obtenerFichaClick(Node clickedNode) {
        ImageView clickedImageView = (ImageView) clickedNode;
        String imagePath = clickedImageView.getImage().getUrl();
        File imageFile = new File(imagePath);
        String imageName = imageFile.getName();
        String imageNameWithoutExtension = imageName.replace(".png", "");
        if (imageNameWithoutExtension.equals("comodin.gif")) {
            return new FichaComodin();
        }
        String[] values = imageNameWithoutExtension.split("_");
        String lI = values[0];
        String lD = values[1];
        return new Ficha(Integer.parseInt(lI), Integer.parseInt(lD), "/util/Fichas/" + lI + "_" + lD + ".png");
    }

    public boolean validarFichaJuego(Ficha ficha_jugada) {
        if (tablero.isEmpty()) {
            return true;
        }
        return ficha_jugada.getLadoD() == this.getExtremeI() || ficha_jugada.getLadoI() == this.getExtremeD();
    }

    public boolean gameOver() {
        int situacion = turno;
        for (Ficha f : jugadores.get(Math.abs(turno - 1)).getMano()) {
            if (f instanceof FichaComodin) {
                situacion = 2;
                break;
            }
            if (this.validarFichaJuego(f)) {
                situacion = 2;
                break;
            }
        }
        if (jugadores.get(turno).getMano().isEmpty()) {
            situacion = turno;
        }
        if (situacion != 2) {
            System.out.println("estamos aqui");
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Selecciona la posición");
            ButtonType ok = new ButtonType("Ok");
            alert.setHeaderText("Ganador: " + jugadores.get(this.getTurno()).getNombre());
            alert.getButtonTypes().setAll(ok);
            alert.showAndWait();
            try {
                App.setRoot("Menu",UtilitariaView.widthWindow , UtilitariaView.heightWindow);
            } catch (IOException e) {
            }
        }
        return situacion == 2;
    }

    public boolean jugarFicha(Ficha f) {
        if (tablero.isEmpty() && f instanceof FichaComodin) {
            int l2 = UtilitariaView.mostrarDialogoNumeros("Elegir el número del lado derecho");
            int l1 = UtilitariaView.mostrarDialogoNumeros("Elegir el número del lado izquierdo");
            tablero.add(new Ficha(l1,l2,"/util/Fichas/"+l1+"_"+l2+".png"));
            jugadores.get(turno).removerFicha(f);
            return true;
        }
        if (tablero.isEmpty()) {
            tablero.add(f);
            jugadores.get(turno).removerFicha(f);
            return true;
        }
        if (f instanceof FichaComodin) {
            String ubi = UtilitariaView.mostrarDialogoPosicion();
            if (ubi == null) {
                return false;
            }
            int l = UtilitariaView.mostrarDialogoNumeros("Elegir número comodin");
            if (l == -1) {
                return false;
            }
            jugadores.get(turno).removerFicha(f);
            if (ubi.equals("Izquierda")) {
                tablero.add(0, new Ficha(l, this.getExtremeI(), "/util/Fichas/" + l + "_" + this.getExtremeI() + ".png"));
            } else {
                tablero.add(new Ficha(this.getExtremeD(), l, "/util/Fichas/" + this.getExtremeD() + "_" + l + ".png"));
            }
            return true;
        }
        if (f.getLadoD() == this.getExtremeI() && f.getLadoI() == this.getExtremeD()) {
            String ubi = UtilitariaView.mostrarDialogoPosicion();
            if (ubi == null) {
                return false;
            }
            if (ubi.equals("Izquierda")) {
                tablero.add(0, f);
            } else {
                tablero.add(f);
            }
            jugadores.get(turno).removerFicha(f);
            return true;
        }
        if (f.getLadoD() == this.getExtremeI()) {
            tablero.add(0, f);
            jugadores.get(turno).removerFicha(f);
            System.out.println("wtf");
            return true;
        }
        if (f.getLadoI() == this.getExtremeD()) {
            tablero.add(f);
            jugadores.get(turno).removerFicha(f);
            System.out.println("wtff");
            return true;
        }
        return false;
    }

    public void jugarFichaComputer() {
        if (tablero.isEmpty()) {
            tablero.add(jugadores.get(1).getMano().get(0));
            jugadores.get(1).removerFicha(jugadores.get(1).getMano().get(0));
        } else {
            for (Ficha f : jugadores.get(1).getMano()) {
                if (f instanceof FichaComodin) {
                    jugadores.get(1).removerFicha(f);
                    int x =(int) (Math.random() * 7);
                    tablero.add(new Ficha(this.getExtremeD(), x, "/util/Fichas/" + this.getExtremeD() + "_" + x + ".png"));
                    break;
                }
                if (f.getLadoI() == this.getExtremeD()) {
                    tablero.add(f);
                    jugadores.get(1).removerFicha(f);
                    break;
                }
                if (f.getLadoD() == this.getExtremeI()) {
                    tablero.add(0, f);
                    jugadores.get(1).removerFicha(f);
                    break;
                }
            }
        }
    }
}
