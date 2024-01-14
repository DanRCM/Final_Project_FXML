/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.poo_project.model;

import java.util.Objects;

/**
 *
 * @author CARLOS THOME
 */
public class Ficha {
    private int ladoI;
    private int ladoD;
    private String img;

    public Ficha(int ladoI, int ladoD, String img) {
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

    public String getImg() {
        return img;
    }

    public void setLadoI(int ladoI) {
        this.ladoI = ladoI;
    }

    public void setLadoD(int ladoD) {
        this.ladoD = ladoD;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        return Objects.equals(this.img, other.img);
    }
    
}
