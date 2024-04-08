/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.ChiTietSP;
import com.raven.form.San_Pham;
import com.repositories.IChiTietSPRepository;
import com.repositories.IDanhMucSPRepository;
import com.repositories.IKhuyenMaiRepository;
import com.repositories.IKichCoRepository;
import com.repositories.IMauSacRepository;
import com.repositories.INSXRepository;
import com.repositories.impl.ChiTietSPRepository;
import com.repositories.impl.DanhMucSPRepository;
import com.repositories.impl.KhuyenMaiRepository;
import com.repositories.impl.KichCoRepository;
import com.repositories.impl.MauSacRepository;
import com.repositories.impl.NSXRepository;
import com.services.IChiTietSPService;
import com.viewModel.ChiTietSPViewModel;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ChiTietSPService implements IChiTietSPService{
    private IChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();
    private INSXRepository iNSX = new NSXRepository();
    private IMauSacRepository imausac = new MauSacRepository();
    private IDanhMucSPRepository idanhmuc = new DanhMucSPRepository();
    private IKichCoRepository ikichco = new KichCoRepository();
    private IKhuyenMaiRepository ikhuyenmai = new KhuyenMaiRepository();

    @Override
    public List<ChiTietSPViewModel> getAll() {
        List<ChiTietSP> list = chiTietSPRepository.getAll();
        List<ChiTietSPViewModel> lst = new ArrayList<>();
        for (ChiTietSP x : list) {
            lst.add(new ChiTietSPViewModel(x.getMa(), x.getTen(), iNSX.getbyid(x.getIdNsx()),
                    imausac.getbyid(x.getIdMauSac()), idanhmuc.getdanhmucbyid(x.getIdDanhMuc()),
                    ikichco.getbyid(x.getIdKichCo()),
                    ikhuyenmai.getbyid(x.getIdKhuyenMai()),
                    x.getSoLuongTon(), x.getGiaNhap(), x.getGiaBan(), x.getMoTa(), x.getQrCode()));
                    
        }
        return lst;
    }

    @Override
    public List<ChiTietSPViewModel> getlistbyTen(String ten) {
        List<ChiTietSP> list = chiTietSPRepository.getlistbyTen(ten);
        List<ChiTietSPViewModel> lst = new ArrayList<>();
        for (ChiTietSP x : list) {
            lst.add(new ChiTietSPViewModel(
                    x.getMa(),
                    x.getTen(),
                    iNSX.getbyid(x.getIdNsx()),
                    imausac.getbyid(x.getIdMauSac()),
                    idanhmuc.getdanhmucbyid(x.getIdDanhMuc()),
                    ikichco.getbyid(x.getIdKichCo()),
                    ikhuyenmai.getbyid(x.getIdKhuyenMai()),
                    x.getSoLuongTon(),
                    x.getGiaNhap(),
                    x.getGiaBan(),
                    x.getMoTa(),
                    x.getQrCode()));
        }

        return lst;
    }

    @Override
    public Date checkngay(String id) {
        return chiTietSPRepository.checkngay(id);
    }

    @Override
    public Date checkngay2(String id) {
        return chiTietSPRepository.checkngay(id);
    }

    @Override
    public boolean Add(ChiTietSPViewModel x) {
        List<ChiTietSP> lst = chiTietSPRepository.check(x.getMa());
        ChiTietSP ctsp = new ChiTietSP(x.getMa(), x.getTen(), x.getNsx().getId(), x.getMausac().getId(),x.getDanhmuc().getId(), x.getKichco().getId(), 0, x.getSoluongton(), x.getMota(), x.getGiaban(), x.getGianhap(), x.getQrcode());
        try {
            ChiTietSP xyy = lst.get(0);
            JOptionPane.showMessageDialog(new San_Pham(), "Không để trùng mã");

            return false;
        } catch (Exception e) {
            if (chiTietSPRepository.insert(ctsp) == 1) {
                JOptionPane.showMessageDialog(new San_Pham(), "Thêm Thành công");
                return true;
            }
            JOptionPane.showMessageDialog(new San_Pham(), "Thất bại");
            return false;
        }
    }

    @Override
    public boolean Update(String ma, ChiTietSPViewModel x) {
        ChiTietSP ctsp = new ChiTietSP(x.getMa(), x.getTen(), x.getNsx().getId(), x.getMausac().getId(),x.getDanhmuc().getId(), x.getKichco().getId(), 0, x.getSoluongton(), x.getMota(), x.getGiaban(), x.getGianhap(), x.getQrcode());
        if (chiTietSPRepository.update(ctsp, ma) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<ChiTietSPViewModel> getSPhet() {
        List<ChiTietSP> list = chiTietSPRepository.getSPhet();
        List<ChiTietSPViewModel> lst = new ArrayList<>();
        for (ChiTietSP x : list) {
            lst.add(new ChiTietSPViewModel(
                    x.getMa(),
                    x.getTen(),
                    iNSX.getbyid(x.getIdNsx()),
                    imausac.getbyid(x.getIdMauSac()),
                    idanhmuc.getdanhmucbyid(x.getIdDanhMuc()),
                    ikichco.getbyid(x.getIdKichCo()),
                    ikhuyenmai.getbyid(x.getIdKhuyenMai()),
                    x.getSoLuongTon(),
                    x.getGiaNhap(),
                    x.getGiaBan(),
                    x.getMoTa(),
                    x.getQrCode()));
        }
        return lst;
    }

    @Override
    public List<ChiTietSPViewModel> GetAll() {
        List<ChiTietSP> list = chiTietSPRepository.GetAll();
        List<ChiTietSPViewModel> lst = new ArrayList<>();
        for (ChiTietSP x : list) {
            ChiTietSPViewModel sp = new ChiTietSPViewModel();
            sp.setMa(x.getMa());
            sp.setTen(x.getTen());
            lst.add(sp);

        }
        return lst;
    }

    @Override
    public boolean Update(String ma, String id) {
        return chiTietSPRepository.Update(id, ma);
    }
    
}
