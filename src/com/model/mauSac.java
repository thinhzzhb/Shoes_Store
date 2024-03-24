/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Admin
 */
public class mauSac {
    private int idMau;
    private String tenMau;

    public mauSac() {
    }

    public mauSac(int idMau, String tenMau) {
        this.idMau = idMau;
        this.tenMau = tenMau;
    }

    public int getIdMau() {
        return idMau;
    }

    public void setIdMau(int idMau) {
        this.idMau = idMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    @Override
    public String toString() {
        return "mauSac{" + "idMau=" + idMau + ", tenMau=" + tenMau + '}';
    }
    
    
    
}
