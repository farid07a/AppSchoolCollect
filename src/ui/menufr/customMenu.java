package ui.menufr;


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class customMenu extends javax.swing.JPanel {
  
    private EventMenuSelected event;

    int lang;
    public void addEventMenuSelected(EventMenuSelected event) {
       this.event = event;
       listMenu121.addEventMenuSelected(event);
    }
   
    
    public customMenu() {
        initComponents();
        setOpaque(false);
        listMenu121.setOpaque(false);
        init();
    }

     
 private void init(){
     //System.out.println("com.raven.component.Menu.init()");
     listMenu121.addItem(new Model_Menu("1","الصفحة الرئيسية",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("1","الــتــســجــيــل",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("8","الـطــلـــــبـــة",Model_Menu.MenuType.MENU));//3
     listMenu121.addItem(new Model_Menu("4","الأســــاتــــذة",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("5","الــحـــضــــور",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("5","الـــدفــــــع",Model_Menu.MenuType.MENU));
    listMenu121.addItem(new Model_Menu("5","المــداخــــيل",Model_Menu.MenuType.MENU));
      listMenu121.addItem(new Model_Menu("9","الــتـــعــديـــلات",Model_Menu.MenuType.MENU));

     listMenu121.addItem(new Model_Menu("","",Model_Menu.MenuType.EMPTY));
  
     listMenu121.addItem(new Model_Menu("","",Model_Menu.MenuType.TITLE));
     //listMenu1.addItem(new Model_Menu("","empty",Model_Menu.MenuType.EMPTY));
     listMenu121.addItem(new Model_Menu("7","",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("8","",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("9","",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("10","",Model_Menu.MenuType.MENU));
     listMenu121.addItem(new Model_Menu("","",Model_Menu.MenuType.EMPTY));
   
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradient1 = new ui.menufr.panelGradient();
        listMenu121 = new ui.menufr.ListMenu12<>();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(200, 680));
        setLayout(new java.awt.CardLayout());

        listMenu121.setPreferredSize(new java.awt.Dimension(212, 680));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N

        javax.swing.GroupLayout panelGradient1Layout = new javax.swing.GroupLayout(panelGradient1);
        panelGradient1.setLayout(panelGradient1Layout);
        panelGradient1Layout.setHorizontalGroup(
            panelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(listMenu121, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        panelGradient1Layout.setVerticalGroup(
            panelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu121, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(panelGradient1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
         Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       // GradientPaint g =new GradientPaint(0, 0, Color.decode("#1CB5E0"),0,getHeight(),Color.decode("#000046"));
      ///  GradientPaint g = new GradientPaint(0, 0, Color.decode("#000000"),0,getHeight(),Color.decode("#f1c96a"));//#f1c96a
      //  g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth()-20   , 0 , getWidth()  , getHeight());
        super.paintComponent(grphcs);
   }
    
    private int x,y;
    public void  initMoving(JFrame frame){
        panelGradient1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x =e.getX();
                y=e.getY();
            }          
        });
        panelGradient1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen()-x ,  e.getYOnScreen()-y);
            }    
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private ui.menufr.ListMenu12<String> listMenu121;
    private ui.menufr.panelGradient panelGradient1;
    // End of variables declaration//GEN-END:variables
}
