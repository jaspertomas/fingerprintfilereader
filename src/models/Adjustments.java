/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.fileaccess.BinaryFileReader;
import utils.fileaccess.BinaryFileWriter;

/**
 *
 * @author jaspertomas
 */
public class Adjustments {

    //---------------SINGLETON-------------------

    static Adjustments instance;

    public static Adjustments getInstance() {
        if (instance == null) {
            instance = new Adjustments();
        }
        return instance;
    }
    //---------------VARIABLES---------------------  
    private static final String OUTPUT_FILE_NAME = "adjustments.dat";    
    
    public static final SimpleDateFormat prettyTimeFormat = new SimpleDateFormat("hh:mm a");
    public static final SimpleDateFormat prettyDateTimeFormat = new SimpleDateFormat("EE, MMMM dd, yyyy hh:mm a");
    public static final SimpleDateFormat prettyDateFormat = new SimpleDateFormat("EE, MMMM dd, yyyy");

    
    ArrayList<Adjustment> items;

    public ArrayList<Adjustment> getItems() {
        if(items==null)
        {
            items=new ArrayList<Adjustment>();
            load();
        }
        
        return items;
    }
    public void reset() {
        items.clear();
//        dump();
        
    }
    public void dump()
    {
        //initialize if necessary
        getItems();
        
        for(Adjustment a:items)
        {
            System.out.println(a);
        }
    }

    public void add(Adjustment adjustment)
    {
        //initialize if necessary
        getItems();
        
        items.add(adjustment);
        save();
    }
    public void delete(Adjustment adjustment)
    {
        //initialize if necessary
        getItems();
        
        items.remove(adjustment);
        save();
    }

    public void edit(Adjustment a,String name, Integer type, Date date, Time time,Boolean absent)
    {
        if(a==null)
        {
            a=new Adjustment();
        }
        a.setEmployeeNickname(name);
        a.setType(type);
        a.setDate(date);
        a.setTime(time);
        a.setAbsent(absent);
        save();
    }      

    public Adjustment getByNicknameTypeAndDate(String name, Integer type, Date date) {
        //initialize if necessary
        getItems();
        
        for(Adjustment a:items)
        {
            if(
                    a.getEmployeeNickname().contentEquals(name)
                    &&
                    a.getType()==type
                    &&
                    a.getDate().equals(date)
                )
                return a;
        }
        return null;
    }   


    public void save()
    {
        //initialize if necessary
        getItems();
        
        //        Collections.sort(items);
        
        BinaryFileWriter writer = new BinaryFileWriter();
        writer.connectToFile(OUTPUT_FILE_NAME);
        
        for(Adjustment item:items)
        {
            writer.writeString(item.getEmployeeNickname());
            writer.writeInt(item.getType());
            writer.writeDate(item.getDate());
            writer.writeTime(item.getTime());
            writer.writeBoolean(item.getAbsent());
        }
        writer.close(); 
    }
    public void load()
    {
        items.clear();
        
        Adjustment item;
                
        BinaryFileReader reader = new BinaryFileReader();
        boolean result=reader.connectToFile(OUTPUT_FILE_NAME);
        
        if(!result)
        {
            //file not found
            System.out.println("File "+OUTPUT_FILE_NAME+" not found");
            return;
        }
        
        while(reader.notEOF())
        {
            try {
                item=new Adjustment();
                
    //            writer.writeString(item.getEmployeeNickname());
    //            writer.writeInt(item.getType());
    //            writer.writeDate(item.getDate(), prettyDateFormat);
    //            writer.writeDate(item.getTime(), prettyTimeFormat);
    //            writer.writeBoolean(item.getAbsent());
                
                
                item.setEmployeeNickname(reader.readString());
                if(!reader.notEOF())break;
                item.setType(reader.readInt());
                item.setDate(reader.readDate());
                item.setTime(reader.readTime());
                item.setAbsent(reader.readBoolean());
                items.add(item);
            } catch (ParseException ex) {
                Logger.getLogger(Adjustments.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        reader.close();        
    }

    
}
