/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.SanPham;
import com.model.Voucher;
import com.model.hangGiay;
import com.model.mauSac;
import com.model.sizeGiay;
import com.utility.DBConnection;
import com.viewModel.sanPham_KhuyenMaiViewModel;
import java.math.BigDecimal;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class voucher_Repo {
    
    public SanPham findByID(int idSP){
        String query = "SELECT idSP, tenSanPham FROM SanPham WHERE idSP = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSP);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                return new SanPham(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    public sizeGiay findByIDSize(int idSize){
        String query = "SELECT * FROM Co WHERE idCo = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSize);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                return new sizeGiay(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public mauSac findByIDMau(int idMau){
        String query = "SELECT * FROM Mau WHERE idMau = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idMau);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                return new mauSac(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public hangGiay findByIDHang(int id){
        String query = "SELECT * FROM Hang WHERE idHang = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                return new hangGiay(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<sanPham_KhuyenMaiViewModel> getAllSP(){
        String query = "select s.tenSanPham, sct.idCo, sct.idMau, sct.idHang, sct.giaTien "
                + "from SanPham s "
                + "join SanPhamCT sct"
                + " on s.idSP = sct.idSanPham";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            List<sanPham_KhuyenMaiViewModel> _lstS = new ArrayList<>();
            while (rs.next()) { 
                sizeGiay sG = findByIDSize(rs.getInt(2));
                mauSac m = findByIDMau(rs.getInt(3));
                hangGiay h = findByIDHang(rs.getInt(4));
                sanPham_KhuyenMaiViewModel smd = new sanPham_KhuyenMaiViewModel(rs.getString(1), sG, m, h, rs.getBigDecimal(5));
                _lstS.add(smd);
            }
            return _lstS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Voucher> getAll(){
        String query = "select idVoucher, maKM, tenKM, mucGiamGia,"
                + " ngayBatDau, ngayKetThuc, idSP, trangThai "
                + "from Voucher";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            List<Voucher> _lst = new ArrayList<>();
            while (rs.next()) { 
                SanPham s = findByID(rs.getInt(7));
                Voucher v = new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(8), s);
                _lst.add(v);
            }
            return _lst;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<hangGiay> getAllHang(){
        String query = "select * "
                + "from Hang";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            List<hangGiay> _lst = new ArrayList<>();
            while (rs.next()) { 
                hangGiay h = new hangGiay(rs.getInt(1), rs.getString(2));
                _lst.add(h);
            }
            return _lst;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
