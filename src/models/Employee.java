/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jaspertomas
 */
public class Employee {
    String fname="",mname="",lname="";
    Double cola=0d;
    Double monthlySalary=0d;

    public Employee() {}
 
    public Employee(String fname, String mname, String lname, Double cola, Double monthlySalary) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.cola = cola;
        this.monthlySalary = monthlySalary;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Double getCola() {
        return cola;
    }

    public void setCola(Double cola) {
        this.cola = cola;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    
}
