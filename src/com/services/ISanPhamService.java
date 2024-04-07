/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.services;

import com.models.SanPham;
import com.viewModel.SanPhamViewModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {

    List<SanPhamViewModel> getListSanPham();

    boolean updateSoLuongSP(String Masp, int SoLuong);

    Integer getIdSanPham(String MaSP);

    List<SanPham> seachSanPham(String Ten);

    List<SanPham> seachBarCodeS(String barcode);

    List<SanPhamViewModel> locTheoDanhMucSP(String TenDanhMuc);
}
