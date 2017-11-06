package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Week extends TreeMap<String, CompiledEmployeeData> {
    //------------FIELDS-----------
    public static final String tablename="week";
    //field names
    public static String[] fields={
            "id"
            ,"startdate"
            ,"enddate"
            ,"total"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"date"
            ,"date"
            ,"decimal(10,2)"
            };
    //-----------------------

    public Integer id;
    public Date startdate;
    public Date enddate;
    public Double total;

    public Week() {
    }
    public Week(ResultSet rs) {
        try {
            id=rs.getInt("id");
            startdate=rs.getDate("startdate");
            enddate=rs.getDate("enddate");
            total=rs.getDouble("total");
        } catch (SQLException ex) {
            Logger.getLogger(Week.class.getName()).log(Level.SEVERE, null, ex);
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

    public Date getStartdate() {
            return startdate;
    }

    public void setStartdate(Date startdate) {
            this.startdate = startdate;
    }

    public Date getEnddate() {
            return enddate;
    }

    public void setEnddate(Date enddate) {
            this.enddate = enddate;
    }

    public Double getTotal() {
            return total;
    }

    public void setTotal(Double total) {
            this.total = total;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(startdate!=null?startdate.toString():null);
            values.add(enddate!=null?enddate.toString():null);
            values.add(total!=null?total.toString():null);

            return values;
    }
    public void delete()
    {
            Weeks.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    id=Weeks.insert(this);
            else
                    Weeks.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
