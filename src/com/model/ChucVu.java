/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Admin
 */
public class ChucVu {
    private int IdCv;
    private String MaCv;
    private String TenCv;
    private TaiKhoan idTk;

    public ChucVu() {
    }

    public ChucVu(int IdCv, String MaCv, String TenCv, TaiKhoan idTk) {
        this.IdCv = IdCv;
        this.MaCv = MaCv;
        this.TenCv = TenCv;
        this.idTk = idTk;
    }

    public int getIdCv() {
        return IdCv;
    }

    public void setIdCv(int IdCv) {
        this.IdCv = IdCv;
    }

    public String getMaCv() {
        return MaCv;
    }

    public void setMaCv(String MaCv) {
        this.MaCv = MaCv;
    }

    public String getTenCv() {
        return TenCv;
    }

    public void setTenCv(String TenCv) {
        this.TenCv = TenCv;
    }

    public TaiKhoan getIdTk() {
        return idTk;
    }

    public void setIdTk(TaiKhoan idTk) {
        this.idTk = idTk;
    }

    @Override
    public String toString() {
        return "ChucVu{" + "IdCv=" + IdCv + ", MaCv=" + MaCv + ", TenCv=" + TenCv + ", idTk=" + idTk + '}';
    }
    
}
