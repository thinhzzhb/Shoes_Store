/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.models.DanhMucSP;
import com.models.HoaDon;
import com.models.SanPham;
import com.services.impl.SanPhamService;
import com.services.IDanhMucSPService;
import com.services.IHoaDonService;
import com.services.ISanPhamService;
import com.services.impl.DanhMucSPService;
import com.services.impl.HoaDonService;
import com.viewModel.CurrentUser;
import com.viewModel.HoaDonChiTietViewModel;
import com.viewModel.HoaDonViewModel;
import com.viewModel.GioHangViewModel;
import com.viewModel.SanPhamViewModel;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Giao_dich extends javax.swing.JPanel implements Runnable, ThreadFactory {

    /**
     * Creates new form San_Pham
     */
    private DefaultTableModel _dtm;
    private DefaultTableModel _dtmGioHang;
    private DefaultComboBoxModel combox;
    private IHoaDonService service;
    private ISanPhamService spService;
    private List<GioHangViewModel> _lstGioHang = new ArrayList<>();
    private IDanhMucSPService danhMucSer;
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    
    public Giao_dich() {
        initComponents();
        setOpaque(false);
        service = new HoaDonService();
        spService = new SanPhamService();
        danhMucSer = new DanhMucSPService();
        _dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
        loadTBSP();
        loadCBDanhmuc();
        loadListHD();
        initWebCam();
    }
    
    private void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
//        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        
        pnlWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 220, 150));
        executor.execute(this);
    }
    
    private void loadTBSP() {
        _dtm = (DefaultTableModel) tblSanPhamHD.getModel();
        _dtm.setRowCount(0);
        List<SanPhamViewModel> getList = spService.getListSanPham();
        for (SanPhamViewModel x : getList) {
            _dtm.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getMauSac().getTen(),
                String.format("%.0f", x.getKhuyenMai().getGiaTriGiam()),
                x.getKhuyenMai().getHinhThucKM(),
                x.getKichCo().getTen(),
                String.format("%.0f", x.getGiaBan()),
                x.getSoLuongTon(),});
        }
    }
    
    private String getTrangThaiHD(int TrangThai) {
        if (TrangThai == 0) {
            return "Chờ thanh toán";
        }
        if (TrangThai == 1) {
            return "Đã thanh toán";
        }
        
        return null;
    }

    //Load dữ liệu lên tbl hóa đơn
    private void loadListHD() {
        _dtm = (DefaultTableModel) tblHoaDon.getModel();
        _dtm.setRowCount(0);
        List<HoaDonViewModel> _lstHD = service.getListHD(0);
        for (HoaDonViewModel hm : _lstHD) {
            _dtm.addRow(new Object[]{
                hm.getMa(),
                hm.getUs().getTen(),
                hm.getNgayTao(),
                getTrangThaiHD(hm.getTinhTrang())
            });
        }
    }
    
    private void loadCBDanhmuc() {
        combox = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        List<DanhMucSP> listDanhMuc = danhMucSer.getAll();
        listDanhMuc.forEach(danhMuc -> combox.addElement(danhMuc.getTen()));
        
    }
    
    private void loadTBGioHang() {
        _dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
        _dtmGioHang.setRowCount(0);
        for (GioHangViewModel gh : _lstGioHang) {
            _dtmGioHang.addRow(new Object[]{
                gh.getMaSP(),
                gh.getTenSP(),
                gh.getMauSac(),
                gh.getKichCo(),
                gh.getSoLuong(),
                String.format("%.0f", gh.getDonGia()),
                String.format("%.0f", gh.getGiamGia()) + " " + gh.getHinhThucGiamGia(),
                String.format("%.0f", gh.getThanhTien()),});
        }
    }

    //Hàm tạo hóa đơn mới
    private HoaDonViewModel addHD() {
        HoaDonViewModel h = new HoaDonViewModel();
        Random r = new Random();
        h.setMa("HD" + r.nextInt());
        Timestamp time = new Timestamp(System.currentTimeMillis());
        h.setNgayTao(time);
        
        return h;
    }
    
    private void clear() {
        lblTenKhach.setText("");
        lblTongTien.setText(String.valueOf(0));
        txtTienKhachDua.setText("");
        lblTienDu.setText("");
        lblSDT.setText("");
        lblGiamGia.setText("");
        txtGhiChu.setText("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPhamHD = new javax.swing.JTable();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        txtSearchSP = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTienDu = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTienCanTra = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTenKhach = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        btnChonKhach = new javax.swing.JButton();
        btnXN = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        pnlWebcam = new javax.swing.JPanel();

        jMenuItem1.setText("Nhập số lượng");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setPreferredSize(new java.awt.Dimension(1124, 666));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1124, 666));

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(650, 600));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPhamHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Màu sắc ", "Giá trị giảm", "Hình thức", "Kích cỡ", "Giá bán", "Số lượng tồn"
            }
        ));
        tblSanPhamHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamHDMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPhamHD);

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbbDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDanhMucItemStateChanged(evt);
            }
        });

        txtSearchSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbGioHang.setBackground(new java.awt.Color(255, 245, 255));
        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu Sắc", "Size", "Số Lượng", "Đơn Giá", "Được giảm", "Thành Tiền"
            }
        ));
        tbGioHang.setComponentPopupMenu(jPopupMenu1);
        tbGioHang.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tbGioHang);

        jButton4.setBackground(new java.awt.Color(125, 224, 237));
        jButton4.setText("Xóa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(125, 224, 237));
        jButton5.setText("Xóa tất cả");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(64, 64, 64))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButton4)
                .addGap(33, 33, 33)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jButton3.setText("Tạo đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Thanh toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Tổng tiền");

        jLabel7.setText("Tiền khách đưa");

        jLabel8.setText("Tiền dư");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel9.setText("Số ĐT");

        lblTongTien.setText("_");

        lblTienDu.setText("_");

        jLabel5.setText("Tên khách hàng");

        jLabel2.setText("Giảm giá");

        lblGiamGia.setText("_");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        jLabel12.setText("Ghi Chú");

        jLabel13.setText("Khách cần trả");

        lblTienCanTra.setText("_");

        lblTenKhach.setText("_");

        lblSDT.setText("_");

        btnChonKhach.setBackground(new java.awt.Color(204, 204, 255));
        btnChonKhach.setText("Chọn");
        btnChonKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachActionPerformed(evt);
            }
        });

        btnXN.setBackground(new java.awt.Color(204, 204, 255));
        btnXN.setText("Xác nhận");
        btnXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(29, 29, 29)
                                .addComponent(lblTienCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(58, 58, 58)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblTienDu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(lblGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnXN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(33, 33, 33))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnChonKhach)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblTenKhach))
                    .addComponent(btnChonKhach))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblSDT)
                    .addComponent(btnXN))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGiamGia)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTienCanTra))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblTienDu))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel6.setLayout(null);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa đơn", "Tên nhân viên", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblHoaDon);

        jPanel6.add(jScrollPane7);
        jScrollPane7.setBounds(18, 18, 452, 130);

        pnlWebcam.setPreferredSize(new java.awt.Dimension(190, 402));
        pnlWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(pnlWebcam);
        pnlWebcam.setBounds(470, 10, 220, 150);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().trim());
                Double khachCanTra = Double.parseDouble(lblTienCanTra.getText());
                Double tienThuaTraKhach = tienKhachDua - khachCanTra;
                DecimalFormat format = new DecimalFormat("#,###");
                lblTienDu.setText(String.valueOf(format.format(tienThuaTraKhach)));
                
            } catch (Exception e) {
                
            }
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        HoaDonViewModel hd = addHD();
        int idNV = CurrentUser.getInstance().getIdNV();
