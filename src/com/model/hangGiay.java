/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Admin
 */
public class hangGiay {

    private int idHang;

    private String tenHang;

    public hangGiay() {
    }

    public hangGiay(int idHang, String tenHang) {
        this.idHang = idHang;
        this.tenHang = tenHang;
    }

    public hangGiay(int idHang) {
        this.idHang = idHang;
    }

    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
        this.idHang = idHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    @Override
    public String toString() {
        return tenHang;
    }

}
