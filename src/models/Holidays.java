package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqliteDbHelper;
import utils.JsonHelper;

public class Holidays {
    //---------------CONSTANTS---------------------  
    public static final Integer REGULAR=0;
    public static final Integer SPECIAL=1;
    public static final Integer OTHER=2;

    //---------------FORMATTERS---------------------  
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");

    //------------FIELDS-----------
    public static final String tablename=Holiday.tablename;
    public static String[] fields=Holiday.fields;
    public static String[] fieldtypes=Holiday.fieldtypes;
    //-----------------------
    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    public static Holiday getByName(String name)
    {
            RecordList map=select(" name = '"+name+"'");
            for(Holiday item:map)return item;
            return null;
    }	
    public static Holiday getById(Integer id) {
            RecordList map=select(" id = '"+id.toString()+"'");
            for(Holiday item:map)return item;
            return null;
    }
    public static Holiday getByDate(LocalDate date) {
            RecordList map=select(" date = '"+date.format(dateFormat)+"'");
            for(Holiday item:map)return item;
            return null;
    }
    //-----------database functions--------------

    public static void delete(Integer id)
    {
        Connection conn=SqliteDbHelper.getInstance().getConnection();            
        Statement st = null;
        try { 
            st = conn.createStatement();
            st.executeUpdate("delete from "+tablename+" where id = '"+id.toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void delete(Holiday item)
    {
        delete(item.getId());
    }
    public static void insert(Holiday item)
    {
        Connection conn=SqliteDbHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            //for tables with integer primary key
            if(fieldtypes[0].contentEquals("integer"))withid=false;                
            //for tables with varchar primary key
            else if(fieldtypes[0].contains("varchar"))withid=true;     
            
            String query="INSERT INTO "+tablename+" ("+implodeFields(withid)+")VALUES ("+implodeValues(item, withid)+");";
            //System.out.println(query);
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void update(Holiday item)
    {
        Connection conn=SqliteDbHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static Integer count(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";

        //if conditions contains a limit clause, remove it. 
        //It is not applicable to a count query
        else if(conditions.contains("limit"))
        {
            String[] segments=conditions.split("limit");
            conditions=segments[0];
        }

        Connection conn=SqliteDbHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
        st = conn.createStatement();
        rs = st.executeQuery("SELECT count(*) from "+tablename+" where "+conditions);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }
    public static RecordList selectForCurrentYear()
    {
        String year=Settings.getCurrentYear();
        return select(" date like \""+year+"-%\" order by date");
    }
    public static RecordList select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
        Connection conn=SqliteDbHelper.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        try { 
            st = conn.createStatement();
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

            RecordList items=new RecordList();
            while (rs.next()) {
                items.add(new Holiday(rs));
                    //items.put(rs.getInt("id"), new Holidays(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(Holiday item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(withId);
            String output="";
            for(String value:values)
            {
                    if(!output.isEmpty())
                            output+=",";
                    output+=(value!=null?"'"+value+"'":"null");
            }
            return output;
    }
    public static String implodeFields(boolean withId)
    {
            String output="";
            for(String field:fields)
            {
                    if(!withId && field.contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=field;
            }
            return output;
    }
    public static String implodeFieldsWithValues(Holiday item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Holidays:implodeFieldsWithValues(): ERROR: values length does not match fields length");
            }

            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(!withId && fields[i].contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=fields[i]+"="+(values.get(i)!=null?"'"+values.get(i)+"'":"null");
            }
            return output;
    }	
    public static String implodeFieldsWithTypes()
    {
            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(fields[i].contentEquals(fields[0]))//fields[0] being the primary key
                    {
                        if(fieldtypes[i].toLowerCase().contains("int"))
                            output+=fields[i]+" INTEGER PRIMARY KEY";
                        else
                            output+=fields[i]+" "+fieldtypes[i]+" PRIMARY KEY";
                    }
                    else
                            output+=","+fields[i]+" "+fieldtypes[i];
            }
            return output;
    }	
    public static void createTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS "+tablename+" ("+implodeFieldsWithTypes()+" );";
        try { 
            SqliteDbHelper.getInstance().getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void deleteTable()
    {
        String query = "DROP TABLE IF EXISTS "+tablename;
        try { 
            SqliteDbHelper.getInstance().getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static class RecordList extends ArrayList<Holiday>{
        public static RecordList fromJsonString(String resultstring) throws IOException
        {
            return JsonHelper.mapper.readValue(resultstring, RecordList.class);
        }
        public String toEscapedJsonString() throws IOException
        {
            return "\""+JsonHelper.mapper.writeValueAsString(this).replace("\"", "\\\"") +"\"";
        }
    }
    public static void main(String args[])
    {
        try {
            deleteTable();
            createTable();
            //Holiday i=new Holiday();
            //i.save();
            
//            Holidays.delete(1);
            for(Holiday j:Holidays.select(""))
                System.out.println(j.getId());
            
            System.out.println(Holidays.count(""));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    public static Holiday getByDateString(String datestring) throws ParseException {
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        for(Holiday h:Holidays.select(" date = \""+date.toString()+"\""))
        {
            return h;
        }
        return null;
    }
    public static LocalDate getFreeDate() {
        LocalDate date;
        String year=Settings.getCurrentYear();
        
        date = LocalDate.parse(Settings.getCurrentYear()+"/01/01", dateFormat);

        while(yearFormat.format(date).contentEquals(year))
        {
            if(getByDate(date)==null)
            {
                return date;
            }
            date=date.plusDays(1);
        }

        return null;
    }        
    public static String getFreeHolidayName() {
        String name="--New Holiday--";
        Integer counter=1;
        while(true)
        {
            if(getByName(name)==null)
            {
                return name;
            }
            counter++;
            name="--New Holiday"+counter.toString()+"--";
        }
    }      
    public static void generate(String yearstring) {
        //Regular holidays
        new Holiday("New Year",Holidays.REGULAR,Date.valueOf(yearstring+"-01-01")).save();
        new Holiday("Araw ng Kagitingan",Holidays.REGULAR,Date.valueOf(yearstring+"-4-9")).save();
        new Holiday("Labor Day",Holidays.REGULAR,Date.valueOf(yearstring+"-5-1")).save();
        new Holiday("Independence Day",Holidays.REGULAR,Date.valueOf(yearstring+"-6-12")).save();
        new Holiday("Bonifacio Day",Holidays.REGULAR,Date.valueOf(yearstring+"-11-30")).save();
        new Holiday("Christmas",Holidays.REGULAR,Date.valueOf(yearstring+"-12-25")).save();
        new Holiday("Rizal Day",Holidays.REGULAR,Date.valueOf(yearstring+"-12-30")).save();

        //movable regular holidays for 2014
        if(yearstring.contentEquals("2014"))
        {
            new Holiday("Maundy Thursday",Holidays.REGULAR,Date.valueOf(yearstring+"-4-17")).save();
            new Holiday("Good Friday",Holidays.REGULAR,Date.valueOf(yearstring+"-4-18")).save();
            new Holiday("National Heroes'' Day",Holidays.REGULAR,Date.valueOf(yearstring+"-8-25")).save();
        }

        //Special non-working holidays
        new Holiday("Ninoy Aquino Day",Holidays.SPECIAL,Date.valueOf(yearstring+"-8-21")).save();
        new Holiday("All Saints'' Day",Holidays.SPECIAL,Date.valueOf(yearstring+"-11-1")).save();
//            new Holiday("All Souls\' Day",Holidays.SPECIAL,Date.valueOf(yearstring+"-11-2")).save();
        new Holiday("Christmas Eve",Holidays.SPECIAL,Date.valueOf(yearstring+"-12-24")).save();
        new Holiday("Day after Christmas",Holidays.SPECIAL,Date.valueOf(yearstring+"-12-26")).save();
        new Holiday("Last Day of the Year",Holidays.SPECIAL,Date.valueOf(yearstring+"-12-31")).save();

        //movable special holidays for 2014
        if(yearstring.contentEquals("2014"))
        {
            new Holiday("Chinese New Year",Holidays.SPECIAL,Date.valueOf(yearstring+"-1-31")).save();
            new Holiday("Black Saturday",Holidays.SPECIAL,Date.valueOf(yearstring+"-4-19")).save();
        }

//!!            Collections.sort(allItems);
//!!            save();
//        load();
    } 
}
