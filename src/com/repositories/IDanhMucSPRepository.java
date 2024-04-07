/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.repositories;

import com.models.DanhMucSP;
import java.util.List;

/**
 *
 * @author user
 */
public interface IDanhMucSPRepository {
    public List<DanhMucSP> getAll();

    public int insert(DanhMucSP x);

    public int update(DanhMucSP dmsp, int id);

    public int delete(int id);

    public DanhMucSP getdanhmucbyid(int id);
}
