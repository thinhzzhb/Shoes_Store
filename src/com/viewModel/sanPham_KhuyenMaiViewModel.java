  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

import com.model.hangGiay;
import com.model.mauSac;
import com.model.sizeGiay;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class sanPham_KhuyenMaiViewModel {

    private String tenSP;

    private sizeGiay idsize;

    private mauSac idMau;

    private hangGiay idHang;

    private BigDecimal giaTien;

    public sanPham_KhuyenMaiViewModel() {
    }

    public sanPham_KhuyenMaiViewModel(String tenSP, sizeGiay idsize, mauSac idMau, hangGiay idHang, BigDecimal giaTien) {
        this.tenSP = tenSP;
        this.idsize = idsize;
        this.idMau = idMau;
        this.idHang = idHang;
        this.giaTien = giaTien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public sizeGiay getIdsize() {
        return idsize;
    }

    public void setIdsize(sizeGiay idsize) {
        this.idsize = idsize;
    }

    public mauSac getIdMau() {
        return idMau;
    }

    public void setIdMau(mauSac idMau) {
        this.idMau = idMau;
    }

    public hangGiay getIdHang() {
        return idHang;
    }

    public void setIdHang(hangGiay idHang) {
        this.idHang = idHang;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    @Override
    public String toString() {
        return "sanPham_KhuyenMaiViewModel{" + "tenSP=" + tenSP + ", idsize=" + idsize + ", idMau=" + idMau + ", idHang=" + idHang + ", giaTien=" + giaTien + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{tenSP, idsize.getCo(), idMau.getTenMau(), idHang.getTenHang(), giaTien};
    }
}
