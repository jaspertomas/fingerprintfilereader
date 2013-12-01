/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jaspertomas
 */
public class Dates{

    //---------------SINGLETON-------------------

    static Dates instance;

    public static Dates getInstance() {
        if (instance == null) {
            instance = new Dates();
        }
        return instance;
    }
    //---------------VARIABLES---------------------      
     ArrayList<String> items=new ArrayList<String>();

    public ArrayList<String> getItems() {
        return items;
    }
    
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public void adjust(String startdatestring, String enddatestring)
    {
        try {
            Date startdate=dateFormat.parse(startdatestring);
            Date enddate=dateFormat.parse(enddatestring);
//            Date counter=startdate;
            
            Calendar c = Calendar.getInstance();
            c.setTime(startdate);
//            dt = dateFormat.format(c.getTime());  // dt is now the new date            
            
            items.clear();
            items.add(startdatestring);
            
            while(!dateFormat.format(c.getTime()).contentEquals(enddatestring))
            {
                c.add(Calendar.DATE, 1);  // number of days to add
                items.add(dateFormat.format(c.getTime()));
            }
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
     
}
