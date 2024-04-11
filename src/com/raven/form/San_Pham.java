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
import com.viewModel.Objecttt;
import com.viewModel.objectSp;
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

    /**
     * Creates new form San_Pham
     */
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
        loadtabledanhmuc();
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
            barcode.renderBarcode("D:\\Qrcode\\" + x.getDanhmuc().getTen()+ ".png");
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

        String mota = txtMoTa.getText();
        if (txtMoTa.getText().equals("")) {
            mota = "không có";
        }
        NSX nsx = (NSX) cbbNSX.getSelectedItem();
        DanhMucSP danhmuc = (DanhMucSP) cbbDanhMuc.getSelectedItem();
        MauSac mausac = (MauSac) cbbMauSac.getSelectedItem();
        KichCo kichco = (KichCo) cbbKichCo.getSelectedItem();
        int soluong;
        try {
            soluong = Integer.parseInt(txtSLTon.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
        }
        soluong = Integer.parseInt(txtSLTon.getText());
        ChiTietSPViewModel ctsp = new ChiTietSPViewModel(txtMaSP.getText(), nsx, mausac, danhmuc, kichco, null, soluong, mota, zenbarcode());
        return ctsp;
    }

    private ChiTietSPViewModel getdataTB(int row) {
        String ma = tblCTSanPham3.getValueAt(row, 0).toString();
        NSX nsx = (NSX) tblCTSanPham3.getValueAt(row, 1);
        MauSac ms = (MauSac) tblCTSanPham3.getValueAt(row, 2);
        DanhMucSP dmsp = (DanhMucSP) tblCTSanPham3.getValueAt(row, 3);
        KichCo kc = (KichCo) tblCTSanPham3.getValueAt(row, 4);
        KhuyenMai km = (KhuyenMai) tblCTSanPham3.getValueAt(row, 5);
        int soluong = (int) tblCTSanPham3.getValueAt(row, 6);
        String mota = tblCTSanPham3.getValueAt(row, 8).toString();
        String barcode = tblCTSanPham3.getValueAt(row, 9).toString();
        return new ChiTietSPViewModel(ma, nsx, ms, dmsp, kc, km, soluong, mota, barcode);
    }

