/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private static final String OUTPUT_FILE_NAME = "holidays.dat";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
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
            writer.writeDate(item.getDate());
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
        reader.close();        
    }

    //add items that don't already exist in the items array
    void generate(ArrayList<Holiday> items) {
//        EmployeeList temp=new EmployeeList();
        
        //scan employee list for matching nickname; 
        //if it doesnt exist, add it
//        for(String name:items)
//        {
//            if(items.getByName(name)==null)
//            items.add(new Holiday(name,"","","",0d,0d,0d));
//        }
//        save();
    }
            
}