//        int idNV = 1;
        Integer addHD = service.saveHD(hd, idNV);
        if (addHD > 0) {
            System.out.println("Thêm thành công");
            List<HoaDonViewModel> _lsthdVM = service.getListHD(1);
            _lsthdVM.clear();
            loadListHD();
        } else {
            System.out.println("Thêm thất bại");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private HoaDonChiTietViewModel inputHDCT(Double dongia, int soluong) {
        HoaDonChiTietViewModel hdvm = new HoaDonChiTietViewModel();
        hdvm.setDonGia(dongia);
        hdvm.setSoluong(soluong);
        
        return hdvm;
    }
    
    private void getListGioHangHDCT(String MaHD) {
        _dtmGioHang = (DefaultTableModel) tbGioHang.getModel();
        _dtmGioHang.setRowCount(0);
        List<HoaDonChiTietViewModel> list = service.getListHoaDonChiTiet(MaHD);
        if (list.isEmpty()) {
            System.out.println("List trống");
            return;
        }
        for (HoaDonChiTietViewModel x : list) {
            GioHangViewModel gioHang = new GioHangViewModel();
            
            int idSP = Integer.parseInt(x.getSanPham().getTen());
            SanPham s = spService.getTenSanPham(idSP);
            String tenSP = s.getTen();
            gioHang.setMaSP(x.getSanPham().getMa());
            gioHang.setTenSP(tenSP);
            gioHang.setMauSac(x.getMauSac().getTen());
            gioHang.setKichCo(x.getKichCo().getTen());
            gioHang.setSoLuong(x.getSoluong());
            gioHang.setDonGia(x.getDonGia());
            gioHang.setGiamGia(x.getSanPham().getKhuyenMai().getGiaTriGiam());
            gioHang.setHinhThucGiamGia(x.getSanPham().getKhuyenMai().getHinhThucKM());
            _lstGioHang.add(gioHang);
            loadTBGioHang();
            
        }
    }

    private void tblSanPhamHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamHDMouseClicked
        // TODO add your handling code here:\
        int row = tblSanPhamHD.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thêm sản phẩm!");
            return;
        }
        String input = JOptionPane.showInputDialog(this, "Nhập Số Lượng!");
        if (input != null) {
            try {
                int NhapSoLuong = Integer.parseInt(input);
                String MaSP = tblSanPhamHD.getValueAt(row, 0).toString();
                String TenSP = tblSanPhamHD.getValueAt(row, 1).toString();
                String mausac = tblSanPhamHD.getValueAt(row, 2).toString();
                String kichco = tblSanPhamHD.getValueAt(row, 5).toString();
                int SoLuong = Integer.parseInt(tblSanPhamHD.getValueAt(row, 7).toString());
                Double DonGia = Double.parseDouble(tblSanPhamHD.getValueAt(row, 6).toString());
                Double GiamGia = Double.parseDouble(tblSanPhamHD.getValueAt(row, 3).toString());
                String hinhThucGiamGia = tblSanPhamHD.getValueAt(row, 4).toString();
                List<HoaDonChiTietViewModel> listh = service.getListHoaDonChiTiet(tblHoaDon.getValueAt(rowHD, 0).toString());
                //validate soluongnhap

                if (NhapSoLuong <= 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 !", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //ket thuc validate soluongnhap
                if (SoLuong >= NhapSoLuong) {
                    for (HoaDonChiTietViewModel x : listh) {
                        if (x.getSanPham().getMa().equals(MaSP)) {
                            JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Có Trên Giỏ Hàng");
                            return;
                        }
                    }
                    _lstGioHang.add(new GioHangViewModel(MaSP, TenSP, mausac, kichco, NhapSoLuong, DonGia, GiamGia, hinhThucGiamGia));
                    loadTBGioHang();
                    
                    int kq = (int) (SoLuong - NhapSoLuong);
                    spService.updateSoLuongSP(MaSP, kq);
                    List<SanPhamViewModel> list = spService.getListSanPham();
                    list.clear();
                    loadTBSP();
                    double tongPT = 0.0;
                    double tongVN = 0.0;
                    double tongTien = 0.0;
                    double giam = 0.0; // Thay đổi giá trị ban đầu của giam từ 0.0 sang  Double.parseDouble(lbl_giamGia1.getText());
                    int count = 0;
                    for (GioHangViewModel x : _lstGioHang) {
                        tongTien += x.getThanhTien();
                        lblTongTien.setText(String.format("%.0f", tongTien));
                        if (x.getHinhThucGiamGia().equals("%")) {
                            tongPT += x.getThanhTien() * x.getGiamGia() / 100;
                        } else {
                            tongVN += x.getGiamGia() * x.getSoLuong();
                        }
                        count++;
                    }
                    giam = tongPT + tongVN; // Tổng giá trị giảm giá bao gồm giảm theo % và giảm theo tiền
                    lblGiamGia.setText(String.format("%.0f", giam)); // Hiển thị tổng giá trị giảm giá lên label
                    Double ThanhTien = Double.parseDouble(lblTongTien.getText()) - Double.parseDouble(lblGiamGia.getText());
                    lblTienCanTra.setText(String.valueOf(String.format("%.0f", ThanhTien)));
                    
                } else if (SoLuong < NhapSoLuong) {
                    
                    JOptionPane.showMessageDialog(null, "Sản phẩm không đủ", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
                List<HoaDonViewModel> listHoaDon = service.getListHD(1);
                System.out.println("Kiểm tra list HD " + listHoaDon.toString());
                for (HoaDonViewModel x : listHoaDon) {
                    if (tblHoaDon.getValueAt(rowHD, 0).toString().equals(x.getMa())) {
                        HoaDonChiTietViewModel hdct = inputHDCT(DonGia, NhapSoLuong);
                        service.saveHDCT(hdct, MaSP, x.getMa());
                        
                        System.out.println("Không có vấn đề");
                        return;
                        
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Vui lòng không nhập kí tự", "Chú ý", JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_tblSanPhamHDMouseClicked
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:\
        
        if (tbGioHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm lên giỏ hàng !");
            return;
        }
        
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn hoá đơn bạn muốn thanh toán");
            return;
            
        }
        if (txtTienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "tiền khách Đưa không được để trống");
            return;
            
        }
        try {
            if (Double.parseDouble(txtTienKhachDua.getText()) < Double.parseDouble(lblTienCanTra.getText())) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa chưa đủ");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thanh toán hóa đơn này không?");
        if (chon != JOptionPane.YES_OPTION) {
            return;
        }
        
        HoaDonViewModel hoaDon = new HoaDonViewModel();
        hoaDon.setGhiChu(txtGhiChu.getText());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hoaDon.setNgayThanhToan(date);
        hoaDon.setMa(tblHoaDon.getValueAt(rowHD, 0).toString());
        hoaDon.setTongTien(Double.parseDouble(lblTienCanTra.getText()));
        service.updateTrangThaiHoaDon(hoaDon);
        
        JOptionPane.showMessageDialog(this, "Thanh toán thành công");
        
        List<HoaDonViewModel> getList = service.getListHD(1);
        
        for (HoaDonViewModel hoaDonViewModel : getList) {
            if (tblHoaDon.getValueAt(rowHD, 0).equals(hoaDonViewModel.getMa())) {
                getList.remove(hoaDonViewModel);
                getList.clear();
                clear();
                loadListHD();
                _lstGioHang.clear();
                loadTBGioHang();
                
                break;
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void mouse() {
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        _lstGioHang.clear();
        String MaHD = tblHoaDon.getValueAt(row, 0).toString();
        lblTongTien.setText(String.valueOf(0));
        lblGiamGia.setText(String.valueOf(0));
        lblTienCanTra.setText(String.valueOf(0));
        getListGioHangHDCT(MaHD);
        Double tongPT = 0.0;
        Double tongVN = 0.0;
        Double tongTien = 0.0;
        Double giam = Double.parseDouble(lblGiamGia.getText());
        int count = 0;
        List<HoaDonChiTietViewModel> list = service.getListHoaDonChiTiet(MaHD);
        for (HoaDonChiTietViewModel x : list) {
            tongTien = tongTien + x.getThanhTien();
            lblTongTien.setText(String.format("%.0f", tongTien));
            List<SanPhamViewModel> listSanPham = spService.getListSanPham();
            if (tbGioHang.getValueAt(count, 0).equals(x.getSanPham().getMa()) && x.getSanPham().getKhuyenMai().getHinhThucKM().equals("%")) {
                tongPT = x.getThanhTien() * x.getSanPham().getKhuyenMai().getGiaTriGiam() / 100;
                lblGiamGia.setText(String.valueOf(giam += tongPT));
                lblGiamGia.setText(String.format("%.0f", giam));
            } else {
                tongVN = x.getSanPham().getKhuyenMai().getGiaTriGiam() * x.getSoluong();
                lblGiamGia.setText(String.valueOf(giam += tongVN));
                lblGiamGia.setText(String.format("%.0f", giam));
            }
            count++;
        }
        Double ThanhTien = Double.parseDouble(lblTongTien.getText()) - Double.parseDouble(lblGiamGia.getText());
        lblTienCanTra.setText(String.valueOf(String.format("%.0f", ThanhTien)));
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int rowSP = tbGioHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowSP < 0) {
            return;
        }
        if (rowHD < 0) {
            return;
        }
        String MaSP = tbGioHang.getValueAt(rowSP, 0).toString();
        String MaHD = tblHoaDon.getValueAt(rowHD, 0).toString();
        
        List<SanPhamViewModel> list = spService.getListSanPham();
//        String input = JOptionPane.showInputDialog(null, "Mời nhập số lượng cần thay đổi", "Hello", JOptionPane.INFORMATION_MESSAGE);
//        if (input != null) {

        try {
            int NhapSoLuong = Integer.parseInt(JOptionPane.showInputDialog(null, "Mời nhập số lượng cần thay đổi", "Hello", JOptionPane.INFORMATION_MESSAGE));
            
            if (NhapSoLuong <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 !", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return;
            }
            for (SanPhamViewModel x : list) {
                if (MaSP.equals(x.getMa())) {
                    if (x.getSoLuongTon() + Integer.parseInt(tbGioHang.getValueAt(rowSP, 4).toString()) >= NhapSoLuong) {
                        
                        Integer isupdate = service.updateSoLuongGioHang(NhapSoLuong, MaSP, MaHD);
                        int updateSoLuong = x.getSoLuongTon() + Integer.parseInt(tbGioHang.getValueAt(rowSP, 4).toString());
                        getListGioHangHDCT(MaHD);
                        spService.updateSoLuongSP(x.getMa(), updateSoLuong - NhapSoLuong);
                        list.clear();
                        loadTBSP();
                        mouse();
                        return;
                    } else if (x.getSoLuongTon() < NhapSoLuong) {
                        JOptionPane.showMessageDialog(this, "số Lượng sản phẩm không đủ");
                        return;
                    }
                }
                
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Vui lòng không nhập kí tự", "Chú ý", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
//        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        _lstGioHang.clear();
        String MaHD = tblHoaDon.getValueAt(row, 0).toString();
        try {
            lblTongTien.setText(String.valueOf(0));
            lblGiamGia.setText(String.valueOf(0));
            lblTienCanTra.setText(String.valueOf(0));
            
            getListGioHangHDCT(MaHD);
            lblTenKhach.setText("");
            lblSDT.setText("");
            
            Double tongPT = 0.0;
            Double tongVN = 0.0;
            Double tongTien = 0.0;
            Double giam = Double.parseDouble(lblGiamGia.getText());
            int count = 0;
            List<HoaDonChiTietViewModel> _lstGH = service.getListHoaDonChiTiet(MaHD);
            for (HoaDonChiTietViewModel x : _lstGH) {
                tongTien = tongTien + x.getThanhTien();
                lblTongTien.setText(String.format("%.0f", tongTien));
                if (tbGioHang.getValueAt(count, 0).equals(x.getSanPham().getMa()) && x.getSanPham().getKhuyenMai().getHinhThucKM().equals("%")) {
                    tongPT = x.getThanhTien() * x.getSanPham().getKhuyenMai().getGiaTriGiam() / 100;
                    lblGiamGia.setText(String.valueOf(giam += tongPT));
                    lblGiamGia.setText(String.format("%.0f", giam));
                    
                } else {
                    tongVN = x.getSanPham().getKhuyenMai().getGiaTriGiam() * x.getSoluong();
                    lblGiamGia.setText(String.valueOf(giam += tongVN));
                    lblGiamGia.setText(String.format("%.0f", giam));
                    
                }
                
                count++;
                
            }
            Double ThanhTien = Double.parseDouble(lblTongTien.getText()) - Double.parseDouble(lblGiamGia.getText());
            lblTienCanTra.setText(String.valueOf(String.format("%.0f", ThanhTien)));
            
        } catch (Exception e) {
            lblTongTien.setText(String.valueOf(0));
            lblGiamGia.setText(String.valueOf(0));
            lblTienCanTra.setText(String.valueOf(0));
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int rowSP = tbGioHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowSP < 0) {
            JOptionPane.showMessageDialog(this, "chọn 1 sản phẩm trong giỏ hàng để xoá");
            return;
        }
        
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn Hoá đơn bạn muốn xoá sản phẩm đấy");
            return;
        }
        String MaSP = tbGioHang.getValueAt(rowSP, 0).toString();
        String MaHD = tblHoaDon.getValueAt(rowHD, 0).toString();
        Integer soLuong = Integer.parseInt(tbGioHang.getValueAt(rowSP, 4).toString());
        Integer idSP = spService.getIdSanPham(MaSP);
        Integer idHd = service.getIdHD(MaHD);
        Integer isDelete = service.deleteSanPham(idHd, idSP);
        List<SanPhamViewModel> list = spService.getListSanPham();
        for (SanPhamViewModel x : list) {
            if (MaSP.equals(x.getMa())) {
                spService.updateSoLuongSP(MaSP, x.getSoLuongTon() + soLuong);
                list.clear();
                loadTBSP();
                getListGioHangHDCT(MaHD);
                mouse();
                break;
            }
        }
        _lstGioHang.clear();
        
        JOptionPane.showMessageDialog(null, "Xóa thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int rowHD = tblHoaDon.getSelectedRow();
        int rowGH = tbGioHang.getSelectedRow();
        if (rowHD < 0) {
            return;
        }
        int kt = JOptionPane.showConfirmDialog(this, "bạn muốn xoá tất cả sản phẩm trên giỏ hàng không", "xoá ", JOptionPane.YES_NO_OPTION);
        if (kt == 0) {
            String MaHD = tblHoaDon.getValueAt(rowHD, 0).toString();
            int count = 0;
            List<SanPhamViewModel> list = spService.getListSanPham();
            
            GioHangViewModel hg = new GioHangViewModel();
            hg.setMaSP(tbGioHang.getValueAt(count, 0).toString());
            hg.setSoLuong(Integer.parseInt(tbGioHang.getValueAt(count, 4).toString()));
            hg.setTenSP(tbGioHang.getValueAt(count, 1).toString());
            hg.setDonGia(Double.parseDouble(tbGioHang.getValueAt(count, 5).toString()));
            
            count++;
            _lstGioHang.add(hg);
            Integer idHd = service.getIdHD(MaHD);
            service.clearSanPhamTrenGioHang(idHd);
            for (GioHangViewModel x : _lstGioHang) {
                for (SanPhamViewModel sanPham : list) {
                    if (x.getMaSP().equals(sanPham.getMa())) {
                        spService.updateSoLuongSP(sanPham.getMa(), sanPham.getSoLuongTon() + x.getSoLuong());
                        getListGioHangHDCT(MaHD);
                        
                    }
                }
                
            }
            list.clear();
            loadTBSP();
            _lstGioHang.clear();
            lblTongTien.setText(String.valueOf(0));
            lblGiamGia.setText(String.valueOf(0));
            lblTienCanTra.setText(String.valueOf(0));
            JOptionPane.showMessageDialog(null, "Xóa tất cả thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Đã hủy", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            return;
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbbDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDanhMucItemStateChanged
        // TODO add your handling code here:
        String TenDanhMuc = cbbDanhMuc.getSelectedItem().toString();
        if (TenDanhMuc.equals("All")) {
            List<SanPhamViewModel> sanPham = spService.getListSanPham();
            sanPham.clear();
            loadTBSP();
            return;
        }
        _dtm = (DefaultTableModel) tblSanPhamHD.getModel();
        _dtm.setRowCount(0);
        List<SanPhamViewModel> locTheoDanhMucSP = spService.locTheoDanhMucSP(TenDanhMuc);
        for (SanPhamViewModel x : locTheoDanhMucSP) {
            _dtm.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getMauSac().getTen(),
                String.format("%.0f", x.getKhuyenMai().getGiaTriGiam()),
                x.getKhuyenMai().getHinhThucKM(),
                x.getKichCo().getTen(),
                String.format("%.0f", x.getGiaBan()),
                x.getSoLuongTon(),});
            
        }
    }//GEN-LAST:event_cbbDanhMucItemStateChanged
    private void TimKiemSanPham() {
        _dtm = (DefaultTableModel) tblSanPhamHD.getModel();
        _dtm.setRowCount(0);
        List<SanPhamViewModel> getList = spService.seachSanPham(txtSearchSP.getText());
        for (SanPhamViewModel x : getList) {
            _dtm.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getMauSac().getTen(),
                String.format("%.0f", x.getKhuyenMai().getGiaTriGiam()),
                x.getKhuyenMai().getHinhThucKM(),
                x.getKichCo().getTen(),
                String.format("%.0f", x.getGiaBan()),
                x.getSoLuongTon(),});
        }
    }
    private void txtSearchSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSPKeyReleased
        // TODO add your handling code here:
        TimKiemSanPham();
    }//GEN-LAST:event_txtSearchSPKeyReleased

    private void btnChonKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachActionPerformed
        // TODO add your handling code here:
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn hoá đơn bạn muốn thêm khách hàng vào");
            return;
        }
        new KhachHangForm(tblHoaDon.getValueAt(rowHD, 0).toString()).setVisible(true);
        
        
    }//GEN-LAST:event_btnChonKhachActionPerformed

    private void btnXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXNActionPerformed
        // TODO add your handling code here:
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn 1 hoá đơn hiện thị khách hàng");
            return;
        }

        List<HoaDon> getList = service.getKhachHang(tblHoaDon.getValueAt(rowHD, 0).toString());
        for (HoaDon hoaDon : getList) {
            lblTenKhach.setText(hoaDon.getKhachHang().getTen());
            lblSDT.setText(hoaDon.getKhachHang().getSdt());

            return;
        }
    }//GEN-LAST:event_btnXNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhach;
    private javax.swing.JButton btnXN;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKhach;
    private javax.swing.JLabel lblTienCanTra;
    private javax.swing.JLabel lblTienDu;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPhamHD;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSearchSP;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Result result = null;
            BufferedImage image = null;
            
            if (webcam.isOpen()) {
                
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
                
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                
                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                }
            }
            
            if (result != null) {
                String barcode = result.getText();
                // Tìm kiếm sản phẩm từ mã vạch
                SanPham sp = spService.seachBarCodeAdd(barcode);
                System.out.println(sp.getTen());
                if (sp != null) {
                    // Kiểm tra xem có hóa đơn nào được chọn hay không
                    int rowHD = tblHoaDon.getSelectedRow();
                    if (rowHD < 0) {
                        JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thêm sản phẩm!");
                        continue; // Tiếp tục vòng lặp để quét mã vạch khác
                    }

                    // Nhập số lượng
                    while (true) {
                        String input = JOptionPane.showInputDialog(this, "Nhập Số Lượng!");
                        if (input != null) {
                            try {
                                int NhapSoLuong = Integer.parseInt(input);
                                String MaSP = sp.getMa();
                                String TenSP = sp.getTen();
                                String mausac = sp.getMauSac().getTen();
                                String kichco = sp.getKichCo().getTen();
                                int SoLuong = sp.getSoLuongTon();
                                Double DonGia = sp.getGiaBan();
                                Double GiamGia = sp.getKhuyenMai().getGiaTriGiam();
                                String hinhThucGiamGia = sp.getKhuyenMai().getHinhThucKM();

                                // Kiểm tra số lượng nhập
                                if (NhapSoLuong <= 0) {
                                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 !", "Chú ý", JOptionPane.WARNING_MESSAGE);
                                    continue; // Tiếp tục vòng lặp để nhập lại số lượng
                                }

                                // Kiểm tra số lượng tồn kho
                                if (SoLuong >= NhapSoLuong) {
                                    List<HoaDonChiTietViewModel> listh = service.getListHoaDonChiTiet(tblHoaDon.getValueAt(rowHD, 0).toString());
                                    for (HoaDonChiTietViewModel x : listh) {
                                        if (x.getSanPham().getMa().equals(MaSP)) {
                                            JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Có Trên Giỏ Hàng");
                                            return;
                                        }
                                    }
                                    // Thêm sản phẩm vào giỏ hàng
                                    _lstGioHang.add(new GioHangViewModel(MaSP, TenSP, mausac, kichco, NhapSoLuong, DonGia, GiamGia, hinhThucGiamGia));
                                    loadTBGioHang();

                                    // Cập nhật số lượng tồn kho
                                    int kq = SoLuong - NhapSoLuong;
                                    spService.updateSoLuongSP(MaSP, kq);

                                    // Tính tổng tiền và giảm giá
                                    double tongPT = 0.0;
                                    double tongVN = 0.0;
                                    double tongTien = 0.0;
                                    double giam = 0.0;
                                    for (GioHangViewModel x : _lstGioHang) {
                                        tongTien += x.getThanhTien();
                                        if (x.getHinhThucGiamGia().equals("%")) {
                                            tongPT += x.getThanhTien() * x.getGiamGia() / 100;
                                        } else {
                                            tongVN += x.getGiamGia() * x.getSoLuong();
                                        }
                                    }
                                    giam = tongPT + tongVN;
                                    lblTongTien.setText(String.format("%.0f", tongTien));
                                    lblGiamGia.setText(String.format("%.0f", giam));
                                    Double ThanhTien = tongTien - giam;
                                    lblTienCanTra.setText(String.valueOf(String.format("%.0f", ThanhTien)));

                                    // Thêm sản phẩm vào hóa đơn chi tiết
                                    HoaDonChiTietViewModel hdct = inputHDCT(DonGia, NhapSoLuong);
                                    service.saveHDCT(hdct, MaSP, tblHoaDon.getValueAt(rowHD, 0).toString());

                                    // Đã xử lý sản phẩm, thoát khỏi vòng lặp nhập số lượng
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Sản phẩm không đủ", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                                    break; // Thoát khỏi vòng lặp nhập số lượng và tiếp tục quét mã vạch khác
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng là số nguyên dương", "Chú ý", JOptionPane.WARNING_MESSAGE);
                                e.printStackTrace();
                            }
                        } else {
                            // Người dùng hủy hoặc không nhập số lượng, tiếp tục quét mã vạch khác
                            break;
                        }
                    }
                }
            }
            
        } while (true);
        
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }
}
