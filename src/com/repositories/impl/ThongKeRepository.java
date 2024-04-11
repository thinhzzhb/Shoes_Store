/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repositories.impl;

import com.helper.DBConnection;
import com.models.ChiTietSP;
import com.models.SanPham;
import com.models.ThongKe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeRepository {

    private List<ThongKe> lst;

    public ThongKeRepository() {
        lst = new ArrayList<>();
    }

    public int gethdday() {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and DAY(NgayThanhToan) = DAY(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int gethdMonth() {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and Month(NgayThanhToan) = Month(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int gethdYear() {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and Year(NgayThanhToan) = Year(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSPbyMonth() {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' "
                + "from HoaDonChiTiet a "
                + "join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id "
                + "where TinhTrang = 1 and Month(c.NgayThanhToan) = Month(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getSPbyYear() {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' "
                + "from HoaDonChiTiet a "
                + "join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id "
                + "where TinhTrang = 1 and Year(c.NgayThanhToan) = Year(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getbyday() {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and Day(c.NgayThanhToan) = Day(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public List<ThongKe> getspday(String date) {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND DAY(NGAYTHANHTOAN) = ? AND MONTH(NGAYTHANHTOAN) = MONTH(GETDATE()) AND YEAR(NGAYTHANHTOAN)=YEAR(GETDATE()) group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public List<ThongKe> getspmonth(String date) {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND MONTH(NGAYTHANHTOAN) = ? AND YEAR(NGAYTHANHTOAN) = YEAR(GETDATE()) group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public List<ThongKe> getspyear(String date) {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND YEAR(NGAYTHANHTOAN) = ? group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public int getdtday() {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and Day(NgayThanhToan) = Day(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getdtMonth() {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and Month(NgayThanhToan) = Month(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getdtYear() {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and Year(NgayThanhToan) = Year(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public List<ThongKe> getsp() {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND DAY(NGAYTHANHTOAN) = DAY(GETDATE()) group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public List<ThongKe> getspByMonth() {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND Month(NGAYTHANHTOAN) = Month(GETDATE()) group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public int getbyday(String date) {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and Day(c.NgayThanhToan) = ? and MONTH(c.NgayThanhToan) = MONTH(GETDATE()) and YEAR(c.NgayThanhToan) = YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getbymonth(String date) {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and MONTH(c.NgayThanhToan) = ? AND YEAR(NGAYTHANHTOAN) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getbyyear(String date) {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and Year(c.NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public List<ThongKe> getspbyYear() {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND Year(NGAYTHANHTOAN) = Year(GETDATE()) group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public int gethdday(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and DAY(NgayThanhToan) = ? and MONTH(NGAYTHANHTOAN) =MONTH(GETDATE()) AND YEAR(NGAYTHANHTOAN) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int gethdmonth(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) = ? and YEAR(NgayThanhToan) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int gethdyear(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and YEAR(NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getdtday(String date) {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and Day(NgayThanhToan) = ? and MONTH(NgayThanhToan) = MONTH(GETDATE()) and  YEAR(NGAYTHANHTOAN) = YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getdtmonth(String date) {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = ? and  YEAR(NGAYTHANHTOAN) = YEAR(GETDATE()) ";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getdtyear(String date) {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and YEAR(NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getdtkhoang1(String date, String date1) {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 "
                + "  and MONTH(NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int getbykhoang1(String date, String date1) {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id "
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and MONTH(c.NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    public int gethdkhoang1(String date, String date1) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getkhkhoang1(String date, String date1) {
        int box = 0;
        String sql = "select COUNT(IdKH) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ThongKe> getspkhoang(String date, String date1) {
        lst.removeAll(lst);
        String sql = "select s.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID join SanPham s on s.Id = a.IdSP "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND MONTH(NGAYTHANHTOAN) BETWEEN ? AND ? AND YEAR(NGAYTHANHTOAN)=YEAR(GETDATE()) group by s.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTen(rs.getString(1));
                lst.add(new ThongKe(rs.getInt(2), sp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }
}
