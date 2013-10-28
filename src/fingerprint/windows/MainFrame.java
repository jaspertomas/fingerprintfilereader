/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.windows;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.Calendar;
import models.DailyEmployeeData;
import models.TimeRecord;
import models.DailyEmployeesData;
import models.DateUtil;
import models.EmployeeNameList;
import models.WeeklyEmployeesData;
import utils.fileaccess.FileReader;

/**
 *
 * @author jaspertomas
 */
public class MainFrame extends java.awt.Frame {

    /**
     * Creates new form MainFrame
     */
       public final Time twelve=new Time(12,0,0);
       public final Time one=new Time(13,0,0);
       public final Time eightthirty=new Time(8,30,0);
       
       //this is a map of TimeRecord array lists
       //it is the result of the parsing.
       //it contains all the timerecords from the parsed file, 
       //regardless of whether they are the first record of the day, or the last, or in between
       //The map's keys are the date
       //The map's values are arraylists, each containing TimeRecords of the same date
    TreeMap<String,ArrayList<TimeRecord>> parseresult;
       
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGenerateExcelFile = new javax.swing.JButton();
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

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        btnGenerateExcelFile.setText("Generate Excel file");
        btnGenerateExcelFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateExcelFileActionPerformed(evt);
            }
        });

        jTextArea.setColumns(60);
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

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(btnChooseCsvFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnGenerateExcelFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnManageEmployeeData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnChooseCsvFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnGenerateExcelFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnManageEmployeeData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtStartDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(txtEndDate)
                    .add(btnRecalculate))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 313, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    ArrayList<TimeRecord> timerecords;
    private void btnGenerateExcelFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateExcelFileActionPerformed

    }//GEN-LAST:event_btnGenerateExcelFileActionPerformed
    private JFileChooser fc;
    File file;
    private void btnChooseCsvFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseCsvFileActionPerformed
        // TODO add your handling code here:
        //Create a file chooser
        fc = new JFileChooser();
        
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //parse file
            file = fc.getSelectedFile();

            //parse file and create records list
            parseFile(file);
            
            //create employee name list
            // and date list
            EmployeeNameList employeenamelist=new EmployeeNameList();
            Calendar calendar=new Calendar();
            for(TimeRecord timerecord:timerecords)
            {
                if(!employeenamelist.contains(timerecord.getName()))
                    employeenamelist.add(timerecord.getName());
                if(!calendar.contains(timerecord.getDate()))
                    calendar.add(timerecord.getDate());
            }
            //set date textboxes according to the date range in the calendar
            txtStartDate.setText(calendar.get(0));
            txtEndDate.setText(calendar.get(calendar.size()-1));
                        
            //start to process data into array structure
            WeeklyEmployeesData wed=new WeeklyEmployeesData();
            //arrange records into employees, 
            //get earliest and latest record per employee
            //discard the rest
            //set these as in and out respectively

            //each ArrayList contains TimeRecords of the same date
            for(ArrayList<TimeRecord> recordlist :parseresult.values())
            {
                //do this for each set of records of the same date
                
                //this map has the employee name as key 
                // and packaged in and out records as data
                //it is recreated for each date
                DailyEmployeesData edatamap=genEmployeeDataMap(recordlist);
//                if(recordlist.size()>0)
                wed.put(recordlist.get(0).getDate(), edatamap);
            }
            
            //sample output
            for(String name:employeenamelist)
            {
                for(String date:calendar)
                {
                    DailyEmployeeData data=wed.get(date).get(name);
                    if(data==null)continue;
                    
                    jTextArea.append(name+"\t");
                    jTextArea.append(date+"\t");
                    if(data.getIn().getTime().equals(one))
                    {
                        jTextArea.append(
                            ""
                            +"\t"
                            +""
                            +"\t"
                            +data.getInTimeString()
                            +"\t"
                            +data.getOutTimeString()
                            +"\n"
                            );
                    }
                    else
                    if(data.getOut().getTime().equals(twelve))
                    {
                        jTextArea.append(
                            data.getInTimeString()
                            +"\t"
                            +data.getOutTimeString()
                            +""
                            +"\t"
                            +""
                            +"\t"
                            +"\n"
                            );
                    }
                      else
                    {
                    jTextArea.append(
                            data.getInTimeString()
                            +"\t"
                            +"1200"
                            +"\t"
                            +"1300"
                            +"\t"
                            +data.getOutTimeString()
                            +"\n"
                            );
                    }
                    
                }
            }
            
        } 
    }//GEN-LAST:event_btnChooseCsvFileActionPerformed

    private boolean validateDates()
    {
        if(txtStartDate.getText().trim().isEmpty() )
        {
            JOptionPane.showMessageDialog (null, "Start Date cannot be empty", "Error", JOptionPane.PLAIN_MESSAGE);            
            return false;
        }
        if(txtEndDate.getText().trim().isEmpty() )
        {
            JOptionPane.showMessageDialog (null, "End Date cannot be empty", "Error", JOptionPane.PLAIN_MESSAGE);            
            return false;
        }

        String[] segments=txtStartDate.getText().trim().replace("-","/").split("/");   
        if(segments.length!=3){JOptionPane.showMessageDialog (null, "Start Date is invalid", "Error", JOptionPane.PLAIN_MESSAGE); return false;}
        for(int i=0;i<3;i++)
        {
            segments[i]=String.format("%02d", Integer.valueOf(segments[i]));
        }
        txtStartDate.setText(segments[0]+"/"+segments[1]+"/"+segments[2]);
        
        segments=txtEndDate.getText().trim().replace("-","/").split("/");   
        if(segments.length!=3){JOptionPane.showMessageDialog (null, "End Date is invalid", "Error", JOptionPane.PLAIN_MESSAGE); return false;}
        for(int i=0;i<3;i++)
        {
            segments[i]=String.format("%02d", Integer.valueOf(segments[i]));
        }
        txtEndDate.setText(segments[0]+"/"+segments[1]+"/"+segments[2]);
            
        if(DateUtil.isEarlierThan(txtEndDate.getText(), txtStartDate.getText()))
        {
            String temp=txtStartDate.getText();
            txtStartDate.setText(txtEndDate.getText());
            txtEndDate.setText(temp);
        }
        return true;
    }
    
    private void btnManageEmployeeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEmployeeDataActionPerformed
        validateDates();
    }//GEN-LAST:event_btnManageEmployeeDataActionPerformed

    private void btnRecalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecalculateActionPerformed
        if(!validateDates())return;

        //if file hasn't been selected, do nothing
        if(file==null)return;

        //parse file and create records list
        parseFile(file);

        //create employee name list
        // and date list
        EmployeeNameList namelist=new EmployeeNameList();
        Calendar datelist=new Calendar();
        for(TimeRecord record:timerecords)
        {
            if(!namelist.contains(record.getName()))
                namelist.add(record.getName());
            if(
                    DateUtil.isLaterThanOrEqualTo(record.getDate(), txtStartDate.getText())
                    &&
                    DateUtil.isEarlierThanOrEqualTo(record.getDate(), txtEndDate.getText())
                    &&
                    !datelist.contains(record.getDate())
              )
                datelist.add(record.getDate());
        }

        //process data into array structure
        WeeklyEmployeesData wed=new WeeklyEmployeesData();
        //arrange records into employees, 
        //get earliest and latest record per employee
        //set these as in and out respectively
        //datesmap is a map with date as key and array of records as value
        //each array of records contains records of the same date
        ArrayList<TimeRecord> recordlist;
        for(String key :parseresult.keySet())
        {
            //only process records that fall between the start and end dates
            if(
                    DateUtil.isLaterThanOrEqualTo(key, txtStartDate.getText())
                    &&
                    DateUtil.isEarlierThanOrEqualTo(key, txtEndDate.getText())
              )
            {
                recordlist=parseresult.get(key);
                //do this for each set of records of the same date

                //this map has the employee name as key 
                // and packaged in and out records as data
                //it is recreated for each date
                DailyEmployeesData edatamap=genEmployeeDataMap(recordlist);
//                if(recordlist.size()>0)
                wed.put(recordlist.get(0).getDate(), edatamap);
            }
        }

        //sample output
        for(String name:namelist)
        {
            for(String date:datelist)
            {
                DailyEmployeeData data=wed.get(date).get(name);
                if(data==null)continue;

                jTextArea.append(name+"\t");
                jTextArea.append(date+"\t");
                if(data.getIn().getTime().equals(one))
                {
                    jTextArea.append(
                        ""
                        +"\t"
                        +""
                        +"\t"
                        +data.getInTimeString()
                        +"\t"
                        +data.getOutTimeString()
                        +"\n"
                        );
                }
                else
                if(data.getOut().getTime().equals(twelve))
                {
                    jTextArea.append(
                        data.getInTimeString()
                        +"\t"
                        +data.getOutTimeString()
                        +""
                        +"\t"
                        +""
                        +"\t"
                        +"\n"
                        );
                }
                  else
                {
                jTextArea.append(
                        data.getInTimeString()
                        +"\t"
                        +"1200"
                        +"\t"
                        +"1300"
                        +"\t"
                        +data.getOutTimeString()
                        +"\n"
                        );
                }

            }
        }
    }//GEN-LAST:event_btnRecalculateActionPerformed

    
    private void parseFile(File file)
    {
        //extract file contents
        String filecontents=FileReader.read(file.getPath());

        //remove double spaces and null characters
        filecontents=filecontents.replace("\0", "");
        filecontents=filecontents.replace("\n\n", "\n");
        
        //split into lines
        String[] lines=filecontents.split("\n");

        //convert lines into objects
        timerecords= new ArrayList<TimeRecord>();
        for(String line:lines)
        {
            if(line.trim().length()!=0 && !line.contains("APB\tJobCode\tDateTime"))
                timerecords.add(new TimeRecord(line));
        }
        
        //group by date
        ArrayList<TimeRecord> dailyrecordlist; 
        parseresult=new TreeMap<String,ArrayList<TimeRecord>>();
        for(TimeRecord record:timerecords)
        {
            if(!parseresult.containsKey(record.getDate()))
            {
                dailyrecordlist=new ArrayList<TimeRecord>();
                dailyrecordlist.add(record);
                parseresult.put(record.getDate(), dailyrecordlist);
            }
            else
            {
                dailyrecordlist=parseresult.get(record.getDate());
                dailyrecordlist.add(record);
//                    datesmap.put(record.getDate(), dailyrecords);
            }

        }
    }
    
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
    private javax.swing.JButton btnGenerateExcelFile;
    private javax.swing.JButton btnManageEmployeeData;
    private javax.swing.JButton btnRecalculate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtStartDate;
    // End of variables declaration//GEN-END:variables

    private DailyEmployeesData genEmployeeDataMap(ArrayList<TimeRecord> recordlist)
    {
        String date;
        DailyEmployeesData employeedatamap=new DailyEmployeesData();
        date=recordlist.get(0).getDate();
//                System.out.println(date);

        //process all records, sort by employee
        DailyEmployeeData edata;
        for(TimeRecord tr:recordlist)
        {
            if(!employeedatamap.containsKey(tr.getName()))
            {
                edata=new DailyEmployeeData();
                employeedatamap.put(tr.getName(), edata);
                edata.setIn(tr);
                edata.setOut(tr);
            }
            else
            {
                edata=employeedatamap.get(tr.getName());
                if(edata.getIn().islaterthan(tr))
                {
                    edata.setIn(tr);
                }
                else if(edata.getOut().isearlierthan(tr))
                {
                    edata.setOut(tr);
                }
            }
        }
//        System.out.println(date);
        Time time;
        for(DailyEmployeeData edata2:employeedatamap.values())
        {
            //adjust in time
            time=edata2.getIn().getTime();
            //adjust in time to 8:30 if earlier than 8:30
            if
            (
                time.before(eightthirty)
            )
            {
                edata2.getIn().setTime(eightthirty);
            }
            else
            //adjust in time to 1:00 if between 12 and 1
            if
            (
                time.after(twelve)
                &&
                time.before(one)
            )
            {
                edata2.getIn().setTime(one);
            }
            //adjust out time to 12:00 if between 12 and 1
            time=edata2.getOut().getTime();
            if
            (
                time.after(twelve)
                &&
                time.before(one)
            )
            {
                edata2.getOut().setTime(twelve);
            }


//            System.out.print(edata2.getIn().getName());
//            System.out.print("-");
//            System.out.print(edata2.getIn().getTime());
//            System.out.print("-");
//            System.out.print(edata2.getOut().getTime());
//            if(edata2.getIn().getTime().compareTo(edata2.getOut().getTime())==0)
//                System.out.print(" only 1 record");
//            System.out.println();
        }  
//        System.out.println("----------");

        return employeedatamap;
    }


}
