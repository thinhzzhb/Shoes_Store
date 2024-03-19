/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.TaiKhoan;
import com.utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class taiKhoanRepo {
    public TaiKhoan getUser(String user, String pass) {
        String query = "select [idTK],maTK,[username],[password],[vaiTro],[TinhTrang] from TaiKhoan where username = ? and [password] = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
