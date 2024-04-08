/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.services.IUserService;
import com.services.impl.UserService;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class frm_DangNhap extends javax.swing.JPanel {

    /**
     * Creates new form frm_DangNhap
     */
    private boolean ishidden = false;

    IUserService service = new UserService();

    public frm_DangNhap() {
        initComponents();
    }

    public void dangnhap() {
        txtUser.grabFocus();
    }

    public void btndangnhapEven(ActionListener event) {
        mybtn.addActionListener(event);
    }

    public void addEventquenmatkhau(ActionListener event) {
        quenmatkhau.addActionListener(event);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        lblToggle = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblerr = new javax.swing.JLabel();
        mybtn = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        quenmatkhau = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(186, 79, 84));
        setPreferredSize(new java.awt.Dimension(400, 405));

        jPanel3.setBackground(new java.awt.Color(186, 79, 84));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 90));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(186, 79, 84));
        jPanel5.setPreferredSize(new java.awt.Dimension(400, 160));
        jPanel5.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(186, 79, 84));
        jPanel6.setPreferredSize(new java.awt.Dimension(250, 50));
        jPanel6.setLayout(new java.awt.BorderLayout(4, 4));

        txtUser.setBackground(new java.awt.Color(186, 79, 84));
        txtUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(null);
        jPanel6.add(txtUser, java.awt.BorderLayout.PAGE_END);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("UserName");
        jPanel6.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jPanel5.add(jPanel6);
        jPanel6.setBounds(75, 5, 250, 50);

        jPanel7.setBackground(new java.awt.Color(186, 79, 84));
        jPanel7.setPreferredSize(new java.awt.Dimension(250, 50));
        jPanel7.setLayout(new java.awt.BorderLayout(4, 4));

        txtPassword.setBackground(new java.awt.Color(186, 79, 84));
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(null);
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });
        jPanel7.add(txtPassword, java.awt.BorderLayout.PAGE_END);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel7.add(jSeparator1, java.awt.BorderLayout.PAGE_START);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PassWord");
        jPanel7.add(jLabel4, java.awt.BorderLayout.LINE_START);

        jPanel5.add(jPanel7);
        jPanel7.setBounds(75, 60, 250, 50);

        jPanel8.setBackground(new java.awt.Color(186, 79, 84));
        jPanel8.setPreferredSize(new java.awt.Dimension(250, 30));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel8.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        jPanel5.add(jPanel8);
        jPanel8.setBounds(75, 115, 250, 30);

        lblToggle.setBackground(new java.awt.Color(255, 255, 255));
        lblToggle.setForeground(new java.awt.Color(255, 102, 102));
        lblToggle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/witness.png"))); // NOI18N
        lblToggle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblToggleMouseClicked(evt);
            }
        });
        jPanel5.add(lblToggle);
        lblToggle.setBounds(330, 90, 24, 28);

        jPanel9.setBackground(new java.awt.Color(186, 79, 84));
        jPanel9.setPreferredSize(new java.awt.Dimension(400, 50));

        lblerr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblerr.setForeground(new java.awt.Color(255, 204, 0));
        jPanel9.add(lblerr);

        mybtn.setBackground(new java.awt.Color(186, 79, 84));
        mybtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mybtn.setForeground(new java.awt.Color(0, 255, 255));
        mybtn.setText("Login");
        mybtn.setBorder(null);
        mybtn.setPreferredSize(new java.awt.Dimension(150, 38));
        mybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybtnActionPerformed(evt);
            }
        });
        jPanel9.add(mybtn);

        jPanel10.setBackground(new java.awt.Color(186, 79, 84));
        jPanel10.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel10.setLayout(null);

        quenmatkhau.setBackground(new java.awt.Color(186, 79, 84));
        quenmatkhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quenmatkhau.setForeground(new java.awt.Color(204, 204, 255));
        quenmatkhau.setText("Forget Pasword");
        quenmatkhau.setBorder(null);
        quenmatkhau.setPreferredSize(new java.awt.Dimension(150, 20));
        quenmatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quenmatkhauActionPerformed(evt);
            }
        });
        jPanel10.add(quenmatkhau);
        quenmatkhau.setBounds(240, 30, 150, 20);

        jPanel4.setBackground(new java.awt.Color(186, 79, 84));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isLogin() == true) {
                Component comp = SwingUtilities.getRoot(this);
                ((Window) comp).dispose();
            }
        }
    }//GEN-LAST:event_txtPasswordKeyReleased
    public boolean isLogin() {
        String user = txtUser.getText().trim();
        String pass = txtPassword.getText().trim();
        boolean dangNhap = service.getUser(user, pass);
        return dangNhap;
    }

    private void hideAndShowPassword() {
        if (ishidden) {
            this.txtPassword.setEchoChar((char) 0);
            this.lblToggle.setIcon(new ImageIcon("D:\\Da1_2024\\Shoes_Store\\src\\com\\raven\\icon\\hide.png"));
        } else {
            this.lblToggle.setIcon(new ImageIcon("D:\\Da1_2024\\Shoes_Store\\src\\com\\raven\\icon\\witness.png"));
            if (txtPassword.getText().equals("Mật khẩu")) {
                return;
            }
            this.txtPassword.setEchoChar('*');
        }
    }

    private void lblToggleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblToggleMouseClicked
        // TODO add your handling code here:
        ishidden = !ishidden;
        hideAndShowPassword();
    }//GEN-LAST:event_lblToggleMouseClicked

    private void mybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mybtnActionPerformed

    private void quenmatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quenmatkhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quenmatkhauActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblToggle;
    private javax.swing.JLabel lblerr;
    private javax.swing.JButton mybtn;
    private javax.swing.JButton quenmatkhau;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
