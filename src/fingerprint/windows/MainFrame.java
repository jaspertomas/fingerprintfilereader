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
import models.TimeInOutData;
import models.TimeRecord;
import models.CompiledEmployeeData;
import models.DateUtil;
import models.EmployeeNameList;
import models.WeeklyTimeData;
import utils.fileaccess.FileReader;

/**
 *
 * @author jaspertomas
 */
public class MainFrame extends java.awt.Frame {
    //if a line contains this, it's the header - ignore it
        public final String headerfingerprint="APB\tJobCode\tDateTime";

       public final Time twelve=new Time(12,0,0);
       public final Time one=new Time(13,0,0);
       public final Time eightthirty=new Time(8,30,0);

    //this is a string array of all employee names included in the parsed file
    EmployeeNameList employeenamelist;
    //this is a string array of all dates included in the parsed file
    Calendar calendar;

    //key = employee
    //value = all timerecords by a specific employee
    TreeMap<String,ArrayList<TimeRecord>> timerecordsbyemployee;
    WeeklyTimeData weeklydata;
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
    private void btnChooseCsvFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseCsvFileActionPerformed

        //Create a file chooser
        fc = new JFileChooser();
        
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            calculate();
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

        //parse file
        File file = fc.getSelectedFile();

        //if file hasn't been selected, do nothing
        if(file==null)
        {
            JOptionPane.showMessageDialog (null, "Please Choose CSV data file", "Error", JOptionPane.PLAIN_MESSAGE);
            return;
        }

            //parse file and create records list
            parseFile(file);
            
            //remove calendar entries that do not fit between startdate and enddate
            ArrayList<TimeRecord> temp=new ArrayList<TimeRecord>();
            for(TimeRecord timerecord:timerecords)
            {
                if(
                    DateUtil.isEarlierThan(timerecord.getDate(), txtStartDate.getText())
                    ||
                    DateUtil.isLaterThan(timerecord.getDate(), txtEndDate.getText())
                )
                    temp.add(timerecord);
            }
            for(TimeRecord timerecord:temp)
                timerecords.remove(timerecord);
            
            //arrange time records into arraylists by employee
            groupTimeRecordsByEmployee();
            
            createNameListAndCalendar();
            
            
            //start to process data into array structure
            weeklydata=new WeeklyTimeData();
            for(ArrayList<TimeRecord> timerecordlist :timerecordsbyemployee.values())
            {
                CompiledEmployeeData edatamap=genEmployeeDataMap(timerecordlist);
                weeklydata.put(timerecordlist.get(0).getName(), edatamap);
            }
            printSampleOutput();
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
            //ignore the header
            if(line.contains(headerfingerprint))continue;
            //ignore empty lines
            else if(line.trim().length()==0 )continue;
            else
                timerecords.add(new TimeRecord(line));
        }
    }
    private void groupTimeRecordsByEmployee()
    {
        //group by date
        ArrayList<TimeRecord> dailyrecordlist; 
        timerecordsbyemployee=new TreeMap<String,ArrayList<TimeRecord>>();
        for(TimeRecord record:timerecords)
        {
            if(!timerecordsbyemployee.containsKey(record.getName()))
            {
                dailyrecordlist=new ArrayList<TimeRecord>();
                dailyrecordlist.add(record);
                timerecordsbyemployee.put(record.getName(), dailyrecordlist);
            }
            else
            {
                dailyrecordlist=timerecordsbyemployee.get(record.getName());
                dailyrecordlist.add(record);
            }

        }
    }
//    private void groupTimeRecordsByDate()
//    {
//        //group by date
//        ArrayList<TimeRecord> dailyrecordlist; 
//        parseresult=new TreeMap<String,ArrayList<TimeRecord>>();
//        for(TimeRecord record:timerecords)
//        {
//            if(!parseresult.containsKey(record.getDate()))
//            {
//                dailyrecordlist=new ArrayList<TimeRecord>();
//                dailyrecordlist.add(record);
//                parseresult.put(record.getDate(), dailyrecordlist);
//            }
//            else
//            {
//                dailyrecordlist=parseresult.get(record.getDate());
//                dailyrecordlist.add(record);
////                    datesmap.put(record.getDate(), dailyrecords);
//            }
//
//        }
//    }
    
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

    private CompiledEmployeeData genEmployeeDataMap(ArrayList<TimeRecord> employeetimerecordlist)
    {
        //employeetimerecordlist contains many login time records per day, spanning many days

        CompiledEmployeeData compiledemployeedata=new CompiledEmployeeData();

        //process all records, sort by date
        //for each time record:
        TimeInOutData inoutdata;
        for(TimeRecord tr:employeetimerecordlist)
        {
            //if the daily record for the time record doesn't exist, create it, 
            //save the time record as a new daily record, using the recorded time as both in and out
            if(!compiledemployeedata.containsKey(tr.getDate()))
            {
                inoutdata=new TimeInOutData();
                inoutdata.setIn(tr);
                inoutdata.setOut(tr);
                compiledemployeedata.put(tr.getDate(), inoutdata);
            }
            //else if the daily record exists
            //set the time as time in if it's earlier than the existing time out
            //or set the time as time out if it's later than the existing time in
            //* this works because time in is always earlier than time out
            else
            {
                inoutdata=compiledemployeedata.get(tr.getDate());
                if(inoutdata.getIn().islaterthan(tr))
                {
                    inoutdata.setIn(tr);
                }
                else if(inoutdata.getOut().isearlierthan(tr))
                {
                    inoutdata.setOut(tr);
                }
            }
        }
        compiledemployeedata=enforceTimeInCeiling(compiledemployeedata);
        return compiledemployeedata;
    }
    
    private CompiledEmployeeData enforceTimeInCeiling(CompiledEmployeeData compiledemployeedata)
    {
        Time time;
        for(TimeInOutData edata2:compiledemployeedata.values())
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
        }  
        return compiledemployeedata;
    }

    private void createNameListAndCalendar()
    {
            //create employee name list
            // and date list
            employeenamelist=new EmployeeNameList();
            calendar=new Calendar();
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
    }

    private void printSampleOutput()
    {
        //clear the textarea
        jTextArea.setText("");

            //sample output
            CompiledEmployeeData edatamap;
            TimeInOutData data;
            for(String name:employeenamelist)
            {
                for(String date:calendar)
                {
                    edatamap= weeklydata.get(name);
                    if(edatamap==null)continue;
                    data=edatamap.get(date);
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
    private void calculate()
    {
            //parse file
            File file = fc.getSelectedFile();

            //if file hasn't been selected, do nothing
            if(file==null)return;

            //parse file and create records list
            parseFile(file);
            
            //arrange time records into arraylists by employee
            groupTimeRecordsByEmployee();
            
            createNameListAndCalendar();
            
            //start to process data into array structure
            weeklydata=new WeeklyTimeData();
            for(ArrayList<TimeRecord> timerecordlist :timerecordsbyemployee.values())
            {
                CompiledEmployeeData edatamap=genEmployeeDataMap(timerecordlist);
                weeklydata.put(timerecordlist.get(0).getName(), edatamap);
            }
            printSampleOutput();
    }
}
