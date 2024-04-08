/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.HoaDon;
import com.models.HoaDonChiTiet;
import com.models.SanPham;
import com.repositories.impl.HoaDonRepository;
import com.services.IHoaDonService;
import com.services.ISanPhamService;
import com.viewModel.HoaDonChiTietViewModel;
import com.viewModel.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService implements IHoaDonService {

    private HoaDonRepository repo;
    private ISanPhamService spSer;
    List<HoaDonChiTietViewModel> getListHDCT;
    List<HoaDonViewModel> _lstHDTT;
    public HoaDonService() {
        this.repo = new HoaDonRepository();
        this.spSer = new SanPhamService();
        getListHDCT = new ArrayList<>();
        _lstHDTT = new ArrayList<>();
    }

    @Override
    public List<HoaDonViewModel> getListHoaDon() {
        List<HoaDonViewModel> _lstHoaDon = new ArrayList<>();

        List<HoaDon> _lst = repo.GetAllHD();
        for (HoaDon hd : _lst) {
            _lstHoaDon.add(new HoaDonViewModel(hd.getKhachHang(), hd.getUser(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(), hd.getTinhTrang(), hd.getGhichu(), hd.getTongTien()));
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
            listHDCT.add(new HoaDonChiTietViewModel(t.getSoluong(), t.getDonGia(), t.getHoaDon(), t.getSanPham(), t.getMauSac(), t.getKichCo()));
        }
        return listHDCT;
    }

    @Override
    public List<HoaDonViewModel> getListHD(int TrangThai) {
        List<HoaDon> list = repo.getListHD(TrangThai);
        for (HoaDon hoaDon : list) {
            HoaDonViewModel hd = new HoaDonViewModel();
            hd.setMa(hoaDon.getMa());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setTinhTrang(hoaDon.getTinhTrang());
            hd.setUs(hoaDon.getUser());
            _lstHDTT.add(hd);
        }
        return _lstHDTT;
//       
    }

    @Override
    public Integer saveHD(HoaDonViewModel hoaDon, int idNV) {
        HoaDon h = new HoaDon();
        h.setMa(hoaDon.getMa());
        h.setNgayTao(hoaDon.getNgayTao());
        h.setTinhTrang(0);

        Integer insertHD = repo.insertHoaDon(h, idNV);

        return insertHD;
    }

    @Override
    public List<HoaDonChiTietViewModel> getListHoaDonChiTiet(String MaHD) {
        getListHDCT = new ArrayList<>();
        List<HoaDonChiTiet> list = repo.getListHoaDonChiTiet(MaHD);
        for (HoaDonChiTiet hoaDonChiTiet : list) {
            HoaDonChiTietViewModel hd = new HoaDonChiTietViewModel();
            hd.setDonGia(hoaDonChiTiet.getDonGia());
            hd.setSoluong(hoaDonChiTiet.getSoluong());

            hd.setMauSac(hoaDonChiTiet.getMauSac());
            hd.setKichCo(hoaDonChiTiet.getKichCo());

            hd.setSanPham(hoaDonChiTiet.getSanPham());
            getListHDCT.add(hd);
        }
        return getListHDCT;
    }

    @Override
    public Integer saveHDCT(HoaDonChiTietViewModel hoaDonChiTiet, String MaSP, String MaHD) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoluong(hoaDonChiTiet.getSoluong());
        hdct.setDonGia(hoaDonChiTiet.getDonGia());
        SanPham sp = new SanPham();
        Integer idSP = spSer.getIdSanPham(MaSP);
        sp.setId(idSP);
        hdct.setSanPham(sp);
        HoaDon hd = new HoaDon();
        Integer idHD = repo.getIdHD(MaHD);
        System.out.println(idHD);
        hd.setId(idHD);
        hdct.setHoaDon(hd);
        Integer isHDCT = repo.insertHoaDonChiTiet(hdct);
        return isHDCT;
    }

    @Override
    public Integer updateTrangThaiHoaDon(HoaDonViewModel hd) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setGhichu(hd.getGhiChu());
        hoaDon.setNgayThanhToan(new java.sql.Date(hd.getNgayThanhToan().getTime()));
        hoaDon.setMa(hd.getMa());
        hoaDon.setTongTien(hd.getTongTien());
        hoaDon.setTinhTrang(1);
        return repo.updateTrangThaiHoaDon(hoaDon);
    }

    @Override
    public Integer updateSoLuongGioHang(int SoLuong, String MaSP, String MaHD) {
        return repo.updateSoLuongGioHang(SoLuong, MaSP, MaHD);
    }

    @Override
    public Integer getIdHD(String MaHD) {
        return repo.getIdHD(MaHD);
    }

    @Override
    public Integer deleteSanPham(int idHD, int idSP) {
        return repo.deleteSanPham(idHD, idSP);
    }

    @Override
    public Integer clearSanPhamTrenGioHang(int idHD) {
        return repo.clearSanPhamTrenGioHang(idHD);
    }

}
