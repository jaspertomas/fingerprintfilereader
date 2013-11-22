/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.windows;

import java.awt.Font;
import java.io.File;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import managers.EmployeeDataManager;
import managers.EmployeeFileManager;
import utils.fileaccess.FileWriter;

/**
 *
 * @author jaspertomas
 */
public class MainFrame extends java.awt.Frame {
    //---------------SINGLETON-------------------

    static MainFrame instance;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
    //---------------VARIABLES---------------------    
    public MainFrame() {
        initComponents();

        instance=this;

        EmployeeDataManager.initialize(txtStartDate, txtEndDate, jTextArea);
        EmployeeFileManager.getInstance().load();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnManageHolidays = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        txtStartDate = new javax.swing.JTextField();
        txtEndDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnChooseCsvFile = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnManageEmployeeData = new javax.swing.JButton();
        btnRecalculate = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbFontSize = new javax.swing.JComboBox();
        btnSaveText = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        btnManageHolidays.setText("Manage Holidays");
        btnManageHolidays.setToolTipText("");
        btnManageHolidays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageHolidaysActionPerformed(evt);
            }
        });

        jTextArea.setColumns(60);
        jTextArea.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTextArea.setRows(20);
        jScrollPane2.setViewportView(jTextArea);

        jLabel1.setText("Start Date");

        jLabel2.setText("End Date");

        btnChooseCsvFile.setText("Choose CSV data file");
        btnChooseCsvFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseCsvFileActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel3.setText("FingerPrint Scanner Payroll Calculator");

        btnManageEmployeeData.setText("Manage Employee Data");
        btnManageEmployeeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageEmployeeDataActionPerformed(evt);
            }
        });

        btnRecalculate.setText("Recalculate");
        btnRecalculate.setToolTipText("");
        btnRecalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecalculateActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel4.setText("Font Size");

        cmbFontSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "12", "14", "16", "18", "20", "22", "24" }));
        cmbFontSize.setSelectedIndex(2);
        cmbFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFontSizeActionPerformed(evt);
            }
        });

        btnSaveText.setText("Save Output to File");
        btnSaveText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTextActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtStartDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtEndDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnRecalculate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 562, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(btnChooseCsvFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnManageEmployeeData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnManageHolidays, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(cmbFontSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(btnSaveText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btnExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnChooseCsvFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnManageHolidays, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnManageEmployeeData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtStartDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(txtEndDate)
                    .add(btnRecalculate))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(cmbFontSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnExit)
                    .add(btnSaveText))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void btnManageHolidaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageHolidaysActionPerformed
        setVisible(false);
        FrmManageHolidays.getInstance().setVisible(true);
//        Holidays.getInstance().generate("2014");
        
    }//GEN-LAST:event_btnManageHolidaysActionPerformed
    private JFileChooser fc,sfc;
    private void btnChooseCsvFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseCsvFileActionPerformed

        //Create a file chooser
        fc = new JFileChooser();
        
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //parse file
            File file = fc.getSelectedFile();

            //if file hasn't been selected, do nothing
            if(file==null)return;
            
//            System.out.println(file.getPath());
//            File file=new File("/Users/jaspertomas/NetBeansProjects/Fingerprint/NewGlog_0001_20130921114600.csv");

            EmployeeDataManager.getInstance().calculate(file);


//            //show dialog box to ask whether to save output to file
//            int n = JOptionPane.showConfirmDialog(
//                    null,
//                    "Would you like to save the output to a file?",
//                    "Save output",
//                    JOptionPane.YES_NO_OPTION);
//
//            if (n == JOptionPane.YES_OPTION) {
//                saveDialog();
//            }        
        
        } 
    }//GEN-LAST:event_btnChooseCsvFileActionPerformed

    private void btnManageEmployeeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEmployeeDataActionPerformed
        setVisible(false);
        FrmManageEmployeeData.getInstance().setVisible(true);
        
    }//GEN-LAST:event_btnManageEmployeeDataActionPerformed

    private void btnRecalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecalculateActionPerformed
        recalculate();
    }//GEN-LAST:event_btnRecalculateActionPerformed

    public void recalculate()
    {
        if(fc==null)return;
        
        //parse file
        File file = fc.getSelectedFile();

        //if file hasn't been selected, do nothing
        if(file==null)return;
//        {
//            JOptionPane.showMessageDialog (null, "Please Choose CSV data file", "Error", JOptionPane.PLAIN_MESSAGE);
//            return;
//        }
        
        if(!EmployeeDataManager.getInstance().validateDates())return;

        EmployeeDataManager.getInstance().recalculate(file);    
        
        JOptionPane.showMessageDialog(this, "Payroll recalculated");
        
    }
    
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void cmbFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFontSizeActionPerformed
        jTextArea.setFont(new Font(jTextArea.getFont().getFontName(),Font.PLAIN,Integer.valueOf(cmbFontSize.getSelectedItem().toString())));
    }//GEN-LAST:event_cmbFontSizeActionPerformed

    private void btnSaveTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTextActionPerformed
        if(jTextArea.getText().isEmpty())
            JOptionPane.showMessageDialog (this, "Nothing to save", "Error", JOptionPane.PLAIN_MESSAGE);
        else    
            saveDialog();
    }//GEN-LAST:event_btnSaveTextActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseCsvFile;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnManageEmployeeData;
    private javax.swing.JButton btnManageHolidays;
    private javax.swing.JButton btnRecalculate;
    private javax.swing.JButton btnSaveText;
    private javax.swing.JComboBox cmbFontSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtStartDate;
    // End of variables declaration//GEN-END:variables

    
       public void saveDialog()
       {

                //write output to file
                Date date=new Date();
                String[] datesegments=date.toString().split(" ");
                String datestring=datesegments[1]+"-"+datesegments[2]+"-"+datesegments[5];

                if(sfc==null)
                {
                    sfc = new JFileChooser();
                    sfc.setSelectedFile(new File(sfc.getCurrentDirectory().getPath()+"/"+datestring+"-payroll.txt"));
                }
                //In response to a button click:
                int returnVal = sfc.showSaveDialog(this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    //parse file
        //            System.out.println(sfc.getSelectedFile().getPath());
                    File savefile = sfc.getSelectedFile();

                    //if file hasn't been selected, do nothing
                    if(savefile==null)return;

                    FileWriter.write(savefile.getPath(), jTextArea.getText());
                }          
       }
}
class MyFileFilter
{

       String description = "Sorted Files (*.srt)";//the filter you see  
       String extension = "srt";//the filter passed to program  
       public String getDescription()  
       {  
         return description;  
       }  
       public boolean accept(File f)  
       {  
         if(f == null) return false;  
         if(f.isDirectory()) return true;  
         return f.getName().toLowerCase().endsWith(extension);  
       }  
       
}