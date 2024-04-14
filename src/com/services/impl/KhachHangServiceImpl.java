/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.KhachHang;
import com.repositories.impl.KhachHangRepository;
import com.services.IKhachHangService;
import com.viewModel.KhachHangViewMD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tggdd
 */
public class KhachHangServiceImpl implements IKhachHangService {

    private final KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    public ArrayList<KhachHang> getAll() {
        return khachHangRepository.getAll();
    }

    @Override
    public void add(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public void update(Integer id, KhachHang khachHang) {
        khachHangRepository.update(id, khachHang);
    }

    @Override
    public void delete(Integer id) {
        khachHangRepository.delete(id);
    }

    @Override
    public List<KhachHangViewMD> getall() {
        List<KhachHangViewMD> list01 = new ArrayList<>();

        List<KhachHang> list = khachHangRepository.getAll();
        for (KhachHang x : list) {
            list01.add(new KhachHangViewMD(x.getId(), x.getTen(), x.getTenDem(), x.getHo(), x.getEmail(), x.getSdt()));
        }
        return list01;

    }

    @Override
    public List<KhachHangViewMD> GetTK(String SDT) {
        List<KhachHang> kh = khachHangRepository.GetTK(SDT);
        List<KhachHangViewMD> list01 = new ArrayList<>();
        for (KhachHang x : kh) {
            list01.add(new KhachHangViewMD(x.getId(), x.getTen(), x.getTenDem(), x.getHo(), x.getEmail(), x.getSdt()));
        }
        return list01;

    }

    @Override
    public String kiemtra(String mail) {
        return khachHangRepository.kiemtra(mail);

    }

    @Override
    public String kiemtrasdt(String sdt) {
        return khachHangRepository.kiemtrasdt(sdt);

    }

}
