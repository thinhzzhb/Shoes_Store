/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class ChiTietSP {
    private String ma;
    private int idNsx;
    private int idMauSac;
    private int idDanhMuc;
    private int idKichCo;
    private int idKhuyenMai;
    private int soLuongTon;
    private String moTa;
    private String qrCode;

    public ChiTietSP() {
    }

    public ChiTietSP(String ma, int idNsx, int idMauSac, int idDanhMuc, int idKichCo, int idKhuyenMai, int soLuongTon, String moTa, String qrCode) {
        this.ma = ma;
        this.idNsx = idNsx;
        this.idMauSac = idMauSac;
        this.idDanhMuc = idDanhMuc;
        this.idKichCo = idKichCo;
        this.idKhuyenMai = idKhuyenMai;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.qrCode = qrCode;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getIdNsx() {
        return idNsx;
    }

    public void setIdNsx(int idNsx) {
        this.idNsx = idNsx;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public int getIdKichCo() {
        return idKichCo;
    }

    public void setIdKichCo(int idKichCo) {
        this.idKichCo = idKichCo;
    }

    public int getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(int idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return ma;
    }

    
    
    
    
    
    
    
}
