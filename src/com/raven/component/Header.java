package com.raven.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(255,102,0));
        setClock();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonBadges1 = new com.raven.swing.ButtonBadges();
        buttonBadges2 = new com.raven.swing.ButtonBadges();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 102, 0));

        buttonBadges1.setBackground(new java.awt.Color(25, 25, 25));
        buttonBadges1.setForeground(new java.awt.Color(9, 129, 233));
        buttonBadges1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/noti.png"))); // NOI18N
        buttonBadges1.setBadges(5);

        buttonBadges2.setBackground(new java.awt.Color(25, 25, 25));
        buttonBadges2.setForeground(new java.awt.Color(247, 58, 58));
        buttonBadges2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/message.png"))); // NOI18N
        buttonBadges2.setBadges(15);

        lblDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDate.setText("Date");

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTime.setText("Time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lblTime))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblDate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
                .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTime))
                    .addComponent(buttonBadges2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBadges1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        area.add(new Area(new Rectangle2D.Double(0, 20, getWidth(), getHeight())));
        g2.fill(area);
        g2.dispose();
        super.paint(grphcs);
    }
    private void setClock() {
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    SimpleDateFormat formatDay = new SimpleDateFormat("dd MMM yyyy");
                    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                    String time = formatDay.format(date);
                    String time2 = formatTime.format(date);
                    lblDate.setText(time);
                    lblTime.setText(time2);
                }
            }
        });
        th1.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonBadges buttonBadges1;
    private com.raven.swing.ButtonBadges buttonBadges2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
