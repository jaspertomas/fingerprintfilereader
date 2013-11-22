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
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
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

    public void edit(Adjustment a,String name, Integer type, Date date, String time)
    {
        if(a==null)
        {
            a=new Adjustment();
        }
        a.setEmployeeNickname(name);
        a.setType(type);
        a.setDate(date);
        a.setTime(time);
        
    }      

    public Adjustment getByNicknameTypeAndDate(String name, Integer type, Date date) {
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

}
