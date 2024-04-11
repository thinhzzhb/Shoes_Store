/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

/**
 *
 * @author Admin
 */
public class ThongKe {

    private double doanhthu, von, loinhuan;
    private Users users;
    private int soSP;
    private ChiTietSP chiTietSP;
    private SanPham sp;

    public ThongKe() {
    }

    public ThongKe(double doanhthu) {
        this.doanhthu = doanhthu;
    }

    public ThongKe(int soSP) {
        this.soSP = soSP;
    }

    public ThongKe(Users users, int soSP) {
        this.users = users;
        this.soSP = soSP;
    }

    public ThongKe(int soSP, SanPham sp) {
        this.soSP = soSP;
        this.sp = sp;
    }

    public ThongKe(int soSP, ChiTietSP chiTietSP) {
        this.soSP = soSP;
        this.chiTietSP = chiTietSP;
    }

    public double getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(double doanhthu) {
        this.doanhthu = doanhthu;
    }

    public double getVon() {
        return von;
    }

    public void setVon(double von) {
        this.von = von;
    }

    public double getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(double loinhuan) {
        this.loinhuan = loinhuan;
    }

    @Override
    public String toString() {
        return "Thongke{" + "doanhthu=" + doanhthu + ", von=" + von + ", loinhuan=" + loinhuan + '}';
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getSoSP() {
        return soSP;
    }

    public void setSoSP(int soSP) {
        this.soSP = soSP;
    }

    public ChiTietSP getChiTietSP() {
        return chiTietSP;
    }

    public void setChiTietSP(ChiTietSP chiTietSP) {
        this.chiTietSP = chiTietSP;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

}
