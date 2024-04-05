/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.HoaDon;
import com.model.HoaDonChiTiet;
import com.model.KhachHang;
import com.model.KichCo;
import com.model.MauSac;
import com.model.SanPham_t;
import com.model.User;
import com.utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {

    public List<HoaDon> GetAllHD() {
        String query = "SELECT a.MA, b.TEN, c.TEN, NGAYTAO, NGAYTHANHTOAN, TinhTrang, GHICHU, a.tongTien FROM HOADON a JOIN USERS b ON a.IDNV = b.ID "
                + "JOIN KHACHHANG c ON a.IDKH = c.ID";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            List<HoaDon> listSP = new ArrayList<>();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getTimestamp(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                listSP.add(hoadon);
            }
            return listSP;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<HoaDon> getHDTen(String Ten) {
        List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT a.MA, b.TEN, c.TEN, NGAYTAO, NGAYTHANHTOAN, TinhTrang, GHICHU, a.tongTien "
                    + "FROM HOADON a JOIN USERS b ON a.IDNV = b.ID "
                    + "JOIN KHACHHANG c ON a.IDKH = c.ID "
                    + "WHERE c.Ten like ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, Ten);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getTimestamp(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                getList.add(hoadon);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return getList;
    }

    public List<HoaDon> getHDTrangThai(int TrangThai) {
        List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT a.MA, b.TEN, c.TEN, NGAYTAO, NGAYTHANHTOAN, TinhTrang, GHICHU, a.tongTien "
                    + "FROM HOADON a JOIN USERS b ON a.IDNV = b.ID \n"
                    + "JOIN KHACHHANG c ON a.IDKH = c.ID\n"
                    + "where a.TinhTrang = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, TrangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getTimestamp(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                getList.add(hoadon);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return getList;
    }

    public List<HoaDon> GetTimNTT(String NgayTT) {
        List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT a.MA, b.TEN, c.TEN, NGAYTAO, NGAYTHANHTOAN, TinhTrang, GHICHU, a.tongTien FROM HOADON a JOIN USERS b ON a.IDNV = b.ID \n"
                    + "JOIN KHACHHANG c ON a.IDKH = c.ID\n"
                    + "where a.NGAYTHANHTOAN = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, NgayTT);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getTimestamp(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                getList.add(hoadon);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return getList;
    }

    public List<HoaDonChiTiet> getHDCTbyMa(String ma) {

        List<HoaDonChiTiet> getList = new ArrayList<>();
        try {
            String sql = "SELECT c.Ma, b.MA, b.TEN, a.Soluong, Dongia FROM HOADONCHITIET a JOIN ChitietSP b ON a.IdCTSP = b.Id"
                    + " JOIN HOADON c ON a.IDHD = c.ID "
                    + "WHERE c.Ma =?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, ma);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hoadonCT = new HoaDonChiTiet();
                hoadonCT.setSoluong(rs.getInt(4));
                hoadonCT.setDonGia(rs.getDouble(5));

                SanPham_t SP = new SanPham_t();
                SP.setMa(rs.getString(2));
                SP.setTen(rs.getString(3));
                hoadonCT.setSanPham(SP);

                HoaDon hd = new HoaDon();
                hd.setMa(rs.getString(1));
                hoadonCT.setHaoDon(hd);
                getList.add(hoadonCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }

    public List<HoaDon> getListHD(int TrangThai) {
        List<HoaDon> _lstHoaDon = new ArrayList<>();
        try {
            String sql = "SELECT HD.Ma , HD.NgayTao , NV.Ten , HD.TinhTrang , NV.TenDem , NV.Ho "
                    + "FROM HoaDon HD JOIN Users NV ON HD.IdNV = NV.Id "
                    + "WHERE HD.TinhTrang = ? ORDER BY HD.NgayTao DESC";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, TrangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();

                hd.setMa(rs.getString(1));
                hd.setNgayTao(rs.getTimestamp(2));
                User u = new User();
                u.setTen(rs.getString(6) + " " + rs.getString(5) + " " + rs.getString(3));
                hd.setUser(u);
                hd.setTinhTrang(rs.getInt(4));
                _lstHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return _lstHoaDon;
    }

    public Integer insertHoaDon(HoaDon hd, Integer idNV) {
        int result = 0;
        try {
            String sql = "insert into HoaDon (Ma ,IdNV, NgayTao , NgayThanhToan , TinhTrang) values(? , ? ,? ,? ,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, hd.getMa());
            pr.setInt(2, idNV);
            pr.setTimestamp(3, hd.getNgayTao());
            pr.setDate(4, hd.getNgayThanhToan());
            pr.setInt(5, hd.getTinhTrang());
            result = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

//    public List<HoaDonChiTiet> getListHoaDonChiTiet(String MaHD) {
//        List<HoaDonChiTiet> getList = new ArrayList<>();
//        try {
//            String sql = "SELECT SP.Ma, SP.Ten, MauSac.Ten, KichCo.Ten, HDCT.Dongia, HDCT.Soluong, KM.Giatrigiam, KM.HinhthucKM, HDCT.IdCTSP  \n"
//                    + "FROM HoaDon HD JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHD \n"
//                    + "JOIN ChitietSP SP ON SP.Id = HDCT.IdCTSP join KhuyenMai km ON SP.IdKM = KM.Id \n"
//                    + "JOIN MauSac ON SP.IdMauSac = MauSac.id\n"
//                    + "JOIN KichCo ON SP.IdKC = KichCo.id\n"
//                    + "WHERE HD.Ma = ?";
//            Connection conn = DBConnection.getConnection();
//            PreparedStatement pr = conn.prepareStatement(sql);
//            pr.setString(1, MaHD);
//            ResultSet rs = pr.executeQuery();
//            while (rs.next()) {
//                HoaDonChiTiet hdct = new HoaDonChiTiet();
//
//                MauSac ms = new MauSac();
//                ms.setTen(rs.getString(3));
//
//                KichCo kc = new KichCo();
//                kc.setTen(rs.getString(4));
//
//                KhuyenMai km = new KhuyenMai();
//                km.setGiaTriGiam(rs.getDouble(7));
//                km.setHinhThucKM(rs.getString(8));
//
//                SanPham_t sp = new SanPham_t();
//                sp.setTen(rs.getString(2));
//                sp.setMa(rs.getString(1));
//                sp.setMauSac(ms);
//                sp.setKichCo(kc);
//                sp.setId(rs.getInt(9));
//
//                sp.setKhuenMai(km);
//                hdct.setSanPham(sp);
//                hdct.setMauSac(ms);
//                hdct.setKichCo(kc);
//                hdct.setSoluong(rs.getInt(6));
//                hdct.setDonGia(rs.getDouble(5));
//                getList.add(hdct);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return getList;
//    }
}
