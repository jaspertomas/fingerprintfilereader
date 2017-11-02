package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqliteDbHelper;
import utils.JsonHelper;

public class Employee {
    //------------FIELDS-----------
    public static final String tablename="employee";
    //field names
    public static String[] fields={
            "id"
            ,"nickname"
            ,"fname"
            ,"mname"
            ,"lname"
            ,"cola"
            ,"monthly_salary"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"varchar(30)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            };
    //-----------------------

    public Integer id;
    public String nickname;
    public String fname;
    public String mname;
    public String lname;
    public Double cola;
    public Double monthly_salary;

    public Employee() {
    }
    public Employee(ResultSet rs) {
        try {
            id=rs.getInt("id");
            nickname=rs.getString("nickname");
            fname=rs.getString("fname");
            mname=rs.getString("mname");
            lname=rs.getString("lname");
            cola=rs.getDouble("cola");
            monthly_salary=rs.getDouble("monthly_salary");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//	public String getUuid()
//	{
//		return id.toString()+"-";
//	}

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public String getNickname() {
            return nickname;
    }

    public void setNickname(String nickname) {
            this.nickname = nickname;
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
            return monthly_salary;
    }

    public void setMonthlySalary(Double monthly_salary) {
            this.monthly_salary = monthly_salary;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(nickname);
            values.add(fname);
            values.add(mname);
            values.add(lname);
            values.add(cola!=null?cola.toString():null);
            values.add(monthly_salary!=null?monthly_salary.toString():null);

            return values;
    }
    public void delete()
    {
            Employees.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Employees.insert(this);
            else
                    Employees.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
