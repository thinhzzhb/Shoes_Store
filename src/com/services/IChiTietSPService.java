/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.services;

import com.viewModel.ChiTietSPViewModel;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface IChiTietSPService {
    public List<ChiTietSPViewModel> getAll();

    public List<ChiTietSPViewModel> getlistbyTen(String ten);

    boolean Add(ChiTietSPViewModel x);

    boolean Update(String ma, ChiTietSPViewModel x);

    public List<ChiTietSPViewModel> getSPhet();

    public Date checkngay(String id);

    public Date checkngay2(String id);

    public List<ChiTietSPViewModel> GetAll();

    public boolean Update(String ma, String id);
}
