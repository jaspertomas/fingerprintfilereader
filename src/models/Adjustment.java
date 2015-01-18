/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaspertomas
 */
public class Adjustment {//implements Comparable<Adjustment> 
    public static final Integer IN=1;
    public static final Integer OUT=2;
    
    String employeeNickname;
    Integer type;
    Date date;
    Time time;
    Boolean absent=false;

    public Adjustment() {
    }

//    public Adjustment(String employeeNickname, Date date, Integer type, String time) {
//        this.employeeNickname = employeeNickname;
//        this.type = type;
//        this.date = date;
//        this.time = time;
//    }

    public String getEmployeeNickname() {
        return employeeNickname;
    }

    public void setEmployeeNickname(String employeeNickname) {
        this.employeeNickname = employeeNickname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        if(time==null)setNullTime();
        else this.time = time;
    }
    private void setNullTime()
    {
        try {
            this.time=new Time(Adjustments.prettyTimeFormat.parse("12:00 am").getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    
    public String toString()
    {
         return "Nickname: "+employeeNickname+"; Date:"+date+"; Type:"+(type==1?"in":"out")+"; time"+time+"; absent"+absent;
    }

    public Boolean getAbsent() {
        return absent;
    }

    public void setAbsent(Boolean absent) {
        this.absent = absent;
    }

    public String getPrettyTimeString() {
        return Adjustments.prettyTimeFormat.format(time);
    }

}
