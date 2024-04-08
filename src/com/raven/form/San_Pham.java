/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.raven.form;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.models.DanhMucSP;
import com.models.KhuyenMai;
import com.models.KichCo;
import com.models.MauSac;
import com.models.NSX;
import com.services.IChiTietSPService;
import com.services.IDanhMucSPService;
import com.services.IKhuyenMaiService;
import com.services.IKichCoService;
import com.services.IMauSacService;
import com.services.INSXService;
import com.services.impl.ChiTietSPService;
import com.services.impl.DanhMucSPService;
import com.services.impl.KhuyenMaiService;
import com.services.impl.KichCoService;
import com.services.impl.MauSacService;
import com.services.impl.NSXService;
import com.viewModel.ChiTietSPViewModel;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class San_Pham extends javax.swing.JPanel {
    
    private IChiTietSPService iChiTietSPServices;
    private INSXService iNSXServices;
    private IMauSacService iMauSacServices;
    private IDanhMucSPService iDanhMucSPServices;
    private IKichCoService iKichCoServices;
    private IKhuyenMaiService iKhuyenmaiService;
    
    DefaultTableModel defaultTableModel;
    private boolean hish = false;
    String sp = null;

    /** Creates new form San_Pham */
    public San_Pham() {
        initComponents();
        setOpaque(false);
        innitTable();
        
        iChiTietSPServices = new ChiTietSPService();
        iNSXServices = new NSXService();
        iMauSacServices = new MauSacService();
        iDanhMucSPServices = new DanhMucSPService();
        iKichCoServices = new KichCoService();
        iKhuyenmaiService = new KhuyenMaiService();
        initcbo();
        loadData(iChiTietSPServices.getAll());
    }
    
    public void initcbo() {
        List<NSX> listnsx = iNSXServices.getAll();
        cbbNSX.setModel(new DefaultComboBoxModel(listnsx.toArray()));

        List<MauSac> listms = iMauSacServices.getAll();
        cbbMauSac.setModel(new DefaultComboBoxModel(listms.toArray()));

        List<DanhMucSP> listdmsp = iDanhMucSPServices.getAll();
        cbbDanhMuc.setModel(new DefaultComboBoxModel(listdmsp.toArray()));

        List<KichCo> listkc = iKichCoServices.getAll();
        cbbKichCo.setModel(new DefaultComboBoxModel(listkc.toArray()));
    }

    private int getindexmausac(ChiTietSPViewModel x) {
        List<MauSac> lst = iMauSacServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getMausac().getId()) {
                index = i;
            }
        }
        return index;
    }
    
    private void xuatbarcode(ChiTietSPViewModel x) {
        System.out.println(x.toString());
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(x.getQrcode());
            barcode.setI(11.0f);
            barcode.renderBarcode("D:\\Qrcode\\" + x.getTen() + ".png");
            System.out.println("xuất thành công");
        } catch (Exception e) {
            System.out.println("xuất thất bại");
        }
    }

    public static void generateQRcode(String data, String path, Map map, int h, int w) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, w, h);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
        } catch (Exception ex) {
            Logger.getLogger(San_Pham.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int getindexnsx(ChiTietSPViewModel x) {
        List<NSX> lst = iNSXServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getNsx().getId()) {
                index = i;
            }
        }
        return index;
    }
    private int getindexdanhmuc(ChiTietSPViewModel x) {
        List<DanhMucSP> lst = iDanhMucSPServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getDanhmuc().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexsize(ChiTietSPViewModel x) {
        List<KichCo> lst = iKichCoServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getKichco().getId()) {
                index = i;
            }
        }
        return index;
    }

    private String zenbarcode() {
        Random random = new Random();
        int ran = random.nextInt(999999);
        int dom = random.nextInt(999999);
        return ran + "" + dom;
    }

    private ChiTietSPViewModel getdadtafrom() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txtMaSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm!");
            return null;

        }
        if (txtMaSP.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không quá 15 kí tự!");
            return null;
        }
