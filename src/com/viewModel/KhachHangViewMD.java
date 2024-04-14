/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHangViewMD {
     private int id;
    private String ten;
    private String tendem;
    private String ho;
    private String email;
    private String sdt;

    public KhachHangViewMD() {
    }

    public KhachHangViewMD(int id, String ten, String tendem, String ho, String email, String sdt) {
        this.id = id;
        this.ten = ten;
        this.tendem = tendem;
        this.ho = ho;
        this.email = email;
        this.sdt = sdt;
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

    public String getTendem() {
        return tendem;
    }

    public void setTendem(String tendem) {
        this.tendem = tendem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    

    public Object[] toDataRow() {
        return new Object[]{id, ho + " " + tendem + " " + ten, sdt, email};

    }

}
