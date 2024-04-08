/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Dto;

import com.models.KhachHang;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tggdd
 */
public class KhachHangTable extends AbstractTableModel{
    private String name[]={"ID","Mã KH","Tên","Địa chỉ","Số điện thoại"};
    private Class classes[]={String.class,String.class, String.class,String.class,String.class};  
    ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
    
    public KhachHangTable(ArrayList<KhachHang> khachHangs){
        this.khachHangs = khachHangs;
    }

    @Override
    public int getRowCount() {
        return khachHangs.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return khachHangs.get(rowIndex).getId(); 
            //Cột mã nhân viên
            case 1: return khachHangs.get(rowIndex).getMaKH(); 
            //Cột 
            case 2:
                StringBuilder ten = new StringBuilder();
                ten.append(khachHangs.get(rowIndex).getHo())
                        .append(" ")
                        .append(khachHangs.get(rowIndex).getTenDem())
                        .append(" ")
                        .append(khachHangs.get(rowIndex).getTen());
                return ten.toString() ;
            //Cột 
            case 3: return khachHangs.get(rowIndex).getDiaChi();
            //Cot 
            case 4: return khachHangs.get(rowIndex).getSdt();
   
            default :return null;
        }
    }
     @Override
    public Class getColumnClass(int columnIndex)
    {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column)
    {
        return name[column];
    }
}
