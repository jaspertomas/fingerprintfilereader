/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
public class Holidays {

    //---------------SINGLETON-------------------

    static Holidays instance;

    public static Holidays getInstance() {
        if (instance == null) {
            instance = new Holidays();
        }
        return instance;
    }
    //---------------VARIABLES---------------------  
    public static final String REGULAR="R";
    public static final String SPECIAL="S";
    public static final String OTHER="O";

    private static final String OUTPUT_FILE_NAME = "holidays.dat";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    
    ArrayList<Holiday> items=new ArrayList<Holiday>();

    public ArrayList<Holiday> getItems() {
        return items;
    }
//    public void reset() {
//        items.clear();
//    }

    public void save()
    {
        BinaryFileWriter writer = new BinaryFileWriter();
        writer.connectToFile(OUTPUT_FILE_NAME);
        
        for(Holiday item:items)
        {
            writer.writeString(item.getName());
            writer.writeString(item.getType());
            writer.writeString(dateFormat.format(item.getDate()));
        }
        writer.close(); 
    }
    public void load()
    {
        items.clear();
        
        Holiday h;
                
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
            h=new Holiday();
            h.setName(reader.readString());
            if(!reader.notEOF())break;
            h.setType(reader.readString());
            try {
                h.setDate(dateFormat.parse(reader.readString()));
            } catch (ParseException ex) {
                ex.printStackTrace();
//                Logger.getLogger(Holidays.class.getName()).log(Level.SEVERE, null, ex);
            }
            items.add(h);
        }
        Collections.sort(items);
        reader.close();        
    }

    //add items that don't already exist in the items array
//    void generate(ArrayList<Holiday> items) {
////        EmployeeList temp=new EmployeeList();
//        
//        //scan employee list for matching nickname; 
//        //if it doesnt exist, add it
////        for(String name:items)
////        {
////            if(items.getByName(name)==null)
////            items.add(new Holiday(name,"","","",0d,0d,0d));
////        }
////        save();
//    }
    public void generate(String yearstring) {
//        try {
//            //Regular holidays
//            items.add(new Holiday("New Year",Holidays.REGULAR,dateFormat.parse(yearstring+"-01-01")));
//            items.add(new Holiday("Araw ng Kagitingan",Holidays.REGULAR,dateFormat.parse(yearstring+"-4-9")));
//            items.add(new Holiday("Labor Day",Holidays.REGULAR,dateFormat.parse(yearstring+"-5-1")));
//            items.add(new Holiday("Independence Day",Holidays.REGULAR,dateFormat.parse(yearstring+"-6-12")));
//            items.add(new Holiday("Bonifacio Day",Holidays.REGULAR,dateFormat.parse(yearstring+"-11-30")));
//            items.add(new Holiday("Christmas",Holidays.REGULAR,dateFormat.parse(yearstring+"-12-25")));
//            items.add(new Holiday("Rizal Day",Holidays.REGULAR,dateFormat.parse(yearstring+"-12-30")));
//
//            //Special non-working holidays
//            items.add(new Holiday("Ninoy Aquino Day",Holidays.SPECIAL,dateFormat.parse(yearstring+"-8-21")));
//            items.add(new Holiday("All Saints' Day",Holidays.SPECIAL,dateFormat.parse(yearstring+"-11-1")));
//            items.add(new Holiday("All Souls' Day",Holidays.SPECIAL,dateFormat.parse(yearstring+"-11-2")));
//            items.add(new Holiday("Christmas Eve",Holidays.SPECIAL,dateFormat.parse(yearstring+"-12-24")));
//            items.add(new Holiday("Last Day of the Year",Holidays.SPECIAL,dateFormat.parse(yearstring+"-12-31")));
//            
//            save();
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
        load();
    }
}
