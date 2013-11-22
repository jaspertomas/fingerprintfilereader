/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.windows;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import managers.EmployeeDataManager;
import managers.EmployeeFileManager;
import models.Adjustments;
import models.CompiledEmployeeData;
import models.Dates;
import models.Employee;
import models.Holidays;
import models.TimeInOutData;
import models.WeeklyTimeData;

/**
 *
 * @author jaspertomas
 */
public class FrmManageEmployeeData extends javax.swing.JFrame {
    //---------------SINGLETON-------------------

    static FrmManageEmployeeData instance;

    public static FrmManageEmployeeData getInstance() {
        if (instance == null) {
            instance = new FrmManageEmployeeData();
        }
        return instance;
    }
    //---------------VARIABLES---------------------
    String value = "";
    //------------------------------------

    /**
     * Creates new form frmManageEmployeeData
     */
    public FrmManageEmployeeData() {
        initComponents();
        
        instance=this;

        refreshList();

        jList1.getSelectionModel().addListSelectionListener(
                new EmployeeListSelectionHandler());
        listDates.getSelectionModel().addListSelectionListener(
                new DateListSelectionHandler());

        DocumentListener doclistener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                FrmManageEmployeeData.getInstance().enableButtons(true);
            }

            public void removeUpdate(DocumentEvent e) {
                FrmManageEmployeeData.getInstance().enableButtons(true);
            }

            public void insertUpdate(DocumentEvent e) {
                FrmManageEmployeeData.getInstance().enableButtons(true);
            }
        };
        txtFname.getDocument().addDocumentListener(doclistener);
        txtMname.getDocument().addDocumentListener(doclistener);
        txtLname.getDocument().addDocumentListener(doclistener);
        txtCola.getDocument().addDocumentListener(doclistener);
        txtSalary.getDocument().addDocumentListener(doclistener);
        txtDeduction.getDocument().addDocumentListener(doclistener);
        onSelect();
        onDateSelect();

