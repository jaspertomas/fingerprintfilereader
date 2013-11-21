/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.windows;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.Holiday;
import models.Holidays;
import models.Settings;

/**
 *
 * @author jaspertomas
 */
public class FrmManageHolidays extends javax.swing.JFrame {
    //---------------SINGLETON-------------------

    static FrmManageHolidays instance;

    public static FrmManageHolidays getInstance() {
        if (instance == null) {
            instance = new FrmManageHolidays();
        }
        return instance;
    }
    //---------------VARIABLES---------------------
    /**
     * Creates new form frmManageHolidays
     */
    public FrmManageHolidays() {
        initComponents();
        
        instance=this;

        refreshList();

        setButtonMode();
        
        btnDownload.setVisible(false);

        lblYear.setText(Settings.getInstance().getCurrentYear().toString());
 
        listHolidays.getSelectionModel().addListSelectionListener(
                new HolidayListSelectionHandler());

        DocumentListener doclistener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                FrmManageHolidays.getInstance().enableButtons(true);
            }

            public void removeUpdate(DocumentEvent e) {
                FrmManageHolidays.getInstance().enableButtons(true);
            }

            public void insertUpdate(DocumentEvent e) {
                FrmManageHolidays.getInstance().enableButtons(true);
            }
        };
        txtName.getDocument().addDocumentListener(doclistener);
        txtDate.getDocument().addDocumentListener(doclistener);
        cmbType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                FrmManageHolidays.getInstance().enableButtons(true);
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNextYear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        btnPreviousYear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listHolidays = new javax.swing.JList();
        btnSave = new javax.swing.JButton();
        btnRevert = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDownload = new javax.swing.JButton();
        btnGenerate = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNextYear.setText("Next Year >>");
        btnNextYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextYearActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setText("Manage Holidays");

        lblYear.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblYear.setText("Year");

        btnPreviousYear.setText("<< Previous Year");
        btnPreviousYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousYearActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listHolidays);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRevert.setText("Revert");
        btnRevert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevertActionPerformed(evt);
            }
        });

        lblName.setText("Name");

        lblType.setText("Type");

        lblDate.setText("Date");

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular Holiday", "Special Non-working Holiday", "Other" }));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDownload.setText("Download Holidays from the Internet");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        btnGenerate.setText("Generate Holidays for this Year");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(layout.createSequentialGroup()
                                    .add(lblName)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(lblType)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(cmbType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(btnDownload, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(btnGenerate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(btnAdd)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnDelete)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnSave)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnRevert))
                            .add(layout.createSequentialGroup()
                                .add(lblDate)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(btnExit)))
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(btnPreviousYear)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblYear)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btnNextYear)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnNextYear)
                    .add(lblYear)
                    .add(btnPreviousYear))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lblName)
                            .add(txtName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lblType)
                            .add(cmbType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lblDate)
                            .add(txtDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnAdd)
                            .add(btnDelete)
                            .add(btnSave)
                            .add(btnRevert))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnGenerate)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnDownload)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnExit)
                        .add(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRevertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevertActionPerformed
        onSelect();
    }//GEN-LAST:event_btnRevertActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Date tempdate;
        
        //validate that date is set to the current year
        try {
            //do a little character conversion
            String temp=txtDate.getText();
            temp=temp.replace("/", "-");
            temp=temp.replace("\\", "-");
            
            tempdate=Holidays.dateFormat.parse(temp);
            if(!Holidays.yearFormat.format(tempdate).contentEquals(Settings.getInstance().getCurrentYear()))
            {
                JOptionPane.showMessageDialog(this, "Cannot set this holiday to a different year", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "This is not a date", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //validate that new date will not conflict with existing dates
        Holiday conflictholiday=Holidays.getInstance().getByDate(tempdate);
        if(conflictholiday!=null && Holidays.getInstance().getItems().indexOf(conflictholiday)!=listHolidays.getSelectedIndex())
        {
            JOptionPane.showMessageDialog(this, Holidays.dateFormat.format(tempdate)+" is already set for "+conflictholiday.getName()+".\nPlease use a different date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Holiday h = Holidays.getInstance().getItems().get(listHolidays.getSelectedIndex());
        Holidays.getInstance().edit(h, txtName.getText(), cmbType.getSelectedIndex(), tempdate);

        //update list in case holiday name has changed
//            listHolidays.repaint();
        Integer listindex=Holidays.getInstance().getItems().indexOf(h);
        refreshList();
        listHolidays.setSelectedIndex(listindex);
        onSelect();

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
//        listHolidays.getSelectedIndex();
        
        Holidays holidays = Holidays.getInstance();
        Holiday h = holidays.getItems().get(listHolidays.getSelectedIndex());

        //show dialog box to confirm delete 
        int n = JOptionPane.showConfirmDialog(
                null,
                "Really delete " + h.getName() + "?",
                "Confirm delete employee",
                JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            holidays.delete(h);
            if(holidays.getItems().isEmpty())
                refreshForm();
            else
                refreshList();
        }        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Holiday holiday=new Holiday("--New Holiday--",Holidays.OTHER,Holidays.getInstance().getFreeDate());
        Holidays.getInstance().add(holiday);
        refreshList();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        Holidays.getInstance().generate(Settings.getInstance().getCurrentYear());
        refreshForm();
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        setVisible(false);
        MainFrame.getInstance().setVisible(true);
//        MainFrame.getInstance().recalculate();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNextYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextYearActionPerformed
        Settings.getInstance().nextYear();
        refreshForm();
    }//GEN-LAST:event_btnNextYearActionPerformed

    private void btnPreviousYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousYearActionPerformed
        Settings.getInstance().previousYear();
        refreshForm();
    }//GEN-LAST:event_btnPreviousYearActionPerformed

    private void refreshForm()
    {
        Holidays.getInstance().load();
        refreshList();
        setButtonMode();        
        lblYear.setText(Settings.getInstance().getCurrentYear().toString());    
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrmManageHolidays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmManageHolidays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmManageHolidays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmManageHolidays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmManageHolidays().setVisible(true);
            }
        });
    }
    

    private void refreshList() {
        DefaultListModel model = new DefaultListModel();
        for (Holiday h : Holidays.getInstance().getItems()) {
            model.addElement(h);
        }
        listHolidays.setModel(model);
        
        //select first element
        listHolidays.setSelectedIndex(0);
        onSelect();
    }
    
    private void setButtonMode()
    {
        boolean formenabled=!Holidays.getInstance().getItems().isEmpty();
        btnAdd.setVisible(formenabled);
        btnDelete.setVisible(formenabled);
        btnSave.setVisible(formenabled);
        btnRevert.setVisible(formenabled);
        lblName.setVisible(formenabled);
        lblDate.setVisible(formenabled);
        lblType.setVisible(formenabled);
        txtName.setVisible(formenabled);
        txtDate.setVisible(formenabled);
        cmbType.setVisible(formenabled);

//        btnDownload.setVisible(!formenabled);
        btnGenerate.setVisible(!formenabled);
    }


    public void onSelect() {
        //if no selected employee, do nothing
        if (listHolidays.getSelectedIndex() == -1) {
            return;
        }

        Holiday e = Holidays.getInstance().getItems().get(listHolidays.getSelectedIndex());
//        System.out.println(e);
        txtName.setText(e.getName());
        txtDate.setText(Holidays.dateFormat.format(e.getDate()));
        cmbType.setSelectedIndex(e.getType());
        enableButtons(false);
    }

    public void enableButtons(boolean b) {
        btnRevert.setEnabled(b);
        btnSave.setEnabled(b);
//        btnDelete.setEnabled(b);
//        btnAdd.setEnabled(b);
//        btnRevert.setEnabled(b);
//        btnSave.setEnabled(b);
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnNextYear;
    private javax.swing.JButton btnPreviousYear;
    private javax.swing.JButton btnRevert;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblYear;
    private javax.swing.JList listHolidays;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}

class HolidayListSelectionHandler implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            FrmManageHolidays.getInstance().onSelect();
        }
    }
}