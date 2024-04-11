/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

import com.models.DanhMucSP;
import com.models.KhuyenMai;
import com.models.KichCo;
import com.models.MauSac;
import com.models.NSX;

/**
 *
 * @author user
 */
public class ChiTietSPViewModel {
    private String ma;
    private NSX nsx;
    private MauSac mausac;
    private DanhMucSP danhmuc;
    private KichCo kichco;
    private KhuyenMai khuyenmai;
    private int soluongton;
    private String mota;
    private String qrcode;

    public ChiTietSPViewModel() {
    }

    public ChiTietSPViewModel(String ma, NSX nsx, MauSac mausac, DanhMucSP danhmuc, KichCo kichco, KhuyenMai khuyenmai, int soluongton, String mota, String qrcode) {
        this.ma = ma;
        this.nsx = nsx;
        this.mausac = mausac;
        this.danhmuc = danhmuc;
        this.kichco = kichco;
        this.khuyenmai = khuyenmai;
        this.soluongton = soluongton;
        this.mota = mota;
        this.qrcode = qrcode;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }


    public NSX getNsx() {
        return nsx;
    }

    public void setNsx(NSX nsx) {
        this.nsx = nsx;
    }

    public MauSac getMausac() {
        return mausac;
    }

    public void setMausac(MauSac mausac) {
        this.mausac = mausac;
    }

    public DanhMucSP getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(DanhMucSP danhmuc) {
        this.danhmuc = danhmuc;
    }

    public KichCo getKichco() {
        return kichco;
    }

    public void setKichco(KichCo kichco) {
        this.kichco = kichco;
    }

    public KhuyenMai getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(KhuyenMai khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    
    
    
}
