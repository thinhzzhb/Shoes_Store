/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.repositories;

import com.models.KhuyenMai;
import java.util.List;

/**
 *
 * @author user
 */
public interface IKhuyenMaiRepository {
    public List<KhuyenMai> GetAll();

    public boolean Add(KhuyenMai km);

    public boolean Update(KhuyenMai km, String id);

    public boolean Delete(String id);

    public String checktrung(String ten);

    public List<KhuyenMai> GetOnebyBD(String date);

    public List<KhuyenMai> GetOnebyKT(String date);

    public List<KhuyenMai> GetOnebyALL(String datedb, String datekt);

    public List<KhuyenMai> GetOnebyten(String ten);

    public boolean UpdateTT();

    public boolean UpdateTT2();

    public KhuyenMai getbyid(int id);
}
