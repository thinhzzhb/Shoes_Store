/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private Integer id;
    private String MaKH;
    private String ten;
    private String tenDem;
    private String ho;
    private String sdt;
    private String diaChi;
    private String email;
    

    public KhachHang() {
    }

    public KhachHang(Integer id, String MaKH, String ten, String tenDem, String ho, String sdt, String diaChi,String email) {
        this.id = id;
        this.MaKH = MaKH;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    
   
}
