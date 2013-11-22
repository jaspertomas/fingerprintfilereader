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
public class Adjustment {//implements Comparable<Adjustment> 
    String name="";
    Integer type=0;
    Date date;

    public Adjustment() {}
 
    public Adjustment(String name,Integer type, Date date) {
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

    
    public String toString()
    {
        return name;
    }

//    public String getFullName() {
//        return fname + " " +mname + " "+lname;
//    }

//@Override public int compareTo(Adjustment aThat) {
//    final int BEFORE = -1;
//    final int EQUAL = 0;
//    final int AFTER = 1;
//    
//    if (this.date.before(aThat.getDate())) return BEFORE;
//    else if (this.date.after(aThat.getDate())) return AFTER;
//    //if date is equal, compare by name alphabetically
//    else return this.name.compareTo(aThat.getName());
//  }
}
