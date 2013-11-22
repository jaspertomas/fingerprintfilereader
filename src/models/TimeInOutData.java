/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import java.util.Date;
import java.util.Date;
import managers.EmployeeDataManager;

/**
 *
 * @author jaspertomas
 */
public class TimeInOutData {
    String name,date;
    TimeRecord in,out;

    public TimeRecord getIn() {
        return in;
    }

    public void setIn(TimeRecord in) {
        this.in = in;
    }

    public TimeRecord getOut() {
        return out;
    }

    public void setOut(TimeRecord out) {
        this.out = out;
    }
    
    public String getInTimeString()
    {
        Time time=in.getTime();
        return 
                String.format("%02d", time.getHours())
//                +"\t"
                +String.format("%02d", time.getMinutes());
    }
    public String getOutTimeString()
    {
        Time time=out.getTime();
        return 
                String.format("%02d", time.getHours())
//                +"\t"
                +String.format("%02d", time.getMinutes());
    }

    public String getPrettyInTimeString()
    {
        Time time=in.getTime();
//        return 
//                String.format("%02d", time.getHours())
//                +":"
//                +String.format("%02d", time.getMinutes());
        return Adjustments.prettyTimeFormat.format(time);
    }
    public String getPrettyOutTimeString()
    {
        Time time=out.getTime();
//        return 
//                String.format("%02d", time.getHours())
//                +":"
//                +String.format("%02d", time.getMinutes());
        return Adjustments.prettyTimeFormat.format(time);
    }    
    public Date getInTime()
    {
        return in.getTime();
    }
    public Date getOutTime()
    {
        return out.getTime();
    }        
    public Integer getTimeDiffMinutes()
    {
        long difference;

        int minutesbeforelunch=0;
        int minutesafterlunch=0;
        
        //calculate minutesbeforelunch
        //came in after lunch
        if(
            in.getTime().after(EmployeeDataManager.twelve)
            )
            minutesbeforelunch=0;
        else
        {
            //early dismissal
            if(out.getTime().before(EmployeeDataManager.twelve))
            {
                difference = out.getTime().getTime()-in.getTime().getTime();
                minutesbeforelunch=Double.valueOf(difference/1000/60).intValue();
            }
            //stayed until lunch or later
            else
            {
                difference = EmployeeDataManager.twelve.getTime()-in.getTime().getTime();
                minutesbeforelunch=Double.valueOf(difference/1000/60).intValue();
            }
        }
        
        //calculate minutesafterlunch
        //left before lunch
        if(
            out.getTime().before(EmployeeDataManager.one)
            )
            minutesafterlunch=0;
        else
        {
            //came in after lunch
            if(in.getTime().after(EmployeeDataManager.one))
            {
                difference = out.getTime().getTime()-in.getTime().getTime();
                minutesafterlunch=Double.valueOf(difference/1000/60).intValue();
            }
            //came in at lunch or before
            else
            {
                difference = out.getTime().getTime()-EmployeeDataManager.one.getTime();
                minutesafterlunch=Double.valueOf(difference/1000/60).intValue();
            }
        }
                
        
        return minutesbeforelunch+minutesafterlunch;
    }
    
    
}
