/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

/**
 *
 * @author Admin
 */
public class SanPhamViewModel {
    private String Ma;
   private String Ten;
   private int mauSac;
   private int khuyenMai;
   private int kichCo;
  
    private int SoLuongTon;
   private Double GiaBan;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(String Ma, String Ten, int mauSac, int khuyenMai, int kichCo, int SoLuongTon, Double GiaBan) {
        this.Ma = Ma;
        this.Ten = Ten;
        this.mauSac = mauSac;
        this.khuyenMai = khuyenMai;
        this.kichCo = kichCo;
        this.SoLuongTon = SoLuongTon;
        this.GiaBan = GiaBan;
    }

  

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public int getMauSac() {
        return mauSac;
    }

    public void setMauSac(int mauSac) {
        this.mauSac = mauSac;
    }

    public int getKichCo() {
        return kichCo;
    }

    public void setKichCo(int kichCo) {
        this.kichCo = kichCo;
    }

    

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public Double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    @Override
    public String toString() {
        return Ten;
    }
}
