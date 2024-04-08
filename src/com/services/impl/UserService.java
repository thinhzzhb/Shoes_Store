/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.User;
import com.models.Users;
import com.raven.form.frm_DangNhap;
import com.raven.form.frm_Login;
import com.raven.main.DashBoard;
import com.repositories.impl.UserRepostory;
import com.services.IUserService;
import com.viewModel.CurrentUser;
import com.viewModel.UsersViewModel;
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
            JOptionPane.showMessageDialog(new frm_Login(), "Mời Bạn Nhập tài khoản");
            return false;

        }
        if (MatKhau.isEmpty()) {

            JOptionPane.showMessageDialog(new frm_Login(), "Mời Bạn Nhập mật khẩu");

            return false;
        }

        if (list != null) {
            for (User x : list) {
                if (x.getVaiTro() == 1) {
                    JOptionPane.showMessageDialog(new frm_Login(), "Đăng nhập thành công với chức vụ Quản Lý");
                    String tenNV = x.getHo() + " " + x.getTenDem() + " " + x.getTen();
                    //gọi form main rồi lưu thông tin vào CurrentUser
                    CurrentUser.getInstance().setUsername(tenNV);
                    CurrentUser.getInstance().setVaitro(x.getVaiTro());
                    CurrentUser.getInstance().setIdNV(x.getId());
                    new DashBoard().setVisible(true);
                    return true;

                } else {
                    JOptionPane.showMessageDialog(new frm_Login(), "Đăng nhập thành công với chức vụ Nhân Viên");
                    String tenNV = x.getHo() + " " + x.getTenDem() + " " + x.getTen();
                    CurrentUser.getInstance().setUsername(tenNV);
                    CurrentUser.getInstance().setVaitro(x.getVaiTro());
                    CurrentUser.getInstance().setIdNV(x.getId());
                    new DashBoard().setVisible(true);
                    return true;
                }

            }

        }
        JOptionPane.showMessageDialog(new frm_Login(), "Sai Tài Khoản Hoặc Mật Khẩu");
        return false;
    }

    @Override
    public Users getUserbytk(String tk) {
        return userRepostory.getUserbytk(tk);
    }

    @Override
    public boolean updateMK(UsersViewModel us, String mail) {
        return userRepostory.updateMK(new Users(us.getMk()), mail);
    }

}
