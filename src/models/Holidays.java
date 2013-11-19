/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

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
    ArrayList<Holiday> items=new ArrayList<Holiday>();

    public ArrayList<Holiday> getItems() {
        return items;
    }
    public void reset() {
        items.clear();
    }
    
}
