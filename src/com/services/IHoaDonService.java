/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.services;

import com.models.HoaDon;
import com.viewModel.HoaDonChiTietViewModel;
import com.viewModel.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonService {

    List<HoaDonViewModel> getListHoaDon();

    List<HoaDonViewModel> getHoaDonByTen(String name);

    List<HoaDonViewModel> TimKiemTheoTTHoaDon(int trangThai);

    List<HoaDonViewModel> GetTimNTT(String NgayTT);

    List<HoaDonChiTietViewModel> getHDCT(String ma);

    List<HoaDonViewModel> getListHD(int TrangThai);

    Integer saveHD(HoaDonViewModel hoaDon, int idNV);

    List<HoaDonChiTietViewModel> getListHoaDonChiTiet(String MaHD);

    Integer saveHDCT(HoaDonChiTietViewModel hoaDonChiTiet, String MaSP, String MaHD);

    Integer updateTrangThaiHoaDon(HoaDonViewModel hd);

    Integer updateSoLuongGioHang(int SoLuong, String MaSP, String MaHD);

    Integer getIdHD(String MaHD);

    Integer deleteSanPham(int idHD, int idSP);

    Integer clearSanPhamTrenGioHang(int idHD);
}
