/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import com.model.HoaDon;
import com.viewModel.HoaDonChiTietViewModel;
import com.viewModel.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonService {
    List<HoaDonViewModel> getListHoaDon();
    
    List<HoaDonViewModel> getHoaDonByTen(String name);
    
    List<HoaDonViewModel> TimKiemTheoTTHoaDon(int trangThai);
    
    List<HoaDonViewModel> GetTimNTT(String NgayTT);
    
    List<HoaDonChiTietViewModel> getHDCT(String ma);
        
}
