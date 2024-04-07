/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.repositories;

import com.models.NSX;
import java.util.List;

/**
 *
 * @author user
 */
public interface INSXRepository {
       public List<NSX> getAll();

    public int insert(NSX x);

    public int update(NSX x, int id);

    public int delete(int id);

    public NSX getbyid(int id);
}
