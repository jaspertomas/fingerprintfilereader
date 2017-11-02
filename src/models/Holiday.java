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

public class Holiday {
    //------------FIELDS-----------
    public static final String tablename="holiday";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            ,"type"
            ,"date"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(30)"
            ,"int(11)"
            ,"date"
            };
    //-----------------------

    public Integer id;
    public String name;
    public Integer type;
    public Date date;

    public Holiday() {
    }
    public Holiday(ResultSet rs) {
        try {
            id=rs.getInt("id");
            name=rs.getString("name");
            type=rs.getInt("type");
            date=rs.getDate("date");
        } catch (SQLException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
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


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(name);
            values.add(type!=null?type.toString():null);
            values.add(date!=null?date.toString():null);

            return values;
    }
    public void delete()
    {
            Holidays.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Holidays.insert(this);
            else
                    Holidays.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
