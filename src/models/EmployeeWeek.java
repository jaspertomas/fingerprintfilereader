package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqliteDbHelper;
import utils.JsonHelper;

public class EmployeeWeek {
    //------------FIELDS-----------
    public static final String tablename="employee_week";
    //field names
    public static String[] fields={
            "id"
            ,"week_id"
            ,"employee_id"
            ,"reg_minutes"
            ,"reg_rate"
            ,"reg_pay"
            ,"ot_minutes"
            ,"ot_rate"
            ,"ot_pay"
            ,"total_cola"
            ,"total_deductions"
            ,"total_holiday"
            ,"gross_pay"
            ,"net_pay"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"int(11)"
            };
    //-----------------------

    public Integer id;
    public Integer week_id;
    public Integer employee_id;
    public Integer reg_minutes;
    public Double reg_rate;
    public Double reg_pay;
    public Integer ot_minutes;
    public Double ot_rate;
    public Double ot_pay;
    public Integer total_cola;
    public Integer total_deductions;
    public Integer total_holiday;
    public Integer gross_pay;
    public Integer net_pay;

    public EmployeeWeek() {
    }
    public EmployeeWeek(ResultSet rs) {
        try {
            id=rs.getInt("id");
            week_id=rs.getInt("week_id");
            employee_id=rs.getInt("employee_id");
            reg_minutes=rs.getInt("reg_minutes");
            reg_rate=rs.getDouble("reg_rate");
            reg_pay=rs.getDouble("reg_pay");
            ot_minutes=rs.getInt("ot_minutes");
            ot_rate=rs.getDouble("ot_rate");
            ot_pay=rs.getDouble("ot_pay");
            total_cola=rs.getInt("total_cola");
            total_deductions=rs.getInt("total_deductions");
            total_holiday=rs.getInt("total_holiday");
            gross_pay=rs.getInt("gross_pay");
            net_pay=rs.getInt("net_pay");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWeek.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getWeekId() {
            return week_id;
    }

    public void setWeekId(Integer week_id) {
            this.week_id = week_id;
    }

    public Integer getEmployeeId() {
            return employee_id;
    }

    public void setEmployeeId(Integer employee_id) {
            this.employee_id = employee_id;
    }

    public Integer getRegMinutes() {
            return reg_minutes;
    }

    public void setRegMinutes(Integer reg_minutes) {
            this.reg_minutes = reg_minutes;
    }

    public Double getRegRate() {
            return reg_rate;
    }

    public void setRegRate(Double reg_rate) {
            this.reg_rate = reg_rate;
    }

    public Double getRegPay() {
            return reg_pay;
    }

    public void setRegPay(Double reg_pay) {
            this.reg_pay = reg_pay;
    }

    public Integer getOtMinutes() {
            return ot_minutes;
    }

    public void setOtMinutes(Integer ot_minutes) {
            this.ot_minutes = ot_minutes;
    }

    public Double getOtRate() {
            return ot_rate;
    }

    public void setOtRate(Double ot_rate) {
            this.ot_rate = ot_rate;
    }

    public Double getOtPay() {
            return ot_pay;
    }

    public void setOtPay(Double ot_pay) {
            this.ot_pay = ot_pay;
    }

    public Integer getTotalCola() {
            return total_cola;
    }

    public void setTotalCola(Integer total_cola) {
            this.total_cola = total_cola;
    }

    public Integer getTotalDeductions() {
            return total_deductions;
    }

    public void setTotalDeductions(Integer total_deductions) {
            this.total_deductions = total_deductions;
    }

    public Integer getTotalHoliday() {
            return total_holiday;
    }

    public void setTotalHoliday(Integer total_holiday) {
            this.total_holiday = total_holiday;
    }

    public Integer getGrossPay() {
            return gross_pay;
    }

    public void setGrossPay(Integer gross_pay) {
            this.gross_pay = gross_pay;
    }

    public Integer getNetPay() {
            return net_pay;
    }

    public void setNetPay(Integer net_pay) {
            this.net_pay = net_pay;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(week_id!=null?week_id.toString():null);
            values.add(employee_id!=null?employee_id.toString():null);
            values.add(reg_minutes!=null?reg_minutes.toString():null);
            values.add(reg_rate!=null?reg_rate.toString():null);
            values.add(reg_pay!=null?reg_pay.toString():null);
            values.add(ot_minutes!=null?ot_minutes.toString():null);
            values.add(ot_rate!=null?ot_rate.toString():null);
            values.add(ot_pay!=null?ot_pay.toString():null);
            values.add(total_cola!=null?total_cola.toString():null);
            values.add(total_deductions!=null?total_deductions.toString():null);
            values.add(total_holiday!=null?total_holiday.toString():null);
            values.add(gross_pay!=null?gross_pay.toString():null);
            values.add(net_pay!=null?net_pay.toString():null);

            return values;
    }
    public void delete()
    {
            EmployeeWeeks.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    id=EmployeeWeeks.insert(this);
            else
                    EmployeeWeeks.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
