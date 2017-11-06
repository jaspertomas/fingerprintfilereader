package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adjustment {
    //------------FIELDS-----------
    public static final String tablename="adjustment";
    //field names
    public static String[] fields={
            "id"
            ,"employee_id"
            ,"employee_nickname"
            ,"type"
            ,"date"
            ,"time"
            ,"absent"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"varchar(30)"
            ,"int(11)"
            ,"date"
            ,"time"
            ,"tinyint(4)"
            };
    //-----------------------

    public Integer id;
    public Integer employee_id;
    public String employee_nickname;
    public Integer type;
    public Date date;
    public Time time;
    public Integer absent;

    public Adjustment() {
    }
    public Adjustment(ResultSet rs) {
        try {
            id=rs.getInt("id");
            employee_id=rs.getInt("employee_id");
            employee_nickname=rs.getString("employee_nickname");
            type=rs.getInt("type");
            date=Date.valueOf(rs.getString("date"));
            time=rs.getTime("time");
            absent=rs.getInt("absent");
        } catch (SQLException ex) {
            Logger.getLogger(Adjustment.class.getName()).log(Level.SEVERE, null, ex);
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

    public Integer getEmployeeId() {
            return employee_id;
    }

    public void setEmployeeId(Integer employee_id) {
            this.employee_id = employee_id;
    }

    public String getEmployeeNickname() {
            return employee_nickname;
    }

    public void setEmployeeNickname(String employee_nickname) {
            this.employee_nickname = employee_nickname;
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
            this.time = time;
    }

    public Integer getAbsent() {
            return absent;
    }

    public void setAbsent(Integer absent) {
            this.absent = absent;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(employee_id!=null?employee_id.toString():null);
            values.add(employee_nickname);
            values.add(type!=null?type.toString():null);
            values.add(date!=null?date.toString():null);
            values.add(time!=null?time.toString():null);
            values.add(absent!=null?absent.toString():null);

            return values;
    }
    public void delete()
    {
            Adjustments.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Adjustments.insert(this);
            else
                    Adjustments.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
    public String getPrettyTimeString() {
        return time.toLocalTime().format(Adjustments.prettyTimeFormat);
    }
}
