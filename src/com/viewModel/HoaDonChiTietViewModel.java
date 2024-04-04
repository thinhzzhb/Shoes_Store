/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

import com.model.HoaDon;
import com.model.KichCo;
import com.model.MauSac;
import com.model.SanPham_t;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietViewModel {
    private int Soluong;
    private Double DonGia;
    private HoaDon haDon;
    private SanPham_t sanPham;
    private MauSac mauSac;
    private KichCo kichCo;

    public HoaDonChiTietViewModel() {
    }

    public HoaDonChiTietViewModel(int Soluong, Double DonGia, HoaDon haDon, SanPham_t sanPham, MauSac mauSac, KichCo kichCo) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.haDon = haDon;
        this.sanPham = sanPham;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
    }

   

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public HoaDon getHoaDon() {
        return haDon;
    }

    public void setHoaDon(HoaDon haDon) {
        this.haDon = haDon;
    }

    public SanPham_t getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham_t sanPham) {
        this.sanPham = sanPham;
    }

    public double getThanhTien() {
        return DonGia * Soluong;
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
}
