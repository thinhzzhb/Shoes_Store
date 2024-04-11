/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

/**
 *
 * @author Admin
 */
public class objectSp {
    private int id;
    private String ten;
    private double giaNhap;
    private double giaBan;
    
    public objectSp() {
    }

    public objectSp(int id, String ten, double giaNhap, double giaBan) {
        this.id = id;
        this.ten = ten;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}
