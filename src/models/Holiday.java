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
public class Holiday implements Comparable<Holiday> {
    String name="",type="";
    Date date;

    public Holiday() {}
 
    public Holiday(String name,String type, Date date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    public String toString()
    {
        return name;
    }

//    public String getFullName() {
//        return fname + " " +mname + " "+lname;
//    }

@Override public int compareTo(Holiday aThat) {
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;
    
    if (this.date.before(aThat.getDate())) return BEFORE;
    else if (this.date.after(aThat.getDate())) return AFTER;
    else return EQUAL;

  }
}
