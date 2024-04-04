/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.impl;

import com.model.User;
import com.raven.main.DashBoard;
import com.raven.main.login_Frame;
import com.repository.UserRepostory;
import com.service.IUserService;
import com.viewModel.CurrentUser;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class UserService implements IUserService {

    private UserRepostory userRepostory;

    public UserService() {
        userRepostory = new UserRepostory();
    }

    @Override
    public boolean getUser(String TaiKhoan, String MatKhau) {
        List<User> list = userRepostory.getUser(TaiKhoan, MatKhau);
        if (TaiKhoan.isEmpty()) {
            JOptionPane.showMessageDialog(new login_Frame(), " Mời Bạn Nhập tài khoản");
            return false;

        }
        if (MatKhau.isEmpty()) {

            JOptionPane.showMessageDialog(new login_Frame(), "Mời Bạn Nhập mật khẩu");

            return false;
        }

        if (list != null) {
            for (User x : list) {
                if (x.getVaiTro() == 1) {
                    JOptionPane.showMessageDialog(new login_Frame(), "Đăng nhập thành công! (Quản Lý)");
                    String tenNV = x.getHo() + " " + x.getTenDem() + " " + x.getTen();
                    //gọi form main rồi lưu thông tin vào CurrentUser
                    CurrentUser.getInstance().setUsername(tenNV);
                    CurrentUser.getInstance().setVaitro(x.getVaiTro());
                    new DashBoard().setVisible(true);
                    return true;

                } else {
                    JOptionPane.showMessageDialog(new login_Frame(), "Đăng nhập thành công! (Nhân Viên)");
                    String tenNV = x.getHo() + " " + x.getTenDem() + " " + x.getTen();
                    CurrentUser.getInstance().setUsername(tenNV);
                    CurrentUser.getInstance().setVaitro(x.getVaiTro());
                    new DashBoard().setVisible(true);
                    return true;
                }

            }

        }
        JOptionPane.showMessageDialog(new login_Frame(), "Sai Tài Khoản Hoặc Mật Khẩu");
        return false;
    }

}
