package timePcker;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class TimePicker extends javax.swing.JPanel {

    private final SimpleDateFormat formatampm = new SimpleDateFormat("hh:mm aa");
    private final SimpleDateFormat format24h = new SimpleDateFormat("HH:mm");
    private final DecimalFormat numberFormat = new DecimalFormat("00");
    private JTextField displayText;
    private List<EventTimePicker> events;
    private JPopupMenu menu;
    private SimpleDateFormat format = formatampm;

    public TimePicker() {
        initComponents();
        init();
        jCB24hour.setSelected(true);
    }

    private void init() {
        events = new ArrayList<>();
        now();
        panel.setVisible(!jCB24hour.isSelected());
        EventTimeSelected event = new EventTimeSelected() {
            @Override
            public void hourSelected(int hour) {
                cmdHour.setText(numberFormat.format(hour));
                displayOnText();
                runEvent();
            }

            @Override
            public void minuteSelected(int minute) {
                cmdMinute.setText(numberFormat.format(minute));
                displayOnText();
                runEvent();
            }
        };
        timeComponent.addEventTimeSelected(event);
        timeComponent.setEventTimeChange(new EventTimeChange() {
            @Override
            public void timeChange(boolean isHour) {
                if (isHour) {
                    cmdHour.setForeground(Color.WHITE);
                    cmdMinute.setForeground(new Color(178, 178, 178));
                } else {
                    cmdMinute.setForeground(Color.WHITE);
                    cmdHour.setForeground(new Color(178, 178, 178));
                }
                displayOnText();
                runEvent();
            }
        });
        setForeground(new Color(37, 88, 207));
    }

    private void changeAM(boolean am) {
        if (am) {
            cmdAM.setForeground(Color.WHITE);
            cmdPM.setForeground(new Color(178, 178, 178));
        } else {
            cmdPM.setForeground(Color.WHITE);
            cmdAM.setForeground(new Color(178, 178, 178));
        }
        displayOnText();
        runEvent();
    }

    private void displayOnText() {
        if (displayText != null) {
            if (jCB24hour.isSelected()) {
                displayText.setText(cmdHour.getText() + ":" + cmdMinute.getText());
            } else {
      displayText.setText(cmdHour.getText() + ":" + cmdMinute.getText() + " " + (cmdAM.getForeground() == Color.WHITE ? "AM" : "PM"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        lbSplit = new timePcker.TimePickerLabel();
        panel = new javax.swing.JPanel();
        cmdAM = new timePcker.TimePickerButton();
        cmdPM = new timePcker.TimePickerButton();
        cmdHour = new timePcker.TimePickerButton();
        cmdMinute = new timePcker.TimePickerButton();
        bg = new javax.swing.JPanel();
        timeComponent = new timePcker.TimeComponent();
        jCB24hour = new javax.swing.JCheckBox();
        cmdCancel = new timePcker.TimePickerButton();
        cmdOK = new timePcker.TimePickerButton();

        setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(37, 88, 207));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        panelHeader.setOpaque(false);

        lbSplit.setForeground(new java.awt.Color(178, 178, 178));
        lbSplit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSplit.setText(":");
        lbSplit.setFont(new java.awt.Font("sansserif", 1, 25)); // NOI18N

        panel.setOpaque(false);

        cmdAM.setBackground(new java.awt.Color(37, 88, 207));
        cmdAM.setBorder(null);
        cmdAM.setForeground(new java.awt.Color(255, 255, 255));
        cmdAM.setText("AM");
        cmdAM.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        cmdAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAMActionPerformed(evt);
            }
        });

        cmdPM.setBackground(new java.awt.Color(37, 88, 207));
        cmdPM.setBorder(null);
        cmdPM.setForeground(new java.awt.Color(255, 255, 255));
        cmdPM.setText("PM");
        cmdPM.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        cmdPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmdAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(cmdAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(cmdPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cmdHour.setBackground(new java.awt.Color(37, 88, 207));
        cmdHour.setForeground(new java.awt.Color(255, 255, 255));
        cmdHour.setText("07");
        cmdHour.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        cmdHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHourActionPerformed(evt);
            }
        });

        cmdMinute.setBackground(new java.awt.Color(37, 88, 207));
        cmdMinute.setForeground(new java.awt.Color(178, 178, 178));
        cmdMinute.setText("30");
        cmdMinute.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        cmdMinute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMinuteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdHour, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbSplit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmdHour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(cmdMinute, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbSplit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(37, 88, 207)));
        bg.setOpaque(false);

        timeComponent.setBackground(new java.awt.Color(242, 242, 242));

        jCB24hour.setText("24hour");
        jCB24hour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB24hourActionPerformed(evt);
            }
        });

        cmdCancel.setForeground(new java.awt.Color(37, 88, 207));
        cmdCancel.setText("Cancel");
        cmdCancel.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        cmdOK.setForeground(new java.awt.Color(37, 88, 207));
        cmdOK.setText("OK");
        cmdOK.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jCB24hour)
                        .addGap(90, 90, 90)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(timeComponent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(timeComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCB24hour)
                .addGap(0, 0, 0)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHourActionPerformed
        timeComponent.changeToHour();
    }//GEN-LAST:event_cmdHourActionPerformed

    private void cmdMinuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMinuteActionPerformed
        timeComponent.changeToMinute();
    }//GEN-LAST:event_cmdMinuteActionPerformed

    private void cmdAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAMActionPerformed
        changeAM(true);
    }//GEN-LAST:event_cmdAMActionPerformed

    private void cmdPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPMActionPerformed
        changeAM(false);
    }//GEN-LAST:event_cmdPMActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        if (menu != null) {
            menu.setVisible(false);
        }
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        if (menu != null) {
            menu.setVisible(false);
        }
    }//GEN-LAST:event_cmdOKActionPerformed
    private int x;
    private int y;
    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        x = evt.getX();
        y = evt.getY() + 6;
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        if (menu != null && menu.isVisible()) {
            if (SwingUtilities.isLeftMouseButton(evt)) {
                int xs = evt.getXOnScreen();
                int ys = evt.getYOnScreen();
                menu.setLocation(xs - x, ys - y);

            }
        }
    }//GEN-LAST:event_headerMouseDragged

    private void jCB24hourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB24hourActionPerformed
        panel.setVisible(!jCB24hour.isSelected());
        timeComponent.set24hour(jCB24hour.isSelected());
        timeComponent.repaint();
      //  checkBorder(jCB24hour.isSelected());
    }//GEN-LAST:event_jCB24hourActionPerformed
    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(color);
            timeComponent.setColor(color);
            cmdCancel.setForeground(color);
            cmdOK.setForeground(color);
            cmdHour.setBackground(color);
            cmdMinute.setBackground(color);
            cmdAM.setBackground(color);
            cmdPM.setBackground(color);
            if (menu != null) {
                menu.setBackground(color);
            }
        }
    }

    public JTextField getDisplayText() {
        return displayText;
    }
    
    public void setDisplayText(JTextField displayText) {
        this.displayText = displayText;
    }

    public void setSelectedTime(Date date) {
        String now = format.format(date);
        int hour = Integer.valueOf(now.split(":")[0]);
        int minute = Integer.valueOf(now.split(":")[1].split(" ")[0]);
        if (format.equals(formatampm)) {
            changeAM(now.split(" ")[1].equals("AM"));
        }
        cmdHour.setText(numberFormat.format(hour));
        cmdMinute.setText(numberFormat.format(minute));
        timeComponent.setSelectedHour(hour, minute);
    }

    public void now() {
        setSelectedTime(new Date());
    }

    public String getSelectedTime() {
        return cmdHour.getText() + ":" + cmdMinute.getText() + " " + (cmdAM.getForeground() == Color.WHITE ? "AM" : "PM");
    }

    public Date getSelectedDate() {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(cmdHour.getText()));
        c.set(Calendar.MINUTE, Integer.valueOf(cmdMinute.getText()));

        return c.getTime();
    }

    private void runEvent() {
        for (EventTimePicker event : events) {
            event.timeSelected(getSelectedTime());
        }
    }

    public void addEventTimePicker(EventTimePicker event) {
        events.add(event);
    }

    public void addActionListener(ActionListener event) {
        cmdOK.addActionListener(event);
    }

    public void set24hourMode(boolean value) {
        jCB24hour.setVisible(false);
        jCB24hour.setSelected(value);
        format = (value ? format24h : formatampm);
        panel.setVisible(!value);
        timeComponent.set24hour(value);
       // checkBorder(value);
        timeComponent.repaint();
    }

    private void checkBorder(boolean value) {
        if (value) {
            bg.setBorder(new EmptyBorder(0, 10, 0, 10));
        } else {
            bg.setBorder(new EmptyBorder(0, 0, 0, 0));
        }
    }

    public void showPopup(Component com, int x, int y) {
        if (menu == null) {
            menu = new TimePickerMenu();
            menu.setBackground(getForeground());
            menu.add(this);
        }
        menu.show(com, x, y);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private timePcker.TimePickerButton cmdAM;
    private timePcker.TimePickerButton cmdCancel;
    private timePcker.TimePickerButton cmdHour;
    private timePcker.TimePickerButton cmdMinute;
    private timePcker.TimePickerButton cmdOK;
    private timePcker.TimePickerButton cmdPM;
    private javax.swing.JPanel header;
    private javax.swing.JCheckBox jCB24hour;
    private timePcker.TimePickerLabel lbSplit;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelHeader;
    private timePcker.TimeComponent timeComponent;
    // End of variables declaration//GEN-END:variables
}
