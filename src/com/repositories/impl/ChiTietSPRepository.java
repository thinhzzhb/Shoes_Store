/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repositories.impl;

import com.helper.DBConnection;
import com.models.ChiTietSP;
import com.repositories.IChiTietSPRepository;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ChiTietSPRepository implements IChiTietSPRepository{
    final String SQL_SELECT_ALL = "SELECT Ma, IdNsx, IdMauSac, IdSP, IdKC, IdKM, SoLuongTon, MoTa, QrCode FROM dbo.ChitietSP WHERE SoLuongTon > 0";
    final String SQL_SELECT_BY_MA = "SELECT Ma, IdNsx, IdMauSac, IdSP, IdKC, IdKM, SoLuongTon, MoTa, QrCode FROM dbo.ChitietSP WHERE Ma = ?";
    final String SQL_SELECT_BY_SL = "SELECT Ma, IdNsx, IdMauSac, IdSP, IdKC, IdKM, SoLuongTon, MoTa, QrCode FROM dbo.ChitietSP WHERE SoLuongTon = 0";
    final String SQL_SELECT_BY_TEN = "SELECT Ma, IdNsx, IdMauSac, IdSP, IdKC, IdKM, SoLuongTon, MoTa, QrCode FROM dbo.ChitietSP WHERE SoLuongTon > 0 AND ten LIKE ?";
    final String SQL_INSERT = "INSERT INTO dbo.ChitietSP "
            + "( Ma, IdNsx, IdMauSac, IdSP, IdKC, IdKM, SoLuongTon, MoTa, QrCode) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    final String SQL_UPDATE = "UPDATE dbo.ChitietSP SET Ma = ?, IdNsx = ?, IdMauSac = ?, IdSP = ?, IdKC = ?, MoTa = ?, SoLuongTon = ? WHERE Ma = ?";
    final String SQL_DELETE = "DELETE dbo.ChitietSP WHERE Ma = ?";

    @Override
    public List<ChiTietSP> getAll() {
        return getdataquery(SQL_SELECT_ALL);
    }

    @Override
    public List<ChiTietSP> getlistbyTen(String ten) {
        return getdataquery(SQL_SELECT_BY_TEN, ten);
    }

    private List<ChiTietSP> getdataquery(String SQL, Object... arvg) {
        List<ChiTietSP> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
            while (rl.next()) {
                lst.add(new ChiTietSP(rl.getNString(1), rl.getInt(2) ,
                        rl.getInt(3) , rl.getInt(4), rl.getInt(5), rl.getInt(6), rl.getInt(7),
                        rl.getNString(8), rl.getNString(9)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;

    }

    @Override
    public int insert(ChiTietSP x) {
        return DBConnection.ExcuteQuery(SQL_INSERT, x.getMa(), x.getIdNsx(), x.getIdMauSac(), x.getIdDanhMuc(), x.getIdKichCo(),1,   x.getSoLuongTon(),x.getMoTa(), x.getQrCode());
    }

    @Override
    public int update(ChiTietSP x, String Ma) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getMa(), x.getIdNsx(), x.getIdMauSac(), x.getIdDanhMuc(), x.getIdKichCo(), x.getMoTa(), x.getSoLuongTon(), Ma);
    }

    @Override
    public int delete(String ma) {
        return DBConnection.ExcuteQuery(SQL_DELETE, ma);
    }

    @Override
    public List<ChiTietSP> getSPhet() {
        return getdataquery(SQL_SELECT_BY_SL);
    }

    @Override
    public List<ChiTietSP> check(String maSP) {
        return getdataquery(SQL_SELECT_BY_MA, maSP);
    }

    @Override
    public Date checkngay(String id) {
        Date time = null;
        try {
            String sql = "Select Ngaybatdau from khuyenmai where id =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                time = rs.getDate(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return time;
    }

    @Override
    public Date checkngay2(String id) {
        Date time = null;
        try {
            String sql = "Select ngayketthuc from khuyenmai where id =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                time = rs.getDate(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return time;
    }

    ////
    @Override
    public List<ChiTietSP> GetAll() {
        try {
            List<ChiTietSP> lst = new ArrayList<>();
            String sql = "Select Ma,IdSP from ChitietSP";
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ChiTietSP sP = new ChiTietSP();
                sP.setMa(rs.getString(1));
                sP.setIdDanhMuc(rs.getInt(2));
                lst.add(sP);
            }
            return lst;
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean Update(String ma, String id) {
        try {
            String sql = "Update Chitietsp set idkm = ? Where Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, ma);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
