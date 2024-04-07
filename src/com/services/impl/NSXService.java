/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.NSX;
import com.repositories.INSXRepository;
import com.repositories.impl.NSXRepository;
import com.services.INSXService;
import com.viewModel.Objecttt;
import java.util.List;

/**
 *
 * @author user
 */
public class NSXService implements INSXService{
    private INSXRepository nSXRepository = new NSXRepository();

    @Override
    public List<NSX> getAll() {
        return nSXRepository.getAll();
    }

    @Override
    public String Add(Objecttt x) {
        NSX nsx = new NSX(x.getId(), x.getTen());
        int xxx = nSXRepository.insert(nsx);
        if (xxx == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Update(Objecttt x, int id) {
        NSX nsx = new NSX(x.getId(), x.getTen());
        int xxx = nSXRepository.update(nsx, id);
        if (xxx == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Delete(int id) {
        if (nSXRepository.delete(id) == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public NSX getbyid(int id) {
        return nSXRepository.getbyid(id);
    }
    
}
