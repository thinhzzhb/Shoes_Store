/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repositories.impl;

import com.models.SanPham;
import com.services.ISanPhamService;
import com.viewModel.SanPhamViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hungh
 */
public class SanPhamService implements ISanPhamService {

    private SanPhamRepostory sanPhamReposory;
    private List<SanPhamViewModel> getListSP;
    private List<SanPhamViewModel> locTheoDanhMucSP;

    public SanPhamService() {
        sanPhamReposory = new SanPhamRepostory();
        getListSP = new ArrayList<>();

    }

    @Override
    public List<SanPhamViewModel> getListSanPham() {

        try {
            List<SanPham> list = sanPhamReposory.getListSanPham();
            for (SanPham sanPham : list) {
                getListSP.add(new SanPhamViewModel(sanPham.getMa(), sanPham.getTen(), sanPham.getMauSac(), sanPham.getKhuyenMai(), sanPham.getKichCo(),
                         sanPham.getSoLuongTon(), sanPham.getGiaBan()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getListSP;
    }

    @Override
    public boolean updateSoLuongSP(String Masp, int SoLuong) {

        return sanPhamReposory.updateSoLuongSP(Masp, SoLuong);
    }

    @Override
    public Integer getIdSanPham(String MaSP) {
        return sanPhamReposory.getIdSanPham(MaSP);
    }

    @Override
    public List<SanPham> seachSanPham(String Ten) {
        return sanPhamReposory.seachSanPham(Ten);

    }

    @Override
    public List<SanPham> seachBarCodeS(String barcode) {
        return sanPhamReposory.seachSanPham(barcode);
    }

    @Override
    public List<SanPhamViewModel> locTheoDanhMucSP(String TenDanhMuc) {
        locTheoDanhMucSP = new ArrayList<>();
        try {
            List<SanPham> list = sanPhamReposory.locTheoDanhMucSP(TenDanhMuc);
            for (SanPham sanPham : list) {
                locTheoDanhMucSP.add(new SanPhamViewModel(sanPham.getMa(), sanPham.getTen(), sanPham.getMauSac(), sanPham.getKhuyenMai(), sanPham.getKichCo(),
                        sanPham.getSoLuongTon(), sanPham.getGiaBan()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locTheoDanhMucSP;
    }

}
