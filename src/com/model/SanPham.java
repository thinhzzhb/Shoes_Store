/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Admin
 */
public class SanPham {
    private int idSP;
    private String tenSP;

    public SanPham(int idSP, String tenSP) {
        this.idSP = idSP;
        this.tenSP = tenSP;
    }

    public SanPham() {
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    @Override
    public String toString() {
        return "SanPham{" + "idSP=" + idSP + ", tenSP=" + tenSP + '}';
    }
    
}
