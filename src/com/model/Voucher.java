/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Voucher {
    private int idVoucher;
    private String maKM;
    private String tenKM;
    private int mucGiam;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int tinhTrang;
    private SanPham idSP;
    public Voucher() {
    }

    public Voucher(int idVoucher, String maKM, String tenKM, int mucGiam, Date ngayBatDau, Date ngayKetThuc, int tinhTrang, SanPham idSP) {
        this.idVoucher = idVoucher;
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.mucGiam = mucGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tinhTrang = tinhTrang;
        this.idSP = idSP;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public int getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(int mucGiam) {
        this.mucGiam = mucGiam;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public SanPham getIdSP() {
        return idSP;
    }

    public void setIdSP(SanPham idSP) {
        this.idSP = idSP;
    }

    @Override
    public String toString() {
        return "Voucher{" + "idVoucher=" + idVoucher + ", maKM=" + maKM + ", tenKM=" + tenKM + ", mucGiam=" + mucGiam + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", tinhTrang=" + tinhTrang + ", idSP=" + idSP + '}';
    }

    
    
    
    public Object[] toDataRow() {
        return new Object[]{maKM, tenKM, ngayBatDau, ngayKetThuc, mucGiam, idSP.getTenSP(), tinhTrang == 1?"Hoạt động":"Dừng hoạt động"};
    }
    
}
