/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.poo_project.model;

import ec.edu.espol.poo_project.util.Utilitaria;
import ec.edu.espol.poo_project.controller.App;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private static ArrayList<Ficha> fichasJuego;
    private ArrayList<Jugador> jugadores;

    public Juego(String nom) {
        tablero = new ArrayList<>();
        fichasJuego = new ArrayList<>();
        System.out.println("Aqui1");
        fichasJuego.addAll(Utilitaria.fichasExistentes);
        System.out.println("Aqui2");
        turno = (Math.random() < 0.5) ? 0 : 1; // elegir aleatoriamente el turno
        jugadores = new ArrayList<>();
        jugadores.add(new Jugador(Utilitaria.generarMano(), nom));
        jugadores.add(new Jugador(Utilitaria.generarMano(), "Computador"));
        System.out.println(jugadores.get(0).getMano());
    }

    public int getTurno() {
        return turno;
    }

    public int getLadoExtremeIz() {
        return tablero.get(0).getLadoI();
    }

    public int getLadoExtremeDer() {
        return tablero.get(tablero.size() - 1).getLadoD();
    }

    public void pasarTurno() {
        this.turno = Math.abs(this.turno - 1);
    }

    public static Ficha buscarFicha(int ladoI, int ladoD) {
        for (Ficha f : Juego.fichasJuego) {
            if (ladoD == f.getLadoD()) {
                if (ladoI == f.getLadoI()) {
                    Juego.fichasJuego.remove(f);
                    return f;
                }
            }
        }
        return null;
    }

    public void actualizar(HBox mano0, HBox mano1, FlowPane tablero) {
        mano0.getChildren().clear();
        mano1.getChildren().clear();
        tablero.getChildren().clear();
        for (Ficha f : this.tablero) {
            tablero.getChildren().add(f.getImg());
        }

        for (Ficha f : this.jugadores.get(0).getMano()) {
            mano0.getChildren().add(f.getImg());
        }

        for (Ficha f : this.jugadores.get(1).getMano()) {
            mano1.getChildren().add(f.getImg());
        }
    }

    public static Ficha obtenerFichaClick(Node clickedNode) {
        if (clickedNode instanceof ImageView) {
            ImageView img = (ImageView) clickedNode;
            System.out.println(img.getImage().getUrl());
            String[] values = img.getImage().getUrl().split("/")[img.getImage().getUrl().split("/").length - 1].replace(".png", "").split("_");
            if (values[0].contains(".gif")) {
                return Juego.buscarFicha(-1, -1);
            }
            return Juego.buscarFicha(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        }
        return null;
    }

    public boolean validarFichaJuego(Ficha ficha_jugada) {
        if (tablero.isEmpty()) {
            return true;
        }
        return ficha_jugada.getLadoD() == this.getLadoExtremeIz() || ficha_jugada.getLadoI() == this.getLadoExtremeDer();
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
                App.setRoot("Menu", Utilitaria.widthWindow, Utilitaria.heightWindow);
            } catch (IOException e) {
            }
        }
        return situacion == 2;
    }

    public boolean jugarFicha(Ficha f) {
        if (tablero.isEmpty() && f instanceof FichaComodin) {
            int ladoI = Utilitaria.obtenerNumeroDialogo("Elegir el número del lado izquierdo");
            int ladoD = Utilitaria.obtenerNumeroDialogo("Elegir el número del lado derecho");
            tablero.add(Juego.buscarFicha(ladoI, ladoD));
            jugadores.get(turno).removerFicha(f);
            return true;
        }
        if (tablero.isEmpty()) {
            tablero.add(f);
            jugadores.get(turno).removerFicha(f);
            return true;
        }
        if (f instanceof FichaComodin) {
            String posicion = Utilitaria.obtenerPosicionDialogo();
            if (posicion == null) {
                return false;
            }
            int lado = Utilitaria.obtenerNumeroDialogo("Elegir número comodin");
            if (lado == -1) {
                return false;
            }
            jugadores.get(turno).removerFicha(f);
            if (posicion.equals("Izquierda")) {
                tablero.add(0, Juego.buscarFicha(lado, this.getLadoExtremeIz()));
            } else {
                tablero.add(Juego.buscarFicha(this.getLadoExtremeDer(), lado));
            }
            return true;
        }
        if (f.getLadoD() == this.getLadoExtremeIz() && f.getLadoI() == this.getLadoExtremeDer()) {
            String ubi = Utilitaria.obtenerPosicionDialogo();
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
        if (f.getLadoD() == this.getLadoExtremeIz()) {
            tablero.add(0, f);
            jugadores.get(turno).removerFicha(f);
            return true;
        }
        if (f.getLadoI() == this.getLadoExtremeDer()) {
            tablero.add(f);
            jugadores.get(turno).removerFicha(f);
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
                    int lado = (int) (Math.random() * 7);
                    tablero.add(Juego.buscarFicha(this.getLadoExtremeDer(), lado));
                    break;
                }
                if (f.getLadoI() == this.getLadoExtremeDer()) {
                    tablero.add(f);
                    jugadores.get(1).removerFicha(f);
                    break;
                }
                if (f.getLadoD() == this.getLadoExtremeIz()) {
                    tablero.add(0, f);
                    jugadores.get(1).removerFicha(f);
                    break;
                }
            }
        }
    }
}
