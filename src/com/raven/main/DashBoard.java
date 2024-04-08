package com.raven.main;

import com.raven.event.EventMenu;
import com.raven.form.Form;
import com.raven.form.Form_1;
import com.raven.form.Giao_dich;
import com.raven.form.Khach_Hang;
import com.raven.form.Khuyen_Mai;
import com.raven.form.Nhan_Vien;
import com.raven.form.San_Pham;
import com.raven.form.Lich_su;
import com.raven.form.Thong_ke;
import com.raven.form.frm_Login;

import com.viewModel.CurrentUser;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;

public class DashBoard extends javax.swing.JFrame {
    
    public DashBoard() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
//                if (index == 0) {
//                    showForm(new Form_1());
//                } else if (index == 8) {
//                    System.exit(0);
//                } else {
//                    showForm(new Form(index));
//                }
                switch (index) {
                    case 0:
                        showForm(new Form(index));
                        break;
                    case 1:
                        showForm(new San_Pham());
                        break;
//                        if (checkRole() == true) {
//                        }
//                        JOptionPane.showMessageDialog(rootPane, "Bạn không có quyền sử dụng chức năng này!");
//                        return;
                    case 2:
                        showForm(new Nhan_Vien());
                        break;
//                        if (checkRole() == true) {
//                        }
//                        JOptionPane.showMessageDialog(rootPane, "Bạn không có quyền sử dụng chức năng này!");
//                        return;

                    case 3:
                        showForm(new Giao_dich());
                        break;
                    case 4:
                        showForm(new Khuyen_Mai());
                        break;
//                        if (checkRole() == true) {
//                        }
//                        JOptionPane.showMessageDialog(rootPane, "Bạn không có quyền sử dụng chức năng này!");
//                        return;
                    case 5:
                        showForm(new Thong_ke());
                        break;
//                        if (checkRole() == true) {
//                        }
//                        JOptionPane.showMessageDialog(rootPane, "Bạn không có quyền sử dụng chức năng này!");
//                        return;
                    case 6:
                        showForm(new Khach_Hang());
                        break;
                    case 7:
                        showForm(new Lich_su());
                        break;
                    case 8:
                        if (JOptionPane.showConfirmDialog(rootPane, "Bạn Có Chắc Chắn Muốn Thoát Không !") != JOptionPane.YES_OPTION) {
                            return;
                        }
                        System.exit(0);
                        break;
                    default:
                    
                }
            }
        };
        menu1.initMenu(event);
        
        showForm(
                new Form(0));
    }
    
    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }
    
    private boolean checkRole() {
        int role = CurrentUser.getInstance().getVaitro();
        if (role == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.raven.swing.RoundPanel();
        header2 = new com.raven.component.Header();
        menu1 = new com.raven.component.Menu();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(204, 204, 255));

        header2.setBackground(new java.awt.Color(204, 255, 255));

        menu1.setBackground(new java.awt.Color(204, 255, 255));

        body.setBackground(new java.awt.Color(204, 255, 255));
        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 1370, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private com.raven.component.Header header2;
    private com.raven.component.Menu menu1;
    private com.raven.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
