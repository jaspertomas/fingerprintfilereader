/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;

/**
 *
 * @author jaspertomas
 */
public class TimeRecord {
    String line,no,dn,uid ,name ,status,action,apb,jobcode,datetime,date;
    Time time;
    public TimeRecord(String name, String datetime)
    {
        //create line from available data
        this.line="\t\t\t"+name+"\t\t\t\t\t"+datetime;
        this.name=name;
        this.datetime=datetime;
        String[] datesegments=datetime.split(" ");
        date=datesegments[0];
//                time=datesegments[1];

        String[] timesegments=datesegments[1].split(":");
//                time=new Time(Integer.valueOf(timesegments[0]),Integer.valueOf(timesegments[1]),Integer.valueOf(timesegments[2]));
            time=new Time(Integer.valueOf(timesegments[0]),Integer.valueOf(timesegments[1]),0);
    }
    public TimeRecord(String line)
    {
        System.out.println(line);
        this.line=line;
        String[] segments=line.split("\t");
        for(int i=0;i<segments.length;i++)
        {
            no=segments[0];
            dn=segments[1];
            uid=segments[2];
            name=segments[3];
            status=segments[4];
            action=segments[5];
            apb=segments[6];
            jobcode=segments[7];
            datetime=segments[8];

            String[] datesegments=datetime.split(" ");
            date=datesegments[0];
//                time=datesegments[1];

            String[] timesegments=datesegments[1].split(":");
//                time=new Time(Integer.valueOf(timesegments[0]),Integer.valueOf(timesegments[1]),Integer.valueOf(timesegments[2]));
                time=new Time(Integer.valueOf(timesegments[0]),Integer.valueOf(timesegments[1]),0);


        }
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApb() {
        return apb;
    }

    public void setApb(String apb) {
        this.apb = apb;
    }

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public boolean isearlierthan(TimeRecord tr)
    {
        if(time.compareTo(tr.getTime())<0)return true;
        else return false;
    }
    public boolean islaterthan(TimeRecord tr)
    {
        if(time.compareTo(tr.getTime())>0)return true;
        else return false;
    }
    public TimeRecord copy()
    {
        return new TimeRecord(line);
    }
    
}
