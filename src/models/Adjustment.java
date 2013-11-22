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
    public static final Integer IN=1;
    public static final Integer OUT=2;
    
    String employeeNickname;
    Integer type;
    Date date;
    String time;

    public Adjustment() {
    }

    public Adjustment(String employeeNickname, Date date, Integer type, String time) {
        this.employeeNickname = employeeNickname;
        this.type = type;
        this.date = date;
        this.time = time;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
//    public String toString()
//    {
//        return employeeNickname;
//    }

}
