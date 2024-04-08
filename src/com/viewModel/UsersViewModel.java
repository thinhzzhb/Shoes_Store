/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viewModel;

/**
 *
 * @author Admin
 */
public class UsersViewModel {
    private String ten, tendem, ho;
    private String ngaysinh;
    private Integer gioitinh;
    private String sdt;
    private String tk;
    private String mk;
    private String email;
    private Integer TT;

    public UsersViewModel() {
    }

    public UsersViewModel(String ten, String tendem, String ho, String ngaysinh, Integer gioitinh, String sdt, String tk, String mk, String email, Integer TT) {
        this.ten = ten;
        this.tendem = tendem;
        this.ho = ho;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.tk = tk;
        this.mk = mk;
        this.email = email;
        this.TT = TT;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTendem() {
        return tendem;
    }

    public void setTendem(String tendem) {
        this.tendem = tendem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTT() {
        return TT;
    }

    public void setTT(Integer TT) {
        this.TT = TT;
    }

    public String getgt(int gt) {
        return gt == 1 ? "Nam" : "Nữ";
    }
}
