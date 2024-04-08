/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helper;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author Admin
 *
 */
public class DBConnection {

//    public static final String HOSTNAME = "localhost";
//
//    public static final String PORT = "1433";
//
//    public static final String DBNAME = "DuAn1_shoes_store";
//
//    public static final String USERNAME = "sa";
//
//    public static final String PASSWORD = "ph19459";
//
//    /**
//     *
//     * Get connection to MSSQL Server
//     *
//     * @return Connection
//     *
//     */
//    public static Connection getConnection() {
//
//        // Create a variable for the connection string.
//        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
//                + "databaseName=" + DBNAME;
//
//        try {
//
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
//
//        } // Handle any errors that may have occurred.
//        catch (ClassNotFoundException | SQLException e) {
//
//            e.printStackTrace(System.out);
//
//        }
//
//        return null;
//
//    }
//
//    public static void main(String[] args) {
//        System.out.println(getConnection());
//    }
private static final String USERNAME = "sa";
    private static final String PASSWORD = "ph19459";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "DuAn1_shoes_store";
    private static final boolean USING_SSL = true;

    private static String CONNECT_STRING;

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            StringBuilder connectStringBuilder = new StringBuilder();
            connectStringBuilder.append("jdbc:sqlserver://")
                    .append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";");
            if (USING_SSL) {
                connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
            }
            CONNECT_STRING = connectStringBuilder.toString();
            System.out.println("Connect String có dạng: " + CONNECT_STRING);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Connection conn;

    //1. Mở kết nối
    public static Connection openDbConnection() {
        try {
            return DriverManager.getConnection(CONNECT_STRING);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //2. Thực thi truy vấn Thêm, Sửa , Xoá
    public static int ExcuteQuery(String sql, Object... args) {
        PreparedStatement pstm = getStmt(sql, args);
        try {
            try {
                return pstm.executeUpdate();
            } finally {
                pstm.close();
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi tại ExcuteDungna");
            ex.printStackTrace();
            return 0;
        }
    }

    //3. Trả lại 1 tập đối tượng
    public static ResultSet getDataFromQuery(String sql, Object... args) throws SQLException {
        PreparedStatement pstm = getStmt(sql, args);
        return pstm.executeQuery();
    }

    //4. Chuẩn bị câu truy vấn trước khi thực thi - Các varargs sử dụng dấu ba chấm (...) sau kiểu dữ liệu.
    public static PreparedStatement getStmt(String sql, Object... args) {
        try {
            conn = openDbConnection();
            PreparedStatement ps;
            //ps = conn.prepareCall(sql) Gọi store procedure
            ps = conn.prepareStatement(sql);//Dùng để triển khai các câu lệnh truy vấn thường
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);//Cộng các value sau câu truy vấn
            }
            return ps;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        Connection con = openDbConnection();
    }
}
