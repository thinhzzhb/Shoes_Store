/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class User {

    private int id;
    private String Ten;
    private String TenDem;
    private String Ho;
    private Date NgaySinh;
    private Boolean GioTinh;
    private String Sdt;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private String maNV;
    private String diaChi;
    private int TrangThai;
    private int vaiTro;

    public User() {
    }

    public User(int id, String Ten, String TenDem, String Ho, Date NgaySinh, Boolean GioTinh, String Sdt, String TaiKhoan, String MatKhau, String Email, int TrangThai, Integer vaiTro) {
        this.Ten = Ten;
        this.TenDem = TenDem;
        this.Ho = Ho;
        this.NgaySinh = NgaySinh;
        this.GioTinh = GioTinh;
        this.Sdt = Sdt;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.TrangThai = TrangThai;
        this.id = id;
        this.vaiTro = vaiTro;
    }

    public User(int id, String Ten, String TenDem, String Ho, Date NgaySinh, Boolean GioTinh, String Sdt, String TaiKhoan, String MatKhau, String Email, String maNV, String diaChi, int TrangThai, int vaiTro) {
        this.id = id;
        this.Ten = Ten;
        this.TenDem = TenDem;
        this.Ho = Ho;
        this.NgaySinh = NgaySinh;
        this.GioTinh = GioTinh;
        this.Sdt = Sdt;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.maNV = maNV;
        this.diaChi = diaChi;
        this.TrangThai = TrangThai;
        this.vaiTro = vaiTro;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getTenDem() {
        return TenDem;
    }

    public void setTenDem(String TenDem) {
        this.TenDem = TenDem;
    }

    public String getHo() {
        return Ho;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public Boolean getGioTinh() {
        return GioTinh;
    }

    public void setGioTinh(Boolean GioTinh) {
        this.GioTinh = GioTinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
