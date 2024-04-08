/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.SanPham;
import com.repositories.impl.SanPhamRepostory;
import com.services.ISanPhamService;
import com.viewModel.SanPhamViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
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
    public List<SanPhamViewModel> seachSanPham(String Ten) {
        List<SanPham> list = sanPhamReposory.seachSanPham(Ten);
        List<SanPhamViewModel> _lstTimKiem = new ArrayList<>();

        for (SanPham sanPham : list) {
            _lstTimKiem.add(new SanPhamViewModel(sanPham.getMa(), sanPham.getTen(), sanPham.getMauSac(), sanPham.getKhuyenMai(), sanPham.getKichCo(),
                    sanPham.getSoLuongTon(), sanPham.getGiaBan()));
        }

        return _lstTimKiem;
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

    @Override
    public SanPham seachBarCodeAdd(String barcode) {
        return sanPhamReposory.seachBarCodeAdd(barcode);
    }

}
