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

public class Holidays {
    //------------FIELDS-----------
    public static final String tablename=Holiday.tablename;
    public static String[] fields=Holiday.fields;
    public static String[] fieldtypes=Holiday.fieldtypes;
    //-----------------------
    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Holidays getByName(String name)
    {
            HashMap<Integer,Holidays> map=select(" name = '"+name+"'");
            for(Holidays item:map)return item;
            return null;
    }	
    */
    public static Holiday getById(Integer id) {
            RecordList map=select(" id = '"+id.toString()+"'");
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
            st.executeUpdate("INSERT INTO "+tablename+" ("+implodeFields(withid)+")VALUES ("+implodeValues(item, withid)+");");
        } catch (SQLException ex) {
            Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
}
