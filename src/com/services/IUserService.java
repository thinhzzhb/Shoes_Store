/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.services;

import com.models.Users;
import com.viewModel.UsersViewModel;


/**
 *
 * @author Admin
 */
public interface IUserService {

    boolean getUser(String TaiKhoan, String MatKhau);
    
    Users getUserbytk(String tk);
    
    boolean updateMK(UsersViewModel us, String mail);
}
