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

public class Setting {
    //------------FIELDS-----------
    public static final String tablename="setting";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            ,"value"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(50)"
            ,"varchar(255)"
            };
    //-----------------------

    public Integer id;
    public String name;
    public String value;

    public Setting() {
    }
    public Setting(ResultSet rs) {
        try {
            id=rs.getInt("id");
            name=rs.getString("name");
            value=rs.getString("value");
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getValue() {
            return value;
    }

    public void setValue(String value) {
            this.value = value;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(name);
            values.add(value);

            return values;
    }
    public void delete()
    {
            Settings.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Settings.insert(this);
            else
                    Settings.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
