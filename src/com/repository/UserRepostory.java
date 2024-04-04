/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.User;
import com.utility.DBConnection;
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

    public List<User> getUser(String TaiKhoan, String MatKhau) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select ho, TenDem, Ten, vaiTro, id from Users where TaiKhoan =? and matKhau =?";
            Connection conn = DBConnection.getConnection();
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
}
