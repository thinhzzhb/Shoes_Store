/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repositories.impl;

import com.helper.DBConnection;
import com.models.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tggdd
 */
public class KhachHangRepository {
    public ArrayList<KhachHang> getAll() {
        String query = "SELECT * FROM KHACHHANG ";
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<KhachHang> listKH = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("Id"));
                kh.setTen(rs.getString("Ten"));
                kh.setTenDem(rs.getString("TenDem"));
                kh.setHo(rs.getString("Ho"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSdt(rs.getString("Sdt"));
                kh.setMaKH(rs.getString("MaKH"));
                kh.setEmail(rs.getString("Email"));
                listKH.add(kh);
            }
            return listKH;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }
    
    public void save(KhachHang kh){
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
                .append("KhachHang(Ten,TenDem,Ho,Sdt,MaKH,DiaChi,Email) ")
                .append("VALUES(? , ? , ? , ? , ? , ?, ?)");
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setString(1,kh.getTen());
            ps.setString(2,kh.getTenDem());
            ps.setString(3,kh.getHo());
            ps.setString(4,kh.getSdt());
            ps.setString(5,kh.getMaKH());
            ps.setString(6,kh.getDiaChi());
            ps.setString(7,kh.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    
    public String kiemtra(String mail) {
        String sql = "SELECT EMAIL FROM KhachHang WHERE EMAIL = ?";
        String box = null;
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mail);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String kiemtrasdt(String sdt) {
        String sql = "SELECT SDT FROM KhachHang WHERE SDT = ? ";
        String box = null;
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, sdt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public List<KhachHang> GetTK(String SDT) {
        List<KhachHang> listkh = new ArrayList<>();
        try {
            listkh.removeAll(listkh);
            String sql = "SELECT [Id]\n"
                    + "      ,[Ten]\n"
                    + "      ,[TenDem]\n"
                    + "      ,[Ho]\n"
                    + "      ,[Gioitinh]\n"
                    + "      ,[NgaySinh]\n"
                    + "      ,[Email]\n"
                    + "      ,[Sdt]\n"
                    + "      ,[Diemthuong]\n"
                    + "  FROM [dbo].[KhachHang]\n"
                    + "  where sdt like ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + SDT + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang khachhang = new KhachHang();
                khachhang.setId(rs.getInt(1));
                khachhang.setTen(rs.getString(2));
                khachhang.setTenDem(rs.getString(3));
                khachhang.setHo(rs.getString(4));
                khachhang.setEmail(rs.getString(7));
                khachhang.setSdt(rs.getString(8));

                listkh.add(khachhang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listkh;
    }
    public void update(Integer id, KhachHang kh){
        StringBuilder query = new StringBuilder();
        query.append("UPDATE KhachHang SET ")
                .append(" Ten = ? ,")
                .append(" TenDem = ? ,")
                .append(" Ho = ? ,")
                .append(" Sdt = ? ,")
                .append(" MaKH = ? ,")
                .append(" DiaChi = ? ,")
                .append(" Email = ?")
                .append(" WHERE ")
                .append(" Id = ?");
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setString(1,kh.getTen());
            ps.setString(2,kh.getTenDem());
            ps.setString(3,kh.getHo());
            ps.setString(4,kh.getSdt());
            ps.setString(5,kh.getMaKH());
            ps.setString(6,kh.getDiaChi());
            ps.setString(7,kh.getEmail());
            ps.setInt(8,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    
    public void delete(Integer id){
        String query = "DELETE FROM KhachHang WHERE Id = ? ";
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
