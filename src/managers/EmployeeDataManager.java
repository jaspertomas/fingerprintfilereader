/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import fingerprint.windows.MainFrame;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import models.Adjustment;
import models.Adjustments;
import models.Dates;
import models.CompiledEmployeeData;
import models.Constants;
import models.DateUtil;
import models.Deduction;
import models.Deductions;
import models.Employee;
import models.EmployeeNameList;
import models.EmployeeWeek;
import models.EmployeeWeeks;
import models.Employees;
import models.Entry;
import models.Holiday;
import models.Holidays;
import models.TimeInOutData;
import models.TimeRecord;
import models.Week;
import models.Weeks;
import utils.DateHelper;
import utils.fileaccess.FileReader;

/**
 *
 * @author jaspertomas
 */
public class EmployeeDataManager {
    //---------------SINGLETON-------------------

    static EmployeeDataManager instance;
    
    static final int hoursInADay=8;
    static final int minutesInADay=hoursInADay*60;

    private EmployeeDataManager() {
    }

    public static EmployeeDataManager getInstance() {
        return instance;
    }

    public static void initialize(JTextField txtStartDate, JTextField txtEndDate, JTextArea jTextArea) {
        if (instance == null) {
            instance = new EmployeeDataManager();
            instance.txtStartDate = txtStartDate;
            instance.txtEndDate = txtEndDate;
            instance.jTextArea = jTextArea;
        }
    }
    //---------------VARIABLES-------------------
    JTextField txtStartDate, txtEndDate;
    JTextArea jTextArea;
    ArrayList<TimeRecord> timerecords;
    //if a line contains this, it's the header - ignore it
    public static final String headerfingerprint = "APB\tJobCode\tDateTime";
    public static final Time twelve = new Time(12, 0, 0);
    public static final Time one = new Time(13, 0, 0);
    public static final Time eightthirty = new Time(9, 00, 0);
    
    //this is a string array of all employee names included in the parsed file
    EmployeeNameList employeenamelist;
    //this is a string array of all dates included in the parsed file
    //key = employee
    //value = all timerecords by a specific employee
    Week week=null;

    public Week getWeek() {
        return week;
    }
/*
    public void setWeek(Week week) {
        this.week = week;
    }
*/
    //----------------------------------
    public CompiledEmployeeData genEmployeeDataMap(ArrayList<TimeRecord> employeetimerecordlist) {
        //employeetimerecordlist contains many login time records per day, spanning many days

        CompiledEmployeeData compiledemployeedata = new CompiledEmployeeData();

        //process all records, sort by date
        //for each time record:
        TimeInOutData inoutdata;
        for (TimeRecord tr : employeetimerecordlist) {
            //if the daily record for the time record doesn't exist, create it, 
            //save the time record as a new daily record, using the recorded time as both in and out
            if (!compiledemployeedata.containsKey(tr.getDate())) {
                inoutdata = new TimeInOutData();
                inoutdata.setIn(tr);
                inoutdata.setOut(tr.copy());
                compiledemployeedata.put(tr.getDate(), inoutdata);
            } //else if the daily record exists
            //set the time as time in if it's earlier than the existing time out
            //or set the time as time out if it's later than the existing time in
            //* this works because time in is always earlier than time out
            else {
                inoutdata = compiledemployeedata.get(tr.getDate());
                if (inoutdata.getIn().islaterthan(tr)) {
                    inoutdata.setIn(tr);
                } else if (inoutdata.getOut().isearlierthan(tr)) {
                    inoutdata.setOut(tr);
                }
            }
        }
        compiledemployeedata = enforceTimeInCeiling(compiledemployeedata);
        return compiledemployeedata;
    }

    public CompiledEmployeeData enforceTimeInCeiling(CompiledEmployeeData compiledemployeedata) {
        Time time;
        for (TimeInOutData edata2 : compiledemployeedata.values()) {
            //adjust in time
            time = edata2.getIn().getTime();
            //adjust in time to 8:30 if earlier than 8:30
            if (time.before(eightthirty)) {
                edata2.getIn().setTime(eightthirty);
            } else //adjust in time to 1:00 if between 12 and 1
            if (time.after(twelve)
                    && time.before(one)) {
                edata2.getIn().setTime(one);
            }

            //if time out is before 8:30, adjust to 8:30 
            //if time out is before time in, adjust to time in
            //if time out is between 12 and 1, adjust to 12:00
            time = edata2.getOut().getTime();
            if (time.before(eightthirty)) {
                edata2.getOut().setTime(eightthirty);
            }else
            if (time.before(edata2.getIn().getTime())) {
                edata2.getOut().setTime(edata2.getIn().getTime());
            }else
            if (time.after(twelve)
                    && time.before(one)) {
                edata2.getOut().setTime(twelve);
            }
        }
        return compiledemployeedata;
    }

