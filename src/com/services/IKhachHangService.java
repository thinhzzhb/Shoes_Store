/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import com.models.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author tggdd
 */
public interface IKhachHangService {
    ArrayList<KhachHang> getAll();
    void add(KhachHang khachHang);
    void update(Integer id, KhachHang khachHang);
    void delete(Integer id);
}
