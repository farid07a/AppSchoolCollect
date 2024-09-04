package material.design;


import ui.card.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Card extends javax.swing.JPanel {


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
   
   public Card() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2= Color.WHITE;
    }

  

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

        panelunguX = new javax.swing.JPanel();
        tableScrollButton11 = new ui.table.TableScrollButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        table_seance_to_day = new javax.swing.JTable();
        btn_exite = new material.design.buttonRounder();
        btn_open = new material.design.buttonRounder();
        jLabel1 = new javax.swing.JLabel();
        labDate_seance = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        panelunguX.setLayout(null);
        panelunguX.add(tableScrollButton11);
        tableScrollButton11.setBounds(12, 60, 300, 260);

        table_seance_to_day.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الحصة", "المادة ", "القسم", "المستوى", "N", "id_saence_Matier"
            }
        ));
        jScrollPane11.setViewportView(table_seance_to_day);

        panelunguX.add(jScrollPane11);
        jScrollPane11.setBounds(15, 85, 300, 260);

        btn_exite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left-arrow.png"))); // NOI18N
        btn_exite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exiteActionPerformed(evt);
            }
        });
        panelunguX.add(btn_exite);
        btn_exite.setBounds(260, 0, 50, 40);

        btn_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left-arrow (1).png"))); // NOI18N
        btn_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openActionPerformed(evt);
            }
        });
        panelunguX.add(btn_open);
        btn_open.setBounds(0, 0, 40, 40);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("حصــص الــيــوم");
        panelunguX.add(jLabel1);
        jLabel1.setBounds(150, 10, 120, 40);

        labDate_seance.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        labDate_seance.setForeground(new java.awt.Color(255, 255, 255));
        labDate_seance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labDate_seance.setText("01-01-2024");
        panelunguX.add(labDate_seance);
        labDate_seance.setBounds(57, 10, 90, 40);

        add(panelunguX, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openActionPerformed
        btn_open.setVisible(false);
        Thread th = new Thread() {
            @Override
            public void run() {
                try {

                    for (int a = 0; a <= 5; a++) {

                        Thread.sleep(8);

                        if (a == 1) {
                            panelunguX.setSize(60, 60);
                        }
                        //                        if (a==2){panelunguX.setSize(70,70);}
                        //                        if (a==3){panelunguX.setSize(80,80);}
                        //                        if (a==4){panelunguX.setSize(90,90);}
                        if (a == 2) {
                            panelunguX.setSize(100, 100);
                        }
                        //                        if (a==6){panelunguX.setSize(110,110);}
                        //                        if (a==7){panelunguX.setSize(120,120);}
                        //                        if (a==8){panelunguX.setSize(130,130);}
                        if (a == 3) {
                            panelunguX.setSize(140, 140);
                        }
                        //                        if (a==10){panelunguX.setSize(150,150);}
                        //                        if (a==11){panelunguX.setSize(160,160);}
                        //                        if (a==12){panelunguX.setSize(170,170);}
                        if (a == 4) {
                            panelunguX.setSize(180, 180);
                        }
                        //                        if (a==14){panelunguX.setSize(190,190);}
                        //                        if (a==15){panelunguX.setSize(200,200);}
                        //                        if (a==16){panelunguX.setSize(210,210);}
                        //                        if (a==5){panelunguX.setSize(220,220);}
                        //                        if (a==18){panelunguX.setSize(230,230);}
                        //                        if (a==19){panelunguX.setSize(250,250);}
                        //                        if (a==20){panelunguX.setSize(300,300);}
                        if (a == 5) {
                            panelunguX.setSize(350, 350);
                        }
                        //                        if (a==6){panelunguX.setSize(400,400);}
                        //                        if (a==7){panelunguX.setSize(450,450);}
                        //                        if (a==8){panelunguX.setSize(500,450);}

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        };
        th.start();
        btn_exite.setVisible(true);
    }//GEN-LAST:event_btn_openActionPerformed

    private void btn_exiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exiteActionPerformed
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    int j = 60;
                    for (int a = 0; a <= 20; a++) {
                        Thread.sleep(20);
                        if (a == 1) {
                            panelunguX.setSize(350, 35);
                        }
                        if (a == 2) {
                            panelunguX.setSize(220, 220);
                        }
                        if (a == 3) {
                            panelunguX.setSize(210, 210);
                        }
                        if (a == 4) {
                            panelunguX.setSize(200, 200);
                        }
                        if (a == 5) {
                            panelunguX.setSize(190, 190);
                        }
                        if (a == 6) {
                            panelunguX.setSize(180, 180);
                        }
                        if (a == 7) {
                            panelunguX.setSize(170, 170);
                        }
                        if (a == 8) {
                            panelunguX.setSize(160, 160);
                        }
                        if (a == 9) {
                            panelunguX.setSize(150, 150);
                        }
                        if (a == 10) {
                            panelunguX.setSize(140, 140);
                        }
                        if (a == 11) {
                            panelunguX.setSize(130, 130);
                        }
                        if (a == 12) {
                            panelunguX.setSize(120, 120);
                        }
                        if (a == 13) {
                            panelunguX.setSize(110, 110);
                        }
                        if (a == 14) {
                            panelunguX.setSize(100, 100);
                        }
                        if (a == 15) {
                            panelunguX.setSize(90, 90);
                        }
                        if (a == 16) {
                            panelunguX.setSize(80, 80);
                        }
                        if (a == 17) {
                            panelunguX.setSize(70, 70);
                        }
                        if (a == 18) {
                            panelunguX.setSize(60, 60);
                        }
                        if (a == 19) {
                            panelunguX.setSize(50, 50);
                        }
                        if (a == 20) {
                            panelunguX.setSize(45, 45);
                        }

                        btn_exite.setVisible(false);
                        btn_open.setVisible(true);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        };
        th.start();
    }//GEN-LAST:event_btn_exiteActionPerformed
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g =new GradientPaint(0, 0,color1,0,getHeight(),color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        
        //cercl white transparent
        
        g2.setColor(new Color(255,255,255,50));
        g2.fillOval(getWidth()-(getHeight()/2), 10, getHeight() , getHeight());
        g2.fillOval(getWidth()-(getHeight()/2)-20, getHeight()/2+20, getHeight() , getHeight());

        super.paintComponent(grphcs); 
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private material.design.buttonRounder btn_exite;
    private material.design.buttonRounder btn_open;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel labDate_seance;
    private javax.swing.JPanel panelunguX;
    private ui.table.TableScrollButton tableScrollButton11;
    private javax.swing.JTable table_seance_to_day;
    // End of variables declaration//GEN-END:variables
}
