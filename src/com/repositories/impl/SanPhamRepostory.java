/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repositories.impl;

import com.models.KhuyenMai;
import com.models.KichCo;
import com.models.MauSac;
import com.models.SanPham;
import com.helper.DBConnection;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungh
 */
public class SanPhamRepostory {

    public List<SanPham> getListSanPham() throws SQLException {
        List<SanPham> getListSP = new ArrayList<>();

        String sql = "SELECT SP.MA , s.Ten , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , SP.SoLuongTon ,s.GiaBan FROM ChitietSP SP JOIN SanPham s on s.Id = SP.IdSP "
                + "                JOIN MauSac MS ON SP.IdMauSac = MS.Id "
                + "                JOIN KichCo KC ON SP.IdKC = KC.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id";
        Connection conn = DBConnection.openDbConnection();
        Statement stt = conn.createStatement();

        ResultSet rs = stt.executeQuery(sql);
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setMa(rs.getString(1));
            sp.setTen(rs.getString(2));

            MauSac ms = new MauSac();
            ms.setTen(rs.getString(3));
            sp.setMauSac(ms);
            KhuyenMai km = new KhuyenMai();
            km.setGiaTriGiam(rs.getDouble(4));
            km.setHinhThucKM(rs.getString(5));
            sp.setKhuyenMai(km);
            KichCo ks = new KichCo();
            ks.setTen(rs.getString(6));
            sp.setKichCo(ks);
            sp.setSoLuongTon(rs.getInt(7));
            sp.setGiaBan(rs.getDouble(8));
            getListSP.add(sp);
        }
        rs.close();
        stt.close();
        conn.close();
        return getListSP;
    }

    public boolean updateSoLuongSP(String Masp, int SoLuong) {
        try {
            String sql = "UPDATE ChitietSP SET SoLuongTon = ? WHERE MA = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, SoLuong);
            pr.setString(2, Masp);
            pr.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Integer getIdSanPham(String MaSP) {
        Integer idSP = 0;
        try {
            String sql = "select id from ChitietSP where Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaSP);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                idSP = rs.getInt(1);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return idSP;
    }

    public SanPham getTenSP(int id) {

        String sql = "select Ten from SanPham where Id = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));

                return sp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public List<SanPham> seachSanPham(String Ten) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , SP.SoLuongTon ,SP.GiaBan FROM ChitietSP SP "
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id "
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id WHERE SP.Ten LIKE ? or SP.QrCode =? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + Ten + "%");
            pr.setString(2, Ten);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuyenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                sp.setKichCo(ks);
                sp.setSoLuongTon(rs.getInt(7));
                sp.setGiaBan(rs.getDouble(8));
                getListSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getListSP;
    }

    public List<SanPham> seachBarCode(String barcode) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , SP.SoLuongTon ,SP.GiaBan ,SP.id FROM ChitietSP SP "
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id "
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id WHERE SP.QrCode like ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + barcode + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                sp.setId(rs.getInt(9));

                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuyenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                sp.setKichCo(ks);
                sp.setSoLuongTon(rs.getInt(7));
                sp.setGiaBan(rs.getDouble(8));
                getListSP.add(sp);
            }
            return getListSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public SanPham seachBarCodeAdd(String barcode) {
        SanPham sp = new SanPham();
        try {
            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , SP.SoLuongTon ,SP.GiaBan ,SP.id FROM ChitietSP SP "
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id "
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id WHERE SP.QrCode like ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + barcode + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                sp.setId(rs.getInt(9));

                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuyenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                sp.setKichCo(ks);
                sp.setSoLuongTon(rs.getInt(7));
                sp.setGiaBan(rs.getDouble(8));
            }
            return sp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<SanPham> locTheoDanhMucSP(String TenDanhMuc) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , SP.SoLuongTon ,SP.GiaBan   FROM ChitietSP SP "
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id "
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id JOIN DanhMucSP DM ON SP.idDMuc = DM.ID WHERE DM.Ten =  ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, TenDanhMuc);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));

                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuyenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                sp.setKichCo(ks);
                sp.setSoLuongTon(rs.getInt(7));
                sp.setGiaBan(rs.getDouble(8));
                getListSP.add(sp);
            }
            return getListSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