//  tên sp
        if (txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm!");
            return null;
        }

        if (p.matcher(txtTenSP.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm phải là chữ!");
            return null;
        }

        if (txtTenSP.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không quá 30 kí tự!");
            return null;
        }
// Số lượng tồn
        if (txtSLTon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng tồn!");
            return null;
        }

        try {
            Integer.valueOf(txtSLTon.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số!");
            return null;
        }

// giá nhập
        if (txtGiaNhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá nhập!");
            return null;
        }

        try {
            Double.valueOf(txtGiaNhap.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số!");
            return null;
        }

        if (Double.valueOf(txtGiaNhap.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0!");
            return null;
        }
// giá bán
        if (txtGiaBan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá bán!");
            return null;
        }

        try {
            Double.valueOf(txtGiaBan.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số!");
            return null;
        }

        if (Double.valueOf(txtGiaBan.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!");
            return null;
        }
        
        String mota = txtMoTa.getText();
        if (txtMoTa.getText().equals("")) {
            mota = "không có";
        }
        NSX nsx = (NSX) cbbNSX.getSelectedItem();
        DanhMucSP danhmuc = (DanhMucSP) cbbDanhMuc.getSelectedItem();
        MauSac mausac = (MauSac) cbbMauSac.getSelectedItem();
        KichCo kichco = (KichCo) cbbKichCo.getSelectedItem();
        double gianhap;
        double giaban;
        int soluong;
        try {
            gianhap = Double.parseDouble(txtGiaNhap.getText());
            giaban = Double.parseDouble(txtGiaBan.getText());
            soluong = Integer.parseInt(txtSLTon.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
        }
        gianhap = Double.parseDouble(txtGiaNhap.getText());
        giaban = Double.parseDouble(txtGiaBan.getText());
        soluong = Integer.parseInt(txtSLTon.getText());
        ChiTietSPViewModel ctsp = new ChiTietSPViewModel(txtMaSP.getText(), txtTenSP.getText(), nsx, mausac, danhmuc, kichco, null, soluong, gianhap, giaban, mota, zenbarcode());
        return ctsp;
    }

    private ChiTietSPViewModel getdataTB(int row) {
        String ma = tblCTSanPham.getValueAt(row, 0).toString();
        String ten = tblCTSanPham.getValueAt(row, 1).toString();
        NSX nsx = (NSX) tblCTSanPham.getValueAt(row, 2);
        MauSac ms = (MauSac) tblCTSanPham.getValueAt(row, 3);
        DanhMucSP dmsp = (DanhMucSP) tblCTSanPham.getValueAt(row, 4);
        KichCo kc = (KichCo) tblCTSanPham.getValueAt(row, 5);
        KhuyenMai km = (KhuyenMai) tblCTSanPham.getValueAt(row, 6);
        int soluong = (int) tblCTSanPham.getValueAt(row, 7);
        double gianhap = (double) tblCTSanPham.getValueAt(row, 8);
        double giaban = (double) tblCTSanPham.getValueAt(row, 9);
        String mota = tblCTSanPham.getValueAt(row, 11).toString();
        String barcode = tblCTSanPham.getValueAt(row, 12).toString();
        return new ChiTietSPViewModel(ma, ten, nsx, ms, dmsp, kc, km, soluong, gianhap, giaban, mota, barcode);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtTenSP = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        cbbNSX = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbKichCo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSLTon = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSanPham = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1124, 666));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1124, 666));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(814, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản lý sản phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(473, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(478, 478, 478))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(814, 280));

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên SP");

        jLabel3.setText("Mã SP");

        jLabel4.setText("NSX");

        jLabel5.setText("Danh mục");

        jLabel6.setText("Số lượng tồn");

        jLabel7.setText("Màu sắc");

        jLabel8.setText("Mô tả");

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        cbbNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Kích cỡ");

        cbbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Giá bán");

        jLabel11.setText("Giá nhập");

        txtSLTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLTonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(0, 276, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSLTon)
                                        .addComponent(txtGiaBan)
                                        .addComponent(txtGiaNhap)
                                        .addComponent(txtMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbbDanhMuc, 0, 229, Short.MAX_VALUE)
                                        .addComponent(cbbKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSLTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        tblCTSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCTSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTSanPham);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Thêm thuộc tính");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        ChiTietSPViewModel x = getdadtafrom();
        if (x == null) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm sản phẩm ?", "Thêm sản phẩm mới", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            if (Integer.valueOf(txtSLTon.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải lớn hơn 0!");
                return;
            }
            boolean kq = iChiTietSPServices.Add(x);
            if (kq == true) {
                loadData(iChiTietSPServices.getAll());
//            xuatbarcode(x);
                String data = x.getQrcode();
                String path = "D:\\Da1_2024\\Shoes_Store//Qrcode//" + x.getTen() + ".png";
                Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                generateQRcode(data, path, hashMap, 200, 200);
                System.out.println("QR Code created successfully.");
            }
       }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSLTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLTonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLTonActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblCTSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }
        ChiTietSPViewModel x = getdataTB(row);
        txtMaSP.setText(x.getMa());
        txtTenSP.setText(x.getTen());
        txtSLTon.setText(x.getSoluongton() + "");
        txtGiaBan.setText(x.getGiaban() + "");
        txtGiaNhap.setText(x.getGianhap() + "");
        txtMoTa.setText(x.getMota());
        cbbDanhMuc.setSelectedIndex(getindexdanhmuc(x));
        cbbMauSac.setSelectedIndex(getindexmausac(x));
        cbbNSX.setSelectedIndex(getindexnsx(x));
        cbbKichCo.setSelectedIndex(getindexsize(x));
    }//GEN-LAST:event_tblCTSanPhamMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        loadData(iChiTietSPServices.getAll());
        clearForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
//        new Them_thuoc_tinh(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbKichCo;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblCTSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSLTon;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

    private void innitTable() {
        defaultTableModel = (DefaultTableModel) tblCTSanPham.getModel();
        defaultTableModel.setColumnCount(0);
        defaultTableModel.addColumn("Mã");
        defaultTableModel.addColumn("Tên");
        defaultTableModel.addColumn("NSX");
        defaultTableModel.addColumn("Màu sắc");
        defaultTableModel.addColumn("Danh mục");
        defaultTableModel.addColumn("Size");
        defaultTableModel.addColumn("Khuyến mãi");
        defaultTableModel.addColumn("Số lượng tồn");
        defaultTableModel.addColumn("Giá nhập");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Được giảm");
        defaultTableModel.addColumn("Mô tả");
        defaultTableModel.addColumn("Mã Qr");

    }

    private void loadData(List<ChiTietSPViewModel> lst) {
        defaultTableModel = (DefaultTableModel) tblCTSanPham.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietSPViewModel x : lst) {
            defaultTableModel.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getNsx(),
                x.getMausac(),
                x.getDanhmuc(),
                x.getKichco(),
                x.getKhuyenmai(),
                x.getSoluongton(),
                x.getGianhap(),
                x.getGiaban(),
                String.format("%.0f", x.getKhuyenmai().getGiaTriGiam()) + " " + x.getKhuyenmai().getHinhThucKM(),
                x.getMota(),
                x.getQrcode()
            });
        }
        System.out.println();
    }
    
    public void clearForm() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSLTon.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtMoTa.setText("");
    }

}
