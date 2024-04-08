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

/**
 *
 * @author tggdd
 */
public class KhachHangRepository {
    public ArrayList<KhachHang> getList(String maKh) {
        String query = "SELECT * FROM KHACHHANG k WHERE k.MAKH like %?%";
        if(maKh == "")
            query = "SELECT * FROM KHACHHANG ";
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query);
            if(maKh != "")
                ps.setString(1, maKh);
            ResultSet rs = ps.executeQuery();
            ArrayList<KhachHang> listKH = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setTenDem(rs.getString(3));
                kh.setHo(rs.getString(4));
                kh.setDiaChi(rs.getString(11));
                kh.setSdt(rs.getString(8));
                kh.setMaKH(rs.getString(10));
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
                .append("KhachHang ")
                .append("VALUES(? ,? ,? , ")
                .append(0)
                .append(", '2001-09-08' ")
                .append(", 'test@gmail.com' ")
                .append(", ? , ")
                .append(0)
                .append(" ,? , ?)");
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setString(1,kh.getTen());
            ps.setString(2,kh.getTenDem());
            ps.setString(3,kh.getHo());
            ps.setString(4,kh.getSdt());
            ps.setString(5,kh.getMaKH());
            ps.setString(6,kh.getDiaChi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    
    public void update(Integer id, KhachHang kh){
        StringBuilder query = new StringBuilder();
        query.append("UPDATE KhangHang SET ")
                .append(" Ten = ? ,")
                .append(" TenDem = ? ,")
                .append(" Ho = ? ,")
                .append(" Sdt = ? ,")
                .append(" MaKH = ? ,")
                .append(" DiaChi = ? ")
                .append(" WHERE ")
                .append(" id = ?");
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setString(1,kh.getTen());
            ps.setString(2,kh.getTenDem());
            ps.setString(3,kh.getHo());
            ps.setString(4,kh.getSdt());
            ps.setString(5,kh.getMaKH());
            ps.setString(6,kh.getDiaChi());
            ps.setInt(7,id);
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