    public Date[] createNameListAndCalendar() {
        //create employee name list
        // and date list
        employeenamelist = new EmployeeNameList();
        ArrayList<String> dates=Dates.getInstance().getItems();
        dates.clear();
        
        for (TimeRecord timerecord : timerecords) {
            if (!employeenamelist.contains(timerecord.getName())) {
                employeenamelist.add(timerecord.getName());
            }
            if (!dates.contains(timerecord.getDate())) {
                dates.add(timerecord.getDate());
            }
        }
        Collections.sort(employeenamelist);
        
        //set date textboxes according to the date range in the calendar
        txtStartDate.setText(dates.get(0));
        txtEndDate.setText(dates.get(dates.size() - 1));
        
        //create return value of start and end dates
        Date[] startAndEndDates = new Date[2];
        startAndEndDates[0]=Date.valueOf(LocalDate.parse(dates.get(0).replace("/", "-")));
        startAndEndDates[1]=Date.valueOf(LocalDate.parse(dates.get(dates.size() - 1).replace("/", "-")));
        return startAndEndDates;
    }

    public void printPayrollOutput() {
        Double regularholidayratenowork=100d;//(percent)
        Double regularholidayratewithwork=200d-100;//(percent)
        Double specialholidayratenowork=0d;//(percent)
        Double specialholidayratewithwork=130d-100;//(percent)
        
        Boolean attendanceMode=MainFrame.getInstance().isAttendanceMode();
        
        DecimalFormat format = new DecimalFormat(",##0.00");
        
        //clear the textarea
        jTextArea.setText("");
        String tempstring="";//,prefix1="",prefix2="",problemdatestring="";
        ArrayList<String> problemdates=new ArrayList<String>();
        ArrayList<String> dates=Dates.getInstance().getItems();
//        Employee e;
        Dates.getInstance().span();
        //for(String d:dates)System.out.println(d);
        
        //sample output
        
        CompiledEmployeeData edatamap=null;
        TimeInOutData data;
        Double regularrate,overtimerate,cola,regularpay,overtimepay,grosspay,totalcola,vale,sss,ph,pi,loan,other,total_deductions,netpay;
        Integer days,regularminutes=0,overtimeminutes=0,totalregularminutes=0,totalovertimeminutes=0,diffminutes;
        Holiday holiday=null;
        Double holidaybonus=0d;

//        for (String name : employeenamelist) 
        for(Employee e:Employees.select())
        {
            totalcola=0d;
            Integer daysworked=0;
            String name=e.getNickname();
//            prefix1=prefix2=problemdatestring=
            problemdates.clear();

            totalregularminutes=0;
            totalovertimeminutes=0;

            holidaybonus=0d;
                        
//            e=EmployeeFileManager.getInstance().getEmployees().getByNickname(name);
            if(e==null)
            {
                tempstring="Employee with nickname \""+name+"\" removed from Employee Data"+"\r\n";
                tempstring+="Skipping..."+"\r\n";
                tempstring+="==============================\r\n";
                jTextArea.append(tempstring);
                continue;
            }
            
            String ename=e.getLname()+", "+e.getFname()+" "+e.getMname();
            if(ename.trim().contentEquals(","))
                tempstring="(Employee name not set)"+"\r\n";
            else
                tempstring=ename+"\r\n";
            tempstring+="- - - - - - - - - - - - - - - \r\n";
            
            //calculate total regular minutes and total overtime minutes
            edatamap = week.get(name);
            if (edatamap == null) {
                continue;
            }
            String temp2string,timeinstring="",timeoutstring="",lunchinstring="",lunchoutstring="";
            Integer holidaytypeid=-1,holidayid=0,isabsent=0,issunday=0;
            Double holidaybonusrate=0d;
            Double holidayregularrate=0d;
            Double holidayregularpay=0d;
            for (String date : dates) 
            {
                temp2string="";
                
                //fetch employee attendance data
                data = edatamap.get(date);

                //process holiday bonus first
                try {
                    holiday=Holidays.getByDateString(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error","Error processing holiday data",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                //if attendance mode, don't print holiday data
                if(!attendanceMode)
                //if holiday, 
                if(holiday!=null)
                {
//                    Double regularholidayratenowork=100d;//(percent)
//                    Double regularholidayratewithwork=200d;//(percent)
//                    Double specialholidayratenowork=0d;//(percent)
//                    Double specialholidayratewithwork=130d;//(percent)
                    
                    //add holiday bonus
                    if(holiday.getType()==Holidays.REGULAR)
                    {
                        temp2string+="Regular Holiday: "+holiday.getName()+"\n";
                        //if absent
                        if (data == null) {
//                            regularholidayratenowork
                            holidaybonusrate=regularholidayratenowork;
                            holidayregularrate=e.getMonthlySalary();//+e.getCola();
                            holidayregularpay=holidayregularrate*holidaybonusrate/100;
                            holidaybonus+=holidayregularpay;
                            temp2string+="Holiday Additional (Absent): P "+format.format(holidayregularpay)+"\r\n";
                            
                        }
                        else
                        {
//                              regularholidayratewithwork      
                            holidaybonusrate=regularholidayratewithwork;
                            holidayregularrate=e.getMonthlySalary();//+e.getCola();
                            holidayregularpay=holidayregularrate*holidaybonusrate/100;
                            holidaybonus+=holidayregularpay;
                            temp2string+="Holiday Additional: P "+format.format(holidayregularpay)+"\r\n";
                        }
                    }
                    else if(holiday.getType()==Holidays.SPECIAL)
                    {
                        temp2string+="Special Non-working Holiday: "+holiday.getName()+"\n";
                        //if absent
                        if (data == null) {
//                            specialholidayratenowork
                            holidaybonusrate=specialholidayratenowork;
                            holidayregularrate=e.getMonthlySalary();
                            holidayregularpay=holidayregularrate*holidaybonusrate/100;
                            holidaybonus+=holidayregularpay;
                            temp2string+="Holiday Additional (Absent): P "+format.format(holidayregularpay)+"\r\n";
                        }
                        else
                        {
//                            specialholidayratewithwork
                            holidaybonusrate=specialholidayratewithwork;
                            holidayregularrate=e.getMonthlySalary();
                            holidayregularpay=holidayregularrate*holidaybonusrate/100;
                            holidaybonus+=holidayregularpay;
                            temp2string+="Holiday Additional: P "+format.format(holidayregularpay)+"\r\n";

                            
                        }
                    }
                    
                    holidaytypeid=holiday.type;
                    holidayid=holiday.id;
                    
                }
                
                if(name.trim().isEmpty())
                    temp2string+="(nickname not set)" + "\t";
                else
                    temp2string+=name + "\t";
                temp2string+=date + "\t";
                
                //count number of days present
                if (data != null) {
                    daysworked++;
                }                
                
                //if absent, do nothing
                if (data == null) {
                    isabsent=1;
                    
                    //if sunday
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(DateHelper.toDate(date));
                    if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
                    {
                        issunday=1;
                        temp2string+=
                                "Sunday"
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
                                + "\r\n";
                    }
                    //else if absent
                    else
                        temp2string+=
                                "Absent"
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
                                + "\r\n";

                    saveEntry( e,  date,  data,  temp2string,  timeinstring,  timeoutstring,  holidaytypeid,  holidayid,  isabsent,  issunday,  holidayregularpay);
                    
                    tempstring+=temp2string;
                    
                    continue;
                }

                //if missing time in or missing time out
                else if (data.getIn().getTime().equals(data.getOut().getTime())) {
//                    prefix1="Error: Missing time in or time out: ";
//                    prefix2="\nPlease go to the Manage Employee Data page to make corrections\n";
                    problemdates.add(date);
                        
//                    if(data.getIn().getTime().after(one))
                        temp2string+=
                                "Missing time in or time out. Fingerprint login at "+data.getPrettyInTimeString()
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
                                + "\r\n";
//                        temp2string+=
//                                ""
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + data.getOutTimeString()
//                                + "\t"
//                                + data.getOutTimeString()
//                                + "\r\n";
//                    else
//                        temp2string+=
//                                data.getOutTimeString()
//                                + "\t"
//                                + data.getOutTimeString()
//                                + "\t"
//                                + ""
//                                + "\t"
//                                + ""
//                                + "\r\n";
                } else                       
                //came to work at lunch break or after
                if (data.getIn().getTime().equals(twelve) || data.getIn().getTime().after(twelve)) {
                    temp2string+=
                            ""
                            + "\t"
                            + ""
                            + "\t"
                            + data.getPrettyInTimeString()
                            + "\t"
                            + data.getPrettyOutTimeString()
                            + "\r\n";
                //left work at lunch break
                } else if (data.getOut().getTime().equals(one) || data.getOut().getTime().before(one)) {
                    temp2string+=
                            data.getPrettyInTimeString()
                            + "\t"
                            + data.getPrettyOutTimeString()
                            + ""
                            + "\t"
                            + ""
                            + "\t"
                            + "\r\n";
                } else {
                    temp2string+=
                            data.getPrettyInTimeString()
                            + "\t"
//                            + "1200"
                            + "12:00 PM"
                            + "\t"
//                            + "1300"
                            + "01:00 PM"
                            + "\t"
                            + data.getPrettyOutTimeString()
                            + "\r\n";
                }
                
                //calculate regular and overtime minutes
                diffminutes=data.getTimeDiffMinutes();
                if(diffminutes>minutesInADay)//8 hours*60 min
                {
                    regularminutes=minutesInADay;
                    overtimeminutes=diffminutes-minutesInADay;
                }
                else
                {
                    regularminutes=diffminutes;
                    overtimeminutes=0;
                }
                
                //if regular minutes less than half of whole day minutes, half day
                if(regularminutes<=minutesInADay/2)
                    //give only half cola for half day's work
                    totalcola+=e.getCola()/2;
                else
                    totalcola+=e.getCola();
                
                totalregularminutes+=regularminutes;
                totalovertimeminutes+=overtimeminutes;

                saveEntry( e,  date,  data,  temp2string,  timeinstring,  timeoutstring,  holidaytypeid,  holidayid,  isabsent,  issunday,  holidayregularpay);
                
                tempstring+=temp2string;
            }

            //if attendance mode, don't print calculations
            if(!attendanceMode)
            {
            //get regular rate and overtime rate
            regularrate=e.getMonthlySalary();
            overtimerate=e.getMonthlySalary()*Constants.overtimemultiplier;
            regularpay=regularrate*totalregularminutes/60/hoursInADay;
            overtimepay=overtimerate*totalovertimeminutes/60/hoursInADay;
            grosspay=regularpay+overtimepay;
            //cola now calculated on a per day basis (above)
//            cola=e.getCola();
//            totalcola=cola*daysworked;
            
            Deduction d=Deductions.getByEmployeeAndWeekIds(e.id, week.id);
            if(d==null)d=new Deduction();
            vale=d.getVale();
            sss=d.getSss();
            ph=d.getPh();
            pi=d.getPi();
            loan=d.getLoan();
            other=d.getOther();
            total_deductions=vale+sss+ph+pi+loan+other;
            netpay=grosspay+totalcola-total_deductions+holidaybonus;
            
            EmployeeWeek ew=new EmployeeWeek();
            ew.setEmployeeId(e.id);
            ew.setWeekId(week.id);
            ew.setRegMinutes(totalregularminutes);
            ew.setRegRate(regularrate);
            ew.setRegPay(regularpay);
            ew.setOtMinutes(totalovertimeminutes);
            ew.setOtRate(overtimerate);
            ew.setOtPay(overtimepay);
            ew.setGrossPay(grosspay);
            ew.setTotalCola(totalcola);
            ew.setTotalDeductions(total_deductions);
            ew.setTotalHoliday(holidaybonus);
            ew.setNetPay(netpay);
            ew.save();
            
            tempstring+="- - - - - - - - - - - - - - - \r\n";
            tempstring+="Regular Minutes: "+totalregularminutes+" minutes ("+totalregularminutes/60+" hours "+totalregularminutes%60+" minutes)\r\n";
            tempstring+="Regular Rate: P "+format.format(regularrate)+"/day\n";
            tempstring+="Regular Pay: P "+format.format(regularpay)+"\r\n";
            tempstring+="Overtime Minutes: "+totalovertimeminutes+" minutes ("+totalovertimeminutes/60+" hours "+totalovertimeminutes%60+" minutes)\r\n";
            tempstring+="Overtime Rate: P "+format.format(overtimerate)+"/day\n";
            tempstring+="Overtime Pay: P "+format.format(overtimepay)+"\r\n";
            tempstring+="Gross Salary: P "+format.format(grosspay)+"\r\n";
            tempstring+="Total COLA: P "+format.format(totalcola)+"\r\n";
            if(holidaybonus!=0)
                tempstring+="Holiday Additional: P "+format.format(holidaybonus)+"\r\n";
            if(vale>0 || loan>0 || other>0)
                tempstring+="Vale: P "+format.format(vale)+"\t"+"Loan: P "+format.format(loan)+"\t"+"Other: P "+format.format(other)+"\r\n";
            if(sss>0 || ph>0 || pi>0)
                tempstring+="SSS: P "+format.format(sss)+"\t"+"PH: P "+format.format(ph)+"\t"+"PI: P "+format.format(pi)+"\r\n";
            tempstring+="Total Deductions: P "+format.format(total_deductions)+"\r\n";
            tempstring+="- - - - - - - - - - - - - - - \r\n";
            tempstring+="NET SALARY: PHP "+format.format(netpay) +"\r\n";
            }
            tempstring+="==============================\n\n";

//            for(int i=0;i<problemdates.size();i++)
//            {
//                if(i!=0)problemdatestring+=", ";
//                problemdatestring+=problemdates.get(i);
//            }
//            problemdatestring=prefix1+problemdatestring+prefix2;
//            jTextArea.append(problemdatestring+tempstring);
            jTextArea.append(tempstring);
            jTextArea.moveCaretPosition(0);
        }
        //totals
        tempstring="Summary\n";
        Double total=0d;
        for(Employee e:Employees.select())
        {
            EmployeeWeek ew=EmployeeWeeks.getByEmployeeAndWeekIds(e.id, week.id);
            tempstring+=e.getNickname()+"\t"+format.format(ew.getNetPay())+"\n";
            total+=ew.getNetPay();
        }
        tempstring+="Total:\t"+format.format(total)+"\n";
        jTextArea.append(tempstring);
        jTextArea.moveCaretPosition(0);
    }
    private void saveEntry(Employee e, String date, TimeInOutData data, String temp2string, String timeinstring, String timeoutstring, Integer holidaytypeid, Integer holidayid, Integer isabsent, Integer issunday, Double holidayregularpay)
    {
        Entry entry=new Entry();
        entry.setEmployeeId(e.id);
        entry.setWeekId(week.id);
        entry.setDate(Date.valueOf(date.replace("/", "-")));
        if(data!=null)
        {
            Time inTime=Time.valueOf(LocalTime.parse(data.getPrettyInTimeString(), Adjustments.prettyTimeFormat));
            Time outTime=Time.valueOf(LocalTime.parse(data.getPrettyOutTimeString(), Adjustments.prettyTimeFormat));
            entry.setTimeIn(inTime);
            entry.setTimeOut(outTime);
//            entry.setLunchIn(Time.valueOf(lunchinstring));
//            entry.setLunchOut(Time.valueOf(lunchoutstring));
        }
        entry.setHolidayId(holidayid);
        entry.setHolidayType(holidaytypeid);
        entry.setHolidayPay(holidayregularpay);
        entry.setIsAbsent(isabsent);
        entry.setIsSunday(issunday);
        entry.setDescription(temp2string.replace("'", "''").replace("\n", "<br>"));
        entry.save();
    }
    public void calculate(File file) {
        calculate(file,false);
    }
    public void calculate(File file,Boolean adjustDates) {

        //parse file and create records list
        parseFile(file);
        
        if(adjustDates)
        {
            //remove calendar entries that do not fit between startdate and enddate
            ArrayList<TimeRecord> temp = new ArrayList<TimeRecord>();
            for (TimeRecord timerecord : timerecords) {
                if (DateUtil.isEarlierThan(timerecord.getDate(), txtStartDate.getText())
                        || DateUtil.isLaterThan(timerecord.getDate(), txtEndDate.getText())) {
                    temp.add(timerecord);
                }
            }
            for (TimeRecord timerecord : temp) {
                timerecords.remove(timerecord);
            }
        }

        //arrange time records into arraylists by employee
        TreeMap<String, ArrayList<TimeRecord>> timerecordsbyemployee=groupTimeRecordsByEmployee();

        Date[] dates;
        if(adjustDates)
        {
            dates=new Date[2];
            dates[0]=Date.valueOf(txtStartDate.getText().replace("/", "-"));
            dates[1]=Date.valueOf(txtEndDate.getText().replace("/", "-"));
        }
        else
            dates=createNameListAndCalendar();
        
        //EmployeeFileManager.getInstance().generateFromStringArray(employeenamelist);
        Employees.generateFromStringArray(employeenamelist);

        //delete previous data 
        Week oldWeek=Weeks.getByStartAndEndDates(dates[0], dates[1]);
        if(oldWeek!=null)oldWeek.delete();
        
        //start to process data into array structure
        week = new Week();
        week.startdate=dates[0];
        week.enddate=dates[1];
        week.save();
        
        for (ArrayList<TimeRecord> timerecordlist : timerecordsbyemployee.values()) {
            CompiledEmployeeData edatamap = genEmployeeDataMap(timerecordlist);
            //if not empty, 
            //take employee name as key, edatamap as value,
            //put into week
            if(timerecordlist.size()>0)week.put(timerecordlist.get(0).getName(), edatamap);
        }
        
        //create data for absent people 
        ArrayList<String> namestoinsert=new ArrayList<String>();
        for (Employee e : Employees.select()) {
            namestoinsert.add(e.getNickname());
        }
        for(String nametoinsert:namestoinsert){
            if (!week.containsKey(nametoinsert)) {
                week.put(nametoinsert, new CompiledEmployeeData());
            }
        }
        
        applyAdjustments();

        printPayrollOutput();
    }
/*
    //recreatenamelistandcalendar is true by default
    //false only if Adjust Date Range button is clicked
    public void recalculate(File file) {
        recalculate(file,true);
    }
    public void recalculate(File file, Boolean recreatenamelistandcalendar) {

        //parse file and create records list
        parseFile(file);
        
        //remove calendar entries that do not fit between startdate and enddate
        ArrayList<TimeRecord> temp = new ArrayList<TimeRecord>();
        for (TimeRecord timerecord : timerecords) {
            if (DateUtil.isEarlierThan(timerecord.getDate(), txtStartDate.getText())
                    || DateUtil.isLaterThan(timerecord.getDate(), txtEndDate.getText())) {
                temp.add(timerecord);
            }
        }
        for (TimeRecord timerecord : temp) {
            timerecords.remove(timerecord);
        }

        //arrange time records into arraylists by employee
        TreeMap<String, ArrayList<TimeRecord>> timerecordsbyemployee=groupTimeRecordsByEmployee();

        if(recreatenamelistandcalendar)
            createNameListAndCalendar();


        //start to process data into array structure
        week = new Week();
        for (ArrayList<TimeRecord> timerecordlist : timerecordsbyemployee.values()) 
        {
            CompiledEmployeeData edatamap = genEmployeeDataMap(timerecordlist);
            if(edatamap!=null)
                week.put(timerecordlist.get(0).getName(), edatamap);
        }

        //create data for absent people 
        ArrayList<String> namestoinsert=new ArrayList<String>();
        for (Employee e : Employees.select()) {
            namestoinsert.add(e.getNickname());
        }
        for(String nametoinsert:namestoinsert){
            if (!week.containsKey(nametoinsert)) {
                week.put(nametoinsert, new CompiledEmployeeData());
            }
        }
        
        
        applyAdjustments();
        
        printPayrollOutput();
    }
    */
    private void applyAdjustments()
    {
        for(Adjustment a:Adjustments.select(""))
        {
            CompiledEmployeeData edatamap = week.get(a.getEmployeeNickname());
            if(edatamap==null)continue;
            TimeInOutData inoutdata = edatamap.get(a.getDate().toLocalDate().format(Holidays.dateFormat));
            if(inoutdata==null)
            {
                //employee has no timerecord for this day (absent)
                //create timerecord to adjust absent to present
                TimeRecord tr=new TimeRecord(a.getEmployeeNickname(),Holidays.dateFormat.format(a.getDate().toLocalDate())+" "+a.getTime().toString());                
                
                inoutdata=new TimeInOutData();
                inoutdata.setIn(tr);
                inoutdata.setOut(tr.copy());
                edatamap.put(tr.getDate(), inoutdata);
            }
            
            //if adjustment is absent=true
            if(a.getAbsent()==1)
            {
                //remove date data
                edatamap.remove(a.getDate().toLocalDate().format(Holidays.dateFormat));
            }
            //apply adjustment time
            else
            {
                if(a.getType()==Adjustments.IN)
                    inoutdata.getIn().setTime(a.getTime());
                else if(a.getType()==Adjustments.OUT)
                    inoutdata.getOut().setTime(a.getTime());
            }
        }    
    }

    private void parseFile(File file) {
        //extract file contents
        String filecontents = FileReader.read(file.getPath());

        //remove double spaces and null characters
        filecontents = filecontents.replace("\0", "");
        filecontents = filecontents.replace("\n\n", "\r\n");

        //split into lines
        String[] lines = filecontents.split("\r\n");

        //convert lines into objects
        timerecords = new ArrayList<TimeRecord>();
        for (String line : lines) {
            String[] segments=line.split("\t");
            
            //ignore the header
            if (line.contains(headerfingerprint)) {
                continue;
            } //ignore empty lines
            else if (line.trim().length() == 0) {
                continue;
            } //ignore lines with no employee name
            else if (segments[3].trim().isEmpty()) {
                continue;
            } else {
                timerecords.add(new TimeRecord(line));
            }
        }
    }

    private TreeMap<String, ArrayList<TimeRecord>> groupTimeRecordsByEmployee() {
        //output
        TreeMap<String, ArrayList<TimeRecord>> timerecordsbyemployee;

        //group by date
        ArrayList<TimeRecord> dailyrecordlist;
        timerecordsbyemployee = new TreeMap<String, ArrayList<TimeRecord>>();
        for (TimeRecord record : timerecords) {
            if (!timerecordsbyemployee.containsKey(record.getName())) {
                dailyrecordlist = new ArrayList<TimeRecord>();
                dailyrecordlist.add(record);
                timerecordsbyemployee.put(record.getName(), dailyrecordlist);
            } else {
                dailyrecordlist = timerecordsbyemployee.get(record.getName());
                dailyrecordlist.add(record);
            }

        }
        return timerecordsbyemployee;
    }


    public boolean validateDates() {
        if (txtStartDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Start Date cannot be empty", "Error", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        if (txtEndDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "End Date cannot be empty", "Error", JOptionPane.PLAIN_MESSAGE);
            return false;
        }

        String[] segments = txtStartDate.getText().trim().replace("-", "/").split("/");
        if (segments.length != 3) {
            JOptionPane.showMessageDialog(null, "Start Date is invalid", "Error", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        for (int i = 0; i < 3; i++) {
            segments[i] = String.format("%02d", Integer.valueOf(segments[i]));
        }
        txtStartDate.setText(segments[0] + "/" + segments[1] + "/" + segments[2]);

        segments = txtEndDate.getText().trim().replace("-", "/").split("/");
        if (segments.length != 3) {
            JOptionPane.showMessageDialog(null, "End Date is invalid", "Error", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        for (int i = 0; i < 3; i++) {
            segments[i] = String.format("%02d", Integer.valueOf(segments[i]));
        }
        txtEndDate.setText(segments[0] + "/" + segments[1] + "/" + segments[2]);

        if (DateUtil.isEarlierThan(txtEndDate.getText(), txtStartDate.getText())) {
            String temp = txtStartDate.getText();
            txtStartDate.setText(txtEndDate.getText());
            txtEndDate.setText(temp);
        }
        return true;
    }
    public String getPayrollText() 
    {
        String string="";
        for(Employee e:Employees.select())
        {
            string+=e.getFullName()+"\r\n";
//            string+="reg hours rh"++"\r\n";
//            string+="reg rate"++"\r\n";
//            string+="reg pay "++"\r\n";
//            string+="overtime hours oh"++"\r\n";
//            string+="overtime rate"++"\r\n";
//            string+="overtime pay"++"\r\n";
//            string+="holiday pay"++"\r\n";
//            string+="gross pay"++"\r\n";
//            string+="cola"++"\r\n";
//            string+="total_deductions"++"\r\n";
//            string+="net salary"++"\r\n";
            /*
reg hours rh
reg rate
reg pay 
overtime hours oh
overtime rate
overtime pay
holiday pay
gross pay
cola
total_deductions
net salary             
             */
            
            
            string+="\n\n----------------------------------";
        }
        return string;
    }

    public Week getWeeklydata() {
        return week;
    }

    

}
