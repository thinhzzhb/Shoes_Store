/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.impl;

import com.model.HoaDon;
import com.model.HoaDonChiTiet;
import com.repository.HoaDonRepository;
import com.service.IHoaDonService;
import com.viewModel.HoaDonChiTietViewModel;
import com.viewModel.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService implements IHoaDonService{
    
    private HoaDonRepository repo;

    public HoaDonService() {
        this.repo = new HoaDonRepository();
    }
    
    @Override
    public List<HoaDonViewModel> getListHoaDon() {
        List<HoaDonViewModel> _lstHoaDon = new ArrayList<>();
        
        List<HoaDon> _lst = repo.GetAllHD();
        for (HoaDon hd : _lst) {
            _lstHoaDon.add( new HoaDonViewModel(hd.getKhachHang(), hd.getUser(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(), hd.getTinhTrang(), hd.getGhichu(), hd.getTongTien()));
        }
        return _lstHoaDon;
    }

    @Override
    public List<HoaDonViewModel> getHoaDonByTen(String name) {
        List<HoaDonViewModel> listHDbyTen = new ArrayList<>();
        
        List<HoaDon> _lstHd = repo.getHDTen(name);
        for (HoaDon hd : _lstHd) {
            listHDbyTen.add(new HoaDonViewModel(hd.getKhachHang(), hd.getUser(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(), hd.getTinhTrang(), hd.getGhichu(), hd.getTongTien()));
        }
        return listHDbyTen;
    }

    @Override
    public List<HoaDonViewModel> TimKiemTheoTTHoaDon(int trangThai) {
        List<HoaDonViewModel> _listByTrangThai = new ArrayList<>();
        
        List<HoaDon> listHdTT = repo.getHDTrangThai(trangThai);
        
        for (HoaDon hd : listHdTT) {
            _listByTrangThai.add(new HoaDonViewModel(hd.getKhachHang(), hd.getUser(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(), hd.getTinhTrang(), hd.getGhichu(), hd.getTongTien()));
        }
        return _listByTrangThai;
    }

    @Override
    public List<HoaDonViewModel> GetTimNTT(String NgayTT) {
        List<HoaDonViewModel> list07 = new ArrayList<>();

        List<HoaDon> Listtm = repo.GetTimNTT(NgayTT);
        for (HoaDon x : Listtm) {
            list07.add(new HoaDonViewModel(x.getKhachHang(), x.getUser(), x.getMa(), x.getNgayTao(), x.getNgayThanhToan(), x.getTinhTrang(), x.getGhichu(), x.getTongTien()));
        }
        return list07;
    }

    @Override
    public List<HoaDonChiTietViewModel> getHDCT(String ma) {
        List<HoaDonChiTietViewModel> listHDCT = new ArrayList<>();

        List<HoaDonChiTiet> ListHDByMa = repo.getHDCTbyMa(ma);
        for (HoaDonChiTiet t : ListHDByMa) {
            listHDCT.add(new HoaDonChiTietViewModel(t.getSoluong(), t.getDonGia(), t.getHaoDon(), t.getSanPham(), t.getMauSac(), t.getKichCo()));
        }
        return listHDCT;
    }

    
    
}
