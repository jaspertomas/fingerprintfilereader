/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author jaspertomas
 */
public class TimeRecord {
    String no,dn,uid ,name ,status,action,apb,jobcode,datetime,date,time;
    public TimeRecord(String line)
    {
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
                
                String[] timesegments=datetime.split(" ");
                date=timesegments[0];
                time=timesegments[1];
                
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
