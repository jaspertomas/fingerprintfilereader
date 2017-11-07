package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Entry {
    //------------FIELDS-----------
    public static final String tablename="entry";
    //field names
    public static String[] fields={
            "id"
            ,"week_id"
            ,"employee_id"
            ,"date"
            ,"time_in"
            ,"lunch_out"
            ,"lunch_in"
            ,"time_out"
            ,"holiday_type"
            ,"holiday_id"
            ,"holiday_pay"
            ,"is_absent"
            ,"is_sunday"
            ,"description"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"date"
            ,"time"
            ,"time"
            ,"time"
            ,"time"
            ,"int(11)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"tinyint(4)"
            ,"tinyint(4)"
            ,"varchar(30)"
            };
    //-----------------------

    public Integer id;
    public Integer week_id;
    public Integer employee_id;
    public Date date;
    public Time time_in;
    public Time lunch_out;
    public Time lunch_in;
    public Time time_out;
    public Integer holiday_type;
    public Integer holiday_id;
    public Double holiday_pay;
    public Integer is_absent;
    public Integer is_sunday;
    public String description;

    public Entry() {
    }
    public Entry(ResultSet rs) {
        try {
            id=rs.getInt("id");
            week_id=rs.getInt("week_id");
            employee_id=rs.getInt("employee_id");
            date=Date.valueOf(rs.getString("date"));
            time_in=Time.valueOf(rs.getString("time_in"));
            lunch_out=Time.valueOf(rs.getString("lunch_out"));
            lunch_in=Time.valueOf(rs.getString("lunch_in"));
            time_out=Time.valueOf(rs.getString("time_out"));
            holiday_type=rs.getInt("holiday_type");
            holiday_id=rs.getInt("holiday_id");
            holiday_pay=rs.getDouble("holiday_pay");
            is_absent=rs.getInt("is_absent");
            is_sunday=rs.getInt("is_sunday");
            description=rs.getString("description");
        } catch (SQLException ex) {
            Logger.getLogger(Entry.class.getName()).log(Level.SEVERE, null, ex);
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

    public Date getDate() {
            return date;
    }

    public void setDate(Date date) {
            this.date = date;
    }

    public Time getTimeIn() {
            return time_in;
    }

    public void setTimeIn(Time time_in) {
            this.time_in = time_in;
    }

    public Time getLunchOut() {
            return lunch_out;
    }

    public void setLunchOut(Time lunch_out) {
            this.lunch_out = lunch_out;
    }

    public Time getLunchIn() {
            return lunch_in;
    }

    public void setLunchIn(Time lunch_in) {
            this.lunch_in = lunch_in;
    }

    public Time getTimeOut() {
            return time_out;
    }

    public void setTimeOut(Time time_out) {
            this.time_out = time_out;
    }

    public Integer getHolidayType() {
            return holiday_type;
    }

    public void setHolidayType(Integer holiday_type) {
            this.holiday_type = holiday_type;
    }

    public Integer getHolidayId() {
            return holiday_id;
    }

    public void setHolidayId(Integer holiday_id) {
            this.holiday_id = holiday_id;
    }

    public Double getHolidayPay() {
            return holiday_pay;
    }

    public void setHolidayPay(Double holiday_pay) {
            this.holiday_pay = holiday_pay;
    }

    public Integer getIsAbsent() {
            return is_absent;
    }

    public void setIsAbsent(Integer is_absent) {
            this.is_absent = is_absent;
    }

    public Integer getIsSunday() {
            return is_sunday;
    }

    public void setIsSunday(Integer is_sunday) {
            this.is_sunday = is_sunday;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(week_id!=null?week_id.toString():null);
            values.add(employee_id!=null?employee_id.toString():null);
            values.add(date!=null?date.toString():null);
            values.add(time_in!=null?time_in.toString():null);
            values.add(lunch_out!=null?lunch_out.toString():null);
            values.add(lunch_in!=null?lunch_in.toString():null);
            values.add(time_out!=null?time_out.toString():null);
            values.add(holiday_type!=null?holiday_type.toString():null);
            values.add(holiday_id!=null?holiday_id.toString():null);
            values.add(holiday_pay!=null?holiday_pay.toString():null);
            values.add(is_absent!=null?is_absent.toString():null);
            values.add(is_sunday!=null?is_sunday.toString():null);
            values.add(description);

            return values;
    }
    public void delete()
    {
            Entrys.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    id=Entrys.insert(this);
            else
                    Entrys.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
