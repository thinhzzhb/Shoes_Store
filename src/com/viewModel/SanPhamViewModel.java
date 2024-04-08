/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

import com.models.KhuyenMai;
import com.models.KichCo;
import com.models.MauSac;

/**
 *
 * @author Admin
 */
public class SanPhamViewModel {
   private String Ma;
   private String Ten;
   private MauSac mauSac;
   private KhuyenMai khuyenMai;
   private KichCo kichCo;
  
    private int SoLuongTon;
   private Double GiaBan;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(String Ma, String Ten, MauSac mauSac, KhuyenMai khuyenMai, KichCo kichCo, int SoLuongTon, Double GiaBan) {
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

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public KichCo getKichCo() {
        return kichCo;
    }

    public void setKichCo(KichCo kichCo) {
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

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    @Override
    public String toString() {
        return Ten;
    }


   
}
