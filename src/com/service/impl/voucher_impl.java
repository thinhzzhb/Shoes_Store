/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.impl;

import com.model.Voucher;
import com.model.hangGiay;
import com.repository.voucher_Repo;
import com.service.voucher_Interface;
import com.viewModel.sanPham_KhuyenMaiViewModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public class voucher_impl implements voucher_Interface{

    voucher_Repo rep;

    public voucher_impl() {
        rep = new voucher_Repo();
    }
    
    
    @Override
    public List<Voucher> getAll() {
        return this.rep.getAll();
    }

    @Override
    public List<sanPham_KhuyenMaiViewModel> getAllSP() {
        return this.rep.getAllSP();
    }

    @Override
    public List<hangGiay> getAllHang() {
        return this.rep.getAllHang();
    }

    
}
