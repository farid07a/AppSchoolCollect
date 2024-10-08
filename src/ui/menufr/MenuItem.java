package ui.menufr;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuItem extends javax.swing.JPanel {

    private boolean selected;
    private boolean over;
   
    public MenuItem(Model_Menu data) {
        initComponents();
       
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if(data.getType()==Model_Menu.MenuType.MENU ){
             lbIcon.setIcon(data.toIcon());
           lbName.setText(data.getName());
            
        }else if(data.getType()==Model_Menu.MenuType.TITLE){  
            lbIcon.setText(data.getName());
            lbIcon.setFont(new Font("sansserif",1,12));
            lbName.setVisible(false);
        }
        else{
            lbName.setText("");
        }
        lbName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

       public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
       public void setOver(boolean over) {
        this.over = over;
        repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbName.setText("Menu Name");
        lbName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbName.setPreferredSize(new java.awt.Dimension(40, 16));

        lbIcon.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        if(selected){
         Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255,255, 80));
        //g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        g2.fillRoundRect(10, 0, getWidth()-20, getHeight(), 5, 5);

        }
        super.paintComponent(grphcs);
        
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
