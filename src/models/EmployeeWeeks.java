package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqliteDbHelper;
import utils.JsonHelper;

public class EmployeeWeeks {
    //------------FIELDS-----------
    public static final String tablename=EmployeeWeek.tablename;
    public static String[] fields=EmployeeWeek.fields;
    public static String[] fieldtypes=EmployeeWeek.fieldtypes;
    //-----------------------
    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static EmployeeWeeks getByName(String name)
    {
            HashMap<Integer,EmployeeWeeks> map=select(" name = '"+name+"'");
            for(EmployeeWeeks item:map)return item;
            return null;
    }	
    */
    public static EmployeeWeek getById(Integer id) {
            RecordList map=select(" id = '"+id.toString()+"'");
            for(EmployeeWeek item:map)return item;
            return null;
    }
    public static EmployeeWeek getByEmployeeAndWeekIds(Integer employee_id, Integer week_id) {
            RecordList map=select(" employee_id = '"+employee_id.toString()+"' and week_id = '"+week_id.toString()+"'");
            for(EmployeeWeek item:map)return item;
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
            Logger.getLogger(EmployeeWeeks.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(EmployeeWeek item)
    {
        delete(item.getId());
    }
    public static void deleteWhereWeekId(Integer week_id)
    {
        Connection conn=SqliteDbHelper.getInstance().getConnection();            
        Statement st = null;
        try { 
            st = conn.createStatement();
            st.executeUpdate("delete from "+tablename+" where week_id = '"+week_id.toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWeeks.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static Integer insert(EmployeeWeek item)
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
            return st.getGeneratedKeys().getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWeeks.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }
    public static void update(EmployeeWeek item)
    {
        Connection conn=SqliteDbHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId().toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWeeks.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EmployeeWeeks.class.getName()).log(Level.SEVERE, null, ex);
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
                items.add(new EmployeeWeek(rs));
                    //items.put(rs.getInt("id"), new EmployeeWeeks(rs));
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWeeks.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    //-----------database helper functions--------------
    public static String implodeValues(EmployeeWeek item,boolean withId)
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
    public static String implodeFieldsWithValues(EmployeeWeek item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("EmployeeWeeks:implodeFieldsWithValues(): ERROR: values length does not match fields length");
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
            Logger.getLogger(EmployeeWeek.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void deleteTable()
    {
        String query = "DROP TABLE IF EXISTS "+tablename;
        try { 
            SqliteDbHelper.getInstance().getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWeek.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static class RecordList extends ArrayList<EmployeeWeek>{
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
            //EmployeeWeek i=new EmployeeWeek();
            //i.save();
            
//            EmployeeWeeks.delete(1);
            for(EmployeeWeek j:EmployeeWeeks.select(""))
                System.out.println(j.getId());
            
            System.out.println(EmployeeWeeks.count(""));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
