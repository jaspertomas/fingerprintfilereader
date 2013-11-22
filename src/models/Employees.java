/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jaspertomas
 */
public class Employees {

    //---------------SINGLETON-------------------

    static Employees instance;

    public static Employees getInstance() {
        if (instance == null) {
            instance = new Employees();
        }
        return instance;
    }
    //---------------VARIABLES---------------------  

    ArrayList<Employee> items;

    public ArrayList<Employee> getItems() {
        if(items==null)
        {
            items=new ArrayList<Employee>();
        }
        
        return items;
    }
//    public void reset() {
//        items.clear();
//    }

    public void delete(Employee employee)
    {
        items.remove(employee);
    }
    public void add(Employee employee)
    {
        items.add(employee);
        Collections.sort(items);
    }    
//    public void edit(Employee e,String name, Integer type, Date date)
//    {
//        
//        e.setName(name);
//        e.setType(type);
//        e.setDate(date);
//        
//        Collections.sort(items);
//    }      

    public Employee getByNickname(String name) {
        for(Employee h:items)
        {
            if(h.getNickname().equals(name))
                return h;
        }
        return null;
    }       
}
