/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.services;

import com.models.KichCo;
import com.viewModel.Objecttt;
import java.util.List;

/**
 *
 * @author user
 */
public interface IKichCoService {
    List<KichCo> getAll();

    String Add(Objecttt x);

    String Update(Objecttt x, int id);

    String Delete(int id);

    KichCo getbyid(int id);
}
