/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import models.Calendar;
import models.CompiledEmployeeData;
import models.DateUtil;
import models.Employee;
import models.EmployeeNameList;
import models.TimeInOutData;
import models.TimeRecord;
import models.WeeklyTimeData;
import utils.fileaccess.FileReader;

/**
 *
 * @author jaspertomas
 */
public class EmployeeDataManager {
    //---------------SINGLETON-------------------
    static EmployeeDataManager instance;
    private EmployeeDataManager(){};
    public static EmployeeDataManager getInstance(){
        return instance;
    }
    public static void initialize(JTextField txtStartDate,JTextField txtEndDate,JTextArea jTextArea)
    {
        if(instance==null)
        {
            instance= new EmployeeDataManager();
            instance.txtStartDate=txtStartDate;
            instance.txtEndDate=txtEndDate;
            instance.jTextArea=jTextArea;
        }
    }
    //---------------VARIABLES-------------------
    JTextField txtStartDate,txtEndDate;
    JTextArea jTextArea;
    
        ArrayList<TimeRecord> timerecords;
    
    //if a line contains this, it's the header - ignore it
       public final String headerfingerprint="APB\tJobCode\tDateTime";

       public final Time twelve=new Time(12,0,0);
       public final Time one=new Time(13,0,0);
       public final Time eightthirty=new Time(8,30,0);
       
       ArrayList<Employee> employees=new ArrayList<Employee>();

    //this is a string array of all employee names included in the parsed file
    EmployeeNameList employeenamelist;
    //this is a string array of all dates included in the parsed file
    Calendar calendar;

    //key = employee
    //value = all timerecords by a specific employee
    TreeMap<String,ArrayList<TimeRecord>> timerecordsbyemployee;
    WeeklyTimeData weeklydata;
    
    //----------------------------------

    public CompiledEmployeeData genEmployeeDataMap(ArrayList<TimeRecord> employeetimerecordlist)
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
    
    public CompiledEmployeeData enforceTimeInCeiling(CompiledEmployeeData compiledemployeedata)
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

    public void createNameListAndCalendar()
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

    public void printSampleOutput()
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
    public void calculate(File file)
    {

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
    public void recalculate(File file)
    {

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
    }

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
    

    public boolean validateDates()
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
    
}
