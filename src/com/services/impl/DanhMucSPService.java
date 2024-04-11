/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.DanhMucSP;
import com.repositories.IDanhMucSPRepository;
import com.repositories.impl.DanhMucSPRepository;
import com.services.IDanhMucSPService;
import com.viewModel.objectSp;
import java.util.List;

/**
 *
 * @author user
 */
public class DanhMucSPService implements IDanhMucSPService{
    private IDanhMucSPRepository danhMucSPRepository = new DanhMucSPRepository();

    @Override
    public List<DanhMucSP> getAll() {
        return danhMucSPRepository.getAll();
    }

    @Override
    public String Add(objectSp x) {
        DanhMucSP danhmuc = new DanhMucSP(x.getId(), x.getTen(), x.getGiaNhap(), x.getGiaBan());
        int them = danhMucSPRepository.insert(danhmuc);
        if (them == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Update(objectSp x, int id) {
        DanhMucSP danhmuc = new DanhMucSP(x.getId(), x.getTen(), x.getGiaNhap(), x.getGiaBan());
        int sua = danhMucSPRepository.update(danhmuc, id);
        if (sua == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Delete(int id) {
        if (danhMucSPRepository.delete(id) == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public DanhMucSP getbyid(int id) {
        return danhMucSPRepository.getdanhmucbyid(id);
    }
    
}
