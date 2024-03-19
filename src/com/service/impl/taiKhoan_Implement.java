/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.impl;

import com.model.TaiKhoan;
import com.repository.taiKhoanRepo;
import com.service.taiKhoan_Interface;

/**
 *
 * @author Admin
 */
public class taiKhoan_Implement implements taiKhoan_Interface{

    taiKhoanRepo rep;

    public taiKhoan_Implement() {
        this.rep = new taiKhoanRepo();
    }
    
    
    @Override
    public TaiKhoan getUser(String user, String pass) {
        return this.rep.getUser(user, pass);
    }
    
}
