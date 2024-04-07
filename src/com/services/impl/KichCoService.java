/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.impl;

import com.models.KichCo;
import com.repositories.IKichCoRepository;
import com.repositories.impl.KichCoRepository;
import com.services.IKichCoService;
import com.viewModel.Objecttt;
import java.util.List;

/**
 *
 * @author user
 */
public class KichCoService implements IKichCoService{
    private IKichCoRepository kichCoRepository = new KichCoRepository();

    @Override
    public List<KichCo> getAll() {
        return kichCoRepository.getAll();
    }

    @Override
    public String Add(Objecttt x) {
        KichCo kichco = new KichCo(x.getId(), x.getTen());
        int xxx = kichCoRepository.insert(kichco);
        if (xxx == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Update(Objecttt x, int id) {
        KichCo kichCo = new KichCo(x.getId(), x.getTen());
        int xxx = kichCoRepository.update(kichCo, id);
        if (xxx == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Delete(int id) {
        if (kichCoRepository.delete(id) == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public KichCo getbyid(int id) {
        return kichCoRepository.getbyid(id);
    }
    
}
