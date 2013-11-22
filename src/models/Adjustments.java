/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    
    ArrayList<Adjustment> items;//,allItems=new ArrayList<Adjustment>();

    public ArrayList<Adjustment> getItems() {
        if(items==null)
            items=new ArrayList<Adjustment>();
        
        return items;
    }
    public void reset() {
        items.clear();
    }

    public void delete(Adjustment holiday)
    {
        items.remove(holiday);
    }
    public void add(Adjustment holiday)
    {
        items.add(holiday);
    }    
    public void edit(Adjustment h,String name, Integer type, Date date)
    {
        
        h.setName(name);
        h.setType(type);
        h.setDate(date);
        
    }      

    public Adjustment getByDate(Date date) {
        for(Adjustment h:items)
        {
            if(h.getDate().equals(date))
                return h;
        }
        return null;
    }

    public Adjustment getByName(String name) {
        for(Adjustment h:items)
        {
            if(h.getName().equals(name))
                return h;
        }
        return null;
    }    

}
