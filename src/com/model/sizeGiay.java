/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Admin
 */
public class sizeGiay {
    private int idCO;
    private String co;

    public sizeGiay() {
    }

    public sizeGiay(int idCO, String co) {
        this.idCO = idCO;
        this.co = co;
    }

    public int getIdCO() {
        return idCO;
    }

    public void setIdCO(int idCO) {
        this.idCO = idCO;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    @Override
    public String toString() {
        return "sizeGiay{" + "idCO=" + idCO + ", co=" + co + '}';
    }
    
}
