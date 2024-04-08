/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repositories.impl;

import com.models.User;
import com.models.Users;
import com.helper.DBConnection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserRepostory {
    final String SQL_SELECT_BY_TK = "SELECT id, Ten, TenDem, Ho, NgaySinh, Gioitinh, Sdt, vaiTro, TaiKhoan, MatKhau, Email, TrangThai FROM Users where Taikhoan = ?";

    public List<User> getUser(String TaiKhoan, String MatKhau) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select ho, TenDem, Ten, vaiTro, id from Users where TaiKhoan =? and matKhau =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, TaiKhoan);
            pr.setString(2, MatKhau);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setHo(rs.getString(1));
                user.setTenDem(rs.getString(2));
                user.setTen(rs.getString(3));
                user.setVaiTro(rs.getInt(4));
                user.setId(rs.getInt(5));
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Users getUserbytk(String tk) {
        Users x = new Users();
        try {
            x = getdataQuery(SQL_SELECT_BY_TK, tk).get(0);
        } catch (Exception e) {
            x = new Users();
        }
        return x;
    }
    
    private List<Users> getdataQuery(String SQL, Object... arvg) {
        List<Users> lst = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.getDataFromQuery(SQL, arvg);
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                String tendem = rs.getString(3);
                String ho = rs.getString(4);
                String ngaysinh = rs.getString(5);
                Integer gioitinh = rs.getInt(6);
                String sdt = rs.getString(7);
                int vaitro = rs.getInt(8);
                String tk = rs.getString(9);
                String mk = rs.getString(10);
                String email = rs.getString(11);
                Integer tt = rs.getInt(12);

                lst.add(new Users(id, ten, tendem, ho, ngaysinh, gioitinh, sdt, tk, mk, email, vaitro, tt));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    public boolean updateMK(Users us, String mail) {
        String sql = "UPDATE Users SET MatKhau = ? WHERE TaiKhoan = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, us.getMk());
            pstm.setString(2, mail);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<User> getList(Integer trangThai){
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from Users ";
        if(trangThai != null){
            sql = "select * from Users where TrangThai = ?";
        }
        try {
            
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            if(trangThai != null)
                pr.setInt(1, trangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setHo(rs.getString(4));
                user.setTenDem(rs.getString(3));
                user.setTen(rs.getString(2));
                user.setVaiTro(rs.getInt(12));
                user.setId(rs.getInt(1));
                user.setMaNV(rs.getString(13));
                user.setDiaChi(rs.getString(14));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return list;
    }
    
    public void save(User user){
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
                .append("Users('Ten','TenDem','Ho','Sdt','TaiKhoan','MatKhau','Email','TrangThai','vaiTro,'MaNV','DiaChi')")
                .append("VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )");
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setString(1,user.getTen());
            ps.setString(2,user.getTenDem());
            ps.setString(3,user.getHo());
            ps.setString(4,user.getSdt());
            ps.setString(5,user.getTaiKhoan());
            ps.setString(6,user.getMatKhau());
            ps.setString(7,user.getEmail());
            ps.setInt(8,user.getTrangThai());
            ps.setInt(9,user.getVaiTro());
            ps.setString(10,user.getMaNV());
            ps.setString(11,user.getDiaChi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    
    public void update(Integer id, User user){
        StringBuilder query = new StringBuilder();
        query.append("UPDATE Users SET ")
                .append(" Ten = ? ,")
                .append(" TenDem = ? ,")
                .append(" Ho = ? ,")
                .append(" Sdt = ? ,")
                .append(" TaiKhoan = ? ,")
                .append(" MatKhau = ? ,")
                .append(" Email = ? ,")
                .append(" TrangThai = ? ,")
                .append(" vaiTro = ? ,")
                .append(" MaNV = ? ,")
                .append(" DiaChi = ? ")
                .append(" WHERE ")
                .append(" id = ?");
        try{
            Connection con = (Connection) DBConnection.openDbConnection();  
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setString(1,user.getTen());
            ps.setString(2,user.getTenDem());
            ps.setString(3,user.getHo());
            ps.setString(4,user.getSdt());
            ps.setString(5,user.getTaiKhoan());
            ps.setString(6,user.getMatKhau());
            ps.setString(7,user.getEmail());
            ps.setInt(8,user.getTrangThai());
            ps.setInt(9,user.getVaiTro());
            ps.setString(10,user.getMaNV());
            ps.setString(11,user.getDiaChi());
            ps.setInt(12,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    
    public void delete(Integer id){
        String query = "DELETE FROM Users WHERE Id = ? ";
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
