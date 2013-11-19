/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
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
            writer.writeString(item.getNickname());
            writer.writeString(item.getFname());
            writer.writeString(item.getMname());
            writer.writeString(item.getLname());
            writer.writeDouble(item.getMonthlySalary());
            writer.writeDouble(item.getCola());
            writer.writeDouble(item.getDeduction());
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
            System.out.println("File fingerprint.dat not found");
            return;
        }
        
        while(reader.notEOF())
        {
            h=new Holiday();
            h.setNickname(reader.readString());
            if(!reader.notEOF())break;
            h.setFname(reader.readString());
            h.setMname(reader.readString());
            h.setLname(reader.readString());
            h.setMonthlySalary(reader.readDouble());
            h.setCola(reader.readDouble());
            h.setDeduction(reader.readDouble());
            items.add(h);
        }
        reader.close();        
    }

    //add items that don't already exist in the items array
    void generateFromStringArray(EmployeeNameList employeenamelist) {
//        EmployeeList temp=new EmployeeList();
        
        //scan employee list for matching nickname; 
        //if it doesnt exist, add it
        for(String nickname:employeenamelist)
        {
            if(items.getByNickname(nickname)==null)
            items.add(new Holiday(nickname,"","","",0d,0d,0d));
        }
        save();
    }
            
}