//        addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowOpened(java.awt.event.WindowEvent evt) {
//                if (jList1.getComponentCount() != 0) {
//                    jList1.setSelectedIndex(0);
//                    onSelect();
//                }
//            }
//        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLname = new javax.swing.JTextField();
        lblNickname = new javax.swing.JLabel();
        btnRevert = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtCola = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtDeduction = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDates = new javax.swing.JList();
        lblDatelbl = new javax.swing.JLabel();
        txtTimeIn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTimeOut = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnRevertTimes = new javax.swing.JButton();
        btnSaveTimes = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblNickname2 = new javax.swing.JLabel();
        btnExit2 = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        btnRevertAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Name");

        txtFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFnameActionPerformed(evt);
            }
        });

        jLabel2.setText("Monthly Salary");

        jLabel3.setText("Cola");

        jLabel5.setText("Nickname: ");

        lblNickname.setText("Nickname: ");

        btnRevert.setText("Revert");
        btnRevert.setEnabled(false);
        btnRevert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevertActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel4.setText("Deduction");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel6.setText("Manage Employees");

        listDates.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listDates);

        lblDatelbl.setText("Date");

        jLabel7.setText("Time In: ");

        jLabel8.setText("Time Out:");

        btnRevertTimes.setText("Revert");
        btnRevertTimes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevertTimesActionPerformed(evt);
            }
        });

        btnSaveTimes.setText("Save");
        btnSaveTimes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTimesActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel9.setText("Adjust Time In / Out: ");

        lblNickname2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblNickname2.setText("Employee Nickname");

        btnExit2.setText("Exit");
        btnExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit2ActionPerformed(evt);
            }
        });

        lblDate.setText("Date");

        btnRevertAll.setText("Revert All");
        btnRevertAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevertAllActionPerformed(evt);
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
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel3)
                                            .add(btnRevert))
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(1, 1, 1)
                                                .add(btnSave)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(btnDelete)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(btnExit))
                                            .add(layout.createSequentialGroup()
                                                .add(15, 15, 15)
                                                .add(txtCola, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(layout.createSequentialGroup()
                                        .add(0, 0, Short.MAX_VALUE)
                                        .add(jLabel1)
                                        .add(62, 62, 62)
                                        .add(txtFname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(txtMname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(txtLname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jSeparator1))
                                .add(78, 78, 78))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel2)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(txtSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel5)
                                        .add(27, 27, 27)
                                        .add(lblNickname))
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel4)
                                        .add(28, 28, 28)
                                        .add(txtDeduction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6)
                            .add(layout.createSequentialGroup()
                                .add(jLabel9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblNickname2)))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(btnRevertTimes)
                                        .add(1, 1, 1)
                                        .add(btnSaveTimes)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(btnRevertAll)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(btnExit2))
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel8)
                                            .add(lblDatelbl)
                                            .add(jLabel7))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(txtTimeIn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(lblDate)
                                            .add(txtTimeOut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(0, 0, Short.MAX_VALUE))))
                            .add(jSeparator3))
                        .add(78, 78, 78))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(lblNickname))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(txtFname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(txtMname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(txtLname, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(txtSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(txtCola, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(txtDeduction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnRevert)
                            .add(btnSave)
                            .add(btnDelete)
                            .add(btnExit)))
                    .add(jScrollPane1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(lblNickname2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lblDatelbl)
                            .add(lblDate))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(txtTimeIn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(txtTimeOut, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnRevertTimes)
                            .add(btnSaveTimes)
                            .add(btnExit2)
                            .add(btnRevertAll))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFnameActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        EmployeeFileManager efm = EmployeeFileManager.getInstance();
        Employee e = efm.getEmployees().get(jList1.getSelectedIndex());

        //show dialog box to confirm delete 
        int n = JOptionPane.showConfirmDialog(
                null,
                "Really delete " + e.getFullName() + "?",
                "Confirm delete employee",
                JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            efm.getEmployees().remove(e);
            efm.save();
            refreshList();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            Employee e = EmployeeFileManager.getInstance().getEmployees().get(jList1.getSelectedIndex());

            e.setNickname(lblNickname.getText());
            e.setFname(txtFname.getText());
            e.setMname(txtMname.getText());
            e.setLname(txtLname.getText());
            e.setMonthlySalary(Double.valueOf(txtSalary.getText().trim()));
            e.setCola(Double.valueOf(txtCola.getText().trim()));
            e.setDeduction(Double.valueOf(txtDeduction.getText().trim()));

            EmployeeFileManager.getInstance().save();

            onSelect();
        } catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRevertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevertActionPerformed
        onSelect();

    }//GEN-LAST:event_btnRevertActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        exit();
    }//GEN-LAST:event_btnExitActionPerformed

    private void exit()
    {
        setVisible(false);
        MainFrame.getInstance().setVisible(true);
        MainFrame.getInstance().recalculate();
    }
    
    private void btnRevertTimesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevertTimesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRevertTimesActionPerformed

    private void btnSaveTimesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTimesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveTimesActionPerformed

    private void btnExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit2ActionPerformed
        exit();
    }//GEN-LAST:event_btnExit2ActionPerformed

    private void btnRevertAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevertAllActionPerformed
        Adjustments.getInstance().reset();
    }//GEN-LAST:event_btnRevertAllActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmManageEmployeeData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmManageEmployeeData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmManageEmployeeData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmManageEmployeeData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmManageEmployeeData().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit2;
    private javax.swing.JButton btnRevert;
    private javax.swing.JButton btnRevertAll;
    private javax.swing.JButton btnRevertTimes;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveTimes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDatelbl;
    private javax.swing.JLabel lblNickname;
    private javax.swing.JLabel lblNickname2;
    private javax.swing.JList listDates;
    private javax.swing.JTextField txtCola;
    private javax.swing.JTextField txtDeduction;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtMname;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtTimeIn;
    private javax.swing.JTextField txtTimeOut;
    // End of variables declaration//GEN-END:variables

    public void onSelect() {
        //if no selected employee, do nothing
        if (jList1.getSelectedIndex() == -1) {
            return;
        }

        Employee e = EmployeeFileManager.getInstance().getEmployees().get(jList1.getSelectedIndex());
//        System.out.println(e);
        lblNickname.setText(e.getNickname());
        txtFname.setText(e.getFname());
        txtMname.setText(e.getMname());
        txtLname.setText(e.getLname());
        txtSalary.setText(e.getMonthlySalary().toString());
        txtCola.setText(e.getCola().toString());
        txtDeduction.setText(e.getDeduction().toString());
        enableButtons(false);

        //for time in / out adjustment
        lblNickname2.setText(e.getNickname());
        refreshDateList();
//        lblDate.setText(e.getNickname());
    
    }

    public void enableButtons(boolean b) {
        btnRevert.setEnabled(b);
        btnSave.setEnabled(b);
    }

    private void refreshList() {
        DefaultListModel model = new DefaultListModel();
        for (Employee e : EmployeeFileManager.getInstance().getEmployees()) {
            model.addElement(e);
        }
        jList1.setModel(model);
        
        //select first element
        jList1.setSelectedIndex(0);
        onSelect();
    }
    
    
    public void onDateSelect() {
        //if no selected employee, do nothing
        if (listDates.getSelectedIndex() == -1) {
            return;
        }
        
        SimpleDateFormat prettyDateFormat = new SimpleDateFormat("EE, MMMM dd, yyyy");

        String datestring = Dates.getInstance().getItems().get(listDates.getSelectedIndex());
//        try {
//            datestring=prettyDateFormat.format(Holidays.dateFormat.parse(datestring));
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
        
        lblDate.setText(datestring);
        WeeklyTimeData weeklydata=EmployeeDataManager.getInstance().getWeeklydata();
        CompiledEmployeeData edatamap = weeklydata.get(lblNickname.getText());
        TimeInOutData data = edatamap.get(datestring);
        if (data == null) {
            txtTimeIn.setText("(Absent)");
            txtTimeOut.setText("(Absent)");
        }       
        else
        {
//        System.out.println(e);
            txtTimeIn.setText(data.getSplitInTimeString());
            txtTimeOut.setText(data.getSplitOutTimeString());
            
            if(data.getInTimeString().contentEquals(data.getOutTimeString()))
            {
                lblDate.setText(datestring+" (Requires Adjustment!)");
            }
        }
        enableButtons(false);
    }    

    private void refreshDateList() {
        String temp;
        DefaultListModel model = new DefaultListModel();
        for (String datestring : Dates.getInstance().getItems()) {
            temp=datestring;
            WeeklyTimeData weeklydata=EmployeeDataManager.getInstance().getWeeklydata();
            CompiledEmployeeData edatamap = weeklydata.get(lblNickname.getText());
            TimeInOutData data = edatamap.get(datestring);

            //if data time in is equal to data time out, show exclamation point to date display
            if(data!=null && data.getInTimeString().contentEquals(data.getOutTimeString()))
            {
                temp+=" (!)";
            }
            model.addElement(temp);
        }
        listDates.setModel(model);
        
        //select first element
        listDates.setSelectedIndex(0);
        onDateSelect();
    }    
}

class EmployeeListSelectionHandler implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
//            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
//            int firstIndex = e.getFirstIndex();
//            int lastIndex = e.getLastIndex();
//            System.out.println("Event for indexes "
//                    + firstIndex + " - " + lastIndex
//                    + "; selected indexes:");
//
//
//            if (lsm.isSelectionEmpty()) {
//                System.out.println(" <none>");
//            } else {
//                // Find out which indexes are selected.
//                int minIndex = lsm.getMinSelectionIndex();
//                int maxIndex = lsm.getMaxSelectionIndex();
//                for (int i = minIndex; i <= maxIndex; i++) {
//                    if (lsm.isSelectedIndex(i)) {
//                        System.out.println(" " + i);
//                    }
//                }
//            }
//            System.out.println();
//            FrmManageEmployeeData.getInstance().onSelect(lsm.getMinSelectionIndex());
            FrmManageEmployeeData.getInstance().onSelect();
        }
    }
}


class DateListSelectionHandler implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            FrmManageEmployeeData.getInstance().onDateSelect();
        }
    }
}


//class DateWrapper extends Date {
//    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    
//    @Override
//    public String toString()
//    {
//        return dateFormat.format(this);
//    }
//}