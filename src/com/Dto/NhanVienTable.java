/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Dto;

import com.models.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tggdd
 */
public class NhanVienTable extends AbstractTableModel{
    private final String name[]={"ID","Mã NV","Tên","Địa chỉ","Số điện thoại","Tài khoản","Mật khẩu"};
    private final Class classes[]={String.class,String.class, String.class,String.class,String.class,String.class,String.class};  
    ArrayList<User> users = new ArrayList<>();
    
    public NhanVienTable(ArrayList<User> users){
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0 -> {
                return users.get(rowIndex).getId();
            }
            case 1 -> {
                return users.get(rowIndex).getMaNV();
            }
            case 2 -> {
                StringBuilder ten = new StringBuilder();
                ten.append(users.get(rowIndex).getHo())
                        .append(" ")
                        .append(users.get(rowIndex).getTenDem())
                        .append(" ")
                        .append(users.get(rowIndex).getTen());
                return ten.toString() ;
            }
            case 3 -> {
                return users.get(rowIndex).getDiaChi();
            }
            case 4 -> {
                return users.get(rowIndex).getSdt();
            }
            
            case 5 -> {
                return users.get(rowIndex).getTaiKhoan();
            }
            
            case 6 -> {
                return users.get(rowIndex).getMatKhau();
            }
   
            default -> {
                    return null;
            }
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
