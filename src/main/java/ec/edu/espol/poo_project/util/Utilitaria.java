/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ec.edu.espol.poo_project.util;

import ec.edu.espol.poo_project.model.Ficha;
import ec.edu.espol.poo_project.model.FichaComodin;
import java.util.ArrayList;

/**
 *
 * @author CARLOS THOME
 */
public class Utilitaria {
    public static ArrayList<Ficha> generarMano() {
        ArrayList<Ficha> mano = new ArrayList<>();
        int l1, l2;
        for (int i = 0; i < 5; i++) {
            l1 = (int) (Math.random() * 7);
            l2 = (int) (Math.random() * 7);
            mano.add(new Ficha(l1, l2, "/util/Fichas/" + l1 + "_" + l2 + ".png"));
        }
        mano.add(new FichaComodin());
        return mano;
    }
}
