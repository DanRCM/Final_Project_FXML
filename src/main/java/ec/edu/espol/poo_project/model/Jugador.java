/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.poo_project.model;

import java.util.ArrayList;

/**
 *
 * @author CARLOS THOME
 */
public class Jugador {
    private String nombre;
    ArrayList<Ficha> mano;

    public Jugador(ArrayList<Ficha> mano, String nombre) {
        this.mano = mano;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public ArrayList<Ficha> getMano() {
        return mano;
    }
    
    public void removerFicha(Ficha ficha) {
        mano.remove(ficha);
    }
}
