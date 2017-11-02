/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jaspertomas
 */
public class Settings {

    //---------------SINGLETON-------------------

    static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
    
    private Settings()
    {
        load();
    }
    //---------------VARIABLES---------------------  

    Integer currentYear=2013;

    public String getCurrentYear() {
        return currentYear.toString();
    }

    public void previousYear() {
        this.currentYear--;
        save();
    }
    public void nextYear() {
        this.currentYear++;
        save();
    }
    
    
    private static final String OUTPUT_FILE_NAME = "settings.dat";

    public void save()
    {
        BinaryFileWriter writer = new BinaryFileWriter();
        writer.connectToFile(OUTPUT_FILE_NAME);
        
        writer.writeInt(currentYear);

        writer.close(); 
    }
    public void load()
    {
        BinaryFileReader reader = new BinaryFileReader();
        boolean result=reader.connectToFile(OUTPUT_FILE_NAME);
        
        if(!result)
        {
            //file not found
//            System.out.println("File "+OUTPUT_FILE_NAME+" not found");
//            return;
            save();
            return;
        }
         
        currentYear=reader.readInt();
         
        reader.close();        
    }
}
