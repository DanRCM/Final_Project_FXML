/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.poo_project.model;

import ec.edu.espol.poo_project.util.Utilitaria;
import javafx.scene.image.ImageView;

/**
 *
 * @author CARLOS THOME
 */
public class Ficha {
    private int ladoI;
    private int ladoD;
    private ImageView img;

    public Ficha(int ladoI, int ladoD, ImageView img) {
        this.ladoI = ladoI;
        this.ladoD = ladoD;
        this.img = img;
    }
    
    public int getLadoI() {
        return ladoI;
    }

    public int getLadoD() {
        return ladoD;
    }

    public ImageView getImg() {
        return img;
    }

    public void setLadoI(int ladoI) {
        this.ladoI = ladoI;
    }

    public void setLadoD(int ladoD) {
        this.ladoD = ladoD;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        if (this.ladoI != other.ladoI) {
            return false;
        }
        return this.ladoD == other.ladoD;
    }

    @Override
    public String toString() {
        return "Ficha{" + "ladoI=" + ladoI + ", ladoD=" + ladoD + ", img=" + img.getImage().getUrl() + '}';
    }


    
}
