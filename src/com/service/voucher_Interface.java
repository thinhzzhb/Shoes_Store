/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import com.model.Voucher;
import com.model.hangGiay;
import com.viewModel.sanPham_KhuyenMaiViewModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface voucher_Interface {
    
    public List<Voucher> getAll();
    
    public List<sanPham_KhuyenMaiViewModel> getAllSP();
    
    public List<hangGiay> getAllHang();
    
}
