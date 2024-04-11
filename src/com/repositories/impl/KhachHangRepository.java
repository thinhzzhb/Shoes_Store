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
