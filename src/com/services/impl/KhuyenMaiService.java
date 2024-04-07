/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.KhuyenMai;
import com.repositories.IKhuyenMaiRepository;
import com.repositories.impl.KhuyenMaiRepository;
import com.services.IKhuyenMaiService;
import com.viewModel.KhuyenMaiViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class KhuyenMaiService implements IKhuyenMaiService{
    private IKhuyenMaiRepository repository;
    private List<KhuyenMaiViewModel> lstKM;

    public KhuyenMaiService() {
        repository = new KhuyenMaiRepository();
    }

    @Override
    public List<KhuyenMaiViewModel> GetALL() {
        List<KhuyenMai> lst = repository.GetAll();
        lstKM = new ArrayList<>();
        for (KhuyenMai x : lst) {
            lstKM.add(new KhuyenMaiViewModel(x.getTenKM(), x.getHinhThucKM(), x.getNgayBatDau(), x.getNgayKetThuc(), x.getGiaTriGiam(), x.getTrangthai()));
        }
        return lstKM;
    }

    @Override
    public boolean Add(KhuyenMaiViewModel km) {
        boolean isAdd = repository.Add(new KhuyenMai(km.getTenKM(), km.getHinhThucKM(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getGiaTriGiam(), km.getTrangthai()));
        return isAdd;
    }

    @Override
    public boolean Update(KhuyenMaiViewModel km, String id) {
        boolean isUpdate = repository.Update(new KhuyenMai(km.getTenKM(), km.getHinhThucKM(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getGiaTriGiam(), km.getTrangthai()), id);
        return isUpdate;
    }

    @Override
    public boolean Delete(String id) {
        boolean isDelete = repository.Delete(id);
        return isDelete;
    }

    @Override
    public String checktrung(String ten) {
        return repository.checktrung(ten);
    }

    @Override
    public List<KhuyenMaiViewModel> GetOnebyBD(String date) {
        List<KhuyenMai> lst = repository.GetOnebyBD(date);
        lstKM = new ArrayList<>();
        for (KhuyenMai x : lst) {
            lstKM.add(new KhuyenMaiViewModel(x.getTenKM(), x.getHinhThucKM(), x.getNgayBatDau(), x.getNgayKetThuc(), x.getGiaTriGiam(), x.getTrangthai()));
        }
        return lstKM;
    }

    @Override
    public List<KhuyenMaiViewModel> GetOnebyKT(String date) {
        List<KhuyenMai> lst = repository.GetOnebyKT(date);
        lstKM = new ArrayList<>();
        for (KhuyenMai x : lst) {
            lstKM.add(new KhuyenMaiViewModel(x.getTenKM(), x.getHinhThucKM(), x.getNgayBatDau(), x.getNgayKetThuc(), x.getGiaTriGiam(), x.getTrangthai()));
        }
        return lstKM;
    }

    @Override
    public List<KhuyenMaiViewModel> GetOnebyALL(String datedb, String datekt) {
        List<KhuyenMai> lst = repository.GetOnebyALL(datedb, datekt);
        lstKM = new ArrayList<>();
        for (KhuyenMai x : lst) {
            lstKM.add(new KhuyenMaiViewModel(x.getTenKM(), x.getHinhThucKM(), x.getNgayBatDau(), x.getNgayKetThuc(), x.getGiaTriGiam(), x.getTrangthai()));
        }
        return lstKM;
    }

    @Override
    public List<KhuyenMaiViewModel> GetOnebyten(String ten) {
        List<KhuyenMai> lst = repository.GetOnebyten(ten);
        lstKM = new ArrayList<>();
        for (KhuyenMai x : lst) {
            lstKM.add(new KhuyenMaiViewModel(x.getTenKM(), x.getHinhThucKM(), x.getNgayBatDau(), x.getNgayKetThuc(), x.getGiaTriGiam(), x.getTrangthai()));
        }
        return lstKM;
    }

    @Override
    public boolean UpdateTT() {
        boolean iscapnhat = repository.UpdateTT();
        return iscapnhat;
    }

    @Override
    public boolean UpdateTT2() {
        boolean iscapnhat = repository.UpdateTT2();
        return iscapnhat;
    }

    @Override
    public List<KhuyenMai> getlist() {
        return repository.GetAll();
    }
    
}
