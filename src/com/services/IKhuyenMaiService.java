/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.services;

import com.models.KhuyenMai;
import com.viewModel.KhuyenMaiViewModel;
import java.util.List;

/**
 *
 * @author user
 */
public interface IKhuyenMaiService {
    public List<KhuyenMaiViewModel> GetALL();

    public boolean Add(KhuyenMaiViewModel km);

    public boolean Update(KhuyenMaiViewModel km, String id);

    public boolean Delete(String id);

    public String checktrung(String ten);

    public List<KhuyenMaiViewModel> GetOnebyBD(String date);

    public List<KhuyenMaiViewModel> GetOnebyKT(String date);

    public List<KhuyenMaiViewModel> GetOnebyALL(String datedb, String datekt);

    public List<KhuyenMaiViewModel> GetOnebyten(String ten);

    public boolean UpdateTT();

    public boolean UpdateTT2();

    public List<KhuyenMai> getlist();
    
}