//sản phẩm
    private void loadtabledanhmuc() {
        DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0);
        for (DanhMucSP x : iDanhMucSPServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen(), x.getGiaNhap(), x.getGiaBan()});
        }
    }

    private objectSp getdatdtb(int row) {
        if (row == -1) {
            return null;
        }
        int id = (int) tblSP.getValueAt(row, 0);
        String ten = (String) tblSP.getValueAt(row, 1);
        double giaNhap = (double) tblSP.getValueAt(row, 2);
        double giaBan = (double) tblSP.getValueAt(row, 3);

        return new objectSp(id, ten, giaNhap, giaBan);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSanPhamTen = new javax.swing.JTextField();
        txtGiaNhapSP = new javax.swing.JTextField();
        txtGiaBanSP = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtMaSP = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        btnSave2 = new javax.swing.JButton();
        btnInQrcode2 = new javax.swing.JButton();
        btnUpdate2 = new javax.swing.JButton();
        btnNew2 = new javax.swing.JButton();
        cbbNSX = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cbbKichCo = new javax.swing.JComboBox<>();
        txtSLTon = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCTSanPham3 = new javax.swing.JTable();
        timKiem3 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1124, 666));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1124, 666));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Tên sản phẩm");

        jLabel2.setText("Giá nhập");

        jLabel3.setText("Giá bán");

        btnThem.setText("Lưu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");

        jButton4.setText("Mới");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSanPhamTen)
                            .addComponent(txtGiaNhapSP)
                            .addComponent(txtGiaBanSP, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))))
                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnThem)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSanPhamTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtGiaNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtGiaBanSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jButton4)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel4);

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(814, 280));

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel28.setText("Mã SP");

        jLabel29.setText("NSX");

        jLabel30.setText("Sản phẩm");

        jLabel31.setText("Số lượng tồn");

        jLabel32.setText("Màu sắc");

        jLabel33.setText("Mô tả");

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSave2.setText("Lưu");
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        btnInQrcode2.setText("In Qrcode");
        btnInQrcode2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInQrcode2ActionPerformed(evt);
            }
        });

        btnUpdate2.setText("Sửa");
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });

        btnNew2.setText("Mới");
        btnNew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew2ActionPerformed(evt);
            }
        });

        cbbNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel34.setText("Kích cỡ");

        cbbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtSLTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLTonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNew2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnUpdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInQrcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 88, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addComponent(jLabel31)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSLTon, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbbDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel33))
                            .addGap(7, 7, 7))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28)
                                .addComponent(jLabel29)
                                .addComponent(jLabel32))
                            .addGap(0, 0, Short.MAX_VALUE)))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtSLTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel33))
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInQrcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnNew2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));

        tblCTSanPham3.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCTSanPham3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSanPham3tblCTSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblCTSanPham3);

        timKiem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiem3timKiemActionPerformed(evt);
            }
        });

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setText("Thêm thuộc tính");
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(timKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(timKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chi tiết sản phẩm", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel37jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37jLabel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37jLabel12MouseClicked

    private void timKiem3timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiem3timKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timKiem3timKiemActionPerformed

    private void tblCTSanPham3tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPham3tblCTSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblCTSanPham3.getSelectedRow();
        if (row == -1) {
            return;
        }
        ChiTietSPViewModel x = getdataTB(row);
        txtMaSP.setText(x.getMa());
        txtSLTon.setText(x.getSoluongton() + "");
        txtMoTa.setText(x.getMota());
        cbbDanhMuc.setSelectedIndex(getindexdanhmuc(x));
        cbbMauSac.setSelectedIndex(getindexmausac(x));
        cbbNSX.setSelectedIndex(getindexnsx(x));
        cbbKichCo.setSelectedIndex(getindexsize(x));
    }//GEN-LAST:event_tblCTSanPham3tblCTSanPhamMouseClicked

    private void txtSLTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLTonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLTonActionPerformed

    private void btnNew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNew2ActionPerformed

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void btnInQrcode2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInQrcode2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInQrcode2ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (getdatafrom() == null) {
            return;
        }
        JOptionPane.showMessageDialog(this, iDanhMucSPServices.Add(getdatafrom()));
        loadtabledanhmuc();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
        int row = tblSP.getSelectedRow();
        if (row == -1) {
            return;
        }
        objectSp x = getdatdtb(row);
        txtSanPhamTen.setText(x.getTen());
        txtGiaNhapSP.setText(String.valueOf(x.getGiaNhap()));
        txtGiaBanSP.setText(String.valueOf(x.getGiaBan()));
    }//GEN-LAST:event_tblSPMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row = tblSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn 1 dòng để sửa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không") != JOptionPane.YES_OPTION) {
            return;
        }
        int id = (int) tblSP.getValueAt(row, 0);
            JOptionPane.showMessageDialog(this, iDanhMucSPServices.Update(getdatafrom(), id));
            loadtabledanhmuc();
    }//GEN-LAST:event_jButton2ActionPerformed
    private objectSp getdatafrom() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txtSanPhamTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, " Bạn chưa nhập tên thuộc tính!");
            txtSanPhamTen.requestFocus();
            return null;
        }

        if (txtSanPhamTen.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính sản phẩm không quá 30 kí tự!");
            txtSanPhamTen.requestFocus();
            return null;
        }

        if (p.matcher(txtSanPhamTen.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính sản phẩm phải là chữ!");
            txtSanPhamTen.requestFocus();
            return null;
        }
        if (txtGiaNhapSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá nhập!");
            return null;
        }

        try {
            Double.valueOf(txtGiaNhapSP.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số!");
            return null;
        }

        if (Double.valueOf(txtGiaNhapSP.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0!");
            return null;
        }
// giá bán
        if (txtGiaBanSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá bán!");
            return null;
        }

        try {
            Double.valueOf(txtGiaBanSP.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số!");
            return null;
        }

        if (Double.valueOf(txtGiaBanSP.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!");
            return null;
        }
        double gianhap;
        double giaban;
        try {
            gianhap = Double.parseDouble(txtGiaNhapSP.getText());
            giaban = Double.parseDouble(txtGiaBanSP.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
        }
        gianhap = Double.parseDouble(txtGiaNhapSP.getText());
        giaban = Double.parseDouble(txtGiaBanSP.getText());
        return new objectSp(0, txtSanPhamTen.getText(), gianhap, giaban);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInQrcode2;
    private javax.swing.JButton btnNew2;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbKichCo;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCTSanPham3;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField timKiem3;
    private javax.swing.JTextField txtGiaBanSP;
    private javax.swing.JTextField txtGiaNhapSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSLTon;
    private javax.swing.JTextField txtSanPhamTen;
    // End of variables declaration//GEN-END:variables

    private void innitTable() {
        defaultTableModel = (DefaultTableModel) tblCTSanPham3.getModel();
        defaultTableModel.setColumnCount(0);
        defaultTableModel.addColumn("Mã");
        defaultTableModel.addColumn("NSX");
        defaultTableModel.addColumn("Màu sắc");
        defaultTableModel.addColumn("Sản phẩm");
        defaultTableModel.addColumn("Size");
        defaultTableModel.addColumn("Khuyến mãi");
        defaultTableModel.addColumn("Số lượng tồn");
        defaultTableModel.addColumn("Được giảm");
        defaultTableModel.addColumn("Mô tả");
        defaultTableModel.addColumn("Mã Qr");

    }

    private void loadData(List<ChiTietSPViewModel> lst) {
        defaultTableModel = (DefaultTableModel) tblCTSanPham3.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietSPViewModel x : lst) {
            defaultTableModel.addRow(new Object[]{
                x.getMa(),
                x.getNsx(),
                x.getMausac(),
                x.getDanhmuc(),
                x.getKichco(),
                x.getKhuyenmai(),
                x.getSoluongton(),
                String.format("%.0f", x.getKhuyenmai().getGiaTriGiam()) + " " + x.getKhuyenmai().getHinhThucKM(),
                x.getMota(),
                x.getQrcode()
            });
        }
        System.out.println();
    }

    public void clearForm() {
        txtMaSP.setText("");
        txtSLTon.setText("");
        txtMoTa.setText("");
    }

}
