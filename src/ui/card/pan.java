package ui.card;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import material.design.ScrollBar;
import ui.table.TableCustom;

public class pan extends javax.swing.JPanel {

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }
    private Color color1;
    private Color color2;

    public pan() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
 
        btn_exite.setVisible(false);
        btn_open.setVisible(false);
        setDesignTable(table_seance_to_day, jScrollPane11);
        table_seance_to_day.setRowHeight(35);
  
    }
    
    public JTable getTtable_seance_to_day(){
        return table_seance_to_day;
    
    }
    
    public void setDesignTable(JTable tab, JScrollPane scrol) {
        TableCustom.apply(scrol, TableCustom.TableType.DEFAULT);
        //tab.getTableHeader().setFont(new Font("", Font.BOLD, 15));
        tab.getTableHeader().setFont(new java.awt.Font("Times New Roman", 1, 18));
        //tab.setFont(new Font("", Font.BOLD, 14));
        tab.setFont(new java.awt.Font("Times New Roman", 1, 16));
        scrol.setVerticalScrollBar(new ScrollBar());
        scrol.getVerticalScrollBar().setBackground(Color.WHITE);
        scrol.getViewport().setBackground(Color.white);// make table without rouw white
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrol.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }

//    public void setData(Model_card data){
//        lbIcon.setIcon(data.getIcon());
//        lbTitle.setText(data.getTitle());
//        lbValues.setText(data.getValues());
//        lbDescription.setText(data.getDescription());
//    }

    /*public Card(Color color1, Color color2, JLabel lbDescription, JLabel lbIcon, JLabel lbTitle, JLabel lbValues) {
        this.color1 = color1;
        this.color2 = color2;
        this.lbDescription = lbDescription;
        this.lbIcon = lbIcon;
        this.lbTitle = lbTitle;
        this.lbValues = lbValues;
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_open = new material.design.buttonRounder();
        jLabel1 = new javax.swing.JLabel();
        labDate_seance = new javax.swing.JLabel();
        btn_exite = new material.design.buttonRounder();
        tableScrollButton11 = new ui.table.TableScrollButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        table_seance_to_day = new javax.swing.JTable();
        lab_day = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonRounder1 = new material.design.buttonRounder();

        setLayout(null);

        btn_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left-arrow (1).png"))); // NOI18N
        btn_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openActionPerformed(evt);
            }
        });
        add(btn_open);
        btn_open.setBounds(170, 10, 50, 20);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("حصــص الــيــوم");
        add(jLabel1);
        jLabel1.setBounds(130, 0, 120, 40);

        labDate_seance.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        labDate_seance.setForeground(new java.awt.Color(255, 255, 255));
        labDate_seance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labDate_seance.setText("01-01-2024");
        add(labDate_seance);
        labDate_seance.setBounds(20, 0, 100, 40);

        btn_exite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left-arrow.png"))); // NOI18N
        btn_exite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exiteActionPerformed(evt);
            }
        });
        add(btn_exite);
        btn_exite.setBounds(220, 10, 40, 20);

        table_seance_to_day.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الحصة", "المادة ", "القسم", "المستوى", "N", "id_saence_Matier"
            }
        ));
        jScrollPane11.setViewportView(table_seance_to_day);

        tableScrollButton11.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        add(tableScrollButton11);
        tableScrollButton11.setBounds(12, 80, 330, 480);

        lab_day.setBackground(new java.awt.Color(255, 255, 255));
        lab_day.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lab_day.setForeground(new java.awt.Color(204, 204, 204));
        lab_day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_day.setText("01");
        lab_day.setOpaque(true);
        add(lab_day);
        lab_day.setBounds(270, 50, 20, 20);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/daily-routine (1)_1.png"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(260, 0, 80, 70);

        buttonRounder1.setBackground(new java.awt.Color(235, 235, 235));
        buttonRounder1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRounder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fast-time (4).png"))); // NOI18N
        buttonRounder1.setText("حصص قيد الدراسة");
        add(buttonRounder1);
        buttonRounder1.setBounds(10, 40, 160, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openActionPerformed
//        btn_open.setVisible(false);
//        Thread th = new Thread() {
//            @Override
//            public void run() {
//                try {
//
//                    for (int a = 1; a <= 21; a++) {
//
//                        Thread.sleep(20);
//                      if (a==1){setSize(60,590);}                        
//                        if (a==2){setSize(70,590);}
//                        if (a==3){setSize(80,590);}
//                        if (a==4){setSize(90,590);}
//                        if (a==5){setSize(100,590);}
//                        if (a==6){setSize(110,590);}
//                        if (a==7){setSize(120,590);}
//                        if (a==8){setSize(130,590);}
//                        if (a==9){setSize(140,590);}
//                        if (a==10){setSize(150,590);}
//                        if (a==11){setSize(160,590);}
//                        if (a==12){setSize(170,590);}
//                        if (a==13){setSize(180,590);}
//                        if (a==14){setSize(190,590);}
//                        if (a==15){setSize(200,590);}
//                        if (a==16){setSize(210,590);}
//                        if (a==17){setSize(220,590);}
//                        if (a==18){setSize(230,590);}
//                         if (a==19){setSize(240,590);}
//                        if (a==20){setSize(300,590);}
//                        if (a==21){setSize(350,590);}
//                    }
//                } catch (Exception ex) {
//                    System.out.println(ex);
//                }
//            }
//        };
//        th.start();
// 
//        btn_exite.setVisible(true);
    }//GEN-LAST:event_btn_openActionPerformed

    private void btn_exiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exiteActionPerformed
//        Thread th = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    int j = 60;
//                    for (int a = 2; a <= 21; a++) {
//                        Thread.sleep(40);
//                    
//                        if (a==2){setSize(350,590);}
//                        
//                        if (a==2){setSize(350,590);}
//                        if (a==3){setSize(250,550);}
//                        if (a==4){setSize(230,500);}
//                        if (a==5){setSize(220,450);}
//                        if (a==6){setSize(210,400);}
//                        if (a==7){setSize(200,350);}
//                        if (a==8){setSize(190,310);}
//                        if (a==9){setSize(180,300);}
//                        if (a==10){setSize(170,250);}
//                        if (a==11){setSize(160,220);}
//                        if (a==12){setSize(150,170);}
//                        if (a==13){setSize(140,160);}
//                        if (a==14){setSize(130,150);}
//                        if (a==15){setSize(120,130);}
//                        if (a==16){setSize(110,120);}
//                        if (a==17){setSize(100,100);}
//                        if (a==18){setSize(90,90);}
//                        if (a==19){setSize(80,80);}
//                        if (a==20){setSize(70,70);}
//                        if (a==21){setSize(45,45);}
//
//                        btn_exite.setVisible(false);
//                        btn_open.setVisible(true);
//                    }
//                } catch (Exception ex) {
//                    System.out.println(ex);
//                }
//            }
//        };
//        th.start();
//       
    }//GEN-LAST:event_btn_exiteActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        //cercl white transparent
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());

        super.paintComponent(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private material.design.buttonRounder btn_exite;
    private material.design.buttonRounder btn_open;
    private material.design.buttonRounder buttonRounder1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel labDate_seance;
    private javax.swing.JLabel lab_day;
    private ui.table.TableScrollButton tableScrollButton11;
    private javax.swing.JTable table_seance_to_day;
    // End of variables declaration//GEN-END:variables
}
