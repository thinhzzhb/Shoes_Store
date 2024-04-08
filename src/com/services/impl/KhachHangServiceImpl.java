/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.KhachHang;
import com.repositories.impl.KhachHangRepository;
import com.services.IKhachHangService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tggdd
 */
public class KhachHangServiceImpl implements IKhachHangService{
    
    private final KhachHangRepository khachHangRepository = new KhachHangRepository();
    
    @Override
    public ArrayList<KhachHang> timKiem(String maKh) {
        return khachHangRepository.getList(maKh);
    }

    @Override
    public void add(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public void update(Integer id, KhachHang khachHang) {
        khachHangRepository.update(id,khachHang);
    }

    @Override
    public void delete(Integer id) {
        khachHangRepository.delete(id);
    }
    
}
